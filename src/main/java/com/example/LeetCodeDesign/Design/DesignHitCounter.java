package com.example.LeetCodeDesign.Design;

import java.util.LinkedList;
import java.util.Queue;

/*

Design a hit counter which counts the number of hits received in the past 5 minutes.
Each function accepts a timestamp parameter (in seconds granularity) and you may assume that calls are being made
to the system in chronological order (ie, the timestamp is monotonically increasing). You may assume that the earliest
timestamp starts at 1.

It is possible that several hits arrive roughly at the same time.

“Design hit counter” problem has recently been asked by many companies including Dropbox and the question is harder
than it seems to be. This week, we’ll uncover all the mysteries of the problem. A couple of topics are discussed
including basic data structures design, various optimization, concurrency and distributed counter.

Each function accepts a timestamp parameter (in seconds granularity) and you may assume that calls are being made to the system in chronological order (i.e. the timestamp is monotonically increasing). You may assume that the earliest timestamp starts at 1.

Example:
HitCounter counter = new HitCounter();

// hit at timestamp 1.
counter.hit(1);

// hit at timestamp 2.
counter.hit(2);

// hit at timestamp 3.
counter.hit(3);

// get hits at timestamp 4, should return 3.
counter.getHits(4);

// hit at timestamp 300.
counter.hit(300);

// get hits at timestamp 300, should return 4.
counter.getHits(300);

// get hits at timestamp 301, should return 3.
counter.getHits(301);


In this question, you need to design a HitCounter class.

In this class, there are the following functions:

HitCounter(): No-argument constructor
void hit(int timestamp): Indicates that a tap occurs at the specified time
int getHits(int timestamp): Returns the total number of hits within 300 seconds before the specified time
Where timestamp is in seconds.

 */
public class DesignHitCounter {

    //We can use a queue to store the hits and delete the entries in queue which are of no use. It will save our space.
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

//---------------------------------------------------------------------------------------------------------
//  What if the data come in unordered way and several hits carry same timestamp?
//---------------------------------------------------------------------------------------------------------
        DesignHitCounter d1 = new DesignHitCounter();
//        d1.hit();



    }
}
