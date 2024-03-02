package com.example.LeetCodeDesign.Design;

import java.util.LinkedList;
import java.util.Queue;

/*
In this question, you need to design a HitCounter class.

In this class, there are the following functions:

HitCounter(): No-argument constructor
void hit(int timestamp): Indicates that a tap occurs at the specified time
int getHits(int timestamp): Returns the total number of hits within 300 seconds before the specified time
Where timestamp is in seconds.
 */
public class DesignHitCounter {

    Queue<Integer> counter;
    int FIVE_MIN = 300;


    DesignHitCounter() {
        counter = new LinkedList<>();
    }

    public void hit(int timestamp) {
        counter.offer(timestamp);
    }

    public int getHits(int timestamp) {
        while(!counter.isEmpty() && (timestamp - counter.peek() >= FIVE_MIN)) {
            counter.poll();
        }
        return counter.size();
    }

    public static void main(String[] args) {
        DesignHitCounter d = new DesignHitCounter();
        d.hit(1);
        d.hit(2);
        d.hit(3);
        System.out.println(d.getHits(4));
        d.hit(300);
        System.out.println(d.getHits(300));
        System.out.println(d.getHits(301));
    }
}
