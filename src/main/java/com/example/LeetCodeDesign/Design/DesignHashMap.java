package com.example.LeetCodeDesign.Design;

/*
Design a HashMap without using any built-in hash table libraries.

Implement the MyHashMap class:

MyHashMap() initializes the object with an empty map.
void put(int key, int value) inserts a (key, value) pair into the HashMap. If the key already exists in the map, update the corresponding value.
int get(int key) returns the value to which the specified key is mapped, or -1 if this map contains no mapping for the key.
void remove(key) removes the key and its corresponding value if the map contains the mapping for the key.


Example 1:

Input
["MyHashMap", "put", "put", "get", "get", "put", "get", "remove", "get"]
[[], [1, 1], [2, 2], [1], [3], [2, 1], [2], [2], [2]]
Output
[null, null, null, 1, -1, null, 1, null, -1]

Explanation
MyHashMap myHashMap = new MyHashMap();
myHashMap.put(1, 1); // The map is now [[1,1]]
myHashMap.put(2, 2); // The map is now [[1,1], [2,2]]
myHashMap.get(1);    // return 1, The map is now [[1,1], [2,2]]
myHashMap.get(3);    // return -1 (i.e., not found), The map is now [[1,1], [2,2]]
myHashMap.put(2, 1); // The map is now [[1,1], [2,1]] (i.e., update the existing value)
myHashMap.get(2);    // return 1, The map is now [[1,1], [2,1]]
myHashMap.remove(2); // remove the mapping for 2, The map is now [[1,1]]
myHashMap.get(2);    // return -1 (i.e., not found), The map is now [[1,1]]
 */
public class DesignHashMap {

    private int MAX_SIZE = 100; //0-99
    private Node[] buckets;

    DesignHashMap() {
        buckets = new Node[MAX_SIZE];
    }


    /*
    Hashmap uses a hash function (some steps of operations), which generates an index value.
    The Hash function should be designed in such a way that it should always generate an index
    that exists in the range of [0, CAPACITY).
     */
    private int findIndex(int key) {
        return key % MAX_SIZE;
    }

    public void put(int key, int value) {

        int index = findIndex(key); //we need to get bucket index where i need to add the key-value entry
        Node prev = find(key, index);

        if (prev.next == null) {
            prev.next = new Node(key,value);
        } else {
            prev.next.val = value;
        }

    }

    //this method if for traversing the linked list at a given bucket index to
    // locate the node with the given or its the last node in the list
    private Node find(int key, int index) {
        //check if linked list/array is empty at given index
        if (buckets[index] == null) {
            buckets[index] = new Node(-1,-1);   //create dummy node
            return buckets[index];
        }

        //traverses the list until it finds the target key or reaches the end of the list
        Node curr = buckets[index];
        while (curr.next!=null && curr.next.key == key) {
            curr = curr.next;
        }
        return curr;
    }

    public int get(int key) {
        int index = findIndex(key);
        Node prev = find(key, index);

        if (prev.next != null) {
            return prev.next.val;
        } else {
            return -1;
        }
    }

    public void remove(int key) {

        int index = findIndex(key);
        Node prev = find(index, key);

        if (prev.next != null) {
            prev.next = prev.next.next;
        }

    }

    class Node {
        int key;
        int val;
        Node next;

        public Node(int key, int val) {
            this.key = key;
            this.val = val;
            next = null;
        }
    }
}


