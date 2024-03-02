package com.example.LeetCodeDesign.Design;

public class QuickSortImpl {

    public static void main(String[] args) {
        int[] arr = {7,2,1,6,8,5,3,4};
        quicksort(arr, 0, arr.length-1);
        for (int i=0;i<arr.length;i++) {
            System.out.print(i+" ");
        }
    }

    private static void quicksort(int[] a, int start, int end) {
        if (start < end) {
            int pIndex = partition(a, start,end);
            quicksort(a, start, pIndex-1);
            quicksort(a, pIndex+1, end);
        }

    }

    private static int partition(int[] a, int start, int end) {
        int pivot = a[end];
        int pIndex = start;

        for (int i=start;i<end;i++) {
            if(a[i] <= pivot) {
                swap(a[i], a[pIndex]);
                pIndex++;
            }
        }
        swap(a[pIndex], a[end]);
        return pIndex;
    }

    private static void swap(int start, int end) {
        int temp = start;
        start = end;
        end = temp;
    }
}
