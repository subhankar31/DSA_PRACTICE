package dsapatternsseventyquestions;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class RoughActivity {
    public int missingNumber(int[] nums) {
        int res = nums.length;         // Initialize res with 'n'

        for (int i = 0; i < nums.length; i++) {
            res += i - nums[i];        // Adjust res using index and value
        }

        return res;
    }
    public List<String> letterCasePermutation(String s) {
        List<String> outputList=new ArrayList<>();
        for (int i=0;i<s.length();i++) {
            char ch = s.charAt(i);
            if ((ch >= 'a' && ch <= 'z') || (ch >= 'A' && ch <= 'Z')) {
                if ((ch >= 'a' && ch <= 'z')) {
                    outputList.add(s.substring(0, i) + ch + s.substring(i + 1));
                }
                if ((ch >= 'A' && ch <= 'Z')) {
                    outputList.add(s.substring(0, i) + ch + s.substring(i + 1));
                }
            }
        }

        return outputList;
    }
    public  String reverseWithStringBuilder(String str) {
       return new StringBuilder(str).reverse().toString();

    }
    public void rotate(int[] nums, int k) {
        /**
         * Normal array rotation will be of left but given question asks for right rotation
         * Input: nums = [1,2,3,4,5,6,7], k = 3
         * Output: [5,6,7,1,2,3,4]
         */
        int n=nums.length;
        k=k%n;
        k=n-k; //only for right shift
        reverse(nums,0,k-1);
        reverse(nums,k,n-1);
        reverse(nums,0,n-1);

    }
    void reverse(int [] nums, int i, int j){
        int n=nums.length;
        while (i<=j){
            int temp=nums[j];
            nums[j]=nums[i];
            nums[i]=temp;
            i++;
            j--;
        }

    }
    public List<Integer> spiralOrder(int[][] mat) {
        List<Integer> res=new ArrayList<>();
        int n=mat.length;
        int m=mat[0].length;
        int top=0;
        int bottom=n-1;
        int left=0;
        int right=m-1;
        while (top<=bottom && left <= right){

            //left to right
            for (int i=left;i<=right;i++){
                res.add(mat[top][i]);
            }
            top++;
            //top to bottom
            for (int i=top;i<=bottom;i++){
                res.add(mat[i][right]);
            }
            right--;
            //right to left
            //extra check
            while (top<=bottom) {
                for (int i = right; i >= left; i--) {
                    res.add(mat[bottom][i]);
                }
                bottom--;
            }
            //bottom to up
            //extra check
            while (left<=right) {
                for (int i = bottom; i >= top; i--) {
                    res.add(mat[i][left]);
                }
                left++;
            }

        }
        return  res;
    }
    public int[][] merge(int[][] intervals) {
        List<int[]> result = new ArrayList<>();
        Arrays.sort(intervals,(a,b)->Integer.compare(a[0],b[0]));
        int []prev=intervals[0];
        for (int i=1;i<intervals.length;i++){
            if (intervals[i][0]<=prev[1]){
                prev[1]=Math.max(prev[1],intervals[i][1]);
            }else{
                result.add(prev);
                prev=intervals[i];
            }
        }
        //if remaining
        result.add(prev);
        return result.toArray(new int[result.size()][]);
    }
    //Sliding window
    public int longestOnes(int[] nums, int k) {
        int left=0;
        int zeroCount=0;
        int maxLength=0;
        for(int right=0;right<nums.length;right++){
            if (nums[right]==0){
                zeroCount++;
            }
            while (zeroCount>k){
                if (nums[left]==0){
                    zeroCount--;
                }
                left++;
            }
            maxLength=Math.max(maxLength,right-left+1);
        }
        return maxLength;
    }
    public int lengthOfLongestSubstring(String s) {
        HashMap<Character,Integer> mpp=new HashMap<>();
        int left=0;
        int maxLength=0;
        int right=0;
        while (right<s.length()){
            if (mpp.containsKey(s.charAt(right))){
                left=Math.max(left,mpp.get(s.charAt(right)));
            }
            mpp.put(s.charAt(right),right);
            maxLength=Math.max(maxLength,right-left+1);
            right++;
        }
        return  maxLength;
    }
    //fixed size sliding window technique
    public double findMaxAverage(int[] nums, int k) {
        int sum=0;
        int maxSum=0;
        //calculate sum till k size
        for (int i=0;i<k;i++){
            sum+=nums[i];
        }
        //slide from k -> n
        for (int i=k;i<nums.length;i++){
            //add new and remove from front
            sum+=nums[i]-nums[i=k];
            maxSum=Math.max(maxSum,sum);
        }
        return maxSum/k;

    }

    public static void main(String[] args) {
        //Create obj and test || Copy methods from any series and modify test have fun
        RoughActivity rough=new RoughActivity();
        rough.letterCasePermutation("a1b2");
    }
}
