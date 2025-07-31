package dsapatternsseventyquestions;

import java.util.Arrays;

public class DynamicProgrammingPattern {
    /**
     * Dynamic Programming  PATTERN STARTED
     *
     */
    //Question -17 Leet Code 322. Coin Change
    //Leet Code URL - > https://leetcode.com/problems/coin-change/
    //This solution gives Time Limit Exceeded without memoization
    public int coinChange(int[] coins, int amount) {

        int ans = coinCount(coins, amount);
        return (ans == Integer.MAX_VALUE) ?  -1 : ans;
    }

    int coinCount(int[] coins, int amount) {
        if(amount == 0) {
            return 0;
        }
        if(amount < 0) {
            return Integer.MAX_VALUE;
        }
        int minCoins = Integer.MAX_VALUE;

        for(int i = 0; i < coins.length; i++) {
            int ans = coinCount(coins, amount - coins[i]);

            if(ans != Integer.MAX_VALUE) {
                //we have returned 0 in ans, so now we are updating the ans count
                //hence 1 + ans
                minCoins = Math.min(minCoins, 1 + ans);
            }
        }
        return minCoins;
    }
    //Approach-2 Tabulation way along with DP[]
    //TODO Not Fully cleared about the solution -> Asked GPT and Understood but still need revision
    public int coinChangeTabulation(int[] coins, int amount) {
        int[] dp = new int[amount + 1]; //dp[i] will store the minimum number of coins needed to make amount i.
        Arrays.fill(dp, Integer.MAX_VALUE);
        dp[0] = 0; //because 0 coins are needed to make amount 0.
        for(int i = 1; i <= amount; i++) {
            for(int j = 0; j < coins.length; j++) {

                if(i - coins[j] >= 0 && dp[i - coins[j]] != Integer.MAX_VALUE) {
                    dp[i] = Math.min(dp[i], 1 + dp[i - coins[j]]);
                }
            }
        }
        return (dp[amount] == Integer.MAX_VALUE) ?  -1 : dp[amount];
    }
    //Question -18 Leet Code 70. Climbing Stairs
    //Leet Code URL - > https://leetcode.com/problems/climbing-stairs/description/
    //Best solution in Leet Code using Tabulation + space Optimization
    public int climbStairs(int n) {
        if(n==0 || n==1) return 1;
        int cur=1,prev=1; // base case
        for(int i=2;i<n;i++){ //bottom up approach (Tabulation DP)
            int temp=cur;
            cur=cur+prev;
            prev=temp;
        }
        return cur;
    }
    //USing memoization Approcach
    public int climbStairsmemoize(int n) {
        int[] memo = new int[n + 1];  // Memoization array
        return climb(n, memo);
    }

    private int climb(int n, int[] memo) {
        if (n == 0 || n == 1) return 1;

        // If already computed, return cached result
        if (memo[n] != 0) return memo[n];

        // Store the result and return
        memo[n] = climb(n - 1, memo) + climb(n - 2, memo);
        return memo[n];
    }

    //Question -19 Leet Code 53. Maximum Subarray
    //Leet Code URL - > https://leetcode.com/problems/maximum-subarray/
    //linear TC solution with Dynamic Programming
    public int maxSubArray(int[] arr) {
        int sum=0;
        int maxSum=Integer.MIN_VALUE;
        for(int i=0;i<arr.length;i++){
            sum+=arr[i];
            maxSum=Math.max(maxSum,sum);
            if(sum<=0) sum=0;
        }
        return maxSum;
    }
    //Question -20 Leet Code 338. Counting Bits
    //Leet Code URL - > https://leetcode.com/problems/counting-bits/description/
    //This solution takes 6ms and can be optimized
    public int[] countBits(int n) {
        int [] res=new int[n+1]; //as we need to store from 0 till n
        //Brute force solution
        for(int i=0;i<=n;i++){
            int count=0;
            int num=i;
            while(num!=0){ //basic math to get last digit
                count+=num%2; //if last bit is 1 then increase counter
                num=num/2; //remove last bit
            }
            res[i]=count;
        }
        return res;
    }

    /**
     * Logis :
     * We will perform bottom up DP[] approach
     * for each number from 0 till n
     * if n is even then the answer will be equal to n/2 which is pre computed
     * if n is odd then the answer will be equal to n/2 +1
     * This way we will progress till N and return the result
     * T.C -> O(n) 2ms in Leet Code
     */
    public int[] countBitsOptimized(int n) {
        int [] res=new int[n+1]; //as we need to store from 0 till n
        for(int i=0;i<=n;i++){
            if(i%2==0){ //if its even
                res[i]=res[i/2]; //in case of even bits for number n will be same as bits in number n/2
            }else res[i]=res[i/2]+1; //in case of odd bits for number n will be same as bits in number n/2+1
        }
        return res;
    }
    //Question -21 Leet Code 303. Range Sum Query - Immutable
    //Leet Code URL - >  https://leetcode.com/problems/range-sum-query-immutable/description/
    //Brute force solution without Dynamic Programming . TC- 55 ms in Leet Code
    int [] nums;
     void numArray(int[] nums) {
        this.nums=nums;
    }
    public int sumRange(int left, int right) {
        int sum=0;
        for(int i=left;i<=right;i++){ //loop in the range and calculate sum
            sum+=nums[i];
        }
        return sum;
    }
    // Approach-2 Using Dynamic Programming Tc -> 8 ms
    int [] pNums;
    void NumArray(int[] nums) {
        pNums=prefixSum(nums); //method call to find Prefix sum
    }
    public int sumRangeOptimized(int left, int right) { //T.C constant time O(1)
        int sum=0;
        sum+=pNums[right+1]-pNums[left]; //return the result directly from right value - left value
        return sum;
    }
    int [] prefixSum(int [] nums){ //T.C O(n) one time
        int [] prefix=new int [nums.length+1]; //taking one extra bit
        prefix[0]=0; //prefixc sum till index 0 will always be 0
        for(int i=0;i<nums.length;i++){
            prefix[i+1]=prefix[i]+nums[i]; //do the prefix sum here by adding the previous result
        }
        return prefix;
    }

}
