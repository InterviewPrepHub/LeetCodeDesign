package com.example.LeetCodeDesign.Design;

import java.util.ArrayList;
import java.util.List;

public class ImplementHashMap {

    int MAX_LEN = 1000;
    private List<MyPair<Integer, Integer>>[] map;

    ImplementHashMap() {
        map = new ArrayList[MAX_LEN];
    }

    /*

    What does it do?
    This function calculates the hash index for a given key in a HashMap.

    Why is it needed?
    In a HashMap, keys are used to determine where to store and retrieve values.
    The getIndex function helps decide which "bucket" in the HashMap array should be
    used to store the value associated with a particular key.

    How does it work?
    It uses the modulo operation (%) to find the remainder when dividing the key by MAX_LEN.
    The result is the hash index.

    Why use modulo operation?
    The modulo operation ensures that the result stays within the range [0, MAX_LEN-1],
    which is the valid index range of the array representing the HashMap. This helps
    distribute keys across the array, preventing the index from going beyond the array size.

    Example:

    Suppose key = 17 and MAX_LEN = 10. The calculation would be 17 % 10, which equals 7.
    Therefore, the key 17 would be placed in the bucket at index 7 in the HashMap array.

     */
    public int getIndex(int key) {
        return key % MAX_LEN;
    }

    /*
    This function searches for a specific key within a particular "bucket" in the HashMap array.

    In a HashMap, keys are hashed to a specific index in the array.The getPos function is used to
    check if a key exists within the list of key-value pairs stored in that particular bucket.

    Suppose index = 5, and the list at that index contains key-value pairs [(12, 45), (7, 32), (22, 18)].
    If we call getPos(7, 5), it will iterate through the list, find the key 7 at index 1, and return 1.
    In summary, the getPos function checks if a given key exists within a specific bucket in the HashMap array.
    If found, it returns the position (index) of the key in the list; otherwise, it returns -1.
     */

    public int getPos(int key, int index) {
        List<MyPair<Integer, Integer>> temp = map[index];
        if (temp == null) {
            return -1;
        }
        for (int i = 0; i < temp.size(); i++) {
            if (temp.get(i).getKey() == key) {
                return i;
            }
        }
        return -1;
    }

    /*
    Here's how the collision is handled in the put method:
    When adding a new key-value pair ((key, value)) to the HashMap, the getPos function is used to check if the key
    already exists in the bucket. If the key is not found (pos < 0), a new pair is added to the list in that bucket.
    If the key already exists, the value is updated in the existing pair.

    In the case of a collision, multiple key-value pairs can coexist in the same bucket as elements of the list.

    For example, after executing hashMap.put(2, 1); when the bucket already contains [(2, 2), (2, 1)],
    the getPos function will find that the key 2 exists at positions 0 and 1 in the list, and the put method will
    update the value of the pair at index 1 to (2, 1).

    This way, the implementation effectively manages collisions by allowing multiple key-value pairs with the same
    hash index to be stored in the same bucket using a list data structure.
     */
    public void put(int key, int value) {

        //get bucket index
        int index = getIndex(key);

        //search for key of a specific index
        int pos = getPos(key, index);

        if (pos < 0 ) {
            //add new key
            if (map[index] == null) {
                map[index] = new ArrayList<MyPair<Integer, Integer>>();
            }
            map[index].add(new MyPair<>(key, value));
        }
        else {
            //update value if the key
            map[index].get(pos).setValue(value);
        }
    }

    public int get(int key) {
        int index = getIndex(key);  //getting the bucket index
        int pos = getPos(key, index); //position of key in the list of bucket index
        if(pos < 0) {
            return -1;
        } else {
            return map[index].get(pos).getValue();
        }
    }

    public void remove(int key) {
        int index = getIndex(key);
        int pos = getPos(key, index);

        if(pos >= 0) {
            map[index].remove(pos);
        }
    }

    public static void main(String[] args) {

        ImplementHashMap hashMap = new ImplementHashMap();
        hashMap.put(1, 1);
        hashMap.put(2, 2);
        System.out.println(hashMap.get(1)); // should print 1
        System.out.println(hashMap.get(3)); // should print -1
        hashMap.put(2, 1);
        System.out.println(hashMap.get(2)); // should print 1
        hashMap.remove(2);
        System.out.println(hashMap.get(2)); // should print -1

    }
}

class MyPair<K,V> {
    K key;
    V value;

    public MyPair(K key, V value) {
        this.key = key;
        this.value = value;
    }

    public K getKey() {
        return key;
    }

    public V getValue() {
        return value;
    }

    public void setKey(K key) {
        this.key = key;
    }

    public void setValue(V value) {
        this.value = value;
    }
}
