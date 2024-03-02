package com.example.LeetCodeDesign.Design;

import java.util.Stack;

public class MinStack {

    Stack<Integer> stk;
    Stack<Integer> min_stk;

    public MinStack() {
        stk = new Stack<>();
        min_stk = new Stack<>();
    }

    public void push(int val) {
        if(stk.isEmpty()) {
            stk.push(val);
            min_stk.push(val);
        } else {
            if (val<min_stk.peek()) {
                stk.push(val);
                min_stk.push(stk.peek());
            }
        }
    }

    public void pop() {

        int curr_val = stk.peek();
        int min_val = min_stk.peek();
        if (curr_val == min_val) {
            stk.pop();
            min_stk.pop();
        } else  {
            stk.pop();
        }

    }

    public int top() {
        return stk.peek();
    }

    public int getMin() {
        return min_stk.peek();
    }

//====================================================================

    Stack<Integer> stk1 = new Stack<>();
    int min = Integer.MAX_VALUE;

    public void push1(int val) {
        if (val >= min) {
            stk1.push(min);
            min = val;
        }
        stk1.push(val);
    }

    public void pop1() {
        if(min == stk1.pop()) {
            min = stk1.pop();
        }
    }

    public int top1() {
        return stk1.peek();
    }

    public int getMin1() {
        return min;
    }

    public static void main(String[] args) {
        MinStack minStack = new MinStack();
        minStack.push1(-2);
        minStack.push1(0);
        minStack.push1(-3);
        System.out.println(minStack.getMin1()); // return -3
        minStack.pop1();
        minStack.top1();    // return 0
        System.out.println(minStack.getMin1()); // return -2

    }
}
