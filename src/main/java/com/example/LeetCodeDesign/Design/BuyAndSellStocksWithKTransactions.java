package com.example.LeetCodeDesign.Design;

public class BuyAndSellStocksWithKTransactions {

    public static void main(String[] args) {
        int[] prices = {3,2,6,5,0,3}; int k = 2;
        System.out.println(transactions(prices, k));
    }

    private static int transactions(int[] prices, int k) {
        if(k==0 || prices.length == 0) {
            return 0;
        }

        int[][] T = new int[k+1][prices.length];
        for (int i=1;i<T.length;i++) {
            for (int j=1;j<T[0].length;j++) {
                int maxValue = 0;
                for (int m=0;m<j;m++) {
                    maxValue = Math.max(maxValue, prices[j]-prices[m]+T[i-1][m]);
                }
                T[i][j] = Math.max(T[i][j-1],maxValue);
            }
        }
        return T[k][prices.length-1];
    }
}
