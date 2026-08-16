package rough;

import java.util.*;

public class ArrayRoughWork {
    //Q-1 check if array is sorted and rotated

    boolean checkIfSorted(int [] nums){
        int oneTimeAllowed=0;
        for (int i=0;i<nums.length;i++){
            if ( nums[i] > nums[(i+1)%nums.length]) oneTimeAllowed++;
        }
        return oneTimeAllowed > 1 ? false : true;
    }
    // TODO Remove duplicastes from sorted Array
    public int removeDuplicates(int[] nums) {

        return 0;
    }

    //Right rotate arr by k place
    //also cover left rotate array
    public void rotate(int[] nums, int k) {
        int n=nums.length;
        //reduce the size if k >n
        k=n%k;
        //if its right shift only
        k=n-k;
        //do reverse ops
        reverse(nums,0,k-1);
        reverse(nums,k,n-1);
        reverse(nums,0,n-1);
    }
    void reverse(int [] nums,int start , int end){
        while (start<=end){
            int temp=nums[start];
            nums[start]=nums[end];
            nums[end]=temp;
            start++;
            end--;
        }
    }
    //Q Move zeros to end
    //brute -count n.o of 0 then add these towards end
    //optimized - do it in one go
    public void moveZeroes(int[] nums) {
        int snowBallSize=0;
        for (int i=0;i<nums.length;i++){
            if (nums[i]==0) snowBallSize++;
            else if (snowBallSize>0) {
                int t = nums[i]; //take non zero element
                nums[i]=0;//add zero to current element as a shift operation
                nums[i-snowBallSize]=t;
            }
        }
    }
    //max consecutive 1
    public int maxOnes(int [] nums){
        int count=0;
        int maxOne=0;
        for (int n:nums){
            if (n==1) {
                count++;
                maxOne=Math.max(maxOne,count);
            }else count=0;
        }
        return maxOne;
    }
    //find single number optimized
    public int singleNumber(int nums[]){
        int xor=0;
        for (int n:nums){
            xor=xor^n;
        }
        return xor;
    }
    //2 sum problem optimized
    public int[] twoSum(int[] nums, int target) {
        int [] res=new int[2];
        Map<Integer,Integer> mpp=new HashMap<>(); // value-->index
        for (int i=0;i<nums.length;i++){
            if (mpp.containsKey(target-nums[i])){
                res[0]=i;
                res[1]=mpp.get(target-nums[i]);
            }
            mpp.put(nums[i],i);
        }

        return res;
    }
    //sort colors of 0,1,2 using Duetch national flag
    public void sortColors(int[] nums) {
        int low=0; //place for 0
        int mid=0; //place for 1
        int high=nums.length-1; //place for 2

        //use mid for traversal
        while (mid<=high){
            if (nums[mid]==0){
                //move left
                int temp=nums[low];
                nums[low]=nums[mid];
                nums[mid]=temp;
                mid++;
                low++;
            }else if (nums[mid]==1) mid++;
            else{
                int temp=nums[high];
                nums[high]=nums[mid];
                nums[mid]=temp;
                high--;
            }
        }
    }
    //majority element
    public int majorityElement(int[] nums) {
        int el=0;
        int count=0;
        for (int num:nums){
            if (count == 0 && el==0){
                count++;
                el=num;
            }else if (num==el) count++;
            else count--;
        }
        //cross check
        count=0;
        for (int num:nums){
            if (num==el)count++;
        }
        return count>= (nums.length/2) ? el : -1;
    }
    //maximum subarray
    //kadanes algo
    public int maxSubArray(int[] arr) {
        int count=0;
        int max=Integer.MIN_VALUE;
        for(int num:arr){
            count+=num;
            max=Math.max(max,count);
            if (count < 0) count=0;
        }
        return max;
    }
    //stock buy and sell
    public int maxProfit(int[] prices) {
        int minPrice=Integer.MAX_VALUE;
        int maxProfit=Integer.MIN_VALUE;
        for (int price:prices){
            minPrice=Math.min(minPrice,price);
            maxProfit=Math.max(maxProfit,price-minPrice);
        }
        return maxProfit;
    }

    //re arrange the array element by sign , +ve first then -ve
    public int[] rearrangeArray(int[] nums) {
        int posInd=0;
        int negInd=1;
        int [] res = new int[nums.length];
        for (int i=0;i<nums.length;i++){
            if (nums[i]>0){
                res[posInd]=nums[i];
                posInd=posInd+2;
            }else{
                res[negInd]=nums[i];
                negInd=negInd+2;
            }
        }
        return res;
    }
    //consecutive sequence question
    public int longestConsecutive(int[] nums) {
        Set<Integer> set =new HashSet<>();
        for (int num:nums) set.add(num); // added all unique elements to set
        int longest=0;
        for (int num:set){
            //check if its a starting number
            //if not then ignore since that number will be covered by someone who is their starting number
            if (!set.contains(num-1)){
                int length=1;
                int currNum=num;
                //traverse and get all number after the starting number from set and increase length and
                //update max length
                while (set.contains(currNum+1)){
                    currNum++;
                    length++;
                    longest=Math.max(longest,length);
                }
            }
        }
        return longest;

    }
    //spiral matrix
    public List<Integer> spiralOrder(int[][] mat) {
        List<Integer> result=new ArrayList<>();
        //declare length
        int n=mat.length;
        int m=mat[0].length;
        //declare 4 co ordinates
        int top=0;
        int left=0;
        int right=m-1;
        int bottom=n-1;
        //traverse
        while (top <= bottom && left <= right){
            //move left to right
            for (int i=left;i<=right;i++){
                result.add(mat[top][i]);
            }
            top++;
            //move top to bottom
            for (int i=top;i<=bottom;i++){
                result.add(mat[i][right]);
            }
            right--;
            //move right to left
            if (top <= bottom){
                for (int i=right;i>=left;i--){
                    result.add(mat[bottom][i]);
                }
                bottom--;
            }
            //move bottom to top
            if (left<=right){
                for (int i=bottom;i>=top;i--){
                    result.add(mat[i][left]);
                }
                left++;
            }
        }
        return result;
    }
    //subArray Sum Equals to K
    public int subarraySum(int[] nums, int k) {
        //declare map tp store prefix sum
        Map<Integer,Integer> mpp=new HashMap<>();
        mpp.put(0,1); // base addition
        int totalCount=0;
        int currentSUm=0;
        for (int num:nums){
            currentSUm+=num;
            //check if we have a prefix sum present
            if (mpp.containsKey(currentSUm-k)){
                totalCount+=mpp.get(currentSUm-k);
            }
            mpp.put(currentSUm,mpp.getOrDefault(currentSUm,0)+1);
        }
        return totalCount;
    }
    //HARD SERIES
    //Pascals Triangle
    public List<List<Integer>> generate(int numRows) {
        List<List<Integer>> res=new ArrayList<>();
        //first row create and add
        List<Integer> firstRow=new ArrayList<>();
        firstRow.add(1);
        res.add(firstRow);
        //generate from row 1 to n-1 based on given n
        for (int i=1;i<numRows;i++){
            List<Integer> prevRow=res.get(i-1); //extract prev row
            List<Integer> currRow=new ArrayList<>(); //create curr row
            currRow.add(1);// extreame left
            //fill middle nodes
            for (int j=1;j<i;j++){
                currRow.add(prevRow.get(j-1)+prevRow.get(j));
            }
            currRow.add(1);// extreame right
            res.add(currRow);
        }

        return res;
    }
    //majority element 2
    //https://leetcode.com/problems/majority-element-ii/
    public List<Integer> majorityElement2(int[] v) {
        List<Integer> res=new ArrayList<>();
        //since at max there can be 2 element
        int cnt1=0;
        int cnt2=0;
        int el1=0;
        int el2=0;
        for (int ele:v){
            if (cnt1==0 && el2 != ele){
                cnt1=1;
                el1=ele;
            }else if (cnt2==0 && el1 != ele){
                cnt2=1;
                el2=ele;
            }else if (ele==el1) cnt1++;
            else if (ele==el2) cnt2++;
            else{
                cnt1--;
                cnt2--;
            }
        }
        //manually cross check the count and whether they are greater than n/3 times or not
        int count1=0;
        int count2=0;
        for (int num:v){
            if (num==el1) count1++;
            if (num==el2) count2++;
        }
        int threshold=(v.length/3)+1;
        if (count1 >= threshold) res.add(el1);
        if (count2 >= threshold) res.add(el2);

        return res;
    }
    //Q Three Sum
    //https://leetcode.com/problems/3sum/description/
    public List<List<Integer>> threeSum(int[] nums) {
        //sort the array first
        Arrays.sort(nums);
        //3 sum = 0
        List<List<Integer>> res=new ArrayList<>();
        for (int i=0;i<nums.length-2;i++){
            //check if fist index is positive then it can n;t make sum as 0
            if (nums[i]>0) return res;
            //check if its duplicate
            if (i>0 && nums[i]==nums[i-1]) continue;
            //2 pointer formation
            int j=i+1;
            int k=nums.length-1;
            while (j < k){
                int sum=nums[i]+nums[j]+nums[k];
                if (sum<0) j++;
                else if (sum>0) k--;
                else{
                    res.add(Arrays.asList(nums[i],nums[j],nums[k]));
                    while (j < k && nums[j]==nums[j+1]){
                        j++;
                    }
                    while (j < k && nums[k]==nums[j-1]){
                        k--;
                    }
                    //normal movement
                    j++;
                    k--;
                }
            }
        }
        return res;
    }
    //merge overlapping sub intervals
    //https://leetcode.com/problems/merge-intervals/description/
    public int[][] merge(int[][] intervals) {
        List<int []> res=new ArrayList<>();
        Arrays.sort(intervals,(a,b)-> Integer.compare(a[0],b[0]));
        int [] first= intervals[0];
        for (int i=1;i<intervals.length;i++){
            if (intervals[i][0]<=first[1]){
                first[1]=Math.max(first[1],intervals[i][1]);
            }else{
                //add current to result and update current for next interation
                res.add(first);
                first=intervals[i];
            }
        }
        res.add(first); // add the last iteration result to res
        return res.toArray(new int[res.size()][]);
    }
    //maximum product subarray
    //https://leetcode.com/problems/maximum-product-subarray/
    public int maxProduct(int[] arr) {
        int pre=1;
        int suf=1;
        int res=Integer.MIN_VALUE;
        for (int i=0;i<arr.length;i++){
            //reset if needed
            if (pre==0) pre=1;
            if (suf==0) suf=1;
            pre*=arr[i];
            suf*=arr[arr.length-i-1];
            res=Math.max(res,Math.max(pre,suf));
        }
        return res;
    }

    /**
     * Completed Array all questions and left 3-4 question which need revision or YT video
     *
     */








    public static void main(String[] args) {
        ArrayRoughWork arrayRoughWork=new ArrayRoughWork();
        int [] nums={2,1,3,4};
        boolean res=arrayRoughWork.checkIfSorted(nums);
        System.out.println("Result:"+res);

    }
}
