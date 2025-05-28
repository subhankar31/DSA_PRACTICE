package binarysearch;

public class BinarySearchPractice {

    /**
     * Question - FIND PEAK ELEMENT
     * LeetCode URL- https://leetcode.com/problems/find-peak-element/description/
     * Solved using binary search algorithm
     * Time Complexity O(log n)
     */
    public int findPeakElement() {
        int[] nums={1,2,3,1};
        int left=1;
        int right= nums.length-2;
        //if array has a single element
        if (nums.length==1) return 0;
        //if first or last element is peak
        if (nums[0]>nums[1]) return 0;
        if (nums[nums.length-1]>nums[nums.length-2]) return nums.length-1;

        //Iterate over
        while (left<=right){
            int mid=(left+right)/2;
            //check if its peak element
            if (nums[mid]>nums[mid-1] && nums[mid]>nums[mid+1]) return mid;
            //check if we are on left part i.e increasing part
            if (nums[mid]>nums[mid-1]) left=mid+1;
            else right=mid-1;

        }
        //didn't find anything
        return -1;
    }
}
