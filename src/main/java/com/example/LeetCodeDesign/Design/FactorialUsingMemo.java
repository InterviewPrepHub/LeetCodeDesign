package com.example.LeetCodeDesign.Design;

import java.util.HashMap;
import java.util.Map;

public class FactorialUsingMemo {

    Map<Integer, Integer> map = new HashMap<>();
    public int fac(int n) {

        if (map.containsKey(n)){
            return map.get(n);
        }

        if (n == 0 || n == 1) {
            return 1;
        }

        int facVal = n * fac(n-1);

        map.put(n, facVal);

        return facVal;

    }

    public static void main(String[] args) {
        FactorialUsingMemo f = new FactorialUsingMemo();
        System.out.println(f.fac(5));
    }
}
