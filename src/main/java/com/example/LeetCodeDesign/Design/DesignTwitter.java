package com.example.LeetCodeDesign.Design;

import java.util.*;

/*
Implement a simple twitter. Support the following method:

postTweet(user_id, tweet_text). Post a tweet.
getTimeline(user_id). Get the given user's most recently 10 tweets posted by himself, order by timestamp from most recent to least recent.
getNewsFeed(user_id). Get the given user's most recently 10 tweets in his news feed (posted by his friends and himself). Order by timestamp from most recent to least recent.
follow(from_user_id, to_user_id). from_user_id followed to_user_id.
unfollow(from_user_id, to_user_id). from_user_id unfollowed to to_user_id.

 */
public class DesignTwitter {

/*
    for follow and unfollow - we need a hashmap where a userId can follow many userIds
    so we should be able to add and remove from that list of userIds, so we should use hashset
    HashMap<userId, HashSet<userIds>>

    for posting a tweet by a userId we have a HashMap where a userId can post list of tweets.
    HashMap<userId, List<Tweet>>

*/

    HashMap<Integer, HashSet<Integer>> followMap;
    HashMap<Integer, List<Tweet>> tweetMap;

    public DesignTwitter() {
        followMap = new HashMap<>();
        tweetMap = new HashMap<>();
    }

    public void postTweet(int userId, int tweetId) {

        if(!tweetMap.containsKey(userId)) {
            tweetMap.put(userId,new ArrayList<Tweet>());
        }
        tweetMap.get(userId).add(0, new Tweet(userId, tweetId));
    }

    /*
    Retrieves the 10 most recent tweet IDs in the user's news feed. Each item in the news feed must be posted by users
    who the user followed or by the user themself. Tweets must be ordered from most recent to least recent.

     */

    public List<Integer> getNewsFeed(int userId) {
        List<Integer> newsFeed = new ArrayList<>();
        PriorityQueue<Tweet> pq = new PriorityQueue<>((a,b) -> (int)(b.timestamp - a.timestamp));

        if (tweetMap.containsKey(userId)) {
            pq.addAll(tweetMap.get(userId));
        }

        if (followMap.containsKey(userId)) {
            for (int followerId : followMap.get(userId)) {
                if (tweetMap.containsKey(followerId)) {
                    pq.addAll(tweetMap.get(followerId));
                }
            }
        }

        while (!pq.isEmpty() && newsFeed.size()<10) {
            newsFeed.add(pq.poll().tweetId);
        }

        return newsFeed;
    }

    public void follow(int followerId, int followeeId) {

        if (!followMap.containsKey(followerId)) {
            followMap.put(followerId, new HashSet<>());
        }
        followMap.get(followerId).add(followeeId);

    }

    public void unfollow(int followerId, int followeeId) {
        HashSet<Integer> followers = followMap.get(followerId);

        if (followers.contains(followeeId)) {
            followers.remove(followeeId);
        }
    }

    public static void main(String[] args) {
        DesignTwitter twitter = new DesignTwitter();
        // Users post tweets
        twitter.postTweet(1, 101);
        twitter.postTweet(1, 102);
        twitter.postTweet(2, 201);
        twitter.postTweet(2, 202);

        // Users follow each other
        twitter.follow(1, 2);

        // Get news feed for user 1
        List<Integer> newsFeedUser1 = twitter.getNewsFeed(1);
        System.out.println("News Feed for User 1: " + newsFeedUser1);  // Output: [102, 101, 202, 201]

        // User 1 unfollows user 2
        twitter.unfollow(1, 2);

        // Get news feed for user 1 after unfollowing
        List<Integer> updatedNewsFeedUser1 = twitter.getNewsFeed(1);
        System.out.println("Updated News Feed for User 1: " + updatedNewsFeedUser1);  // Output: [102, 101]

        // Get news feed for user 2
        List<Integer> newsFeedUser2 = twitter.getNewsFeed(2);
        System.out.println("News Feed for User 2: " + newsFeedUser2);  // Output: [202, 201]
    }
}

class Tweet {
    int userId;
    int tweetId;
    long timestamp;

    Tweet(int userId, int tweetId) {
        this.userId = userId;
        this.tweetId = tweetId;
        this.timestamp = System.nanoTime();
    }
}
