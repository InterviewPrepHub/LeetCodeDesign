package com.example.LeetCodeDesign.Design;

import java.util.*;

/*
Design a data structure that simulates an in-memory file system.

Implement the FileSystem class:

FileSystem() Initializes the object of the system.
List<String> ls(String path)
If path is a file path, returns a list that only contains this file's name.
If path is a directory path, returns the list of file and directory names in this directory.
The answer should in lexicographic order.
void mkdir(String path) Makes a new directory according to the given path. The given directory path does not exist. If the middle directories in the path do not exist, you should create them as well.
void addContentToFile(String filePath, String content)
If filePath does not exist, creates that file containing given content.
If filePath already exists, appends the given content to original content.
String readContentFromFile(String filePath) Returns the content in the file at filePath.


Example 1:

Input
["FileSystem", "ls", "mkdir", "addContentToFile", "ls", "readContentFromFile"]
[[], ["/"], ["/a/b/c"], ["/a/b/c/d", "hello"], ["/"], ["/a/b/c/d"]]
Output
[null, [], null, null, ["a"], "hello"]

Explanation
FileSystem fileSystem = new FileSystem();
fileSystem.ls("/"); // return []
fileSystem.mkdir("/a/b/c");
fileSystem.addContentToFile("/a/b/c/d", "hello");
fileSystem.ls("/"); // return ["a"]
fileSystem.readContentFromFile("/a/b/c/d"); // return "hello"

 */
public class DesignInMemoryFileSystem {

    TrieNode root;

    public DesignInMemoryFileSystem() {
        root = new TrieNode();
    }

    public List<String> ls(String path) {
        List<String> list = new ArrayList<>();
        TrieNode node = root.search(path);
        if(node == null) {
            return list;
        }

        if (node.isFile) {
            list.add(node.name);
        } else {
            list.addAll(node.children.keySet());
        }
        Collections.sort(list);
        return list;
    }

    public void mkdir(String path) {
        root.insert(path, false);
    }

    public void addContentToFile(String filePath, String content) {
        TrieNode node = root.insert(filePath, true);
        node.content.append(content);

    }

    public String readContentFromFile(String filePath) {
        TrieNode node = root.search(filePath);
        if(node!=null && node.isFile) {
            return node.content.toString();
        }
        return "";
    }

    public static void main(String[] args) {
        DesignInMemoryFileSystem fileSystem = new DesignInMemoryFileSystem();
        List<String> list1 = fileSystem.ls("/"); // return []
        System.out.println("get list :"+list1);
        fileSystem.mkdir("/a/b/c");
        fileSystem.addContentToFile("/a/b/c/d", "hello");
        List<String> list2 = fileSystem.ls("/"); // return ["a"]
        System.out.println("get list :"+list2);
        fileSystem.readContentFromFile("/a/b/c/d"); // return "hello"
    }
}

class TrieNode {
    String name; // Name of the file or directory
    boolean isFile; // Flag indicating whether it is a file or not
    StringBuilder content = new StringBuilder(); // Content of the file if it is a file
    Map<String, TrieNode> children = new HashMap<>(); // Child nodes, representing files and directories

    // Method to insert a node and return the last node in the path.
    TrieNode insert(String path, boolean isFile) {
        TrieNode node = this;
        String[] parts = path.split("/");

        for (int i = 1; i < parts.length; ++i) {
            String part = parts[i];
            if (!node.children.containsKey(part)) {
                node.children.put(part, new TrieNode());
            }
            node = node.children.get(part);
        }

        node.isFile = isFile;
        if (isFile) {
            node.name = parts[parts.length - 1]; // Set the name of the file
        }

        return node;
    }

    // Method to search for a node given a path.
    TrieNode search(String path) {
        TrieNode node = this;
        String[] parts = path.split("/");

        for (int i = 1; i < parts.length; ++i) {
            String part = parts[i];
            if (!node.children.containsKey(part)) {
                return null; // Node not found
            }
            node = node.children.get(part);
        }

        return node; // Node found
    }
}
