package com.example.LeetCodeDesign.Design;


import java.util.HashMap;
import java.util.Map;

//https://aaronice.gitbook.io/lintcode/high_frequency/two-sum-iii-data-structure-design
public class TwoSumIII {

    Map<Integer, Integer> map = new HashMap<>();
    int i = 0;

    public void add(int x) {
        map.put(x,i++);
    }

    public boolean find(int val){
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            int diff = Math.abs(val - entry.getKey());
            if (map.containsKey(diff)) {
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        TwoSumIII t = new TwoSumIII();
        t.add(1);
        t.add(3);
        t.add(5);
        System.out.println(t.find(4));
        System.out.println(t.find(7));
    }
}
