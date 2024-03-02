package com.example.LeetCodeDesign.Design;

import java.util.*;

public class GraphImplementation {

    int v;
    ArrayList<Edge> adj[];

    GraphImplementation(int v) {
        this.v = v;
        adj = new  ArrayList[v];
        for (int i = 0; i < v; i++) {
            adj[i] = new ArrayList<>();
        }
    }

    public void addEdge(int src, int nbr, int wt) {
        adj[src].add(new Edge(src, nbr, wt));
//        adj[nbr].add(new Edge(nbr, src, wt));
    }

    public boolean hasPath(int src, int dest, boolean[] visited) {
        if (src == dest) {
            return true;
        }

        visited[src] = true;

        System.out.print(src + " ");
        for (Edge edge : adj[src]) {
            if (visited[edge.nbr] == false) {
                boolean hasNbrPath = hasPath(edge.nbr, dest, visited);
                if (hasNbrPath == true) {
                    return true;
                }
            }
        }

        return false;
    }

    public void BFS (int s) {

        boolean visited[] = new boolean[v];
        LinkedList<Integer> queue = new LinkedList<Integer>();

        visited[s] = true;
        queue.add(s);

        while (!queue.isEmpty()) {
            s = queue.poll();
            System.out.print(s + " ");

            Iterator<Edge> i = adj[s].listIterator();
            while (i.hasNext()) {
                Edge n = i.next();
                if (visited[n.nbr] == false) {
                    visited[n.nbr] = true;
                    queue.add(n.nbr);
                }
            }
        }

    }

    private void printAllPaths(int src, int dest, boolean[] visited, String psf) {

        if (src == dest) {
            System.out.println(psf);
            return;
        }

        visited[src] = true;
        ListIterator<Edge> list = adj[src].listIterator();
        while (list.hasNext()) {
            Edge edge = list.next();
            if (visited[edge.nbr] == false) {
                printAllPaths(edge.nbr, dest, visited, psf+edge.nbr);
            }
        }
        visited[src] = false;

    }

    public static void main(String[] args) {
        int v = 4;
        GraphImplementation g = new GraphImplementation(v);
        boolean[] visited = new boolean[v];

        g.addEdge(0,1,10);
        g.addEdge(0,2,10);
        g.addEdge(1,2,10);
        g.addEdge(2,0,10);
        g.addEdge(2,3,10);
        g.addEdge(3,3,10);

        boolean res = g.hasPath(1,3, visited); //dfs
        System.out.println(res);

        g.BFS(2); //bfs

        GraphImplementation h = new GraphImplementation(v);
        h.addEdge(0,1,2);
        h.addEdge(0,2,2);
        h.addEdge(1,0,2);
        h.addEdge(1,3,1);
        h.addEdge(2,0,2);
        h.addEdge(2,1,1);
        h.addEdge(2,3,2);
        h.addEdge(3,1,1);
        h.addEdge(3,2,2);

        //print all paths
        h.printAllPaths(0,3,visited,0+"");

    }


    class Edge {
        int src;
        int nbr;
        int wt;

        Edge(int src, int nbr, int wt) {
            this.src = src;
            this.nbr = nbr;
            this.wt = wt;
        }
    }
}

