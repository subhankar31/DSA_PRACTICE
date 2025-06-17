import java.util.ArrayList;
import java.util.List;

public class TestClassPractice {
    public boolean isPalindrome(int x) {
        boolean result=false;
        int copyNumber=x;
        int reverseNumber=0;
        while(x>0){
            reverseNumber=(reverseNumber*10)+x%10;
            x=x/10;
        }
        if(reverseNumber==copyNumber){
            result=true;
        }
        return result;

    }
    public int[] plusOne(int[] digits) {
        //Q-> https://leetcode.com/problems/plus-one/
        int[] outputArray=new int[digits.length+1];
        for(int i=0;i<digits.length-3;i++){
            outputArray[i]=digits[i];
        }
        if(digits[digits.length-1]==9){
            outputArray[digits.length-2]=digits[digits.length-2]+1;
            outputArray[digits.length]=0;
        }
        else{
            outputArray[digits.length]=digits[digits.length]+1;
        }
        return outputArray;
    }
    public void secondLargest(){
        int [] nums={1,2,4, 7,7,5};
        int secondLargest=-1;
        int largest=nums[0];
        for (int i=1;i<= nums.length-1;i++){
            if (nums[i]>largest){
                largest=nums[i];
            }
        }
        for (int i=0;i<= nums.length-1;i++){
            if (nums[i]>secondLargest && nums[i]<largest){
                secondLargest=nums[i];
            }
        }
        System.out.println(secondLargest);

    }
    public void secondLargestOptimalSolution(){
        int [] nums={1,6,2,4, 7,7,5,6};
        int secondLargest=-1;
        int largest=nums[0];
        for (int i=1;i<= nums.length-1;i++){
            if (nums[i]>largest){
                secondLargest=largest;
                largest=nums[i];
            }
            if (nums[i]>secondLargest && nums[i]<largest){
                secondLargest=nums[i];
            }
        }


        System.out.println(secondLargest);

    }
    public void checkIfSorted(){
        int [] nums={1,3,5,7,2};
        boolean isSorted=false;
        for (int i=0;i<=nums.length-2;i++){
            if (nums[i]<=nums[i+1]){
                isSorted=true;
            }
            else isSorted=false;
        }
        if (isSorted==true){
            System.out.println("Array is Sorted");
        }
        else System.out.println("Not Sorted");
    }
    public void removeDuplicateInOrderOfN() {
        int[] nums = {1,3,3,5,7,7,7,67};
        int i=0;
        for (int j=1;j< nums.length;j++){
            if (nums[j]!=nums[i]){
                nums[i+1]=nums[j];
                i++;
            }
        }
        System.out.println(i+1);

    }
    public void leftRotateByOnePlace(){
        //Q-> Left rotate an Array by one Place
        int[] nums = {1,3,5,7,67};
        System.out.println("Before Rotation");
        for (int j=0;j<= nums.length-1;j++){
            System.out.print(nums[j]+" ");
        }
        int rotateValue=nums[0];
        for (int i=1;i<= nums.length-1;i++){
            nums[i-1]=nums[i];
        }
        nums[nums.length-1]=rotateValue;
        System.out.println("After Rotation");
        for (int j=0;j<= nums.length-1;j++){
            System.out.print(nums[j]+" ");
        }
    }
    public void moveZeros(){
        //Move zeros tot the end of the Array
        int[] nums = {1,3,0,0,5,7,0,67};
        System.out.println("Before Rotation");
        for (int j=0;j<= nums.length-1;j++){
            System.out.print(nums[j]+" ");
        }

        //Rotation Logic
        List<Integer> nonZeroList=new ArrayList<>();
        int nonZeroCounter=0;
        for (int i=0;i<= nums.length-1;i++){
            if (nums[i]!=0){
                nonZeroList.add(nums[i]);
                nonZeroCounter++;
            }
        }

        int zeroCounter= nums.length-nonZeroCounter;

        System.out.println("After Rotation");
        for (int j=0;j<= nums.length-1;j++){
            System.out.print(nums[j]+" ");
        }
    }
    public void moveZerosOptimal(){
        int[] nums = {1,3,0,0,5,7,0,67};
        System.out.println("Before Rotation");
        for (int j=0;j<= nums.length-1;j++){
            System.out.print(nums[j]+" ");
        }
        //Logic
        int nonZeroIndex=0;
        for (int i=0;i<= nums.length-1;i++){
            if (nums[i]!=0){
                nums[nonZeroIndex]=nums[i];
                nonZeroIndex++;
            }
        }
    }
    public void longestSubArray(){
        int[] nums = {1,3,0,0,5,7,0,1,1,1,1,2};
        int sum=3;
        int i=0;
        while (i<= nums.length-1){

        }
    }

}
