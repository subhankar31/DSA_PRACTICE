package dsapatternsseventyquestions;

import java.net.Inet4Address;
import java.util.*;

public class ArrayPattern {

    //Question-1 EASY detect duplicate
    //LeetCode URL -> https://leetcode.com/problems/contains-duplicate/
    //Best Solution
    public boolean containsDuplicate1(int[] nums) {
        Set<Integer> set = new HashSet<>();

        for (int i = 0; i < nums.length; i++) {
            int number = nums[i];
            if (set.contains(number)) {
                return true;
            }
            set.add(number);
        }
        return false;
    }

     public boolean containsDuplicate2(int[] nums) {
        Set<Integer> set=new HashSet<>();
        for(int num:nums){
            set.add(num);
        }
     return set.size()!=nums.length;

     }

     public boolean containsDuplicate3(int[] nums) {
        Arrays.sort(nums); //TC- O(long n)
        boolean flag=false;
        for(int i=0;i<nums.length-1;i++){ //T.C O(n)
            if(nums[i]==nums[i+1]){
                flag=true;
             }
        }
        return flag;
     }
     //Question 2 EASY Find Missing NUmber from 0 -> n
    //Logic :
    //You're adding i (the expected value)
    //Subtracting nums[i] (the actual value)
    //So you're effectively computing:
    //res = n + (0 + 1 + 2 + ... + (n - 1)) - (sum of array values)
     public int missingNumber(int[] nums) {
         int res = nums.length;         // Initialize res with 'n'

         for (int i = 0; i < nums.length; i++) {
             res += i - nums[i];        // Adjust res using index and value
         }

         return res;
     }
     //Question-3 Find all missing Numbers/Dissapared Numbers
    //LeetCode URl-> https://leetcode.com/problems/find-all-numbers-disappeared-in-an-array/
     public List<Integer> findDisappearedNumbers(int[] nums) {
         Set<Integer> seen = new HashSet<>();
         for (int num : nums) {
             seen.add(num);
         }

         List<Integer> result = new ArrayList<>();
         for (int i = 1; i <= nums.length; i++) {
             if (!seen.contains(i)) {
                 result.add(i);
             }
         }

         return result;

     }
     //Question - 4 Leet Code Two Sum
    //Leet Code URL -> https://leetcode.com/problems/two-sum/submissions/1685063785/
     public int[] twoSum(int[] nums, int target) {
         int[] result=new int[2];
         Map<Integer,Integer> hash=new HashMap<>(); //key->index ,Value->value
         for(int i=0;i<nums.length;i++){
             if(hash.containsKey(target-nums[i])){
                 result[0]=i;
                 result[1]=hash.get(target-nums[i]);
             }
             else hash.put(nums[i],i);
         }
         return result;
     }
     //Question -5 how-many-numbers-are-smaller-than-the-current-number
    //Leet Code URL - > https://leetcode.com/problems/how-many-numbers-are-smaller-than-the-current-number/description/

    public int[] smallerNumbersThanCurrent(int[] nums) {
        int [] temp=Arrays.copyOf(nums, nums.length); //This will make a copy
        Arrays.sort(temp);
        Map<Integer,Integer> hash=new HashMap<>();
        for(int i=0;i<temp.length;i++){
            if(!hash.containsKey(temp[i])){
                hash.put(temp[i],i);
            }
        }
        int [] res=new int[nums.length];
        for(int i=0;i<nums.length;i++){
            res[i]=hash.get(nums[i]);
        }
        return res;
    }

    //Question -6  Minimum Time Visiting All Points
    //Leet Code URL - > https://leetcode.com/problems/minimum-time-visiting-all-points/
    public int minTimeToVisitAllPoints(int[][] points) {
        int res=0;
        for (int i = 0; i < points.length - 1; i++) {
            int x1 = points[i][0];
            int y1 = points[i][1];

            int x2 = points[i + 1][0];
            int y2 = points[i + 1][1];
            res+=Math.max(Math.abs(y2-y1),Math.abs(x2-x1));
        }
        return res;
    }
    //Question -7 Spiral Matrix
    //Leet Code URL - > https://leetcode.com/problems/spiral-matrix/

    public List<Integer> spiralOrder(int[][] mat) {
        int n=mat.length;
        int m=mat[0].length;
        List<Integer> res=new ArrayList<>();
        //define all starting points
        int left=0,right=m-1;
        int top=0,bottom=n-1;

        //traversing started
        //1.move from left to right
        for(int i=left;i<=right;i++){
            res.add(mat[top][i]);
        }
        top++; //move top to second row
        //2. Move from top to bottom
        for(int i=top;i<=bottom;i++){
            res.add(mat[i][right]);
        }
        right--;
        //3/move from  right to left
        if(top<=bottom){
            for(int i=right;i>=left;i--){
                res.add(mat[bottom][i]);
            }
            bottom--;
        }
        //4. Move from bottom to top
        if (left <= right) { //edge case handled
            for (int i = bottom; i >= top; i--)
                res.add(mat[i][left]);

            left++;
        }
        return res;
    }
    //Question -8 Number if Island
    //Leet Code URL - > https://leetcode.com/problems/number-of-islands/
    //The below solution give Time limit excedeed so I solved using one more approach where no extra [][] needed
    public int numIslands(char[][] grid) {
        int count=0;
        int n=grid.length;
        int m=grid[0].length;
        int vis[][] =new int[n][m];
        for(int row=0;row<n;row++){
            for (int col=0;col<m;col++){
                if(grid[row][col]=='1' && vis[row][col]==0){
                    count++;
                    bfsTraversal(row,col,vis,grid,n,m);
                }
            }
        }
        return count;
    }
    //supporting method for above question
    void bfsTraversal(int row,int col, int[][] vis , char [][] grid, int n, int m){
        //Declare Queue for the traversal purpose
        Queue<Pair> queue= new LinkedList<>();
        queue.add(new Pair(row,col));

        while (!queue.isEmpty()){
            int[] delrow = {-1, 0, 1, 0};
            int[] delcol = {0, 1, 0, -1};
            int currentRow=queue.peek().row;
            int currentCol=queue.peek().col;
            queue.remove();
            //traverse the adjacent and mark them visited
            for(int k=0;k<4;k++)
            {
                int newRow=currentRow+delrow[k];
                int newCol=currentCol+delcol[k];
                //chech for valid condition
                if(newRow>0 && newRow<=n && newCol>0 && newCol<=m && grid[newRow][newCol]=='1'){
                    vis[newRow][newCol]=1;
                    queue.add(new Pair(newRow,newCol));
                }
            }

        }

    }
    //Approach -2
    public int numIslandsSecondApproach(char[][] grid) {
        if (grid == null || grid.length == 0) return 0;

        int islands = 0;
        int rows = grid.length, cols = grid[0].length;

        for (int i = 0; i < rows; i++)
            for (int j = 0; j < cols; j++)
                if (grid[i][j] == '1') {
                    islands++;
                    bfs(grid, i, j);
                }

        return islands;
    }

    private void bfs(char[][] grid, int i, int j) {
        Queue<int[]> q = new LinkedList<>();
        q.offer(new int[]{i, j});
        grid[i][j] = '0';

        int[][] dirs = {{1,0}, {-1,0}, {0,1}, {0,-1}};

        while (!q.isEmpty()) {
            int[] curr = q.poll();
            for (int[] d : dirs) {
                int ni = curr[0] + d[0];
                int nj = curr[1] + d[1];

                if (ni >= 0 && nj >= 0 && ni < grid.length && nj < grid[0].length && grid[ni][nj] == '1') {
                    q.offer(new int[]{ni, nj});
                    grid[ni][nj] = '0';
                }
            }
        }
    }

    /**
     * ARRAYS TWO POINTER PATTERN STARTED
     *
     */
    //Question -9  Best Time to Buy and Sell Stock
    //Leet Code URL - > https://leetcode.com/problems/best-time-to-buy-and-sell-stock/
    public int maxProfit(int[] prices) {
        int maxProfit=0;
        int minPrice=Integer.MAX_VALUE;
        for(int i=0;i<prices.length;i++){
            minPrice=Math.min(minPrice,prices[i]); //As we remember the price on the Go so its part of Dynamic  PRogramming Topic
            maxProfit=Math.max(maxProfit,prices[i]-minPrice);
        }
        return maxProfit;
    }
    //Question -10 Leet Code 977. Squares of a Sorted Array
    //Leet Code URL - > https://leetcode.com/problems/squares-of-a-sorted-array/description/
    public int[] sortedSquares(int[] nums) {
        for(int i=0;i<nums.length;i++){
            nums[i]=Math.abs(nums[i]*nums[i]);
        }
        Arrays.sort(nums);
        return nums;

    }
    // TODO Second Approach using 2 pointer Not giving correct Answer
    public int[] sortedSquaresTwo(int[] nums) {
        int left=0;
        int right=nums.length-1;
        int res[]=new int[nums.length];
        while (left<=right){
            if(Math.abs(nums[left]*nums[left])<Math.abs(nums[right]*nums[right])){
                res[left]=Math.abs(nums[left]*nums[left]);
                left++;
            }
            else {
                res[right]=Math.abs(nums[right]*nums[right]);
                right--;
            }
        }
        return res;
    }

    public static void main(String[] args) {
        ArrayPattern arrayPattern=new ArrayPattern();

    }
}
