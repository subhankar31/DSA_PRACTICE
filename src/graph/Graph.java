package graph;

import java.util.*;

public class Graph {
    private Map<Integer, List<Integer>> adjList = new HashMap<>();

    public void addEdge(int u, int v) {
        adjList.putIfAbsent(u, new ArrayList<>());
        adjList.putIfAbsent(v, new ArrayList<>());
        adjList.get(u).add(v);  // for undirected graph also do: adjList.get(v).add(u);
    }

    public void printGraph() {
        for (int node : adjList.keySet()) {
            System.out.print(node + " -> " + adjList.get(node));
            System.out.println();
        }
    }

    public static void main(String[] args) {
        Graph g = new Graph();
        g.addEdge(1, 2);
        g.addEdge(1, 3);
        g.addEdge(2, 4);
        g.printGraph();
    }
}
