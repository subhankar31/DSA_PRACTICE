package rough;

import java.util.*;
class Pair{
    int value;
    int freq;

    public Pair(int key, int i) {
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public int getFreq() {
        return freq;
    }

    public void setFreq(int freq) {
        this.freq = freq;
    }
}
public class StackAndQeueRoughWork {
    //Balance parenthesis
    //https://leetcode.com/problems/valid-parentheses/description/
    public boolean isValid(String s) {
        Stack<Character> stack=new Stack<>();
        for (char c:s.toCharArray()){
            if (c == '(') stack.push(')');
            else if (c == '{') stack.push('}');
            else if (c == '[') stack.push(']');
            else if (stack.isEmpty() || stack.pop() != c) {
                return false;
            }
        }
        return stack.isEmpty();
    }
    //Monotonic Stack Problems
    //Next greater element
    //https://leetcode.com/problems/next-greater-element-i/description/
    public int[] nextGreaterElement(int[] nums1, int[] nums2) {
        int [] nge=new int[1001];
        //declare a stack to push element from right and pop to check if its greater
        Stack<Integer> stack=new Stack<>();

        for (int i=nums2.length-1;i>=0;i--){
            //pop everything less than current element
            while (!stack.isEmpty() && stack.peek() <= nums2[i]){
                stack.pop();
            }
            nge[nums2[i]]= stack.isEmpty() ? -1 : stack.peek();
            stack.push(nums2[i]);
        }
        for (int i=0;i<nums1.length;i++){
            nums1[i]=nge[nums1[i]];
        }
        return nums1;
    }

    //NGE part 2
    //https://leetcode.com/problems/next-greater-element-ii/
    //check even if its rotated
    public int[] nextGreaterElements(int[] nums) {
        int n=nums.length;
        Stack<Integer> stack=new Stack<>();
        int [] nge= new int[n];

        for (int i=2*n-1;i>=0;i--){
            int element=nums[i%n];
            //check from back
            while (!stack.isEmpty() && stack.peek() <= element){
                stack.pop();
            }
            //if we are within size n then put too result []
            if (i < n){
                nge[i]= stack.isEmpty() ? -1 : stack.peek();
            }
            stack.push(nums[element]);
        }
        return nge;
    }
    //Trapping rain Water
    //https://leetcode.com/problems/trapping-rain-water/
    public int trap(int[] height) {
        int left=0;
        int right=height.length-1;
        int leftmax=height[left];
        int rightMax=height[right];
        int totalWater=0;
        while (left < right){
            if (leftmax < rightMax){
                left++; // Imp always first move since the first one cann't store water
                leftmax=Math.max(leftmax,height[leftmax]);
                totalWater+=leftmax-height[leftmax];
            }else{
                right--;
                rightMax=Math.max(rightMax,height[right]);
                totalWater+=rightMax-height[right];
            }
        }
        return totalWater;
    }
    //sum of Subarray minimum , Good Question
    //https://leetcode.com/problems/sum-of-subarray-minimums/description/
    public int sumSubarrayMins(int[] arr) {
        int [] nse=getNSE(arr);
        int [] pse=getPSE(arr);
        int sum=0;
        //traverse
        for (int i=0;i<arr.length;i++){
            int right=nse[i]-i;
            int left=i-pse[i];
            int freq=right*left;
            sum+=freq*arr[i];
        }
        return sum;
    }
    public int [] getNSE(int [] arr){
        int [] nse=new int[arr.length];
        Stack<Integer> stack=new Stack<>();
        for (int i=arr.length-1;i>=0;i--){
            while (!stack.isEmpty() && stack.peek() >= arr[i]){
                stack.pop();
            }
            nse[i]= stack.isEmpty() ? arr.length : stack.peek();
            stack.push(i);
        }
        return nse;

    }
    public int [] getPSE(int [] arr){
        int [] pse=new int[arr.length];
        Stack<Integer> stack=new Stack<>();
        for (int i=0;i<arr.length;i++){
            //Imp below we dont have >=
            while (!stack.isEmpty() && stack.peek() > arr[i]){
                stack.pop();
            }
            pse[i]= stack.isEmpty() ? -1 : stack.peek();
            stack.push(i);
        }
        return pse;
    }
    //Asteroid collision
    //https://leetcode.com/problems/asteroid-collision/
    public int[] asteroidCollision(int[] asteroids) {
        //create stack to store element
        Stack<Integer> stack =new Stack<>();
        for (int element:asteroids){
            //if +ve
            if (element > 0){
                //simply push to stack
                stack.push(element);
            }else {
                //-ve
                //first remove all element less than this
                while (!stack.isEmpty() && stack.peek() < -element){
                    stack.pop();
                }
                //if stack became empty or already contains negative then push this elelemtn
                if (stack.isEmpty() || stack.peek() < 0){
                    stack.push(element);
                }
                //remove if element is same as stack peek
                if (stack.peek() == -element){
                    stack.pop();
                }
            }
        }
        int [] res=new int[stack.size()];
        int i=res.length-1;
        while (!stack.isEmpty()){
            res[i]=stack.pop();
            i--;
        }
        return res;
    }
    //Sum of Subarray ranges
    //https://leetcode.com/problems/sum-of-subarray-ranges/description/
    public long subArrayRanges(int[] arr) {
        return subArrayRangesmxs(arr)-subArrayRangesMins(arr);
    }
    public int subArrayRangesmxs(int [] arr){
        //find out NGE and PGEE
        int [] NGE=findNGE(arr);
        int [] PGEE=findPGEE(arr);
        int total=0;
        for (int i=0;i<arr.length;i++){
            int right=NGE[i]-i;
            int left=i-PGEE[i];
            total+=right*left*arr[i];
        }
        return total;
    }
    public int subArrayRangesMins(int [] arr){
        //Find out NSE and PSEE
        int [] NSE=findNSE(arr);
        int [] PSEE=findPSEE(arr);
        int total=0;
        for (int i=0;i<arr.length;i++){
            int left=i-PSEE[i];
            int right=NSE[i]-i;
            total+=left*right*arr[i];
        }
        return total;
    }
    public int [] findNGE(int [] arr){
        int [] NGE=new int[arr.length];
        Stack<Integer> stack=new Stack<>();
        for (int i=arr.length-1;i>=0;i--){
            while (!stack.isEmpty() && stack.peek()<=arr[i]){
                stack.pop();
            }
            NGE[i]=stack.isEmpty() ? arr.length : stack.peek();
            stack.push(arr[i]);
        }
        return NGE;
    }
    public int[] findPGEE(int [] arr){
        int [] PGEE=new int[arr.length];
        Stack<Integer> stack=new Stack<>();
        for (int i=0;i<arr.length;i++){
            while (!stack.isEmpty() && stack.peek() < arr[i]){
                stack.pop();
            }
            PGEE[i]= stack.isEmpty() ? -1 : stack.peek();
            stack.push(arr[i]);
        }
        return PGEE;
    }
    public int [] findNSE(int [] arr){
        int [] NSE=new int[arr.length];
        Stack<Integer> stack=new Stack<>();
        for (int i=arr.length-1;i>=0;i--){
            while(!stack.isEmpty() && stack.peek() >= arr[i]){
                stack.pop();
            }
            NSE[i]= stack.isEmpty() ? arr.length : stack.peek();
            stack.push(arr[i]);
        }
        return NSE;
    }
    public int [] findPSEE(int [] arr){
        int [] PSEE=new int[arr.length];
        Stack<Integer> stack=new Stack<>();
        for (int i=0;i<arr.length;i++){
            while (!stack.isEmpty() && stack.peek() > arr[i]){
                stack.pop();
            }
            PSEE[i]= stack.isEmpty() ? -1 : stack.peek();
            stack.push(arr[i]);
        }
        return PSEE;
    }
    //Remove K digits
    //https://leetcode.com/problems/remove-k-digits/description/
    public String removeKdigits(String num, int k) {
        StringBuilder res=new StringBuilder();
        Deque<Character> deque=new LinkedList<>();
        for (char c:num.toCharArray()){
            //if element exist is greater then remove
            while (!deque.isEmpty() && k>0 && deque.peek()>c){
                deque.pop();
                k--;
            }
            deque.push(c);
            //check if we have 0s at back then remove
            while (!deque.isEmpty() && deque.peekLast()=='0'){
                deque.pollLast();
            }
            //if we have more k left to remove then remove to get more smaller number
            while (!deque.isEmpty() && k>0){
                deque.pop();
                k--;
            }
            //build result
            while (!deque.isEmpty()) {
                res.append(deque.pollLast());
            }
        }
        return res.isEmpty() ? "0" : res.toString();
    }

    //Large rectangle in a Histogram
    //https://leetcode.com/problems/largest-rectangle-in-histogram/
    public static int largestRectangleArea(int[] heights) {
        // define 2 arrays to store right small and left small
        int [] rightSmall=new int[heights.length];
        int [] leftSmall=new int[heights.length];
        int totalMaxRec=0;
        Stack<Integer> stack=new Stack<>();
        //add values to left small
        for (int i=0;i<heights.length;i++){
            while (!stack.isEmpty() && stack.peek() >= heights[i]){
                stack.pop();
            }
            leftSmall[i]= stack.isEmpty() ? 0 : stack.peek()+1;
            stack.push(i);
        }
        while (!stack.isEmpty()) stack.pop();

        for (int i=heights.length-1;i>=0;i--){
            while (!stack.isEmpty() && stack.peek() >= heights[i]){
                stack.pop();
            }
            rightSmall[i]=stack.isEmpty() ? heights.length-1 : stack.peek()-1;
            stack.push(i);
        }
        for (int i=0;i<heights.length;i++){
            totalMaxRec=Math.max(totalMaxRec,heights[i]*(rightSmall[i]-leftSmall[i]+1));
        }
        return totalMaxRec;
    }
    //Maximum rectangle in a matrix
    ////Uses previous uestion histogram technique , here we take one by one row as a single row and
    // try to find largest rectangle
    //https://leetcode.com/problems/maximal-rectangle/
        public int maximalRectangle(char[][] matrix) {
            int rows=matrix.length;
            int cols=matrix[0].length;
            int [] height=new int[cols];
            int totalMax=0;
            for (int i=0;i<rows;i++){
                for (int j=0;j<cols;j++){
                    if (matrix[i][j]==0){
                        height[j]=0;
                    }else height[j]=height[j]+1;
                }
                //call for this row and calculate max
                totalMax=Math.max(totalMax,largestRectangleArea(height));
            }
            return totalMax;
        }
        //Sliding window maximum
        //https://leetcode.com/problems/sliding-window-maximum/
        public int[] maxSlidingWindow(int[] nums, int k) {
            Deque<Integer> deque=new LinkedList<>();
            List<Integer> res=new ArrayList<>();
            //traverse
            for(int i=0;i<nums.length;i++){
                //remove from deque if it contains small than current
                while (!deque.isEmpty() && deque.getLast()<nums[i]){
                    deque.pollLast();
                }
                deque.addLast(nums[i]);
                //check if deque contains element outside of window
                if (i>k-1 && deque.getFirst()==nums[i-k]){
                    deque.pollFirst();
                }
                //add to res
                res.add(deque.getFirst());
            }
            //convert List to proper return type
            //return res.stream().mapToInt(i -> i).toArray();
            int[] resultArr=new int[res.size()];
            int i=0;
            for (int result:res){
                resultArr[i]=result;
                i++;
            }
            return resultArr;
        }
    //LFU Cache , Implementation heavy
    //HARD
    //https://leetcode.com/problems/lfu-cache/
    int size=0; //size of cache
    //below map structure HashMap<Key,HashMap<value,fre>>
    HashMap<Integer,Pair> hm=new LinkedHashMap<>();
    //Below hashmap structure HashMap<freq,List<Keys>>
    HashMap<Integer,List<Integer>> list=new HashMap<>();
    public void LFUCache(int givenSize){
        size=givenSize; //initializes the size
    }
    public int get(int key){
        if (size == 0) return -1;
        if (!hm.containsKey(key)) return -1;
        //then it contains key
        //get the key and update freq in hm
        Pair p=hm.get(key);
        int fre=p.getFreq();
        hm.put(key,new Pair(key,fre+1));
        //update freq in list
        if (!list.containsKey(fre+1)) list.put(fre+1,new ArrayList<>());
        list.get(fre+1).add(key);
        //remove key from  old freq list
        list.get(fre).remove(list.get(fre).indexOf(key));
        return p.getValue();
    }
    public void put(int key,int value){
        //if given size is 0 then dont do anything
        if (size == 0) return;
        //CASE 1 Size Remaining
        if (hm.size() < size){
            //CASE 1.1 key does n't exist
            if (!hm.containsKey(key)){
                Pair p =new Pair(value,1);
                hm.put(key,p);
                if (!list.containsKey(1)) list.put(1, new ArrayList<>());
                List<Integer> temp=list.get(1);
                temp.add(key);
                list.put(1,temp);
            }else{
                //CASE 1.2 Key does exist
                Pair p= hm.get(key);
                int freq=p.getFreq();
                p.setFreq(freq+1);
                hm.put(key,p);
                if (!list.containsKey(freq+1)) list.put(freq+1,new ArrayList<>());
                List<Integer> temp=list.get(freq+1);
                temp.add(key);
                list.put(freq+1,temp);
                //remove the old one
                list.get(freq).remove(list.get(freq).indexOf(key));
            }
        }
        //CASE 2 Size Full but key exist so update only
        else if (hm.size() == size && hm.containsKey(key)) {
            Pair p=hm.get(key);
            int freq=p.getFreq();
            p.setFreq(freq+1);
            hm.put(key,p);
            //operations on list
            if (!list.containsKey(freq+1)) list.put(freq+1,new ArrayList<>());
            List<Integer> temp = list.get(freq+1);
            temp.add(key);
            list.put(freq+1,temp);
            list.get(freq).remove(list.get(freq).indexOf(key));
        }
        //CASE 3 Size Full and Key doesn't exist , need to remove LRU
        else{
            //first remove
            Iterator<Map.Entry<Integer,List<Integer>>> itr=list.entrySet().iterator();
            int removeKey=-1;
            int removeFreq=-1;
            while (itr.hasNext()){
                Map.Entry<Integer,List<Integer>> entry=itr.next();

                removeFreq=entry.getKey();
                if (list.get(removeFreq).size()==0) continue;
                removeKey=list.get(removeFreq).get(0);
                break;
            }
            //remove old key
            hm.remove(removeKey);
            list.get(removeFreq).remove(list.get(Integer.valueOf(removeKey)));
            //add new key
            hm.put(key,new Pair(value,1));
            if (!list.containsKey(1)) list.put(1,new ArrayList<>());
            List<Integer> temp=list.get(1);
            temp.add(key);
            list.put(1,temp);
        }
    }







    public static void main(String[] args) {

    }
}
