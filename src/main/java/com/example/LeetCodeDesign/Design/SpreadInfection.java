package com.example.LeetCodeDesign.Design;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class SpreadInfection {

    int v;
    ArrayList<Edge> adj[] = null;

    SpreadInfection(int v) {
        this.v = v;
        adj = new ArrayList[v];
        for (int i = 0; i < v; i++) {
            adj[i] = new ArrayList<>();
        }
    }

    private void addEdge(int src, int nbr) {
        adj[src].add(new Edge(src, nbr));
    }

    public static void main(String[] args) {

        int n = 7;
        SpreadInfection s = new SpreadInfection(n);

        s.addEdge(0,1);
        s.addEdge(0,3);
        s.addEdge(1,0);
        s.addEdge(1,2);
        s.addEdge(2,1);
        s.addEdge(2,3);
        s.addEdge(3,0);
        s.addEdge(3,2);
        s.addEdge(3,4);
        s.addEdge(4,3);
        s.addEdge(4,5);
        s.addEdge(4,6);
        s.addEdge(5,4);
        s.addEdge(5,6);
        s.addEdge(6,4);
        s.addEdge(6,5);

        int[] visited = new int[n];
        s.spread(0, 4, visited);

    }

    public void spread(int src, int t, int[] visited) {

        Queue<Pair> q = new LinkedList<>();
        q.add(new Pair(src, 1));
        int count = 0;

        while (!q.isEmpty()) {
            Pair rem = q.poll();

            if (visited[rem.v] > 0) {
                continue;
            }
            visited[rem.v] = rem.time;
            count++;

            for (Edge e : adj[src]) {
                if (visited[e.nbr] == 0) {
                    q.add(new Pair(e.nbr, rem.time+1));
                }
            }

        }
        System.out.println(count);
    }


}

class Edge {
    int src;
    int nbr;

    Edge(int src, int nbr) {
        this.src = src;
        this.nbr = nbr;
    }
}

class Pair {
    int v;
    int time;

    public Pair(int v, int time) {
        this.v = v;
        this.time = time;
    }
}
