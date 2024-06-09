package com.example.LeetCodeDesign.Design;

import java.util.*;

public class TopKFrequentInDataStream {

    int k;
    Map<String, Integer> freqMap;
    PriorityQueue<String> minHeap;

    public TopKFrequentInDataStream(int k) {
        this.k = k;
        freqMap = new HashMap<>();
        minHeap = new PriorityQueue<>((a,b) -> freqMap.get(a)- freqMap.get(b)); //store top k elements based on freq
    }

    public void add(String word) {
        freqMap.put(word, freqMap.getOrDefault(word, 0)+1);

        if(!minHeap.contains(word)) {
            minHeap.add(word);
        }
        if(minHeap.size() > k) {
            minHeap.poll();
        }

    }

    public List<String> topk() {
        List<String> result = new ArrayList<>(minHeap);
        result.sort((a, b) -> freqMap.get(b) - freqMap.get(a));
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
