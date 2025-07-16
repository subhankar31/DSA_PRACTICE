package dsapatternsseventyquestions;

import java.util.LinkedList;
import java.util.Queue;

public class QueuePattern {
    //Question -37 Leet Code 225. Implement Stack using Queues
    //Leet Code URL - > https://leetcode.com/problems/implement-stack-using-queues/description/

    public void myStack() {
        Queue<Integer> q = new LinkedList<>();
    }
    public void push(int x,Queue<Integer> q) {
        q.add(x);
        for (int i = 1; i < q.size(); i++) {
            q.add(q.remove());
        }
    }
    public int pop(Queue<Integer> q) {
        return q.remove();
    }
    public int top(Queue<Integer> q) {
        return q.peek();
    }
    public boolean empty(Queue<Integer> q) {
        return q.isEmpty();
    }
    //Question -38 Leet Code 2073. Time Needed to Buy Tickets
    //Leet Code URL - > https://leetcode.com/problems/time-needed-to-buy-tickets/description/

    /**
     Step-by-step Explanation:
     Initialize a queue with all people's indices (0 to n-1) ➡️ represents their positions in line.
     Keep a counter cnt to track total seconds passed ⏱️.
     Loop until the person at index k buys all their tickets:
     Pop the front person from the queue.
     Let them buy 1 ticket (reduce their ticket count).
     If person k has completed buying all tickets after this, break the loop.
     If the person still has tickets left, push them to the back of the queue 🔁.
     Increment time by 1.
     Finally, return cnt + 1 to count the last second when k bought their final ticket ✅.
     Dry Run to get more idea
     TC - 9ms in Leet Code and can be optimized to 0ms
     */

    public int timeRequiredToBuy(int[] arr, int k) {
        Queue<Integer> q = new LinkedList<>();
        int n = arr.length;
        for(int i = 0; i < n; i++) {
            q.add(i);
        }
        int cnt = 0;
        while(arr[k] != 0) {
            int idx = q.remove();
            arr[idx] -= 1;
            if(arr[k] == 0) {
                break;
            }
            if(arr[idx] != 0) {
                q.add(idx);
            }
            cnt++;
        }
        return cnt+1;
    }
    //Question -39 Leet Code -> Not available
    //Leet Code URL - > Reverse first K elements



}
