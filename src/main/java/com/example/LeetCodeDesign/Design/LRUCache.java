package com.example.LeetCodeDesign.Design;

import java.util.HashMap;
import java.util.Map;

public class LRUCache {

    Node head;
    Node tail;
    Map<Integer, Node> map;
    int capacity;

    LRUCache() {
        head = new Node(0, 0);
        tail = new Node(0, 0);
        head.next = tail;
        tail.prev = head;
        head.prev = null;
        tail.next = null;
        map = new HashMap<>();
        capacity = 3;
    }

    private void addToHead(Node newNode) {
        newNode.next = head.next;
        newNode.next.prev = newNode;
        newNode.prev = head;
        head.next = newNode;

    }

    private void removeNode(Node newNode) {
        newNode.prev.next = newNode.next;
        newNode.next.prev = newNode.prev;
    }

    public int get(int key) {
        int res = map.get(key).value;
        if (map.containsKey(key)) {
            Node node = new Node(key, res);
            removeNode(node);
            addToHead(node);
            return res;
        }
        return -1;
    }

    public void put(int key, int value) {

        if (map.containsKey(key)) {
            Node existingNode = new Node(key, value);
            removeNode(existingNode);
            addToHead(existingNode);
        } else {
            Node newNode = new Node(key, value);
            if(map.size() < capacity) {
                map.put(key, newNode);
                addToHead(newNode);
            } else {
                map.remove(tail.prev.key);
                removeNode(tail.prev);
                addToHead(newNode);
            }
        }

    }


    public static void main(String[] args) {
        LRUCache lru = new LRUCache();
        lru.put(1,1);
        lru.put(2,2);
        lru.put(3,3);
        lru.get(2);
        lru.put(4,4);
    }
}


class Node {

    int key;
    int value;
    Node next;
    Node prev;

    Node(int key, int value) {
        this.key = key;
        this.value = value;
        next = null;
        prev = null;
    }
}