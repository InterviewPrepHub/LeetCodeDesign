package com.example.LeetCodeDesign.Design;

import java.util.HashMap;
import java.util.TreeMap;

/*
Design a time-based key-value data structure that can store multiple values for the same key at different time stamps
and retrieve the key's value at a certain timestamp.

Implement the TimeMap class:

TimeMap() Initializes the object of the data structure.
void set(String key, String value, int timestamp) Stores the key key with the value value at the given time timestamp.
String get(String key, int timestamp) Returns a value such that set was called previously, with timestamp_prev <= timestamp.
If there are multiple such values, it returns the value associated with the largest timestamp_prev. If there are no values, it returns "".

 */
public class TimeBasedKeyValueStore {

    HashMap<String, TreeMap<Integer, String>> map;

    public TimeBasedKeyValueStore() {
        map = new HashMap<>();
    }

    public void set(String key, String value, int timestamp) {
        if(!map.containsKey(key)) {
            map.put(key, new TreeMap<>());
        }
        map.get(key).put(timestamp, value);
    }

    public String get(String key, int timestamp) {

        if (!map.containsKey(key)) {
            return "";
        }

        TreeMap<Integer, String> timeMap = map.get(key);
        //the floorKey function in treemap
        //if no such key exists, returns for the greatest key less than the specified key; if no such entry exists, returns null.
        Integer floorValue = timeMap.floorKey(timestamp);

        if (floorValue == null) {
            return "";
        }
        return timeMap.get(floorValue);
    }

    public static void main(String[] args) {
        TimeBasedKeyValueStore kv = new TimeBasedKeyValueStore();
        kv.set("foo", "bar", 1); // store the key "foo" and value "bar" along with timestamp = 1
        System.out.println(kv.get("foo", 1));  // output "bar"
        System.out.println(kv.get("foo", 3)); // output "bar" since there is no value corresponding to foo at timestamp 3 and timestamp 2, then the only value is at timestamp 1 ie "bar"
        kv.set("foo", "bar2", 4);
        System.out.println(kv.get("foo", 4)); // output "bar2"
        System.out.println(kv.get("foo", 5)); //output "bar2"
    }
}
