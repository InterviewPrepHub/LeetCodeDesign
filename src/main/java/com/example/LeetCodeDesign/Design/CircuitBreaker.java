package com.example.LeetCodeDesign.Design;

import java.util.concurrent.Callable;

/*
The Circuit Breaker starts in the CLOSED state.
After three consecutive failures, it transitions to the OPEN state.
After a timeout, it transitions to the HALF_OPEN state and requires two consecutive successful calls to move back to the CLOSED state.
Subsequent successful calls keep it in the CLOSED state.

                                            CLOSED
                                          /        \
                                        /            \
                  n failures calls    /                \   m successful calls
                                    /                    \ no more failures
                                  /                        \
                                /     timeout/delay         \
                              OPEN -----------------------> HALF-OPEN
                                   <-----------------------
                                         failure


 */
public class CircuitBreaker {

    public enum State {
        OPEN,
        CLOSED,
        HALF_OPEN
    }

    private State state;    //current state of circuit breaker
    private int failureCount; //no of consecutive failures
    private final int failureThreshold; //no of failure to open circuit
    private final int successThreshold; //no of successful calls to close the circuit
    private final long timeout; // Time duration in ms for which circuit remains open
    private long lastFailureTime; //time when the last failure occurred

    public CircuitBreaker(int failureThreshold, int successThreshold, long timeout) {
        this.successThreshold = successThreshold;
        this.failureThreshold = failureThreshold;
        this.timeout = timeout;
        this.failureCount = 0;
        this.lastFailureTime = 0;
        this.state = State.CLOSED;
    }

    public boolean execute(Callable<Boolean> callable) {
        if (state == State.OPEN) {
            if (System.currentTimeMillis() - lastFailureTime > timeout) {
                state = State.HALF_OPEN; //Move it to half open
            } else {
                return false; //short circuit the call
            }
        }

        try {

            boolean result = callable.call();
            if (result) {
                onSuccess();
            } else {
                onFailure();
            }
            return result;
        } catch (Exception ex) {
            onFailure();
            return false;
        }
    }

    //handle successful call
    private void onSuccess() {
        if(state == State.HALF_OPEN) {
            failureCount++; // here failureCount is no of consecutive successful calls
            if(failureCount >= successThreshold) { //here the threshold of no of consecutive successful calls is greater than successThreshold
                state = State.CLOSED;   //
                failureCount = 0;
            }
        } else {
            failureCount = 0;
        }
    }

    private void onFailure() {
        if(state == State.HALF_OPEN || state == State.CLOSED) {
            failureCount++; //here failureCount tracks no of consecutive failed attempts
            if (failureCount >= failureThreshold) {
                state = State.OPEN;
                lastFailureTime = System.currentTimeMillis();
            }
        }
    }

    public static void main(String[] args) {

        CircuitBreaker circuitBreaker = new CircuitBreaker(3, 2, 5000);

        boolean[] callResults = {false, false, false, false, false, true, true, true, true, true};

        for (int i = 0; i < 10; i++) {
            boolean callResult = callResults[i];
            boolean result = circuitBreaker.execute(() -> callResult); //here () -> callResult means that it would take no arguments and returns callResult


            System.out.println("Call result : "+ result + ", Circuit breaker state : "+ circuitBreaker.state);

            /*
            After the Circuit Breaker transitions to the OPEN state, a Thread.sleep(5000) is used to simulate the passage of time (5 seconds).
            This ensures that the Circuit Breaker has enough time to transition from OPEN to HALF_OPEN for subsequent calls.
             */
            if (circuitBreaker.state == State.OPEN) {
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException ex) {
                    Thread.currentThread().interrupt();
                }
            }
        }

    }
}

/*
This output shows:

Call result: false, Circuit Breaker State: CLOSED
Call result: false, Circuit Breaker State: CLOSED
Call result: false, Circuit Breaker State: CLOSED
Call result: false, Circuit Breaker State: OPEN
Call result: false, Circuit Breaker State: OPEN
Call result: true, Circuit Breaker State: OPEN
Call result: true, Circuit Breaker State: HALF_OPEN
Call result: true, Circuit Breaker State: HALF_OPEN
Call result: true, Circuit Breaker State: CLOSED
Call result: true, Circuit Breaker State: CLOSED

 */

