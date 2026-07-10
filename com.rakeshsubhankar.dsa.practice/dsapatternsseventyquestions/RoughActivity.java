package dsapatternsseventyquestions;

import dsapatternsseventyquestions.util.ListNode;

import java.util.*;

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
    //2 pointer
    public int maxArea(int[] height) {
        int l=0;
        int r= height.length;
        int max=0;
        while (l<=r){
            int currmax=(r-l)*Math.min(height[l],height[r]);
            if (currmax>max) max=currmax;

            if (height[l]>height[r]) r--;
            else l++;

        }
    return max;
    }
    //3 sum
    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> res=new ArrayList<>();
        Arrays.sort(nums);
        for (int i=0;i<nums.length-2;i++){
            if (nums[i]>0) return res;
            if (i>0 && nums[i]==nums[i-1]) continue;
            int left=i+1;
            int right=nums.length-1;
            while (left < right){
                int sum=nums[i]+nums[left]+nums[right];
                if (sum>0){
                    right--;
                }else if (sum<0){
                    left++;
                }else {
                    res.add(Arrays.asList(nums[i], nums[left], nums[right]));
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
    public int subarraySum(int[] nums, int k) {
        Map<Integer,Integer> mpp=new HashMap<>();
        mpp.put(0,1);
        int sum=0;
        int total=0;
        for (int num:nums){
            sum+=num;
            int prefix=sum-k;
            if (mpp.containsKey(prefix)){
                total+=mpp.get(prefix);
            }
            mpp.put(sum,mpp.getOrDefault(sum,0)+1);
        }
        return total;
    }
    public int trap(int[] height) {
        int left=0;
        int right=height.length-1;
        int leftMax=height[left];
        int rightmax=height[right];
        int totalWater=0;
        while (left<right){
            if (leftMax<rightmax){
                left++;
                leftMax=Math.max(leftMax,height[left]);
                totalWater+=leftMax-height[left];
            }else{
                right--;
                rightmax=Math.max(rightmax,height[right]);
                totalWater+=rightmax-height[right];
            }
        }
        return totalWater;
    }
    //String practice starts here
    public List<List<String>> groupAnagrams(String[] strs) {
        Map<String,List<String>> mpp=new HashMap<>();
        for (String word:strs){
            char [] chars = word.toCharArray();
            Arrays.sort(chars);
            String key=new String(chars);
            mpp.computeIfAbsent(key,k->new ArrayList<>()).add(word);
        }
        return new ArrayList<>(mpp.values());
    }
    public List<Integer> findAnagrams(String s, String p) {
        List<Integer> res=new ArrayList<>();
        int slen=s.length();
        int plen=p.length();
        if (slen<plen) return res;
        int [] pWin=new int[26];
        int [] win=new int[26];
        //track p
        for (int i=0;i<plen;i++){
            pWin[p.charAt(i)-'a']++;
            win[s.charAt(i)-'a']++;
        }
        if (Arrays.equals(pWin,win)) res.add(0);
        //slide the window
        for (int i=plen;i<slen;i++){
            win[s.charAt(i)-'a']++;
            win[s.charAt(i-plen)-'a']--;
            if (Arrays.equals(pWin,win)){
                res.add(i-plen+1);
            }
        }
        return res;
    }
    public String longestPalindrome(String s) {
        int start=0;
        int end=0;
        if (s==null || s.length()<1) return ""; //base case
        for (int i=0;i<s.length();i++){
            //odd
            int l1=expandCenter(s,i,i);
            //even
            int l2=expandCenter(s,i,i+1);
            int l=Math.max(l1,l2);

            //check if its more than prev max
            if (l>(end-start)){
                //update end and start
                int ll=(l-1)/2;
                int rl=l/2;
                start=i-ll;
                end=i+ll;
            }
        }
        return s.substring(start,end+1);
    }
    int expandCenter(String s, int left , int right){
        while (left>=0 && right<s.length() && s.charAt(left)==s.charAt(right)){
            left--;
            right++;
        }
        return right-left-1;
    }
    public int countSubstrings(String s) {
        int count=0;
        for (int center=0;center<s.length();center++){
            count+=expandCenter2(s,center,center);
            count+=expandCenter2(s,center,center+1);
        }
        return count;
    }
    int expandCenter2(String s, int left, int right){
        int count=0;
        while (left>=0 && right<s.length() && s.charAt(left)==s.charAt(right)){
            count++;
            left--;
            right++;
        }
        return count;
    }
    //Binary search Revision
    public int search(int[] nums, int target) {
        int start=0;
        int end=nums.length-1;

        while (start<=end){
        int mid=(start+end)/2;
        if (mid==target) return mid;

        //if left is sorted
            if (nums[start]<=nums[mid]){
                //if element lies
                if (nums[start]<= target && target<= nums[mid]){
                    end=mid-1;
                }else start=mid+1;
            }else{
                //right is sorted
                if (nums[mid]<=target && target ==nums[end]){
                    start=mid+1;
                }else end=mid-1;
            }
        }
        return -1;
    }
    //Binary search on Answers
    public int minEatingSpeed(int[] v, int h) {
        int low=1;
        int high=findmax(v);
        while (low<=high){
            int mid=(low+high)/2;
            int totalHour=hours(v,mid);
            if (totalHour<h){
                high=mid-1;
            }else low=mid+1;
        }
        return low;
    }
    int hours(int []v, int hourly){
        int hours=0;
        for (int num:v){
            hours+=Math.ceil((double) num/(double) hourly);
        }
        return hours;
    }
    int findmax(int [] v){
        int maxi=Integer.MIN_VALUE;
        for (int num:v){
            maxi=Math.max(maxi,num);
        }
        return maxi;
    }
    //Greedy patterns starts here
    public int eraseOverlapIntervals(int[][] intervals) {
        int result=0;
        Arrays.sort(intervals,(a,b)->a[1]-b[1]);
        int prev_end=intervals[0][1];
        for (int i=1;i<intervals.length;i++){
            if (intervals[i][0]<prev_end){
                result++;
            }else prev_end=intervals[i][1];
        }
        return result;
    }
    //Heap Patterns
    public int[] topKFrequent(int[] nums, int k) {
        Map<Integer, Integer> mpp = new HashMap<>();
        for (int num : nums) {
            mpp.put(num, mpp.getOrDefault(num, 0) + 1);
        }
        PriorityQueue<Map.Entry<Integer, Integer>> pq =
                new PriorityQueue<>((a, b) -> a.getValue() - b.getValue());

        for (Map.Entry<Integer,Integer> entry:mpp.entrySet()){
            pq.offer(entry);
            if (pq.size()>k){
                pq.poll();
            }
        }
        //now return result from pq
        int [] result =new int[k];
        int i=0;
        while (!pq.isEmpty()){
            result[i++]=pq.poll().getKey();
        }
        return result;
    }
    public ListNode mergeKLists(ListNode[] lists) {
        //base case check
        if (lists == null || lists.length==0) return null;
        PriorityQueue<ListNode> pq=new PriorityQueue<>(
                (a,b)-> a.val -b.val
        );
        //put data into PQ
        for (ListNode node:lists) pq.offer(node);

        //create result node
        ListNode res=new ListNode(0);
        ListNode tail=res;
        while (!pq.isEmpty()){
            ListNode cur=pq.poll();
            tail.next=cur;
            tail=tail.next;

            if (cur.next!=null){
                pq.offer(cur.next);
            }
        }
        return res.next;
    }
    //From here we will revise DSA A2Z questions starting from Array
    //Array
    public boolean check(int[] nums) {
        int k=0;
        for (int i=0;i<nums.length;i++){
            if (nums[i] > nums[(i+1)%nums.length]){
                k++;
            }
            if (k>1) return false;
        }
        return true;
    }
    public int removeDuplicates(int[] nums) {
        int j=1;
        for (int i=1;i<nums.length;i++){
            if (nums[i]!=nums[i-1]){
                nums[j]=nums[i];
                j++;
            }
        }
        return j;
    }
    public void moveZeroes(int[] nums) {
        int snowballSize=0;
        for (int i=0;i<nums.length;i++){
            if (nums[i]==0){
                snowballSize++;
            }else if (snowballSize>0){
                int curr=nums[i];
                nums[i-snowballSize]=curr;
                nums[i]=0;
            }
        }
    }
    public int findMaxConsecutiveOnes(int[] nums) {
        int count=0;
        int maxOne=0;
        for (int num:nums){
            if (num==1){
                count++;
                maxOne=Math.max(maxOne,count);

            }else {
                count=0;
            }
        }
        return maxOne;
    }
    public int singleNumber(int[] nums) {
        int xor=0;
        for (int num:nums){
            xor=xor ^ xor;
        }
        return xor;
    }
    public int[] twoSum(int[] nums, int target) {
        int [] res=new int[2];
        Map<Integer,Integer> mpp=new HashMap<>();
        for (int i=0;i<nums.length;i++){
            if (mpp.containsKey(target-nums[i])){
                res[0]=i;
                res[1]=mpp.get(target-nums[i]);
            }else{
                mpp.put(nums[i],i);
            }
        }
        return res;
    }
    public void sortColors(int[] nums) {
        int low=0;
        int mid=0;
        int high=nums.length-1;
        while (mid<=high){
            if (mid==1) mid++;
            else if (mid==0) {
                //move towards left
                int temp=nums[low];
                nums[low]=nums[mid];
                nums[mid]=temp;
                mid++;
                low++;
            }else{
                //if its 2
                int temp=nums[high];
                nums[high]=nums[mid];
                nums[mid]=temp;
                high--;
            }
        }
    }
    public int majorityElement(int[] nums) {
        int count=0;
        int element=0;
        for (int num:nums){
            if (count==0){
                count=1;
                element=nums[num];
            } else if (element==nums[num]) {
                count++;
            }else count--;
        }
        int countOne=0;
        for (int num:nums){
            if (num==element) countOne++;
        }
        if (countOne > nums.length/2) return countOne;
        else return -1;
    }
    //kadanes algo to find maximum sum subarray
    public int maxSubArray(int[] arr) {
        int sum=0;
        int maxi=Integer.MIN_VALUE;
        for (int num:arr){
            sum+=num;
            maxi=Math.max(maxi,sum);

            if (sum<0) sum=0;
        }
        return maxi;
    }
    //stock buy and sell
    public int maxProfit(int[] prices) {
        int minprice=Integer.MAX_VALUE;
        int maxprofit=Integer.MIN_VALUE;
        for (int price:prices){
            minprice=Math.min(minprice,price);
            maxprofit=Math.max(maxprofit,price-minprice);
        }
        return  maxprofit;
    }
    public int[] rearrangeArray(int[] nums) {
        int posInd=0;
        int negInd=1;
        int [] res=new int[nums.length];
        for (int i=0;i<nums.length;i++){
            if (nums[i]<0){
                res[negInd]=nums[i];
                negInd=negInd+2;
            }else{
                res[posInd]=nums[i];
                posInd=posInd+2;
            }
        }
        return res;
    }
    public int longestConsecutive(int[] nums) {
        Set<Integer> set =new HashSet<>();
        for (int num:nums){
            set.add(num);
        }
        int longest=0;
        for (int num:nums){
            if (!set.contains(num-1)){
                int length=1;
                while (set.contains(num+length)){
                    length++;
                }
                longest=Math.max(longest,length);
            }
        }
        return longest;
    }
    public int subarraySum2(int[] nums, int k) {
        Map<Integer,Integer> mpp=new HashMap<>();
        mpp.put(0,1);
        int sum=0;
        int count=0;
        for (int num:nums){
            sum+=num;
            int target=sum-k;
            if (mpp.containsKey(target)){
                count+=mpp.get(target);
            }
            mpp.put(sum,mpp.getOrDefault(sum,0)+1);
        }
        return count;
    }
    public List<List<Integer>> generate(int numRows) {
        List<List<Integer>> res=new ArrayList<>();
        if (numRows==0) return res;
        List<Integer> firstRow=new ArrayList<>();
        firstRow.add(1);
        res.add(firstRow);

        for (int i=1;i<numRows;i++){
            List<Integer> prevRow=res.get(i-1);
            List<Integer> currRow=new ArrayList<>();
            currRow.add(1);
            //Middle Rows
            for (int j=1;j<i;j++){
                currRow.add(prevRow.get(j-1)+prevRow.get(j));
            }

            currRow.add(1);
            res.add(currRow);
        }
        return res;
    }
    public List<Integer> majorityElement2(int[] v) {
        List<Integer> res=new ArrayList<>();
        int cnt1=0;
        int cnt2=0;
        int ele1=Integer.MIN_VALUE;
        int ele2=Integer.MIN_VALUE;
        for (int value:v){
            if (cnt1==0 && value !=ele2){
                cnt1=1;
                ele1=value;
            }else if (cnt2==0 && value !=ele1){
                cnt2=1;
                ele2=value;
            } else if (value==ele1) {
                cnt1++;
            } else if (value==ele2) {
                cnt2++;
            }else{
                cnt1--;
                cnt2--;
            }
        }

        cnt1=0;
        cnt2=0;
        for (int num:v){
            if (num==ele1) cnt1++;
            if (num==ele2) cnt2++;
        }

        int mini=(int) (v.length/3)+1;
        if (cnt1>=mini) res.add(ele1);
        if (cnt2>=mini) res.add(ele2);

        return res;
    }
    public int smallestDivisor(int[] arr, int limit) {
        int length=arr.length;
        int low=1;
        int high=findMax(arr);
        if (length>limit) return -1;
        while (low<=high){
            int mid=(low+high)/2;
            if (sumBy(arr,mid)<=limit){
                high=mid-1;
            }else low=mid+1;
        }
        return low;
    }
    int findMax(int [] arr){
        int max=Integer.MIN_VALUE;
        for (int num:arr){
            max=Math.max(max,num);
        }
        return max;
    }
    int sumBy(int[] arr, int mid){
        int sum=0;
        for (int num:arr){
            sum+= (int) Math.ceil(num/mid);
        }
        return  sum;
    }
    public int shipWithinDays(int[] weights, int days) {
        int minCap=findMinCap(weights);
        int maxCap=findMaxCap(weights);
        while (minCap <=maxCap){
            int midCap=minCap+maxCap/2;
            if (findDays(weights,midCap)<days){
                maxCap=midCap-1;
            }else{
                minCap=midCap+1;
            }
        }
        return minCap;

    }
    int findMinCap(int [] weights){
        int minCap=Integer.MIN_VALUE;
        for (int weight:weights){
            minCap=Math.max(minCap,weight);
        }
        return minCap;
    }
    int findMaxCap(int [] weights){
        int sum=0;
        for (int weight:weights){
            sum+=weight;
        }
        return sum;
    }
    int findDays(int [] weights, int cap){
        int load=0;
        int days=1;
        for (int i=0;i<weights.length;i++){
            if (load+weights[i]>cap){
                days+=1;
                load=weights[i];
            }else{
                load+=weights[i];

            }
        }
        return days;
    }
    public int splitArray(int[] nums, int k) {
        List<Integer> input= new ArrayList<>();
        for (int num:nums) input.add(num);
        int low=Collections.max(input);
        int high=input.stream()
                .mapToInt(Integer::intValue).sum();

        while (low <= high){
            int mid=(low+high)/2;
            int subArray=calculatePossible(input,mid);
            if (subArray>k){
                low=mid+1;
            }else high=mid-1;
        }
        return low;
    }
    int calculatePossible(List<Integer> nums, int mid){
        int subArray=1;
        int sum=0;
        for (int i=0;i<nums.size();i++){
            if (sum+nums.get(i)<=mid){
                sum+=nums.get(i);
            }else{
                subArray++;
                sum=nums.get(i);
            }
        }
        return subArray;
    }
    public String removeOuterParentheses(String s) {
        Stack<Character> stack = new Stack<>();
        StringBuilder sb=new StringBuilder();
        for (int i=0;i<s.length();i++){
            if (s.charAt(i)=='('){
                if (stack.size()>0){
                    sb.append(s.charAt(i));
                }
                stack.push(s.charAt(i));
            }else{
                stack.pop();
                if (stack.size()>0){
                    sb.append(s.charAt(i));
                }
            }
            return sb.toString();
        }
        return sb.toString();
    }

        public static void main(String[] args) {
        //Create obj and test || Copy methods from any series and modify test have fun
        RoughActivity rough=new RoughActivity();
        rough.letterCasePermutation("a1b2");
    }
}
