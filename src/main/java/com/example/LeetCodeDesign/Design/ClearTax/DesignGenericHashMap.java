package com.example.LeetCodeDesign.Design.ClearTax;

public class DesignGenericHashMap<K,V> {

    Node<K,V>[] buckets;
    int capacity;
    int size;

    DesignGenericHashMap(int capacity) {
        this.capacity = capacity;
        this.buckets = new Node[capacity];
        this.size = 0;
    }

    public V get(K key) {
        int index = hash(key);
        Node<K,V> prev = find(key, index);

        if(prev.next !=null) {
            return prev.next.val;
        } else {
            return null;
        }
    }

    public void put(K key, V val) {
        int index = hash(key);
        Node<K,V> prev = find(key, index);

        if (prev.next == null) {
            prev.next = new Node<>(key, val);
        } else {
            prev.next.val = val;
        }

    }

    private Node<K,V> find(K key, int index) {
        //check if the bucket at any index is empty
        if(buckets[index] == null) {
            //create new Node
            buckets[index] = new Node<>(null, null);
            return buckets[index];
        }

        //traverse through linked list at given index
        Node<K,V> curr = buckets[index];
        while (curr.next!=null || curr.next.key.equals(key)) {
            curr = curr.next;
        }
        return curr;
    }

    private int hash(K key) {
        return key.hashCode() % capacity;
    }
}

//Create a generic Node

class Node<K,V> {
    K key;
    V val;
    Node<K,V> next;

    Node(K key, V val) {
        this.key = key;
        this.val = val;
        next = null;
    }
}
