package com.example.LeetCodeDesign.Design;

/*
Design logger system which would recievestream of messages along with its timestamp,
each message should be printed if and only if it is not printed in the last 10 secs.

Given a message and timestamp, return true if the message should be printed in the
given timestamp, otherwise return false

It is possible that several message arrive roughly at the same time.

 */
public class DesignLoggerRateLimiter {

    /*
     * @param timestamp: the current timestamp
     * @param event: the string to distinct different event
     * @param rate: the format is [integer]/[s/m/h/d]
     * @param increment: whether we should increase the counter
     * @return: true or false to indicate the event is limited or not
     */
    public boolean isRatelimited(int timestamp, String event, String rate, boolean increment) {
        return false;
    }
}
