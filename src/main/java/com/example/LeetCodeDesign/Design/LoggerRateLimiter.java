package com.example.LeetCodeDesign.Design;

/*
Design a logger system that receives a stream of messages along with their timestamps.
Each unique message should only be printed at most every 10 seconds (i.e. a message
printed at timestamp t will prevent other identical messages from being printed until
timestamp t + 10).

All messages will come in chronological order. Several messages may arrive at the same timestamp.

Implement the Logger class:

Logger() Initializes the logger object.
bool shouldPrintMessage(int timestamp, string message) Returns true if the message should be
printed in the given timestamp, otherwise returns false.

Logger logger = new Logger();

// logging string "foo" at timestamp 1
logger.shouldPrintMessage(1, "foo"); returns true;

// logging string "bar" at timestamp 2
logger.shouldPrintMessage(2,"bar"); returns true;

// logging string "foo" at timestamp 3
logger.shouldPrintMessage(3,"foo"); returns false;

// logging string "bar" at timestamp 8
logger.shouldPrintMessage(8,"bar"); returns false;

// logging string "foo" at timestamp 10
logger.shouldPrintMessage(10,"foo"); returns false;

// logging string "foo" at timestamp 11
logger.shouldPrintMessage(11,"foo"); returns true;

 */


import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

/*
Hashmap => <message, timestamp>
avoid duplication, records timestamp for validation
time complexity -> O(1)
space complexity -> O(n)

Queue+Set
 */
public class LoggerRateLimiter {

    //a queue to track message and their timestamp
    Queue<Message> queue;
    //a set to keep track of messages that are currently within the given timestamp say 10 sec window
    Set<String> messageSet;

    LoggerRateLimiter() {
        queue = new LinkedList<>();
        messageSet = new HashSet<>();
    }

    class Message {
        String message;
        int timestamp;

        Message(int timestamp, String message) {
            this.message = message;
            this.timestamp = timestamp;
        }
    }

    public boolean shouldPrintMessage(int timestamp, String message) {
        //remove message from queue and set that are older than 10 sec
        while (!queue.isEmpty() && queue.peek().timestamp <= timestamp-10) {
            Message oldMessage = queue.poll();
            messageSet.remove(oldMessage.message);
        }

        //if message is not in the set then it means it is not printed in the last 10 secs
        if(!messageSet.contains(message)) {
            //add the message to the queue and set
            queue.add(new Message(timestamp, message));
            messageSet.add(message);
            return true;
        } else {
            return false;
        }
    }

    public static void main(String[] args) {
        LoggerRateLimiter logger = new LoggerRateLimiter();
        System.out.println(logger.shouldPrintMessage(1, "foo"));  // returns true
        System.out.println(logger.shouldPrintMessage(2, "bar"));  // returns true
        System.out.println(logger.shouldPrintMessage(3, "foo"));  // returns false
        System.out.println(logger.shouldPrintMessage(8, "bar"));  // returns false
        System.out.println(logger.shouldPrintMessage(10, "foo")); // returns false
        System.out.println(logger.shouldPrintMessage(11, "foo")); // returns true
    }
}
