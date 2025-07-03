package dsapatternsseventyquestions;

import java.util.*;

public class ArrayPattern {

    //Question-1 EASY detect duplicate
    //LeetCode URL -> https://leetcode.com/problems/contains-duplicate/
    //Best Solution
    public boolean containsDuplicate1(int[] nums) {
        Set<Integer> set = new HashSet<>();

        for (int i = 0; i < nums.length; i++) {
            int number = nums[i];
            if (set.contains(number)) {
                return true;
            }
            set.add(number);
        }
        return false;
    }

     public boolean containsDuplicate2(int[] nums) {
        Set<Integer> set=new HashSet<>();
        for(int num:nums){
            set.add(num);
        }
     return set.size()!=nums.length;

     }

     public boolean containsDuplicate3(int[] nums) {
        Arrays.sort(nums); //TC- O(long n)
        boolean flag=false;
        for(int i=0;i<nums.length-1;i++){ //T.C O(n)
            if(nums[i]==nums[i+1]){
                flag=true;
             }
        }
        return flag;
     }
     //Question 2 EASY Find Missing NUmber from 0 -> n
    //Logic :
    //You're adding i (the expected value)
    //Subtracting nums[i] (the actual value)
    //So you're effectively computing:
    //res = n + (0 + 1 + 2 + ... + (n - 1)) - (sum of array values)
     public int missingNumber(int[] nums) {
         int res = nums.length;         // Initialize res with 'n'

         for (int i = 0; i < nums.length; i++) {
             res += i - nums[i];        // Adjust res using index and value
         }

         return res;
     }
     //Question-3 Find all missing Numbers/Dissapared Numbers
    //LeetCode URl-> https://leetcode.com/problems/find-all-numbers-disappeared-in-an-array/
     public List<Integer> findDisappearedNumbers(int[] nums) {
         Set<Integer> seen = new HashSet<>();
         for (int num : nums) {
             seen.add(num);
         }

         List<Integer> result = new ArrayList<>();
         for (int i = 1; i <= nums.length; i++) {
             if (!seen.contains(i)) {
                 result.add(i);
             }
         }

         return result;

     }
     //Question - 4 Leet Code Two Sum
    //Leet Code URL -> https://leetcode.com/problems/two-sum/submissions/1685063785/
     public int[] twoSum(int[] nums, int target) {
         int[] result=new int[2];
         Map<Integer,Integer> hash=new HashMap<>(); //key->index ,Value->value
         for(int i=0;i<nums.length;i++){
             if(hash.containsKey(target-nums[i])){
                 result[0]=i;
                 result[1]=hash.get(target-nums[i]);
             }
             else hash.put(nums[i],i);
         }
         return result;
     }
     //Question -5 how-many-numbers-are-smaller-than-the-current-number
    //Leet Code URL - > https://leetcode.com/problems/how-many-numbers-are-smaller-than-the-current-number/description/

    public int[] smallerNumbersThanCurrent(int[] nums) {
        int [] temp=Arrays.copyOf(nums, nums.length); //This will make a copy
        Arrays.sort(temp);
        Map<Integer,Integer> hash=new HashMap<>();
        for(int i=0;i<temp.length;i++){
            if(!hash.containsKey(temp[i])){
                hash.put(temp[i],i);
            }
        }
        int [] res=new int[nums.length];
        for(int i=0;i<nums.length;i++){
            res[i]=hash.get(nums[i]);
        }
        return res;
    }

    //Question -6  Minimum Time Visiting All Points
    //Leet Code URL - > https://leetcode.com/problems/minimum-time-visiting-all-points/
    public int minTimeToVisitAllPoints(int[][] points) {
        int res=0;
        for (int i = 0; i < points.length - 1; i++) {
            int x1 = points[i][0];
            int y1 = points[i][1];

            int x2 = points[i + 1][0];
            int y2 = points[i + 1][1];
            res+=Math.max(Math.abs(y2-y1),Math.abs(x2-x1));
        }
        return res;
    }


    public static void main(String[] args) {
        ArrayPattern arrayPattern=new ArrayPattern();

    }
}
