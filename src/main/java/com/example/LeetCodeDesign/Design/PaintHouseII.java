package com.example.LeetCodeDesign.Design;

public class PaintHouseII {

    public static void main(String[] args) {

        //                       c1, c2, c3
        int cost[][] = { { 14, 2, 11 }, //---> h1
                { 11, 14, 5 }, //---> h2
                { 14, 3, 10 }};//---> h3
        int N = cost.length;
        minCost(cost, N);
    }

    private static void minCost(int[][] cost, int n) {

        int[][] dp = new int[cost.length][cost[0].length];

        for (int i = 0; i < cost.length; i++) {
            dp[0][i] = cost[0][i];
        }

        /*for (int i = 1; i < dp.length; i++) {
            for ()
        }*/

    }
}
