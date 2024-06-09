package com.example.LeetCodeDesign.Design;

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

/*
Now you are required to design a Leaderboard that can dynamically display the score data of the players.

Please refine the 3 methods in the Leaderboard class to achieve the following effects:

addScore(playerId, score):
If the player is already on the leaderboard, add score points to his current score and update the ranking
If the player is not on the leaderboard, add him to the list and set his score to score
top(k): returns the sum of the scores of the top k players in terms of scores
reset(playerId): Zeroes out the score for the specified player. It is guaranteed that the player has a score and is on the list before calling this function.
 */
public class DesignLeaderBoard {
    private Map<Integer, Integer> playerScores;
    private PriorityQueue<Integer> topPlayers;

    public DesignLeaderBoard() {
        playerScores = new HashMap<>();
        topPlayers = new PriorityQueue<>((a, b) -> playerScores.get(b) - playerScores.get(a));
    }

    /**
     * @param playerId: ID of a player.
     * @param score: Score of the player.
     * @return: nothing
     */
    public void addScore(int playerId, int score) {
        if (playerScores.containsKey(playerId)) {
            int currentScore = playerScores.get(playerId);
            // Remove the player from the priority queue for reinsertion with updated score
            topPlayers.remove(playerId);
            playerScores.put(playerId, currentScore + score);
        } else {
            playerScores.put(playerId, score);
        }
        topPlayers.add(playerId);
    }

    /**
     * @param k: Top k players.
     * @return: summation of the scores of the top k players.
     */
    public int top(int k) {
        int sum = 0;
        PriorityQueue<Integer> tempQ = new PriorityQueue<>((a,b) -> playerScores.get(b) - playerScores.get(a));
        for (int i = 0; i < k && !topPlayers.isEmpty(); i++) {
            int playerId = topPlayers.poll();
            tempQ.add(playerId);
            sum += playerScores.get(playerId);
        }
        // Reinsert players back into the priority queue after extracting their scores
        topPlayers.addAll(tempQ);
        return sum;
    }

    /**
     * @param playerId: ID of a player.
     * @return: nothing
     */
    public void reset(int playerId) {
        playerScores.remove(playerId);
        // Remove the player from the priority queue (if present) after resetting the score
        topPlayers.remove(playerId);
    }

    public static void main(String[] args) {
        DesignLeaderBoard leaderboard = new DesignLeaderBoard();
        /*leaderboard.addScore(1,73);   // leaderboard = [[1,73]];
        leaderboard.addScore(2,56);   // leaderboard = [[1,73],[2,56]];
        leaderboard.addScore(3,39);   // leaderboard = [[1,73],[2,56],[3,39]];
        leaderboard.addScore(4,51);   // leaderboard = [[1,73],[2,56],[3,39],[4,51]];
        leaderboard.addScore(5,4);    // leaderboard = [[1,73],[2,56],[3,39],[4,51],[5,4]];
        System.out.println(leaderboard.top(1));           // returns 73;
        leaderboard.reset(1);         // leaderboard = [[2,56],[3,39],[4,51],[5,4]];
        leaderboard.reset(2);         // leaderboard = [[3,39],[4,51],[5,4]];
        leaderboard.addScore(2,51);   // leaderboard = [[2,51],[3,39],[4,51],[5,4]];
        System.out.println(leaderboard.top(3));*/

        leaderboard.addScore(1, 10);
        leaderboard.addScore(2, 5);
        System.out.println(leaderboard.top(2)); // Output: 15
        leaderboard.addScore(3, 2);
        leaderboard.addScore(4, 4);
        System.out.println(leaderboard.top(1)); // Output: 14
        leaderboard.reset(2);
        System.out.println(leaderboard.top(3)); // Output: 14
    }
}
