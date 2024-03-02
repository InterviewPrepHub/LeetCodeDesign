package com.example.LeetCodeDesign.Design;

public class HouseRobberII {

    public static void main(String[] args) {

        int[] arr = {5,10,10,100,5,6};

        int inc = arr[0];
        int exc = 0;

        for (int i = 1; i < arr.length-1; i++) {
            int new_inc = exc + arr[i];
            int new_exc = Math.max(inc, exc);
            inc = new_inc;
            exc = new_exc;

        }

        System.out.println(exc);
    }
}
