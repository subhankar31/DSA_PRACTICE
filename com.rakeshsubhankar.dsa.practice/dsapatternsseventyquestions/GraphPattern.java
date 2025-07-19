package dsapatternsseventyquestions;

import dsapatternsseventyquestions.util.GraphNode;

import java.util.HashMap;
import java.util.Map;

public class GraphPattern {
    //Question -63 Leet Code 133. Clone Graph
    //Leet Code URL  -> https://leetcode.com/problems/clone-graph/description/

    //store oldNode and new Node as key value pair in Map to make Dyanamic Programming
    Map<GraphNode,GraphNode> map=new HashMap<>();
    public GraphNode cloneGraph(GraphNode node) {
        //base condition
        if(node==null) return null;
        //check if we already have new node for the old node in map
        //then directly return new node of that old node
        if(map.containsKey(node)) return map.get(node);
        //create new node from old node value
        GraphNode newNode=new GraphNode(node.val);
        //add the new node to the map for DP
        map.put(node,newNode);
        //traverse adjacent nodes and add to new nodes adj list
       for (GraphNode neighbour: node.neighbors){
           newNode.neighbors.add(cloneGraph(neighbour));
       }
       return newNode;
    }

    //Question -64 Find Largest Node
    //Leet Code URL  -> N/A
    /**
     * Traverse complete graph and keep track of largest and return at the end
     */

    //Question -65 Cycle Detection in Graph
    //Leet Code URL  -> N/A
    /**
     * In Undirected Graph if visited array is not null for any node and we see parent [] is marked with different parent
     * then cycle is present , in directed graph parent [] is not needed
     */

    //Question -66 Count numder of edges
    //Leet Code URL  -> N/A
    /**
     * Sum of the adjacency list after iterating through all the vertex of the graph
     */

    //Question -67 Leet Code 787. Cheapest Flights Within K Stops
    //Leet Code URL  -> https://leetcode.com/problems/cheapest-flights-within-k-stops/description/
    // TODO VV.IMP

}
