package arraypractice;

import java.util.*;
import java.util.stream.Collectors;

public class ArraysPractice {
    public void startRun(){
        //Q1-> https://leetcode.com/problems/remove-duplicates-from-sorted-array/description/
        int [] nums={0,0,1,1,1,2,2,3,3,4};
            int sortedArraySize=0;
            Set<Integer> sortedSet=new HashSet<>();
            for(int i=0;i<nums.length;i++){
                sortedSet.add(nums[i]);
            }

            List<Integer>sortedList=sortedSet.stream()
                    .collect(Collectors.toList());
        for (int k=0;k<nums.length;k++){
            nums[k]=0;
        }
        for (int j=0;j<sortedList.size();j++){
            nums[j]=sortedList.get(j);

        }
            sortedArraySize=sortedSet.size();
            //return sortedArraySize;
        System.out.println(sortedArraySize);
        System.out.println(sortedSet);


    }
    public void startRun2(){
        //Q2 https://leetcode.com/problems/remove-element/
        int [] nums={0,0,1,1,1,2,2,3,3,4};
        int removeElemnt = 3;
        List<Integer>outputList=new ArrayList<>();
        for (int i=0;i<nums.length;i++){
            if (nums[i]!=removeElemnt){
                outputList.add(nums[i]);
            }
        }
        System.out.println(outputList);
    }

  public void startRun3(){
        //Q-> https://leetcode.com/problems/search-insert-position/description/
      //Need to solve using (Ologn) time complexity
      int [] nums={1,3,5,6};
      int target = 4;
      int outputPosition=0;
      for(int i=0;i< nums.length;i++){
         if (nums[i]==target){
             outputPosition=i;
         } else if (nums[i]>target) {
             outputPosition=i;
             break;
         }
         else outputPosition= nums.length+1;
      }
      System.out.println(outputPosition);
  }
  public void startRun4(){
        //Q-> https://www.codingninjas.com/studio/problems/count-frequency-in-a-range_8365446?utm_source=striver&utm_medium=website&utm_campaign=a_zcoursetuf
      int [] nums={1, 3, 1, 9, 2, 7};
      Map<Integer,Integer> inputMap=new HashMap<>();
      for (int j=1;j<=9;j++){
          inputMap.put(j,0);
      }
      for(int i=0;i<nums.length;i++){
          int data=nums[i];
          inputMap.put(data,inputMap.get(data)+1);
      }
      System.out.println(inputMap);
      int [] returnArray=new int[9];
      for(int i=0;i<inputMap.size();i++){
          returnArray[i]=inputMap.get(i+1);
      }

  }

  public void startRun5(){
      int [] nums={1, 2, 3, 1, 1, 4};
      Map<Integer,Integer> inputMap=new HashMap<>();
      for (int j=1;j<=9;j++){
          inputMap.put(j,0);
      }
      for(int i=0;i<nums.length;i++){
          int data=nums[i];
          inputMap.put(data,inputMap.get(data)+1);
      }

  }
  public void testSort(){
        int [] newInputArray={1,5,7,2,3,4};
        List<Integer>inputList=new ArrayList<>();
        for (int i=0;i<= newInputArray.length-1;i++){
            inputList.add(newInputArray[i]);
        }
        Collections.sort(inputList);
  }
  public void maxSumSubArray(){
      //Find maximum sub Array of sum
      //Optimized Approach O(n)
        //Using Kadanes Algo
      int [] newInputArray={-2, 1, -3, 4, -1, 2, 1, -5, 4};
      int sum=0;
      int max=newInputArray[0];
      for (int i=0;i<=newInputArray.length-1;i++){
          sum+=newInputArray[i];
          if (sum>max)max=sum;
          if (sum<0) sum=0;
      }
      System.out.println(max);

  }
  public void byeAndSellStock(){
      int [] newInputArray={7, 1, 5, 4, 3, 6};
      int buyStock=newInputArray[0];
      int buyStockIndex=-1;
      for (int i=0;i<=newInputArray.length-2;i++){
          if (newInputArray[i]<buyStock){
              buyStock=newInputArray[i];
              buyStockIndex=i;

          }
      }
      int sellStock=newInputArray[buyStockIndex];
      for (int j=buyStockIndex;j<=newInputArray.length-1;j++){
          if (newInputArray[j]>sellStock){
              sellStock=newInputArray[j];
          }
      }
      System.out.println(sellStock-buyStock);

  }
  public void leadersProblem(){
        //Any element will be a leader if all the element towards its right will be less than that element
      int [] inputArray={10,22,3,4,5};
      int max=-1;
      List<Integer>outputList=new ArrayList<>();
      for (int i=inputArray.length-1;i>0;i--){
          if (inputArray[i]>max){
              max=inputArray[i];
              outputList.add(inputArray[i]);
          }
      }
      for (int entity:outputList){
          System.out.println(entity);
      }
  }
  public void longestConcecutive(){
        int [] arr={100, 200, 1, 2, 3, 4};
        int [] sorted= Arrays.stream(arr).sorted().toArray(); //1,2,3,4,100,200
        int count=0;
        int longest=1;
        int lastElement=-1;
        for (int i=0;i<= sorted.length-1;i++){
            if (sorted[i]-1==lastElement){
                count++;
                lastElement=sorted[i];
            } else if (sorted[i]!=lastElement) {
                count=1;
                lastElement=sorted[i];
            }
            longest=Math.max(longest,count);
        }
        System.out.println(longest);
  }
    public void rotate() {
        int [] nums={2,5,6,7,8,9};
        int k=3;
        int n = nums.length;
        k = k % n;
        int[] rotated = new int[n];

        for (int i = 0; i < n; i++) {
            rotated[(i + k) % n] = nums[i];
        }

        for (int i = 0; i < n; i++) {
            nums[i] = rotated[i];
        }
    }
    public int subarraySum() {
        int[] nums={5,9,1,2,3};
        int k=3;
        Map<Integer, Integer> cache = new HashMap<>();
        cache.put(0, 1);
        int count = 0;
        int sum = 0;

        for (int num : nums) {
            sum += num;
            int diff = sum - k;
            if (cache.containsKey(diff)) {
                count += cache.get(diff);
            }
            cache.put(sum, cache.getOrDefault(sum, 0) + 1);
        }

        return count;
    }
    public int rob() {
        int n=5;
        String str= String.valueOf(n);
        int nums[]={1,2,3,1};
        //int n = nums.length;

        if (n == 1) {
            return nums[0];
        }

        int[] dp = new int[n];

        dp[0] = nums[0];
        dp[1] = Math.max(nums[0], nums[1]);

        for (int i = 2; i < n; i++) {
            dp[i] = Math.max(dp[i - 1], nums[i] + dp[i - 2]);
        }

        return dp[n - 1];
    }
    public void getMinMax() {
        int arr[]={28078 ,19451 ,935 ,28892 ,2242 ,3570 ,5480 ,231};
        // Code Here
        int min=Integer.MAX_VALUE;
        int max=Integer.MIN_VALUE;
        for(int i=0;i<=arr.length-1;i++){
            if(arr[i]>max){
                max=arr[i];
            }
            if(arr[i]<min){
                min=arr[i];
            }
        }
    }

    //Sort the array containing 0,1,2 using Deutch National flag algo
    public void sort012(int[] arr) {
        // code here
        //3 pointers
        int low=0;
        int mid=0;
        int high=arr.length-1;

        //loop check
        while(mid<=high){
            //if its 0
            if(arr[mid]==0){
                int temp=arr[mid];
                arr[mid]=arr[low];
                arr[low]=temp;
                mid++;
                low++;
            }

            //If its 1
            else if(arr[mid]==1){
                mid++;
            }

            //if its 2
            else{
                int temp=arr[mid];
                arr[mid]=arr[high];
                arr[high]=temp;
                high--;
            }
        }
    }
    public void moveNegativePositive(){
        int arr[]={-12, 11, -13, -5, 6, -7, 5, -3, -6};
        int low=0;
        int high=arr.length-1;
        while (low<=high){
            if (arr[low]<0){
                low++;
            }
            else if(arr[high]>0){
                high--;
            }else if(arr[low]>0){
                int temp=arr[low];
                arr[low]=arr[high];
                arr[high]=temp;
                low++;
            }
        }
    }
    public int findDuplicate() {
        String str="1";
        int [] nums={1,3,4,2,2};

        for(int i = 0;i<nums.length;i++){

            int index = Math.abs(nums[i]);
            if(nums[index]<0)
                return index;
            nums[index] = -nums[index];

        }

        return -1;

    }
    public void test(){
        Map<Integer,Integer> mapN =new HashMap<>();
        mapN.put(1,15);
        mapN.put(1,13);

        Deque<Integer> testQueue=new LinkedList<>();
        Map<Integer,Integer> map=new Hashtable<>();

        List<String> inp=List.of("Rakesh","Rajesh");
        List<Integer> inpInt=List.of(10,11,2,6,1,21);
        inpInt.stream()
                .max(Integer::compareTo);


    }
}
