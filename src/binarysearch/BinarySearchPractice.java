package binarysearch;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

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
    /**
     * BOOK ALLOCATION PROBLEM
     * BINARY SEARCH HARD QUESTION BASED ON MIN MAX PATTERN
     * Article url - https://takeuforward.org/data-structure/allocate-minimum-number-of-pages/
     */
        public static int countStudents(ArrayList<Integer> arr, int pages) {
            int n = arr.size(); // size of array
            int students = 1;
            long pagesStudent = 0;
            for (int i = 0; i < n; i++) {
                if (pagesStudent + arr.get(i) <= pages) {
                    // add pages to current student
                    pagesStudent += arr.get(i);
                } else {
                    // add pages to next student
                    students++;
                    pagesStudent = arr.get(i);
                }
            }
            return students;
        }

        public static int findPages() {
            ArrayList<Integer> arr = new ArrayList<>(Arrays.asList(25, 46, 28, 49, 24));
            int n = arr.size();
            int m = 4;
            // book allocation impossible
            if (m > n)
                return -1;
            int low = Collections.max(arr);
            int high = arr.stream().mapToInt(Integer::intValue).sum();
            while (low <= high) {
                int mid = (low + high) / 2;
                int students = countStudents(arr, mid);
                if (students > m) {
                    low = mid + 1;
                } else {
                    high = mid - 1;
                }
            }
            return low;
        }
        /**
         * Question Painters problem or LeetCode 410 Split Array Largest Sum
         * Leet Code URL - > https://leetcode.com/problems/split-array-largest-sum/description/
         */
        public int splitArray(int[] nums, int k) {
            ArrayList<Integer> inputArray=new ArrayList<>();
            for (int num:nums)inputArray.add(num);
            int low=Collections.max(inputArray);
            int high=inputArray.stream()
                    .mapToInt(Integer::intValue).sum();
            while (low<=high){
                int mid=(low+high)/2;
                int subArray = calculatePossible(inputArray, mid);
                if (subArray > k) {
                    low = mid + 1;
                } else {
                    high = mid - 1;
                }
            }
            return low;
        }
        int calculatePossible(ArrayList<Integer> nums,int mid){
            int subArraay=1;
            int sum=0;
            for (int i=0;i<nums.size();i++){
                if (sum+nums.get(i)<=mid){
                    sum+=nums.get(i);
                }else{
                    subArraay++;
                    sum=nums.get(i);
                }
            }
            return subArraay;
        }
}
