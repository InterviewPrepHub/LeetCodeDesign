package com.example.LeetCodeDesign.Design;

import java.util.HashMap;
import java.util.Map;

public class TrieImplementation {

    TNode root;

    public void insert(String s) {
        TNode current = root;
        for (char ch: s.toCharArray()) {
            TNode nodeToAdd = current.children.get(ch);
            if (nodeToAdd==null) {
                nodeToAdd = new TNode();
                current.children.put(ch, nodeToAdd);
            }
            current = nodeToAdd;
        }
        current.endOfWord = true;
    }

    public boolean search(String word) {
        TNode current = root;
        for (char w : word.toCharArray()) {
            if (!current.children.containsKey(w)) {
                return false;
            }
            current = current.children.get(w);
        }
        return current!=null && current.endOfWord;
    }

    public boolean startsWith(String prefix) {
        TNode current = root;
        for (char p : prefix.toCharArray()) {
            if (!current.children.containsKey(p)) {
                return false;
            }
            current = current.children.get(p);
        }
        return current!=null;
    }

}

class TNode {

    Map<Character, TNode> children;
    boolean endOfWord;

    TNode() {
        children = new HashMap<>();
        endOfWord = false;
    }

}
