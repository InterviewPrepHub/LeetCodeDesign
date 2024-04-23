package com.example.LeetCodeDesign.Design;

import java.util.HashMap;
import java.util.Map;

/*
    We will remove element from bottom and add element on head of LinkedList and whenever any DNode is accessed ,
    it will be moved to top. so that recently used entries will be on Top and Least used will be on Bottom.
    https://krishankantsinghal.medium.com/my-first-blog-on-medium-583159139237
     */
public class LRUCache {

    Map<Integer, DNode> map;
    DNode head, tail;
    int capacity = 4; // Here i am setting 4 to test the LRU cache
    // implementation, it can make be dynamic
    public LRUCache() {
        map = new HashMap<Integer, DNode>();
    }

    public int getEntry(int key) {
        if (map.containsKey(key)) { // Key Already Exist, just update the
            DNode DNode = map.get(key);
            removeNode(DNode);
            addAtTop(DNode);
            return DNode.value;
        }
        return -1;
    }

    public void putEntry(int key, int value) {
        if (map.containsKey(key)) { // Key Already Exist, just update the value and move it to top
            DNode DNode = map.get(key);
            DNode.value = value;
            removeNode(DNode);
            addAtTop(DNode);
        } else {
            DNode newnode = new DNode();
            newnode.left = null;
            newnode.right = null;
            newnode.value = value;
            newnode.key = key;
            if (map.size() > capacity) {// We have reached maxium size so need to make room for new element.
                map.remove(tail.key);
                removeNode(tail);
                addAtTop(newnode);
            } else {
                addAtTop(newnode);
            }

            map.put(key, newnode);
        }
    }
    public void addAtTop(DNode node) {
        node.right = head;
        node.left = null;
        if (head != null)
            head.left = node;
        head = node;
        if (tail == null)
            tail = head;
    }

    public void removeNode(DNode node) {

        if (node.left != null) {
            node.left.right = node.right;
        } else {
            head = node.right;
        }

        if (node.right != null) {
            node.right.left = node.left;
        } else {
            tail = node.left;
        }
    }
    public static void main(String[] args) throws Exception {
        // your code goes here
        LRUCache lrucache = new LRUCache();
        lrucache.putEntry(1, 1);
        lrucache.putEntry(10, 15);
        lrucache.putEntry(15, 10);
        lrucache.putEntry(10, 16);
        lrucache.putEntry(12, 15);
        lrucache.putEntry(18, 10);
        lrucache.putEntry(13, 16);

        System.out.println(lrucache.getEntry(1));
        System.out.println(lrucache.getEntry(10));
        System.out.println(lrucache.getEntry(15));

    }
    
}

class DNode {
    int value;
    int key;
    DNode left;
    DNode right;
}
