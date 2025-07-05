package dsapatternsseventyquestions;

public class BitManipulationPattern {
    /**
     * BIT MANIPULATION PATTERN STARTED
     *
     */
    //Question -16 Leet Code 136. Single Number
    //Leet Code URL - > https://leetcode.com/problems/single-number/description/
    //constraints to solve this with T.C -> O(n)
    //brute ->use 2 loops to filter single element
    //Better -> Use Set to get the single element || Use sorting and check with neighbour to get single element
    //Optimized -> Use bit manipulation to perform XOr operation to get single element
    public int singleNumber(int[] nums) {
        int xor=0; //initialize
        for(int num:nums){
            xor=xor ^ num; //xor each element one by one
        }
        return xor; //the result stores the unique element as rest are cancelled by each other
    }
}
