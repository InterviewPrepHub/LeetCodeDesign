package com.example.LeetCodeDesign.Design;

public class DesignBrowserHistory {

    Node head;
    Node tail;
    Node p1;

    public DesignBrowserHistory(String homepage) {
        Node nodeToAdd = new Node(homepage);
        if(tail == null) {
            head = nodeToAdd;
            tail = nodeToAdd;
        }
    }

    public void visit(String url) {
        Node nodeToAdd = new Node(url);

        tail.next = nodeToAdd;
        nodeToAdd.prev = tail;
        System.out.println("I am currently at "+tail.data+" and going to visit "+tail.next.data);
        tail = nodeToAdd;
        p1 = tail;
    }

    public String back(int steps) {
        while(steps > 0) {
            p1 = p1.prev;
            steps--;
        }
        return p1.prev.data;
    }

    public String forward(int steps) {
        int k = 0;
        while(k != steps) {
            if(p1.next != null) {
                p1 = p1.next;
                k++;
            } else {
                System.out.println("I am currently at "+p1.data+" you cannot move forward any steps");
                break;
            }
        }
        return p1.data;
    }

    public static void main(String[] args) {
        DesignBrowserHistory browserHistory = new DesignBrowserHistory("leetcode.com");
        browserHistory.visit("google.com");
        browserHistory.visit("facebook.com");
        browserHistory.visit("youtube.com");
        System.out.println(browserHistory.back(1));
        System.out.println(browserHistory.back(1));
        System.out.println(browserHistory.forward(1));
        browserHistory.visit("linkedin.com");
        System.out.println(browserHistory.forward(2));
        System.out.println(browserHistory.back(2));
        System.out.println(browserHistory.back(1));
    }

    class Node {
        String data;
        Node next;
        Node prev;

        public Node(String data) {
            this.data = data;
            this.next = null;
            this.prev = null;
        }
    }
}

