package dsapatternsseventyquestions;

import dsapatternsseventyquestions.util.Points;

import java.util.*;

public class HeapsPattern {

    //Question -59 Leet Code 215. Kth Largest Element in an Array
    //Leet Code URL  ->https://leetcode.com/problems/kth-largest-element-in-an-array/description/
    //Brute 72 ms in Leet Code
    public int findKthLargest(int[] nums, int k) {
        //created priority queue which is mean heap in nature
        //as we need max heap so provided comparator
        Queue<Integer> queue=new PriorityQueue<>(Comparator.reverseOrder());
        for(int num:nums){
            queue.add(num);
        }
        for(int i=1;i<=k;i++){
            int res= queue.poll();
            if (i==k) return res;
        }
        return -1;
    }
    //Optimized as we don't need extra comparator to reverse
    //Why This Works :
    //The heap always contains the k largest elements seen so far.
    //The smallest of these k is the k-th largest overall — and that’s at the top of the min-heap.


    public int findKthLargestOptimized(int[] nums, int k) {
        PriorityQueue<Integer> minHeap = new PriorityQueue<>(); // default is min-heap

        for (int num : nums) {
            minHeap.offer(num);
            if (minHeap.size() > k) {
                minHeap.poll(); // remove smallest to keep only k largest
            }
        }
        return minHeap.peek(); // k-th largest element
    }

    //Question -60 Leet Code 973. K Closest Points to Origin
    //Leet Code URL  -> https://leetcode.com/problems/k-closest-points-to-origin/description/
    //TODO need to practice code as it involves [][] operations
    /**
     * The problem reduces to calculating the distance of each point from the origin (0, 0).
     * The points closest to the origin will have the smallest squared distances.
     * Using a PriorityQueue / MinHeap allows us to efficiently pick the k closest points.
     * 1️⃣ Calculate distance squared (no need for square root, it preserves ordering).
     * 2️⃣ Store each point in a PriorityQueue (or any structure that gives smallest first).
     * 3️⃣ Extract the top k points from the queue to get the closest points.
     * Time Complexity:O(nlogn)
     * Insertion of n points into the PriorityQueue takes O(log n) each.
     * Space Complexity:O(n)
     * PriorityQueue stores n elements.
     */
    public int[][] kClosest(int[][] points, int k) {
        PriorityQueue<Points> pq = new PriorityQueue<>(); //mean heap
        //traverse and all
        for(int i=0 ; i<points.length ; i++)
        {
            int distSq = points[i][0]*points[i][0] + points[i][1]*points[i][1];
            pq.add(new Points(points[i][0], points[i][1], distSq, i));
        }
        int ans[][] = new int[k][2];
        for(int i=0 ; i<k ; i++)
        {
            ans[i][0] = pq.peek().x;
            ans[i][1] = pq.peek().y;
            pq.remove();
        }
        return ans;
    }
    //Question -61 Leet Code 347. Top K Frequent Elements
    //Leet Code URL  -> https://leetcode.com/problems/top-k-frequent-elements/description/

    public int[] topKFrequent(int[] nums, int k) {
        Map<Integer, Integer> freqMap = new HashMap<>();
        // Count frequencies
        for (int num : nums) {
            freqMap.put(num, freqMap.getOrDefault(num, 0) + 1);
        }
        // Min-heap of size k TODO Tricky part be careful
        PriorityQueue<Map.Entry<Integer, Integer>> minHeap =
                new PriorityQueue<>((a, b) -> a.getValue() - b.getValue());
        // Keep top k frequent elements in the heap
        for (Map.Entry<Integer, Integer> entry : freqMap.entrySet()) {
            minHeap.offer(entry);
            if (minHeap.size() > k) {
                minHeap.poll(); // remove least frequent
            }
        }
        // Extract results from heap
        //As per question we can return in any order so we directly took mean heap top k elements
        //and returndd , if it ask order then we need to get one by one values from end side
        int[] result = new int[k];
        int i = 0;
        while (!minHeap.isEmpty()) {
            result[i++] = minHeap.poll().getKey();
        }
        return result;
    }
    //Question -62 Leet Code 621. Task Scheduler
    //Leet Code URL  -> https://leetcode.com/problems/task-scheduler/
    //TODO Pending , did n't understood the Heap based solution ,Need to watch other resources
    /**
     * This solution is not the Optimized solution but it uses Heap technique
     * Idea Behind the Heap-Based Approach
     * Use a max-heap to always pick the task with the highest remaining frequency.
     * Use a queue to store tasks during their cooldown period (n units).
     * Each time unit:
     * Execute the most frequent available task.
     * Decrease its frequency and put it in cooldown (if it's not done).
     * If tasks in cooldown finish their cooldown, push them back into the heap.
     * Repeat until all tasks are done.
     */



    /**
     *This below solution is the most Optimized but it uses Greedy Approach not heap
     * Dry Run
     * tasks = ['A', 'A', 'A', 'B', 'B', 'C'], n = 2
     * Step 1: Count frequencies
     * | Task | Count |
     * | ---- | ----- |
     * | A    | 3     |
     * | B    | 2     |
     * | C    | 1     |
     * So: maxFreq = 3 (task A) and maxFreqCount = 1 (only task A has maxFreq)
     * Step 2: Apply Formula
     * (min time) = (maxFreq - 1) * (n + 1) + maxFreqCount
     *            = (3 - 1) * (2 + 1) + 1
     *            = 2 * 3 + 1 = 7
     * So the minimum time needed = 7
     * But we also check tasks.length = 6
     * So final result = max(7, 6) = 7
     * Visual Scheduling (for understanding)
     * Time Slot:   1   2   3   4   5   6   7
     * Task:        A   B   C   A   B   idle A
     * All tasks scheduled in 7 time units, no more, no less.
     * The greedy formula builds this layout mathematically, no simulation or heap needed.
     * TODO Need practice to recall the process during exams
     */
    public int leastIntervalOptimized(char[] tasks, int n) {
        int[] freq = new int[26];
        // Count frequency of each task
        for (char task : tasks) {
            freq[task - 'A']++;
        }
        // Find max frequency
        int maxFreq = 0;
        for (int f : freq) {
            maxFreq = Math.max(maxFreq, f);
        }
        // Count how many tasks have max frequency
        int maxFreqCount = 0;
        for (int f : freq) {
            if (f == maxFreq) {
                maxFreqCount++;
            }
        }
        // Calculate the idle frame structure:
        // (maxFreq - 1) full intervals, each with (n + 1) slots
        // + maxFreqCount tasks in the last row (all same max frequency)
        int partCount = maxFreq - 1;
        int partLength = n + 1;
        int emptySlots = partCount * partLength + maxFreqCount;

        // Result is either the length of tasks or the full empty frame
        return Math.max(emptySlots, tasks.length);
    }











}
