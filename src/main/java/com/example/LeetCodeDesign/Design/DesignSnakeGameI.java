package com.example.LeetCodeDesign.Design;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

public class DesignSnakeGameI {

    int[][] board;
    Deque<int[]> snake;
    List<int[]> foods;
    int foodIndex;

    DesignSnakeGameI(int ht, int wd, int[][] food) {
        this.board = new int[ht][wd];
        this.snake = new LinkedList<>();
        this.snake.add(new int[]{0,0});
        this.foods = new ArrayList<>();

        this.foodIndex = 0;

        for(int[] f : food) {
            foods.add(f);
        }
    }

    public int move(String direction) {
        System.out.println(direction);
        int[] head = snake.poll();
        int[] newHead = new int[]{head[0], head[1]};

        if(direction.equals("Up")) {
            newHead[0]--;
        }
        if(direction.equals("Down")) {
            newHead[0]++;
        }
        if(direction.equals("Left")) {
            newHead[1]--;
        }
        if(direction.equals("Right")) {
            newHead[1]++;
        }

        //check the boundary
        if(newHead[0] < 0 || newHead[0] >= board.length || newHead[1] < 0 || newHead[1] >= board.length) {
            System.out.println("here1");
            return -1;
        }

        //check if snake collides with itself
        for(int[] segment : snake) {
            if(segment[0] == newHead[0] && segment[1] == newHead[1]) {

                System.out.println("here2");
                return -1;// game over
            }
        }

        //snake eats the foods
        int[] fd = foods.get(foodIndex);
        if(foodIndex < foods.size() && fd[0] == newHead[0] && fd[1] == newHead[1]) {
            foodIndex++;
        } else {
            snake.removeLast(); //snake moves forward by removing tail and  moving head ahead
        }

        snake.addFirst(newHead); //snake grows by adding new head and not removing tail

        return snake.size();

    }
}
