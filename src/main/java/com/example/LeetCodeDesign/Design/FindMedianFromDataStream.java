package com.example.LeetCodeDesign.Design;

import java.util.PriorityQueue;

public class FindMedianFromDataStream {

    PriorityQueue<Integer> minHeap = new PriorityQueue<>();
    PriorityQueue<Integer> maxHeap = new PriorityQueue<>((a,b) -> b.compareTo(a));


    public void addNum(int num) {
        if (minHeap.isEmpty() || num > minHeap.peek()) {
            minHeap.add(num);
        } else {
            maxHeap.add(num);
        }

        rebalance();
    }

    public void rebalance() {
        PriorityQueue<Integer> biggerHeap = minHeap.size() > maxHeap.size() ? minHeap : maxHeap;
        PriorityQueue<Integer> smallerHeap = minHeap.size() > maxHeap.size() ? maxHeap : minHeap;

        if (biggerHeap.size() - smallerHeap.size() >= 2) {
            smallerHeap.add(biggerHeap.poll());
        }
    }

    private double findMedian() {

        PriorityQueue<Integer> biggerHeap = minHeap.size() > maxHeap.size() ? minHeap : maxHeap;
        PriorityQueue<Integer> smallerHeap = minHeap.size() > maxHeap.size() ? maxHeap : minHeap;

        if (biggerHeap.size()== smallerHeap.size()){
            return (double)(biggerHeap.peek() + smallerHeap.peek())/2;
        }
        return biggerHeap.peek();
    }


    public static void main(String[] args) {
        FindMedianFromDataStream f = new FindMedianFromDataStream();
        f.addNum(3);
        f.addNum(8);
        f.addNum(6);
        double val1 = f.findMedian();
        System.out.println(val1);
        f.addNum(10);
        f.addNum(9);
        f.addNum(11);
        double val2 = f.findMedian();
        System.out.println(val2);
    }
}
