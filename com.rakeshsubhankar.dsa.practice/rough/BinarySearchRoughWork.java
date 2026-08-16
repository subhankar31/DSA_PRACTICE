package rough;

import java.util.Arrays;

public class BinarySearchRoughWork {
    /**
     * This class covers all Questions from Binary Search Chapter
     */

    //Search Insert Position
    //https://leetcode.com/problems/search-insert-position/
    public int searchInsert(int[] nums, int target) {
        int res=-1;
        int low=0;
        int high=nums.length-1;
        while (low<=high){
            int mid=(low+high)/2;
            if (nums[mid]==target) return mid;
            else if (nums[mid]> target) high=mid-1;
            else low=mid+1;
        }
        return res;
    }
    //find first and last occurance
    //https://leetcode.com/problems/find-first-and-last-position-of-element-in-sorted-array/description/
    public int[] searchRange(int[] nums, int target) {
        int [] res=new int[2];
        Arrays.fill(res,-1);
        int low=0;
        int high=nums.length-1;
        int res1=-1;
        while (low<=high){
            int mid=(low+high)/2;
            if (nums[mid]==target){
                high=mid-1; // search more left to get first insert position
                res1=mid;
            }
            else if (nums[mid]>target) high=mid-1;
            else low=mid+1;
        }
        //check
        if (res1== -1) return res;
        //otherwise search for right most insert position
        low=0;
        high=nums.length-1;
        int res2=-1;
        while (low<=high){
            int mid=(low+high)/2;
            if (nums[mid]==target){
                res2=mid;
                low=mid+1;
            }else if (nums[mid]>target) high=mid-1;
            else low=mid+1;
        }
        res[0]=res1;
        res[1]=res2;
        return res;
    }
    //search in Rotated Sorted Array
    //https://leetcode.com/problems/search-in-rotated-sorted-array/
    public int search(int[] nums, int target) {
        int low=0;
        int high=nums.length-1;
        while (low <= high){
            int mid=(low+high)/2;
            if (nums[mid]==target) return mid;
            //if you are in increasing part
            if (nums[mid] >= nums[low]){
                //if element exist
                if (nums[low]<= target && target <= nums[mid]){
                    high=mid-1;
                }else low=mid+1;
            }else{
                if (nums[mid]<= target && target <= nums[high]){
                    low=mid+1;
                }else high=mid-1;
            }
        }
        return -1;
    }
    //search in rotated sorted array part-II
    //https://leetcode.com/problems/search-in-rotated-sorted-array-ii/
    public boolean search2(int[] arr, int k) {
        int low=0;
        int high=arr.length-1;
        while (low <= high){
            int mid=(low+high)/2;
            if (arr[mid]==k) return true;
            //check for duplicate
            if (arr[low]==arr[mid] && arr[mid]==arr[high]){
                low++;
                high--;
                continue;
            }
            //check if left is sorted
            if (arr[low]<= arr[mid]){
                //check if element exist
                if (arr[low]<=k && k <= arr[mid]){
                    high=mid-1;
                }else low=mid+1;
            }
            //check if right is sorted
            else{
                if (arr[mid] <= k && k<= arr[high]){
                    low=mid+1;
                }else high=mid-1;
            }
        }
        return false;
    }
    //Find minimum in rotated sorted array
    //https://leetcode.com/problems/find-minimum-in-rotated-sorted-array/description/
    //Same as above just keep a min variable and look into sorted section to get more min

    //Q- Single element in a sorted Array
    //https://leetcode.com/problems/single-element-in-a-sorted-array/
    public int singleNonDuplicate(int[] arr) {
        int singleEle=-1;
        int low=1;
        int high=arr.length-2;
        //edge cases
        if (arr.length==1) return arr[0];
        if(arr[0] != arr[1]) return arr[0];
        if (arr[arr.length-1]!= arr[arr.length-2]) return arr[arr.length-1];
        while (low <= high){
            int mid=(low+high)/2;
            //check if its unique
            if (arr[mid]!= arr[mid-1] && arr[mid]!= arr[mid+1]) return mid;
            //if we are in left half before unique element
            //i.e odd- even pair
            if ((mid %2==1 && arr[mid]==arr[mid-1]) ||
                    (mid%2==0 && arr[mid]== arr[mid+1])){
                //then go to right to get unique element
                low=mid+1;
            }else high=mid-1;
        }
        return singleEle;
    }
    //find peak element
    //https://leetcode.com/problems/find-peak-element/description/
    public int findPeakElement(int[] nums) {
        int low=1;
        int high=nums.length-2;
        if (nums.length==1) return  0;
        if (nums[0]>nums[1]) return 0;
        if (nums[nums.length-1] > nums[nums.length-2]) return nums.length-1;
        while (low <= high){
            int mid=(low+high)/2;
            if (nums[mid] > nums[mid-1] && nums[mid] > nums[mid+1]) return mid;
            if (nums[mid] > nums[mid-1]) low=mid+1;
            else high=mid-1;
        }
        return -1;
    }
    //KOKO Eating Bananas
    //https://leetcode.com/problems/koko-eating-bananas/description/public int minEatingSpeed(int[] v, int h) {
    public int minEatingSpeed(int[] v, int h) {
        int low=1;
        int high=findMax(v);

        while (low <= high){
            int mid=(low+high)/2;
            if (possible(v,mid,h)) high=mid-1;
            else low=mid+1;
        }
        return low;
    }
    boolean possible(int [] v,int mid,int totalh){
        int count=0;
        for (int i=0;i<v.length;i++){
            count+=Math.ceil((double) (v[i])/(double) (mid));
        }
        return count <= totalh;
    }
    int findMax(int [] v){
        int max=Integer.MIN_VALUE;
        for (int num:v) max=Math.max(max,num);
        return max;
    }
    //Minimum days to make m bouquets
    //https://leetcode.com/problems/minimum-number-of-days-to-make-m-bouquets/description/
    public int minDays(int[] arr, int m, int k) {
        //base condition
        if (arr.length < m*k) return -1;
        int low=1;
        int high=findMax(arr);
        while (low <= high){
            int mid=(low+high)/2;
            if (bPossible(arr,mid,m,k)){
                high=mid-1;
            }else low=mid+1;
        }
        return low;
    }
     boolean bPossible(int [] arr, int mid, int m , int k){
        int count=0;
        int totalB=0;
        for (int i=0;i < arr.length; i++){
            if (arr[i] <= mid){
                count++;
            }else{
                totalB+=count/k;
                count=0;
            }
        }
        totalB+=count/k;
        return  totalB >= m*k;
     }
     //find the smallest divisor
    // skipping as its same as above

    //Leet code capcaity to ship packages within D days
    //https://leetcode.com/problems/capacity-to-ship-packages-within-d-days/description/
    public int shipWithinDays(int[] weights, int days) {
        int low=findLow(weights);
        int high=findHigh(weights);
        while (low <= high){
            int mid=(low+high)/2;
            if (ship(weights,mid,days)) high=mid-1;
            else low=mid+1;
        }
        return low;
    }
     boolean ship(int [] weights, int mid, int days){
        int weight=0;
        int day=1;
        for (int i=0;i<weights.length;i++){
            if (weight > mid){
                day+=1;
                weight=weights[i];
            }else weight+=weights[i];
        }
        //check
         return day <= days;
     }
    int findLow(int [] weights){
        int max=Integer.MIN_VALUE;
        for (int weight:weights) max=Math.max(max,weight);
        return max;
    }
    int findHigh(int [] weights){
        int sum=0;
        for (int weight:weights) sum+=weight;
        return sum;
    }

    // kth missing number
    //https://leetcode.com/problems/kth-missing-positive-number/
    public int findKthPositive(int[] arr, int k) {
        int low=0;
        int high=arr.length-1;
        while (low <= high){
            int mid=(low+high)/2;
            //calculate current missing '
            int missing=arr[mid]-(mid+1);
            if (missing < k) low=mid+1;
            else high=mid-1;
        }
        //once the BS over low and high will cross each other at a point where k lies in between low and high
        //formulla to return res
        return high+k+1;
    }
    //Q- Allocate Books || Painters Problem || Split Array Largest Sum all are same pattern
    public int splitArray(int[] nums, int k) {
        int low=findLow(nums);
        int high=findHigh(nums);

        while (low <= high){
            int mid=(low+high)/2;
            if (splitNotPossible(nums,mid,k)){
                low=mid+1; //increase more to get within k
            }else high=mid-1;
        }
        return low;
    }
    public boolean splitNotPossible(int [] nums, int mid, int k){
        int subArray=1;
        int sum=0;
        for (int num:nums){
            if (sum+num > mid){
                subArray++;
                sum=num;
            } else sum+=num;
        }
        //handle return
        return subArray > k;
    }
    //Median of 2 sorted Arrays
    //leetCode HARD
    //skipped code since not fully comfortable , added to re watch queue

    //Question staretd for Binary Search in Matrix
    //search in 2D matrix
    //https://leetcode.com/problems/search-a-2d-matrix/description/
    public boolean searchMatrix(int[][] matrix, int target) {
        int low=0;
        int high=matrix.length-1;
        int row=matrix.length;
        int col=matrix[0].length;

        while (low <= high){
            int mid=(low+high)/2;
            //check if target exist in this mid row
            if (matrix[mid][0] <= target && target <= matrix[mid][col-1]){
                return binarySearch(matrix[mid],target);
            }else if (matrix[mid][0] < target) low=mid+1;
            else high=mid-1;
        }
        return false; //default return
    }
    public boolean binarySearch(int [] matrix, int target){
        int low=0;
        int high=matrix.length-1;
        while (low <= high){
            int mid=(low+high)/2;
            if (matrix[mid]==target) return true;
            else if (matrix[mid]< target) low=mid+1;
            else high=mid-1;
        }
        return false;
    }
    //{art II search in 2D matrix where row and column both are sorted
    //https://leetcode.com/problems/search-a-2d-matrix-ii/description/
    public boolean searchMatrix2(int[][] matrix, int target) {
        int row=0;
        int col=matrix[0].length-1;

        while (row <= matrix.length-1 && col >=0){
            int element= matrix[row][col];
            if (element == target) return true;
            else if (element < target) row++;
            else col--;
        }
        return false;
    }
    //Find Peak element 2
    //https://leetcode.com/problems/find-a-peak-element-ii/description/
    public int[] findPeakGrid(int[][] arr) {
        //declare low as 0 and high as last column index
        int low=0;
        int high=arr[0].length;
        while (low <= high){
            int mid=(low+high)/2;
            //check max element row in mid column
            int row=checkMax(arr,mid);
            //calculate its left and right
            int left= mid-1 > 0 ? arr[row][mid-1] : Integer.MIN_VALUE;
            int right=mid+1 < arr[0].length ? arr[row][mid+1] : Integer.MIN_VALUE;

            //check if its highest and add to res.
            if (arr[row][mid] > left && arr[row][mid] >  right) {
                return new int[]{row,mid};
            }else if(left > arr[row][mid]) high=mid-1;
            else low=mid+1;
        }
        return new int[]{-1,-1};
    }
    int checkMax(int [][] arr, int midCol ){
        int max=Integer.MIN_VALUE;
        int index=-1;
        for (int i=0;i<arr.length;i++){
            if (arr[i][midCol] > max){
                max=arr[i][midCol];
                index=i;
            }

        }
        return index;
    }











        public static void main(String[] args) {

    }
}
