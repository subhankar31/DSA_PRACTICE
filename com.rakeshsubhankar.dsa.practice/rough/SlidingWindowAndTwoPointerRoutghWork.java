package rough;

import javax.swing.plaf.IconUIResource;
import java.util.HashMap;
import java.util.Map;

public class SlidingWindowAndTwoPointerRoutghWork {

    //Longest Substring without repeatating characters
    //https://leetcode.com/problems/longest-substring-without-repeating-characters/description/
    public int lengthOfLongestSubstring(String s) {
        //craete map to know char and its position
        Map<Character,Integer> mpp=new HashMap<>();
        int l=0;
        int r=0;
        int maxLength=0;
        while (r < s.length()){
            //check if we encounter repeated char then move left
            if (mpp.containsKey(s.charAt(r))){
                l=Math.max(l,s.charAt(r)+1);
            }
            //put r every time
            mpp.put(s.charAt(r),r);
            //calculte maxlength
            maxLength=Math.max(maxLength,r-l+1);
            r++; //move right
        }
        return maxLength;
    }
    //Max Consecutive Ones
    //https://leetcode.com/problems/max-consecutive-ones-iii/description/
    public int longestOnes(int[] nums, int k) {
        int left=0;
        int maxLength=0;
        int zeroCount=0;
        for (int right=0; right < nums.length; right++){
            if (nums[right]==0) zeroCount++;
            //check if count is more than k then reduce and move left
            while (zeroCount > k){
                if (nums[left]==0){
                    zeroCount--;
                }
                left++;
            }
            //finally calculate max size
            maxLength=Math.max(maxLength,right-left+1);
        }
        return maxLength;
    }
    //Longest repeating character replacement
    //https://leetcode.com/problems/longest-repeating-character-replacement/
    public int characterReplacement(String s, int k) {
        int maxLength=0;
        //loop from A to Z
        for (char c='A';c<='Z';c++){
            int i=0; //left
            int j=0; //right
            int replaced=0;
            while (j<s.length()){
                if (s.charAt(j)==c){
                    j++;
                }else if (replaced < k){
                    j++;
                    replaced++;
                }else if (s.charAt(i)==c){
                    i++;
                }else {
                    i++;
                    replaced--;
                }
            }
            maxLength=Math.max(maxLength,j-1);
        }
        return maxLength;
    }
    //Binary Subarray with Sum
    //https://leetcode.com/problems/binary-subarrays-with-sum/description/
    public int numSubarraysWithSum(int[] nums, int goal) {
        return numSubarraysLessThanSum(nums,goal)-numSubarraysLessThanSum(nums,goal-1);
    }
    public int numSubarraysLessThanSum(int [] nums, int goal){
        if (goal < 0) return 0;
        int l=0;
        int r=0;
        int count=0;
        int sum=0;
        while (r<nums.length){
            sum+=nums[r];
            while (sum > goal){
                sum-=nums[l];
                l++;
            }
            count+=r-l+1;
            r++;
        }
        return count;
    }
    //count number of nice subarray
    //https://leetcode.com/problems/count-number-of-nice-subarrays/
    public int numberOfSubarrays(int[] nums, int k) {
        //we will solve in similar technique of at most k odds - at most k-1 odds
        return atMostkodds(nums,k)-atMostkodds(nums,k-1);
    }
    public int atMostkodds(int [] nums, int k){
        int l=0;
        int r=0;
        int sum=0;
        int count=0;
        while (r < nums.length){
            if (nums[r] % 2 == 1){
                count++;
            }
            while (count > k){
                if (nums[l] % 2 == 1){
                    count--;
                }
                l++;
            }
            sum+=r-l+1;
        }
        return sum;
    }
    //N.O of substring containing all 3 chars A,B,C
    //https://leetcode.com/problems/number-of-substrings-containing-all-three-characters/
    public int numberOfSubstrings(String s) {
        //delare array for A,B,C latest position track
        int [] mpp={-1,-1,-1};
        int count=0;
        for (int i=0;i<s.length();i++){
            mpp[s.charAt(i)-'a']=i;
            if (mpp[0] != -1 && mpp[1]!=-1 && mpp[2]!=-1){
                int minIndex=Math.min(mpp[0],Math.min(mpp[1],mpp[2]));
                count+=1+minIndex;
            }
        }
        return count;
    }

    //Maximum point from cards
    //https://leetcode.com/problems/maximum-points-you-can-obtain-from-cards/
    public int maxScore(int[] cardPoints, int k) {
        int totalSum=0;
        int lSum=0;
        int rSum=0;
        for(int i=0;i<k;i++){
            lSum+=cardPoints[i];
        }
        totalSum=lSum;
        int rightIndex=cardPoints.length-1;
        for (int i=k;i>0;i--){
            //reduce lSum
            lSum-=cardPoints[i];
            rSum+=cardPoints[rightIndex];
            rightIndex++;
            totalSum=Math.max(totalSum,lSum+rSum);
        }
        return totalSum;
    }
    //HARD SERIES
    //SubArrays with k different integers
    //https://leetcode.com/problems/subarrays-with-k-different-integers/
    public int subarraysWithKDistinct(int[] nums, int k) {
        return subArrayLessthanKDistinct(nums,k)-subArrayLessthanKDistinct(nums,k-1);
    }
    public int subArrayLessthanKDistinct(int [] nums,int k){
        //declare a hashmap to memoize distinct integers
        Map<Integer,Integer> mpp=new HashMap<>();
        int left=0;
        int right=0;
        int total=0;
        while (right < nums.length){
            //increase right in map
            mpp.put(nums[right],mpp.getOrDefault(nums[right],0)+1);
            //check if map size is more than k
            if (mpp.size() > k){
                //first reduce the element of left
                mpp.put(nums[left],mpp.get(nums[left])-1);
                //remove only if its 0
                if (mpp.get(nums[left])==0){
                    mpp.remove(nums[left]);
                }
                left++;
            }
            total+=right-left+1;
            right++;
        }
        return total;
    }
    //Minimum Window Substring
    //https://leetcode.com/problems/minimum-window-substring/
    public String minWindow(String s, String t) {
        int l=0;
        int r=0;
        int minLen=Integer.MAX_VALUE;
        int n=s.length();
        int m=t.length();
        int count=0;
        int sIndex=-1;
        //create hash and add all element of t
        int [] hash=new int[256];
        //traverse t and increase hash
        for (int i=0;i<m;i++){
            hash[t.charAt(i)]++;
        }
        while (r < n){
            if (hash[s.charAt(r)]>0){
                count++;
            }
            r++;
            //shrink left pointer
            while (count == m){
                if (r-l < minLen){
                    minLen=r-l;
                    sIndex=l;
                }
                hash[s.charAt(l)]++;
                if (hash[s.charAt(l)] > 0){
                    count--;
                }
                l++;
            }
        }
        return sIndex == -1 ? null : s.substring(sIndex,sIndex+minLen);

    }











    public static void main(String[] args) {

    }
}
