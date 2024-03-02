package com.example.LeetCodeDesign.Design;

/*
Question Statement:
1. You are given a number n (representing the number of students). Each student will have an id
     from 0 to n - 1.
2. You are given a number k (representing the number of clubs)
3. In the next k lines, two numbers are given separated by a space. The numbers are ids of
     students belonging to same club.
4. You have to find in how many ways can we select a pair of students such that both students are
     from different clubs.
 */

import java.util.ArrayList;
import java.util.ListIterator;

public class PerfectFriends {

    int v;
    ArrayList<Edge> adj[];

    PerfectFriends(int v) {
        this.v = v;
        adj = new ArrayList[v];
        for (int i = 0; i < v; i++) {
            adj[i] = new ArrayList<>();
        }
    }

    public void addEdge(int src, int dest) {
        adj[src].add(new Edge(src, dest));
    }

    public void findPerfectFriends(int src, boolean[] visited, ArrayList<Integer> comp) {
        visited[src] = true;
        comp.add(src);

        ListIterator<Edge> it = adj[src].listIterator();
        while (it.hasNext()) {
            Edge edge = it.next();
            if (visited[edge.nbr] == false) {
                findPerfectFriends(edge.nbr, visited, comp);
            }
        }
    }

    public static void main(String[] args) {

        int n = 7;
        PerfectFriends p = new PerfectFriends(n);
        p.addEdge(0,1);
        p.addEdge(2,3);
        p.addEdge(4,5);
        p.addEdge(5,6);
        p.addEdge(4,6);

        boolean[] visited = new boolean[n];

        ArrayList<ArrayList<Integer>> comps = new ArrayList<>();
        for (int i = 0; i < p.v; i++) {
            ArrayList<Integer> comp = null;
            if(visited[i] == false) {
                comp = new ArrayList<>();
                p.findPerfectFriends(i, visited, comp);
                comps.add(comp);
            }
        }

        int pair = 0;

        for (int i = 0; i < comps.size(); i++) {
            for (int j = i+1; j < comps.size(); j++) {
                int count = comps.get(i).size() * comps.get(j).size();
                pair += count;
            }
        }

        System.out.println(pair);

    }

    class Edge {
        int src;
        int nbr;

        Edge(int src, int nbr) {
            this.src = src;
            this.nbr = nbr;
        }
    }
}






















