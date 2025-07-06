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
    //TODO Not Fully cleared about the solution
    public int coinChangeTabulation(int[] coins, int amount) {
        int[] dp = new int[amount + 1];
        Arrays.fill(dp, Integer.MAX_VALUE);
        dp[0] = 0;
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
    //Best solution in Leet Code
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
    //Question -12 Leet Code
    //Leet Code URL - >

}
