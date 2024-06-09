package com.example.LeetCodeDesign.Design;

import java.util.HashMap;
import java.util.Map;

public class TrieImplementation {

    TNode root;

    public TrieImplementation() {
        root = new TNode();
    }

    public void insert(String word) {
        TNode current = root;
        for (char ch : word.toCharArray()) {
            current.children.putIfAbsent(ch, new TNode());
            current = current.children.get(ch);
        }
        current.endOfWord = true;
    }

    public boolean search(String word) {
        TNode current = root;
        for (char ch : word.toCharArray()) {
            current = current.children.get(ch);
            if(current == null) {
                return false;
            }
        }
        return current.endOfWord;
    }

    // Method to check if any word in the Trie starts with the given prefix
    public boolean startsWith(String prefix) {
        TNode current = root;
        for (char p : prefix.toCharArray()) {
            current = current.children.get(p);

            if(current == null) {
                return false; // If the character is not found, return false
            }
        }
        return true; // If all characters in the prefix are found, return true
    }

    public static void main(String[] args) {
        TrieImplementation trie = new TrieImplementation();

        // Inserting strings into the Trie
        trie.insert("abcbd");
        trie.insert("bcb");

        // Searching for strings in the Trie
        System.out.println(trie.search("abcbd")); // Should print: true
        System.out.println(trie.search("bcb"));   // Should print: true
        System.out.println(trie.search("abcd"));  // Should print: false

        // Checking for prefixes in the Trie
        System.out.println(trie.startsWith("abc")); // Should print: true
        System.out.println(trie.startsWith("bc"));  // Should print: true
        System.out.println(trie.startsWith("abx")); // Should print: false
    }

    class TNode {

        Map<Character, TNode> children;
        boolean endOfWord;

        TNode() {
            children = new HashMap<>();
            endOfWord = false;
        }

    }

}
