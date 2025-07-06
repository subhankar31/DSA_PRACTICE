package dsapatternsseventyquestions;

public class RoughActivity {
    public int missingNumber(int[] nums) {
        int res = nums.length;         // Initialize res with 'n'

        for (int i = 0; i < nums.length; i++) {
            res += i - nums[i];        // Adjust res using index and value
        }

        return res;
    }
    public static void main(String[] args) {
        //Create obj and test || Copy methods from any series and modify test have fun
        RoughActivity rough=new RoughActivity();
        rough.missingNumber(new int[]{0,1,2,3,5,6,7,8});
    }
}
