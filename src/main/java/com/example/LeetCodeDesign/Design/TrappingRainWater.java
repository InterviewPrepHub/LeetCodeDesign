package com.example.LeetCodeDesign.Design;

/*
The trap method takes an array of heights as input and returns the total trapped water.
We initialize two pointers left and right pointing to the leftmost and rightmost positions of the array, respectively.
leftMax and rightMax are initialized to keep track of the maximum height encountered from the left and right, respectively.
We move the pointers inward while updating leftMax and rightMax as necessary, and calculating the trapped water at each step.
 */
public class TrappingRainWater {

    private static int trapWater(int[] input) {

        int left = 0;
        int right = input.length-1;

        int leftMax = 0;
        int rightMax = 0;

        int trapped_water = 0;

        while(left < right) {
            if(input[left] < input[right]) {
                if(input[left]  >= leftMax) {
                    leftMax = input[left];
                } else {
                    trapped_water += leftMax - input[left];
                }
                left++;
            } else {
                if (input[left] >= rightMax) {
                    rightMax = input[right];
                } else {
                    trapped_water += rightMax - input[right];
                }
                right--;
            }
        }
        return trapped_water;
    }

    public static void main(String[] args) {

        int[] heights = {0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1};
        System.out.println(trapWater(heights));

    }
}
