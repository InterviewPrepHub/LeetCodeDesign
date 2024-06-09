package com.example.LeetCodeDesign.Design;

import java.util.HashMap;
import java.util.Map;

/*
primary goal of LFUCache is to evict the least frequently used items first
 */
public class LFUCacheI<K,V> {

    private final int capacity;
    private int minFreq;
    private final DoublyLinkedList dll;
    private final Map<K, Node> cache;
    private final Map<Integer, DoublyLinkedList> freqMap;

    public LFUCacheI(int capacity) {
        this.capacity = capacity;
        this.cache = new HashMap<>();
        this.freqMap = new HashMap<>();
        this.minFreq = 1;
        this.dll = new DoublyLinkedList();
    }

    public V get(K key) {
        if(!cache.containsKey(key)) {
            return null;
        }
        //get node from the key
        Node node = cache.get(key);
        //update freq
        updateFrequency(node);
        return (V) node.val;
    }

    public void put(K key, V val) {
        //if key already exists, update the value and increase the frequency of the corresponding node.
        if(cache.containsKey(key)) {
            Node node = cache.get(key);
            node.val = val;
            updateFrequency(node);
        } else {
            if(cache.size() >= capacity) {
                removeLeastFrequentlyUsed();
            }
            Node newNode = new Node(key, val);
            cache.put(key, newNode);
            addNode(newNode);
        }
    }

    /*
    When a node's frequency is increased:
        - Remove the node from its current frequency list in the frequencyMap.
        - Increase the node's frequency.
        - Add the node to the new frequency list in the frequencyMap.

     */
    private void updateFrequency(Node node) {
        int currFreq = node.freq;
        DoublyLinkedList ddl = freqMap.get(currFreq);
        ddl.removeNode(node);

        node.freq++;
        addNode(node);
    }

    private void addNode(Node node) {
        int currFreq = node.freq;
        freqMap.put(currFreq, new DoublyLinkedList());
        freqMap.get(currFreq).addNodeToFront(node);
        minFreq = Math.min(minFreq, currFreq);
    }

    private void removeLeastFrequentlyUsed() {
        DoublyLinkedList dll1 = freqMap.get(minFreq);
        Node nodeToRemove = dll1.removeNodeFromEnd();

        if(dll1.size == 0) {
            freqMap.remove(minFreq);
        }

        cache.remove(nodeToRemove.key);
    }

    class DoublyLinkedList {
        Node head;
        Node tail;
        int size;

        public DoublyLinkedList() {
            this.head = new Node(null, null);
            this.tail = new Node(null, null);
            head.next = tail;
            tail.next = head;
            this.size = 0;
        }

        public void addNodeToFront(Node node) {
            Node next = head.next;  // tail or any other node
            node.next = next;       // node -> tail or any other node
            node.prev = head;       // head <- node
            head.next = node;       // head -> node
            next.prev = node;       // node <- tail or any other node
        }

        void removeNode(Node node) {
            Node prevNode = node.prev;
            Node nextNode = node.next;

            prevNode.next = nextNode;
            nextNode.prev = prevNode;
            size--;
        }

        Node removeNodeFromEnd() {
            if(size == 0) {
                return null;
            }
            Node node = tail.prev;
            removeNode(node);
            return node;
        }


    }

    class Node<K,V> {
        K key;
        V val;
        int freq;
        Node prev;
        Node next;

        public Node(K key, V val) {
            this.key = key;
            this.val = val;
            this.freq = 1; //keeps track of how many times each node has been accessed
        }
    }
}
