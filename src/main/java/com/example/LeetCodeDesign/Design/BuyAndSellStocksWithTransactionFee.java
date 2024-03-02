package com.example.LeetCodeDesign.Design;

public class BuyAndSellStocksWithTransactionFee {

    public static int maxProfitWithFee(int[] prices, int fee) {
        int n = prices.length;

        if (n==0) {
            return 0;
        }
        int[] hold = new int[n];
        int[] cash = new int[n]; //when we not hold, max profit on day i

        hold[0] = - prices[0];
        cash[0] = 0;

        /*
        hold[i] represents the maximum profit on day i if you decide to hold a stock.
        To calculate this, you either continue holding the stock from the previous day
        (hold[i - 1]) or you sell the stock on day i and pay the transaction fee
        (cash[i - 1] - prices[i] - fee).

        cash[i] represents the maximum profit on day i if you decide not to hold a stock.
        To calculate this, you either continue not holding a stock from the previous day
        (cash[i - 1]) or you buy a stock on day i using the money you earned from the
        previous transaction (hold[i - 1] + prices[i]).
         */
        for(int i=1;i<n;i++) {
            hold[i] = Math.max(hold[i-1], cash[i-1]-prices[i]-fee); //max profit on day i, when we buy and hold
            cash[i] = Math.max(cash[i-1], hold[i-1]+prices[i]);     //max profit on day i, when we do not hold
        }

        return cash[n-1];
    }

    public static void main(String[] args) {

        int[] prices = {1,3,7,5,10,3}; int fee = 3;
        System.out.println(maxProfitWithFee(prices, fee));

    }
}
