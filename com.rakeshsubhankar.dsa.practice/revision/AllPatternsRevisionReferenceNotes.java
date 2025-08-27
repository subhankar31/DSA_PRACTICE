package revision;

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
    //Q-2

    public static void main(String[] args) {

    }
}
