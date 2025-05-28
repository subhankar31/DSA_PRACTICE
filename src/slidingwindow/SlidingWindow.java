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
    public int longestOnesOptimizedApproach(int[] nums, int k) {
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
    //Another solution of same above given problem
    //time complexity O(2n)
    public int longestOnesBetterApproach(int[] nums, int k) {
        int left = 0, maxLength = 0, zeroCount = 0;
        for (int right = 0; right < nums.length; ++right) {
            if (nums[right] == 0) {
                zeroCount++;
            }
            while (zeroCount > k) {
                if (nums[left] == 0) {
                    zeroCount--;
                }
                left++;
            }
            maxLength = Math.max(maxLength, right - left + 1);
        }
        return maxLength;
    }
    //Q- Binary SubArray with sum
    //Leet code URl- https://leetcode.com/problems/binary-subarrays-with-sum/description/
    public int numSubarraysWithSum(int[] nums, int goal) {
        return check(nums,goal)-check(nums,goal-1);
        /**
         * Reason behind above step like why we did check(nums,goal)-check(nums,goal-1);
         * Tweek that in this question its asking number of subarrays rather than traditional
         * 2 pointer based question where it asks only the length or subarray size
         Example: Counting people by weight categories
         Imagine you have a group of people, and each person has a specific weight. You want to know how many people weigh exactly 70 kg.

         Step 1: Count all people weighing less than or equal to 70 kg
         This will include people who weigh:

         Less than 70 kg (like 60 kg, 50 kg, etc.)
         Exactly 70 kg
         Let’s say we count all the people who weigh 70 kg or less:

         There are 25 people who weigh 70 kg or less.
         We can represent this as fun(weights, goal=70) = 25.

         Step 2: Count all people weighing less than or equal to 69 kg
         Now, let’s count all the people who weigh 69 kg or less. These are people who weigh:

         Less than 70 kg (like 69 kg, 60 kg, 50 kg, etc.)
         But not those who weigh exactly 70 kg.
         Let’s say there are 18 people who weigh 69 kg or less.

         We can represent this as fun(weights, goal=69) = 18.

         Step 3: Subtract the two results
         To find out how many people weigh exactly 70 kg, we subtract:

         fun(weights, goal=70) (people weighing 70 kg or less) = 25
         fun(weights, goal=69) (people weighing 69 kg or less) = 18
         The number of people who weigh exactly 70 kg is:

         25 - 18 = 7 people. */
    }
    int check(int[] nums,int goal){
        int n=nums.length;
        if(goal<0)
            return 0;
        int l=0,r=0,count=0,sum=0;
        while(r<n){
            sum+=nums[r];
            while(sum>goal){
                sum-=nums[l];
                l++;
            }
            count+=r-l+1;
            r++;
        }
        return count;
    }
    /**
     * Question - Leet Code 1358
     * LeetCode URL -> https://leetcode.com/problems/number-of-substrings-containing-all-three-characters/description/
     * Video tutorial-> https://www.youtube.com/watch?v=xtqN4qlgr8s
     */
    public int numberOfSubstrings() {
        String s="abcabc";
        //Maintaining hash using array to memorize last seen of 3 characters
        int[] lastSeen = {-1, -1, -1};
        int count = 0;

        //Traverse till end
        for (int i = 0; i < s.length(); i++) {
            //update the last seen of every character
            lastSeen[s.charAt(i) - 'a'] = i;
            //if its not -1 that means we got all three characters
            if (lastSeen[0] != -1 && lastSeen[1] != -1 && lastSeen[2] != -1) {
                //this works as the total substring it will generate ending at the position will be
                //count+=minimum Index value + 1
                //try using pen and paper
                count += 1 + Math.min(lastSeen[0], Math.min(lastSeen[1], lastSeen[2]));
            }
        }
        return count;


    }

    /**
     * Question -> Leet Code 1423. Maximum Points You Can Obtain from Cards
     * Leet Code URL -> https://leetcode.com/problems/maximum-points-you-can-obtain-from-cards/description/
     */

    public void sumCheck(){
        int [] nums={1,2,3,4,5,6,1};
        int k=3;
        int left=0;
        int right=nums.length-1;
        int maxSum=0;
        while (left<=right){
        }
    }
}
