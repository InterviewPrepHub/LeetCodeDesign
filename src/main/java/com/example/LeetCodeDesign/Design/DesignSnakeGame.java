package com.example.LeetCodeDesign.Design;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/*
Design a Snake game that is played on a device with screen size = width x height. Play the game online if you are not familiar with the game.
The snake is initially positioned at the top left corner (0,0) with length = 1 unit.
You are given a list of food's positions in row-column order. When a snake eats the food, its length and the game's score both increase by 1.
Each food appears one by one on the screen. For example, the second food will not appear until the first food was eaten by the snake.
When a food does appear on the screen, it is guaranteed that it will not appear on a block occupied by the snake.
 */
public class DesignSnakeGame {

    int height;
    int width;
    int[][] board;
    LinkedList<int[]> snake;
    List<List<Integer>> food;

    public DesignSnakeGame(int width, int height, List<List<Integer>> food) {
        height = height;
        width = width;
        board = new int[height][width];
        snake = new LinkedList<int[]>();
        food = new ArrayList<>();

        int[] head = {0,0};
        snake.add(head);
        board[0][0] = 1;
    }

    /**
     * @param direction: the direction of the move
     * @return: the score after the move
     */
    public int move(String direction) {
        int[] head = snake.getFirst();
        int[] newHead = {head[0], head[1]};

        switch (direction){
            case "U" :
                newHead[0]--;   //moving up means decreasing row
                break;
            case "D" :
                newHead[0]++;
                break;
            case "L" :
                newHead[1]--;
                break;
            case "R" :
                newHead[1]++;
                break;
        }

        //check if new head is out of bounds or hits snake body
        if (newHead[0] < 0 || newHead[0] >= board.length || newHead[1] <0 || newHead[1] >= board[0].length) {
            return -1;
        }

        //check if snake hits new food
        if(!food.isEmpty() || newHead[0] == food.get(0).get(0) || newHead[1] == food.get(0).get(1)) {
            snake.add(newHead); //add new head
            board[newHead[0]][newHead[1]] = 1; //mark new snake head on board
            food.remove(0); //remove consumed food from board
            return snake.size();
        }

        //move the snake ahead by removing the tail
        int[] tail = snake.removeLast();
        board[tail[0]][tail[1]] = 0;    //clear the tail

        //check in case the head hits its body
        if (board[newHead[0]] [newHead[1]] == 1) {
            return -1;
        }

        //but if snake does not hit the food
        snake.add(newHead);
        board[newHead[0]][newHead[1]] = 1;

        return -1;
    }

    public static void main(String[] args) {

    }
}
