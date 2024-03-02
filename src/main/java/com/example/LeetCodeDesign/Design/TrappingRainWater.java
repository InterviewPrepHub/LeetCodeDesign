package com.example.LeetCodeDesign.Design;

public class TrappingRainWater {

    public static void main(String[] args) {
        int[] input = {0,1,0,2,1,0,1,3,2,1,2,1};
        //find max on the left
        int[] maxLeft = getMaxLeft(input);

        //find max on the right
        int[] maxRight = getMaxRight(input);

        int[] res = new int[input.length];

        //min(left, right) index data
        //min(left, right) - h[i]
        for (int i=0;i<input.length;i++) {
            res[i] = Math.min(maxLeft[i], maxRight[i])-input[i];
        }

        int sum = 0;
        for (int i=0;i< res.length;i++) {
            if (res[i] >= 0) {
                sum+=res[i];
            }
        }
        System.out.println(sum);

    }

    private static int[] getMaxRight(int[] input) {
        int[] maxRight = new int[input.length];
        int maxSoFar = 0;
        maxRight[input.length-1] = 0;

        for (int i= input.length-2;i>=0;i--) {
            maxSoFar = Math.max(maxSoFar, input[i+1]);
            maxRight[i] = maxSoFar;
        }
        return maxRight;
    }

    private static int[] getMaxLeft(int[] input) {

        int[] maxLeft = new int[input.length];
        int maxSofar = 0;
        maxLeft[0] = 0;

        for (int i = 1; i < input.length; i++) {
            maxSofar = Math.max(maxSofar, input[i-1]);
            maxLeft[i] = maxSofar;
        }
        return maxLeft;
    }
}
