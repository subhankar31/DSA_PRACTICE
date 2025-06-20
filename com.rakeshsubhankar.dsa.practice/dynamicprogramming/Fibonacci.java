package dynamicprogramming;

import java.util.*;
class Fibonacci{
    //Approach-1 Memoization
    static int memoizationDpFibonacci(int n, int[] dp){
        if(n<=1) return n;

        if(dp[n]!= -1) return dp[n];
        return dp[n]= memoizationDpFibonacci(n-1,dp) + memoizationDpFibonacci(n-2,dp);
    }

    //Approach-2 Tabulation
    public void tabulationDpFibonacci(){
        int n=5;
        int dp[]=new int[n+1];
        Arrays.fill(dp,-1);
        dp[0]= 0;
        dp[1]= 1;

        for(int i=2; i<=n; i++){
            dp[i] = dp[i-1]+ dp[i-2];
        }
        System.out.println(dp[n]);
    }

    //Approach-3 Tabulation Space Optimized
    public void tabulationDpFibonacciSpaceOptimized(){
        int n=5;

        int prev2 = 0;
        int prev = 1;

        for(int i=2; i<=n; i++){
            int cur_i = prev2+ prev;
            prev2 = prev;
            prev= cur_i;
        }
        System.out.println(prev);
    }


    public static void main(String args[]) {

        int n=5;
        int dp[]=new int[n+1];
        Arrays.fill(dp,-1);
        System.out.println(memoizationDpFibonacci(n,dp));

    }
}
