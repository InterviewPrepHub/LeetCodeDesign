package com.example.LeetCodeDesign.Design;

import java.util.HashMap;

/*
The challenge is to create a virtual file system that can create new paths and assign values to them.
Each path follows a structure similar to file directories in operating systems, beginning with a / (e.g., /path),
and can have multiple nested levels (e.g., /path/to/resource). It's important to note that paths like
an empty string "" or a single slash / are not considered valid.

createPath: This method should create a new path with an associated value, returning true if successful.
The method can only succeed if the path does not already exist and only if the "parent" path (the path
minus the last component) already exists. If these conditions are not met, the method should return false.

get: This method should retrieve the value associated with an existing path. If the path does not exist,
the method should return -1.


 */
public class DesignFileSystem {

    HashMap<String, Integer> fileValuesMap;

    public DesignFileSystem() {
        fileValuesMap = new HashMap<>();
    }

    public boolean createPath(String path, int value) {

        if(fileValuesMap.isEmpty()) {
            fileValuesMap.put(path,value);
            return true;
        }

        if(fileValuesMap.containsKey(path) || !isValidPath(path)) {
            return false;
        }

        fileValuesMap.put(path, value);

        return true;
    }

    public boolean isValidPath(String path) {
        int lastIndexPath = path.lastIndexOf("/");
        if (lastIndexPath == -1) {
            return false;
        }

        String parentPath = path.substring(0, lastIndexPath);
        return fileValuesMap.containsKey(parentPath);
    }

    // Method to get the value of a given path in the file system.
    public int get(String path) {

        if (fileValuesMap.get(path) == null) {
            return -1;
        }

        return fileValuesMap.get(path);
    }


    public static void main(String[] args) {
        DesignFileSystem d = new DesignFileSystem();
        System.out.println(d.createPath("/a", 1));
        System.out.println(d.createPath("/a/b", 2));
        System.out.println(d.createPath("/a/b/c", 3));
        System.out.println(d.get("/a/b"));
        System.out.println(d.createPath("/a/b/c", 4));
        System.out.println(d.get("/a/b/c"));
        System.out.println(d.get("/d"));
    }
}
