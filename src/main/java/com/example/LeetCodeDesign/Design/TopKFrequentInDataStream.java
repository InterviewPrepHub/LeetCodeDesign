package com.example.LeetCodeDesign.Design;

import java.util.*;

public class TopKFrequentInDataStream {

    private int k;
    private Map<String, Integer> wordFrequency;
    private PriorityQueue<String> topKFrequent;

    private Comparator<String> myComparator = new Comparator<String>() {
        public int compare(String left, String right) {
            if (left.equals(right))
                return 0;

            int leftCount = wordFrequency.get(left);
            int rightCount = wordFrequency.get(right);
            if (leftCount != rightCount) {
                return rightCount - leftCount;
            }
            return left.compareTo(right);
        }
    };


    public TopKFrequentInDataStream(int k) {
        this.k = k;
        this.wordFrequency = new HashMap<>();
        this.topKFrequent = new PriorityQueue<>(myComparator);
    }


    public void add(String word) {
        wordFrequency.put(word, wordFrequency.getOrDefault(word, 0) + 1);
        updateTopKFrequent(word);
    }

    private void updateTopKFrequent(String word) {
        if (topKFrequent.contains(word)) {
            topKFrequent.remove(word);
        }
        topKFrequent.offer(word);
        if (topKFrequent.size() > k) {
            topKFrequent.poll();
        }
    }

    public List<String> topk() {
        List<String> result = new ArrayList<>();
        while (!topKFrequent.isEmpty()) {
            result.add(0, topKFrequent.poll());
        }
        return result;
    }

    public static void main(String[] args) {

        TopKFrequentInDataStream topK = new TopKFrequentInDataStream(2);
        topK.add("i");
        topK.add("love");
        topK.add("i");
        topK.add("leetcode");
        topK.add("i");
        topK.add("love");
        topK.add("coding");
        List<String> topKWords = topK.topk();
        System.out.println(topKWords);
    }
}
