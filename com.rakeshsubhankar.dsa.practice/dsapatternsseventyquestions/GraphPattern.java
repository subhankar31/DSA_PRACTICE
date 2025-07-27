package dsapatternsseventyquestions;

import dsapatternsseventyquestions.util.Flight;
import dsapatternsseventyquestions.util.GraphNode;
import dsapatternsseventyquestions.util.Tuple;

import java.util.*;

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
    public int findCheapestPrice(int numOfCities, int[][] flights, int source, int destination, int maxStops) {
        //Step-1 Create Adj list from [][]
        //List-> Source , List->Flight obj which stores destination and cost
        List<List<Flight>> adjList=new ArrayList<>();
        for(int i=0;i<numOfCities;i++){
            adjList.add(new ArrayList<>());
        }
        //traverse [][] and add to adjList
        for(int [] flight:flights){
            int fromCity=flight[0];
            int toCity=flight[1];
            int cost=flight[2];
            adjList.get(fromCity).add(new Flight(toCity,cost));
        }
        //Step-2 Create tuple and add it to the Queue to start
        //as unit weights so created normal queue in place of priority queue
        Queue<Tuple> queue=new LinkedList<>();
        queue.add(new Tuple(0,source,0));
        //Step-3 Create cost [] and update value to infinity
        int [] costs=new int[numOfCities];
        for(int i=0;i<numOfCities;i++){
            costs[i]=Integer.MAX_VALUE;
        }
        //Step-4 Iterate over Queue and visit adj and update cost[]
        while (!queue.isEmpty()){
            Tuple current = queue.poll();
            int stops = current.totalStops;
            int city = current.currentCity;
            int cost = current.cost;
            if(stops>maxStops) continue;
            //check for adj using loops
            for(Flight flight:adjList.get(city)){
                int adjCity= flight.toCity;
                int newCost=flight.price;
                //check if its valid
                if (cost+newCost<=costs[adjCity]){
                    //lets go thwn
                    costs[adjCity]=cost+newCost;
                    queue.add(new Tuple(stops+1,adjCity,cost+newCost));
                }
            }
        }
        return costs[destination]==Integer.MAX_VALUE? -1 : costs[destination];
    }
    //Question -68 Leet Code 207. Course Schedule
    //Leet Code URL  -> https://leetcode.com/problems/course-schedule/description/
    //The below solution uses TOPO Sort process using BFS technique
    //This algorithm is also called as kanh's Algorithm TODO VVIMP
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        // Step 1: Create adjacency list for the graph
        List<List<Integer>> adj = new ArrayList<>();
        for (int i = 0; i < numCourses; i++) {
            adj.add(new ArrayList<>()); // Initialize each course's list
        }
        // Step 2: Create in-degree array to count prerequisites for each course
        int[] indegree = new int[numCourses];
        // Step 3: Fill the adjacency list and indegree array
        for (int[] pair : prerequisites) {
            int course = pair[0];
            int prereq = pair[1];
            // prereq → course (prerequisite must be done before course)
            adj.get(prereq).add(course);
            indegree[course]++; // course has one more incoming edge
        }
        // Step 4: Start with courses that have no prerequisites (indegree = 0)
        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i < numCourses; i++) {
            if (indegree[i] == 0) {
                queue.offer(i);
            }
        }
        // Step 5: Kahn's algorithm - Topological sort using BFS
        int finishedCourses = 0;
        while (!queue.isEmpty()) {
            int current = queue.poll();
            finishedCourses++;

            // Reduce the indegree of all dependent courses
            for (int neighbor : adj.get(current)) {
                indegree[neighbor]--;
                // If indegree becomes 0, add it to queue (it’s now ready to be taken)
                if (indegree[neighbor] == 0) {
                    queue.offer(neighbor);
                }
            }
        }
        // Step 6: If we finished all courses, there was no cycle and we return True
        return finishedCourses == numCourses;
    }

    //TODO Completed //

}
