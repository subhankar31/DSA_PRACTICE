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

    //One more solution using 2 pointer where K is dynamic

    public void maxSlidingWindowUsing2Pointer(int arr[], int k) {
        int left = 0, right = 0;
        int i, j;
        ArrayList < Integer > maxx = new ArrayList < > ();
        //moving second pointer to place at k position
        while (right < k - 1) {
            right++;
        }
        //traversing till end as right will reach the end first
        while (right < arr.length) {
            GetMax(arr, left, right, maxx);
            left++;
            right++;
        }
        //Result stored inside  maxx;
    }
    //method to give max value in that window
    static void GetMax(int arr[], int l, int r, ArrayList < Integer > maxx) {
        int i, maxi = Integer.MIN_VALUE;
        for (i = l; i <= r; i++)
            maxi = Math.max(maxi, arr[i]);
        maxx.add(maxi);
    }
    /**
    Q- 1004. Max Consecutive Ones III
    Leet Code link - https://leetcode.com/problems/max-consecutive-ones-iii/
     The below solution usese 2 pointer approach and solves it using O(n) time complexity
     We can have other 2 pointer solution which uses o(2n) complexity
     */
    public int longestOnes(int[] nums, int k) {
        int start=0;
        int end=0;
        int zeros=0;

        //Move end pointer till end
        while(end<nums.length){
            //increase 0 once you encounter 0
            if(nums[end] == 0){
                zeros++;
            }
            end++;
            //if xeros overflow then move left untill zeros are less than K
            if(zeros>k){
                if(nums[start] == 0){
                    zeros--;
                }
                start++;
            }
        }
        //calculate the length of substring
        return end-start;
    }
}
