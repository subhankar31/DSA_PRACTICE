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
}
