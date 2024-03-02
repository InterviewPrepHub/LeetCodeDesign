package com.example.LeetCodeDesign.Design;

import java.util.Arrays;

public class ImplementMinHeap {

    int capacity = 10;
    int size = 0;

    int[] items = new int[capacity];

    public int getLeftChildIndex(int parentIndex) {
        return (2*parentIndex+1);
    }

    public int getRightChildIndex(int parentIndex) {
        return (2*parentIndex+2);
    }

    public int getParentIndex(int childIndex) {
        return (childIndex-1)/2;
    }

    public boolean hasLeftChild(int index) {
        return getLeftChildIndex(index) < size;
    }

    public boolean hasRightChild(int index) {
        return getRightChildIndex(index) < size;
    }

    public boolean hasParent(int index) {
        return getParentIndex(index) >= 0;
    }

    public int leftChild(int index) {
        return items[getLeftChildIndex(index)];
    }

    public int rightChild(int index) {
        return items[getRightChildIndex(index)];
    }

    public int parent(int index) {
        return items[getParentIndex(index)];
    }

    public void ensureCapacity() {
        if (size == capacity) {
            items = Arrays.copyOf(items, capacity*2);
            capacity *= 2;
        }
    }

    public void swap(int index1, int index2) {
        int temp = items[index1];
        items[index1] = items[index2];
        items[index2] = temp;
    }

    public int peek() {
        return items[0];
    }

    public int poll() {
        int item = items[0];
        items[0] = items[size-1];
        size--;
        heapifyDown();
        return item;
    }

    public void add(int item) {
        ensureCapacity();
        items[size] = item;
        size++;
        heapifyUp();
    }

    private void heapifyUp() {
        int index = size-1;
        while (hasParent(index) && parent(index) > items[index]) {
            swap(index, getParentIndex(index));
            index = getParentIndex(index);
        }

    }

    private void heapifyDown() {
        int index = 0;
        while (hasLeftChild(index)){
            int smallestChildIndex = getLeftChildIndex(index);
            if (hasRightChild(index) && leftChild(index) > rightChild(index)) {
                smallestChildIndex = getRightChildIndex(index);
            }

            if (items[index] < items[smallestChildIndex]) {
                break;
            } else {
                swap(smallestChildIndex, index);
                index = smallestChildIndex;
            }
        }

    }

}
