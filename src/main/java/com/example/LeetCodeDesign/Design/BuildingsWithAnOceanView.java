package com.example.LeetCodeDesign.Design;

/*
There are n buildings in a line. You are given an integer array heights of size n that represents the heights
of the buildings in the line.The ocean is to the right of the buildings. A building has an ocean view if the
building can see the ocean without obstructions. Formally, a building has an ocean view if all the buildings
to its right have a smaller height.

Return a list of indices (0-indexed) of buildings that have an ocean view, sorted in increasing order.

Example 1:
Input: heights = [4,2,3,1]
Output: [0,2,3]

 */

public class BuildingsWithAnOceanView {

    public int[] findBuildings(int[] heights) {
        int n = heights.length;
        int[] res = new int[n];
        res[0] = n-1;
        int k= 1;
        int max_so_far = heights[n-1];

        for (int i = n-1; i >= 0 && i-1 >=0; i--) {
            if (heights[i-1] > max_so_far ) {
                res[k] = i-1;
                k++;
                max_so_far = heights[i-1];
            }
        }
        return res;
    }

    public static void main(String[] args) {

        int[] heights = {5, 2, 3, 4};
        BuildingsWithAnOceanView b = new BuildingsWithAnOceanView();
        int[] res = b.findBuildings(heights);
        for (int i = 0; i < res.length; i++) {
            System.out.println(res[i]);
        }
    }
}
