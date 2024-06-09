package com.example.LeetCodeDesign.Design;

import java.util.HashMap;

/*
Design and implement a data structure for a Least Frequently Used (LFU) cache.

Implement the LFUCache class:

LFUCache(int capacity) Initializes the object with the capacity of the data structure.
int get(int key) Gets the value of the key if the key exists in the cache. Otherwise, returns -1.
void put(int key, int value) Update the value of the key if present, or inserts the key if not already present.

When the cache reaches its capacity, it should invalidate and remove the least frequently used key before inserting
a new item. For this problem, when there is a tie (i.e., two or more keys with the same frequency), the least
recently used key would be invalidated.

To determine the least frequently used key, a use counter is maintained for each key in the cache. The key with
the smallest use counter is the least frequently used key.

When a key is first inserted into the cache, its use counter is set to 1 (due to the put operation). The use counter
for a key in the cache is incremented either a get or put operation is called on it.

The functions get and put must each run in O(1) average time complexity.


 */
public class LFUCache {

    private int capacity;
    private int size;
    private int minFreq;
    private HashMap<Integer, Node> nodeMap;
    private HashMap<Integer, DoublyLinkedList> freqMap; //map fo list of nodes with same freq


    public LFUCache(int capacity) {

        this.capacity = capacity;
        this.size = 0;
        this.minFreq = 0;
        this.nodeMap = new HashMap<>();
        this.freqMap = new HashMap<>();
    }

    //fetch value from key and adjust the position of node in freq map based on freq val
    public int get(int key) {
        if(!nodeMap.containsKey(key)) return -1;

        Node node = nodeMap.get(key);
        updateFrequency(node);

        return node.val;
    }

    public void put(int key, int value) {
        if(capacity == 0) return;

        if(nodeMap.containsKey(key)) {
            Node node = nodeMap.get(key);
            node.val = value;
            updateFrequency(node);
        } else {
            if(nodeMap.size() == capacity) {
                DoublyLinkedList minFreqList = freqMap.get(minFreq);
                nodeMap.remove(minFreqList.tail.prev.key);
                minFreqList.removeNode(minFreqList.tail.prev);
            }

            Node newNode = new Node(key, value);
            nodeMap.put(key, newNode);
            minFreq = 1;
            DoublyLinkedList dll = new DoublyLinkedList();
            dll.addNode(newNode);
            freqMap.put(1, dll);
        }

    }

    //update the freq if te item(key) is accessed
    private void updateFrequency(Node node) {

        int oldFreq = node.freq;
        DoublyLinkedList oldFreqList = freqMap.get(oldFreq);
        oldFreqList.removeNode(node);

        if (oldFreqList.isEmpty() && oldFreq == minFreq) {
            minFreq++;
        }

        node.freq++;

        DoublyLinkedList doublyLinkedList = new DoublyLinkedList();
        doublyLinkedList.addNode(node);
        freqMap.put(node.freq, doublyLinkedList);

    }

}

//Node represents each entry in cache
class Node {
    int key;
    int val;
    int freq;
    Node prev, next;

    Node(int key, int val) {
        this.key = key;
        this.val = val;
        this.freq = 1;
    }
}

// maintains order of nodes with same frequency.
// It has head and tail pointers for easy addition and removal
class DoublyLinkedList {

    Node head, tail;

    DoublyLinkedList() {
        head = new Node(0,0);
        tail = new Node(0,0);
        head.next = tail;
        tail.prev = head;
    }

    public void addNode(Node node) {
        node.next = head.next;  //head <-> node <-> tail for the first time, currently its head <-> tail
        node.prev = head;
        head.next.prev = node;
        head.next = node;
    }

    public void removeNode(Node node) {
        node.prev.next = node.next;
        node.next.prev = node.prev;
    }

    public boolean isEmpty() {
        return head.next == tail;
    }

}
