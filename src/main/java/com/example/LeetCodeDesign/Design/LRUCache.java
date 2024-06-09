package com.example.LeetCodeDesign.Design;

import java.util.HashMap;
import java.util.Map;

/*
Adding a node: Always add new nodes to the front (head) of the doubly linked list.
Removing a node: Remove nodes from the back (tail) of the doubly linked list when the cache exceeds its capacity.
Updating a node: When a node is accessed or updated, move it to the front (head) of the doubly linked list to mark it
as the most recently used.

By maintaining the doubly linked list and the HashMap, you ensure that both the access (get) and insertion (put)
operations are efficient, with an average time complexity of O(1).
 */

public class LRUCache<K,V> {

    int capacity;
    Map<K,Node> cache;
    DoublyLinkedList<K,V> dll;

    LRUCache(int capacity) {
        this.capacity = capacity;
        cache = new HashMap<>();
        dll = new DoublyLinkedList<>();
    }

    public static void main(String[] args) {
        LRUCache<Integer, Integer> lru = new LRUCache<>(2);

        lru.put(1, 1);
        lru.put(2, 2);
        System.out.println(lru.get(1));

        lru.put(3, 3);
        System.out.println(lru.get(2));
        System.out.println(lru.get(3));

        lru.put(4, 4);
        System.out.println(lru.get(1));
        System.out.println(lru.get(3));
        System.out.println(lru.get(4));
    }

    public V get(K key) {
        if(!cache.containsKey(key)) {
            return null;
        }
        Node node = cache.get(key);
        dll.removeNode(node);
        dll.addNodeToFront(node);
        return (V) node.val;
    }

    public void put(K key, V value) {
        if (!cache.containsKey(key)) {  //if key is not present in hashmap
            Node node = new Node(key, value);
            if (cache.size() >= capacity) {
                Node tailNode = dll.removeTail(); // Remove the least recently used node
                cache.remove(tailNode.key);
            }
            cache.put(key, node);
            dll.addNodeToFront(node);
        } else {
            Node node = cache.get(key);
            node.val = value; // Update the value of the existing node
            dll.removeNode(node);
            dll.addNodeToFront(node);
        }
    }



    class Node<K,V> {

        K key;
        V val;
        Node next;
        Node prev;

        Node(K key, V val) {
            this.key = key;
            this.val = val;
        }
    }

    class DoublyLinkedList<K,V> {

        private Node head;
        private Node tail;

        DoublyLinkedList() {
            head = new Node(null, null);
            tail = new Node(null, null);
            head.next = tail;
            tail.prev = head;
        }

        void addNodeToFront(Node node) {
            node.next = head.next;  // node -> tail
            node.prev = head;   //head <- node
            head.next.prev = node;  //  node <- tail
            head.next = node;   // head -> node       head -> <- node -> <- tail
        }

        void removeNode(Node node) {
            node.prev.next = node.next;
            node.next.prev = node.prev;
        }

        Node removeTail() {
            if (tail.prev == head) {
                return null; // List is empty
            }
            Node node = tail.prev;
            removeNode(node);
            return node;
        }
    }
    
}


