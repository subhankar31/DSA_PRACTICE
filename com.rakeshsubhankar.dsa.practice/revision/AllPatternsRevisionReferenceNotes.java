package revision;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class AllPatternsRevisionReferenceNotes {
    /**
     * This class will be used to revise all the patterns chapter wise
     */
    //Q-1 https://leetcode.com/problems/rotate-array/description/
    public void rotate(int[] nums, int k) {
        int kValue=k%nums.length;
        kValue=nums.length-kValue;
        reverse(nums,0,kValue-1);
        reverse(nums,kValue, nums.length-1);
        reverse(nums,0, nums.length-1);


    } void reverse(int [] nums, int i,int j){
        while (i<=j){
            int temp=nums[i];
            nums[i]=nums[j];
            nums[j]=temp;
            i++;
            j--;
        }
    }
    //Q-2 Spiral matrix
    public List<Integer> spiralOrder(int[][] mat) {
        List<Integer> res=new ArrayList<>();
        int n = mat.length; // no. of rows
        int m = mat[0].length; // no. of columns

        // Initialize the pointers required for traversal.
        int top = 0, left = 0, bottom = n - 1, right = m - 1;

        while (top <= bottom && left <= right){
            //left to right
            for(int i=left;i<=right ;i++){
                res.add(mat[top][i]);
            }
            left++;

            //top to botom
            for(int i=top;i<=bottom;i++) {
                res.add(mat[i][right]);
            }
            right--;
            //right to left
            if(top <=bottom){
                for (int i=right;i<=left;i++){
                    res.add(mat[bottom][i]);
                }
                bottom--;
            }
            //bottom to top
            if(left <= right ){
                for (int i=bottom;i>=top;i--){
                    res.add(mat[i][left]);
                }
                left++;
            }
        }

        return res;
    }
    //Q-3 Merge Intervals
    public int[][] merge(int[][] intervals) {
        List<int[]> res=new ArrayList<>();
        Arrays.sort(intervals,(a,b)->Integer.compare(a[0],b[0]));

        //merge part
        int [] curr= intervals[0];
        for(int i=1;i<intervals.length;i++){
            if(intervals[i][1]<=curr[1]){
                //merge
                curr[1]=Math.max(curr[1],intervals[i][1]);
            }else{
                res.add(curr);
                curr=intervals[i];
            }
        }
        res.add(curr);

        return res.toArray(new int[res.size()][]);

    }

    //Q-4
    public int longestOnes(int[] nums, int k) {
        int maxSize=0;
        int zeroCount=0;
        int left=0;
        for(int right=0;right<nums.length;right++){
            if(nums[right]==0){
                zeroCount++;
            }
            while(zeroCount>k){
                if(nums[left]==0){
                    zeroCount--;
                }
                left++;
            }
            maxSize=Math.max(maxSize,right-left+1);
        }
        return maxSize;
    }
    //Q-5
    public int lengthOfLongestSubstring(String s) {
        HashMap<Character,Integer> map=new HashMap<>(); // stores char , index
        int maxLen=0;
        int left=0;
        int right=0;
        int n=s.length();
        while (right<=n){
            if (map.containsKey(s.charAt(right))) left=Math.max(left,map.get(s.charAt(right)+1));

            map.put(s.charAt(right),right);

            maxLen=Math.max(maxLen,right-left+1);
            right++;
        }
        return maxLen;
    }
    //Q-6
    public double findMaxAverage(int[] nums, int k) {
        int sum=0;
        int maxSum=0;
        for(int i=0;i<k;i++){
            sum+=nums[i]; //sum of first window
        }
        maxSum=sum;
        for(int i=k;i< nums.length;i++){
            sum+=nums[i]-nums[i-k];
            maxSum=Math.max(maxSum,sum);
        }
        return (double) maxSum/k;
    }
    //Q-7
    public int maxArea(int[] height) {
        int left=0;
        int right=height.length-1;
        int maxWater=0;
        while(left<=right){
            int currWater=(right-left)*Math.min(height[left],height[right]);
            maxWater=Math.max(maxWater,currWater);
            if (height[left]<=height[right]){
                left++;
            }else right--;
        }
        return maxWater;
    }
    //Q-8
    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> res=new ArrayList<>();
        Arrays.sort(nums);
        for(int i=0;i<nums.length-2;i++){
            if (nums[0]>0) return res;

            if(i!=0 && nums[i]==nums[i-1]){
                continue;
            }
            //create 2 pointers
            int left=i+1;
            int right= nums.length-1;
            while (left<=right){
                int sum=nums[i]+nums[left]+nums[right];
                if(sum>0) right--;
                else if (sum<0) {
                    left++;
                }else {
                    res.add(Arrays.asList(nums[i],nums[left],nums[right]));

                    while (left<right && nums[left]==nums[left+1]){
                        left++;
                    }
                    while (left<right && nums[right]==nums[right-1]){
                        right--;
                    }
                    left++;
                    right--;
                }
            }
        }
        return res;

    }
    //Q-9
    public static void main(String[] args) {

    }
}
