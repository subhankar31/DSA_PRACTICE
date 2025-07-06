package dsapatternsseventyquestions;

import java.util.ArrayList;
import java.util.List;

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
    public static void main(String[] args) {
        //Create obj and test || Copy methods from any series and modify test have fun
        RoughActivity rough=new RoughActivity();
        rough.letterCasePermutation("a1b2");
    }
}
