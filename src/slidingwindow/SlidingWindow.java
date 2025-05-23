package slidingwindow;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

public class SlidingWindow {

    //LeetCode HARD Series
    //Problem URL https://leetcode.com/problems/sliding-window-maximum/description/
    //This solution works fine if we know value of K
    public void slidingWindowMaximum(){
        int []nums = {1,3,-1,-3,5,3,6,7};
        int windowSize = 3;
        int i=0;
        int j=1;
        int k=2;
        int result[]=new int[nums.length];
        int counter=0;
        while (k!=nums.length){
            if (nums[i]>nums[j]){
                if (nums[i]>nums[k]){
                    result[counter++]=nums[i];
                    i++;
                    j++;
                    k++;
                }else{ result[counter++]=nums[k];
                i++;
                j++;
                k++;}
            } else if (nums[j]>nums[i]) {
                if (nums[j]>nums[k]){
                    result[counter++]=nums[j];
                    i++;
                    j++;
                    k++;
                }else{ result[counter++]=nums[k];
                i++;
                j++;
                k++;}
            } else{ result[counter++]=nums[k];
            i++;
            j++;
            k++;}
        }
        for (int n:result){
            System.out.println(n +"");
        }

    }

    //LeetCode HARD Series
    //Problem URL https://leetcode.com/problems/sliding-window-maximum/description/
    //Optimal Approach for the same above problem
    //This solution works i =f we don't know value of K and its dynamic in nature
    public int[] maxSlidingWindow(int[] nums, int k) {
        List<Integer> res = new ArrayList<>();
        Deque<Integer> deque = new LinkedList<>();

        for (int idx = 0; idx < nums.length; idx++) {
            int num = nums[idx];

            while (!deque.isEmpty() && deque.getLast() < num) {
                deque.pollLast();
            }
            deque.addLast(num);

            if (idx >= k && nums[idx - k] == deque.getFirst()) {
                deque.pollFirst();
            }

            if (idx >= k - 1) {
                res.add(deque.getFirst());
            }
        }

        return res.stream().mapToInt(i -> i).toArray();
    }
}
