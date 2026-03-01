package revision;

import dsapatternsseventyquestions.util.ListNode;

import java.util.*;

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
    public int subarraySum(int[] nums, int k) {
        Map<Integer,Integer> cache = new HashMap<>();
        cache.put(0,1);
        int sum=0;
        int total=0;
        for(int i=0;i< nums.length;i++){
            sum+=nums[i];
            if (cache.containsKey(sum-k)){
                total+=cache.get(sum-k);
            }
            cache.put(sum,cache.getOrDefault(sum,0)+1);
        }
        return total;
    }

    // q-10
    public int trap(int[] height) {
        int left=0;
        int right= height.length-1;
        int leftMax=height[left];
        int rightMax=height[right];
        int water=0;
        while (left<right){
            if (leftMax<rightMax){
                left++;
                leftMax=Math.max(leftMax,height[left]);
                water+=leftMax-height[left];
            }else{
                right--;
                rightMax=Math.max(rightMax,height[right]);
                water+=rightMax-height[right];
            }
        }
        return water;
    }
    //String and Hashing
    //Q-11
    public List<List<String>> groupAnagrams(String[] strs) {
        Map<String,List<String>> map=new HashMap<>();
        for(String str:strs){
            char [] charArray =str.toCharArray();
            Arrays.sort(charArray);
            String key=new String(charArray);
            map.computeIfAbsent(key,k-> new ArrayList<>()).add(str);
        }
        return new ArrayList<>(map.values());
    }
    //Q-12
    public List<Integer> findAnagrams(String s, String p) {
        List<Integer> resullt=new ArrayList<>();
        //create 2 Arrays to store and compare
        int [] pChar=new int[26];
        int [] window=new int[26];
        for (int i=0;i<p.length();i++){
            pChar[p.charAt(i)-'a']++;
            window[s.charAt(i)-'a']++;
        }
        if (Arrays.equals(window,pChar)){
            resullt.add(0);
        }
        //slide the window
        for(int i=p.length();i<s.length();i++){
            window[s.charAt(i)-'a']++; //Added
            //remove
            window[s.charAt(i-p.length())]--;
            if (Arrays.equals(window,pChar)){
                resullt.add(i-p.length()+1); //right-left+1
            }
        }
        return resullt;
    }

    //Q-13
    public String longestPalindrome(String s) {
        if (s.length()==0 || s==null) return ""; //base case
        int start=0;
        int end=0;

        for(int i=0;i<s.length();i++){
            //odd
            int oddLen=calculateLength(s,i,i);
            //even
            int evenLen=calculateLength(s,i,i+1);
            //check MAx
            int len=Math.max(oddLen,evenLen);
            //update the start and end
            if (len>end-start){
                start = i - (len - 1) / 2;
                end = i + len / 2;
            }
        }
        //return the resultant string
        return s.substring(start,end+1);

    }
    int calculateLength(String s,int start,int end){
        while(start>=0 && end<s.length() && s.charAt(start)==s.charAt(end)){
            start--;
            end++;
        }
        return end-start-1;
    }

    //Q-14
    int calculateTotalPanindrome(String s){
        int total=0;
        for(int center=0;center<s.length();center++){
            total+=calculateLen(s,center,center);
            total+=calculateLen(s,center,center+1);
        }
        return total;
    }
    int calculateLen(String s, int start , int end){
        int total=0;
        while (start>=0 && end<s.length() && s.charAt(start)==s.charAt(end)){
            total++;
            start--;
            end++;
        }
        return total;
    }
    //Q-15 Binary Search
    public int search(int[] nums, int target) {
        int start=0;
        int end=nums.length-1;
        while (start<=end){
            int mid=start+end/2;
            if (nums[mid]==target) return mid;
            //if left is sorted
            if (nums[start]<=nums[mid]){
                if (nums[start]<=target && target <=nums[end]){
                    end=mid-1;
                }else start=mid+1;
            }else{
                if(nums[mid]<=target && target <= nums[end]){
                    start=mid+1;
                }else end=mid-1;
            }
        }
        return -1;
    }

    //Q-16
    public int[] searchRange(int[] nums, int target) {
        int first=findFirst(nums,target);
        int last=findLast(nums,target);
        return new int[]{first,last};
    }
    public int findFirst(int [] nums, int target ){
        int result=-1;
        int low=0;
        int high=nums.length-1;
        while (low<=high){
            int mid=low+high/2;
            if (nums[high]==target){
                result=mid;
                high=mid-1;
            }else if(nums[mid]>target){
                high=mid-1;
            }else low=mid+1;
        }
        return result;
    }
    public int findLast(int [] nums, int target ){
        int result=-1;
        int low=0;
        int high=nums.length-1;
        while (low<=high){
            int mid=low+high/2;
            if (nums[high]==target){
                result=mid;
                low=mid+1;
            }else if(nums[mid]>target){
                high=mid-1;
            }else low=mid+1;
        }
        return result;
    }

    //Q-17 Koko eating bananas
    public int minEatingSpeed(int[] v, int h) {
        int low=1;
        int high=findHigh(v);
        while (low<=high){
            int mid=low+high/2;
            if (possible(v,mid,h)){
                //check for more low possible by going more left
                high=mid-1;
            }else low=mid+1;
        }
        return low;
    }
    boolean possible(int [] v, int hourly, int limit){
        int total=0;
        for(int i=0;i<v.length;i++){
          total+=Math.ceil((double)(v[i])/(double)(hourly));
        }
        return (total<limit)? true:false;
    }
    int findHigh(int [] v){
        int high=Integer.MIN_VALUE;
        for(int i=0;i<v.length;i++){
            high= Math.max(high,v[i]);
        }
        return high;
    }
    //q-18
    public int minDays(int[] arr, int m, int k) {
        //base case
        if (m*k>arr.length) return -1;
        int mini=Integer.MAX_VALUE;
        int maxi=Integer.MAX_VALUE;
        for(int i=0;i<arr.length;i++){
            mini= Math.min(mini,arr[i]);
            maxi=Math.max(maxi,arr[i]);
        }
        int low=mini;
        int high=maxi;
        while (low<=high){
            int mid=low+high/2;
            if (possibleBq(mid,m,k,arr)){
                high=mid-1;
            }else low=mid+1;
        }
        return low;
    }
    boolean possibleBq(int days,int m , int k , int [] arr){
        int count=0;
        int noBq=0;
        for(int i=0;i<arr.length;i++){
            if (arr[i]<=days){
                count++;
            }else{
                noBq+=count/k;
                count=0;
            }
        }
        //last one
        noBq+=count/k;
        return noBq>=m;
    }
    //Q-19
    public int eraseOverlapIntervals(int[][] intervals) {
        int res=0;
        Arrays.sort(intervals,(a,b)->a[1]-b[1]);
        int prev=intervals[0][1];
        for(int i=1;i<intervals.length;i++){
            if (prev>intervals[i][1]){
                res++;
            }else{
                //update the prev
                prev=intervals[i][1];
            }
        }
        return res;
    }
    //Q-20
    public String reorganizeString(String s) {
     return null;
    }

    //21
    public int[] topKFrequent(int[] nums, int k) {
        //freq map to store int and its freq
        Map<Integer,Integer> freqMap=new HashMap<>();
        for (int num:nums){
            freqMap.put(num, freqMap.getOrDefault(num,0)+1);
        }
        //PQ to make min heap
        PriorityQueue<Map.Entry<Integer,Integer>> pq=new PriorityQueue<>((a,b)->a.getValue()-b.getValue());
        //put to pq
        for (Map.Entry<Integer,Integer> entry: freqMap.entrySet()){
            pq.offer(entry);
            if (pq.size()>k){
                pq.poll();
            }
        }
        int [] res=new int[k];
        int counter=0;
        while (!pq.isEmpty()){
            res[counter++]=pq.poll().getKey();
        }
        return res;
    }
    //Q-22
    public ListNode mergeKLists(ListNode[] lists) {
        ListNode dummy=new ListNode(-1);
        ListNode curr=dummy;
        //Pq to sort in Asc order
        PriorityQueue<ListNode> pq= new PriorityQueue<>((a,b)->a.val-b.val); //Min heap
        for(ListNode list:lists){
            pq.offer(list);
        }
        while (!pq.isEmpty()){
            ListNode node=pq.poll();
            curr.next=node;
            curr= curr.next;
            if (node.next!=null){
                pq.offer(node.next);
            }
        }


        return dummy.next;
    }
    //Q-23
    public int climbStairs(int n) {
        if (n==0 || n==1) return 1;
        int [] memo=new int[n+1];
        return climb(n,memo);
    }
    int climb(int n, int [] memo){
        if (n==0 || n==1) return 1;
        if (memo[n]>0) return memo[n];
        memo[n]=climb(n-1,memo) + climb(n-2,memo);
        return memo[n];
    }

    public static void main(String[] args) {

    }
}
