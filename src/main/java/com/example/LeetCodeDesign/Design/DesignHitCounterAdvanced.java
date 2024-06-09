package com.example.LeetCodeDesign.Design;

import java.util.Map;
import java.util.TreeMap;

/*
In previous question:
Design a hit counter which counts the number of hits received in the past 5 minutes.

Each function accepts a timestamp parameter (in seconds granularity) and you may assume that calls are being made
to the system in chronological order (ie, the timestamp is monotonically increasing). You may assume that the earliest
timestamp starts at 1.

It is possible that several hits arrive roughly at the same time.

If the data can come in unordered and multiple hits can have the same timestamp, you'll need to ensure that your
hit counter can handle both these situations.
so for that we can use a TreeMap where the key [timestamp] is ordered and the values in the count of hits

 */
public class DesignHitCounterAdvanced {

    private final int FIVE_MIN = 300;
    TreeMap<Integer, Integer> map = new TreeMap<>();

    public void hit(int timestamp) {
        map.put(timestamp, map.getOrDefault(timestamp, 0)+1);
    }

    public int getHits(int timestamp) {
        int total_hits = 0;
        int threshold = timestamp - FIVE_MIN + 1;
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            if(entry.getKey() >= threshold) {
                total_hits+=entry.getValue();
            }
        }
        return total_hits;
    }

    public static void main(String[] args) {
    //-----------------------------------------------------------------------------------
    //  What if the data come in unordered way and several hits carry same timestamp?
    //-----------------------------------------------------------------------------------
        DesignHitCounterAdvanced d = new DesignHitCounterAdvanced();
        d.hit(1);
        d.hit(2);
        d.hit(2);
        d.hit(3);
        System.out.println(d.getHits(4)); // expect 4 -> {1,2,2,3}
        d.hit(300);
        System.out.println(d.getHits(300)); // expect 5 -> {1,2,2,3,300}
        System.out.println(d.getHits(301));
        d.hit(303);
        System.out.println(d.getHits(303));
    }
}

/*
https://leetcode.com/discuss/interview-question/178662/Design-a-Hit-Counter/
How to handle concurrent requests?
Distribute the counter
https://aonecode.com/getArticle/211


 */