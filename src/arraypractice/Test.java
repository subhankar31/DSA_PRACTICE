package arraypractice;

import java.util.*;
import java.util.stream.Collectors;

public class Test {
    // List of Emp . name and city , find from banglore location ..
    Employee emp1=new Employee(12,"Rakesh","Delhi");
    Employee emp2=new Employee(13,"Rajesh","Banglore");
    Employee emp3=new Employee(14,"Saroj","Banglore");
    public void filterOutOperation(){
        List<Employee> inputList=new ArrayList<>();
        inputList.add(emp1);
        inputList.add(emp2);
        inputList.add(emp3);

        List<Employee>outputList=inputList.stream()
                .filter(e->e.getLocation().equals("Banglore"))
                .collect(Collectors.toList());
                //.forEach(System.out::println);
        Employee filterredEmp=null;
        for (int i=0;i<outputList.size();i++){
            //System.out.println(outputList.get(i).getId()+);
            filterredEmp=outputList.get(i);
            printObject(filterredEmp);
        }



    }
    public void printObject(Employee emp){
        System.out.println(emp.getId());
        System.out.println(emp.getLocation());
        System.out.println(emp.getName());
    }

    public void streamExample(){
        List<Integer> inputList= Arrays.asList(1,2,3,4,5,6,7,8,9,10);
        List<Integer> oddList= new ArrayList<>();
        List<Integer> evenList= new ArrayList<>();

        int sum=0;

        evenList=inputList.stream()
                .filter(e->e%2==0)
                .map(e->sum+e)
                .collect(Collectors.toList());
    }

    //
    public void check(){
        int [] nums={2,1,3,4};
            int checkElement=nums[0];
            boolean flag=false;
            for(int i=0;i<=nums.length-2;i++){
                if(nums[i]<=nums[i+1] || nums[i]>=checkElement){
                    flag=true;
                }

            }
    }

    public void  generate(int numRows) {
        List<List<Integer>> finalList=new ArrayList<>();
        for(int row=1;row<=numRows;row++){
            List<Integer> tempList=new ArrayList<>();
            for(int column=1;column<=numRows;column++){
                tempList.add(nCr(row-1,column-1));
            }
            finalList.add(tempList);
        }

    }
    public int nCr(int n,int r){
        long res = 1;
        // calculating nCr:
        for (int i = 0; i < r; i++) {
            res = res * (n - i);
            res = res / (i + 1);
        }
        return (int) res;
    }

    public boolean isAnagram(String s, String t) {
        char[] sArray=s.toCharArray();
        char[] tArray=t.toCharArray();
        List<String> sList= new ArrayList<>();
        Map<Character,Integer> dataMap=new HashMap<>();
        for (int i=0;i<sArray.length;i++){
            int value=dataMap.getOrDefault(sArray[i],0);
            dataMap.put(sArray[i],value+1);
        }
        for (int i=0;i<tArray.length;i++){
            int value=dataMap.getOrDefault(tArray[i],0);
            dataMap.put(tArray[i],value+1);
        }
        Iterator iterator=dataMap.entrySet().iterator();
        boolean flag=true;
        while (iterator.hasNext()){
            Map.Entry mapElement
                    = (Map.Entry)iterator.next();
            int marks = ((int)mapElement.getValue() + 10);
            if ((int) mapElement.getValue()%2!=0){
                flag=false;
                break;
            }
        }
        return flag;
    }

    public void threeSum(){
        // sum of 3 element must be equal to sum
        //Constraint will be we cann't take similar element more than 1 time .
        int [] nums={1,2,3,4,2,2,1,3,4,3};
            Set<List<Integer>> outputSet=new HashSet<>();
            for(int i=0;i<nums.length;i++){
                for(int j=i+1;j<nums.length;j++){
                    for(int k=j+1;k<nums.length;k++){
                        if(nums[i]+nums[j]+nums[k]==0){
                            List<Integer>tempList=Arrays.asList(nums[i],nums[j],nums[k]);
                            tempList.sort(null);
                            outputSet.add(tempList);
                        }

                    }
                }
            }
            List<List<Integer>> finalResult=new ArrayList<>(outputSet);
             System.out.println(finalResult);
    }

    public void threeSumOptimized(int [] nums){
        List<List<Integer>> finalList=new ArrayList<>();
        for (int i=0;i< nums.length;i++){
            int j=i+1;
            int k= nums.length-1;
            while (j<k) {
                if (i != 0 && nums[i] == nums[i - 1]) {
                    continue;
                }
                int sum = nums[i] + nums[j] + nums[k];
                if (sum < 0) {
                    j++;
                } else if (sum > 0) {
                    k--;
                } else {
                    List<Integer> tempList = Arrays.asList(nums[i], nums[j], nums[k]);
                    finalList.add(tempList);
                    while ( j<k && nums[j] == nums[j - 1]) {
                        j++;
                    }
                    while (j<k && nums[k] == nums[k + 1]) {
                        k--;
                    }

                }
            }
        }
    }
    public void maxArea() {
        //to store water
        //Q-11 LeetCode
        //Dos't runs all test cases
        int [] height={1,1};
        int maxCapacity=0;
        for(int i=0;i<=height.length-1;i++){
            for(int j=i+1;j<=height.length-1;j++){
                int minimumHeight= height[i]<height[j] ? height[i] : height[j];
                int capacity= minimumHeight*(j-i);
                if(capacity>maxCapacity) {
                    maxCapacity=capacity;
                }
            }
        }


    }
    public  void fact(){
        int [] nums={0,1,0,3,12};
        int snowBallSize = 0;
        for (int i=0;i<nums.length;i++){
            if (nums[i]==0){
                snowBallSize++;
            }
            else if (snowBallSize > 0) {
                int t = nums[i];
                nums[i]=0;
                nums[i-snowBallSize]=t;
            }
        }
    }

        public String[] findRelativeRanks() {
        int [] score = {10,3,8,9,4};
            int n = score.length;
            String[] result = new String[n];
            Integer[] indices = new Integer[n];

            for (int i = 0; i < n; i++) {
                indices[i] = i;
            }

            Arrays.sort(indices, (a, b) -> Integer.compare(score[b], score[a])); // Sort indices based on score

            for (int i = 0; i < n; i++) {
                if (i == 0) {
                    result[indices[i]] = "Gold Medal";
                } else if (i == 1) {
                    result[indices[i]] = "Silver Medal";
                } else if (i == 2) {
                    result[indices[i]] = "Bronze Medal";
                } else {
                    result[indices[i]] = Integer.toString(i + 1);
                }
            }

            return result;
        }
    public int[] resultArray() {
        //LeetCode 3069
        int [] nums={5,4,3,8};

        int len=nums.length;
        int a1[]=new int[len];
        int a2[]=new int[len];
        int ind1=1,ind2=1;
        a1[0]=nums[0];
        a2[0]=nums[1];
        for(int i=2;i<len;i++)
        {
            if(a1[ind1-1] > a2[ind2-1])
            {
                a1[ind1++]=nums[i];

            }
            else
            {
                a2[ind2++]=nums[i];
                //System.out.println(nums[i]);
            }
        }
        for(int i=0;i<ind1;i++)
        {
            if(a1[i]!=0)
            {
                nums[i]=a1[i];
            }
        }
        for(int i=ind1,j=0;j<ind2;j++,i++)
        {
            if(a2[j]!=0)
            {
                nums[i]=a2[j];
            }
        }
        return nums;

    }
    public static List<List<Integer>> mergeOverlappingIntervals() {
        //LeetCode 56
        //Brute Force Solution
        int[][] arr = {{1, 3}, {8, 10}, {2, 6}, {15, 18}};
        int n = arr.length; // size of the array
        //sort the given intervals:
        Arrays.sort(arr, new Comparator<int[]>() {
            public int compare(int[] a, int[] b) {
                return a[0] - b[0];
            }
        });

        List<List<Integer>> ans = new ArrayList<>();

        for (int i = 0; i < n; i++) { // select an interval:
            int start = arr[i][0];
            int end = arr[i][1];

            //Skip all the merged intervals:
            if (!ans.isEmpty() && end <= ans.get(ans.size() - 1).get(1)) {
                continue;
            }

            //check the rest of the intervals:
            for (int j = i + 1; j < n; j++) {
                if (arr[j][0] <= end) {
                    end = Math.max(end, arr[j][1]);
                } else {
                    break;
                }
            }
            ans.add(Arrays.asList(start, end));
        }
        return ans;
    }

    public void merge() {
        //Leetcode 88
        int [] nums1 = {1,2,3,0,0,0};
        int  m = 3;
        int [] nums2 = {2,5,6};
        int n = 3;
        for (int j = 0, i = m; j < n; j++) {
            nums1[i] = nums2[j];
            i++;
        }
        Arrays.sort(nums1);

    }
    public void intersection() {
        int [] nums1={1,2,2,1};
        int [] nums2={2,2};
        Set<Integer> outputSet =new HashSet<>();
        Arrays.sort(nums1);
        Arrays.sort(nums2);
        int first=0;
        int second=0;

        while(first<=nums1.length && second<=nums2.length){
            int minValue=Integer.MIN_VALUE;
            if(nums1[first]==nums2[second]){
                outputSet.add(nums1[first]);
                first++;
                second++;
            }
            else if(nums1[first]>nums2[second]){
                second++;
            }
            else first++;
        }

    }
    public int missingNumber() {
        int [] nums={9,6,4,2,3,5,7,0,1};
        Arrays.sort(nums);
        int res = nums.length;

        for (int i = 0; i < nums.length; i++) {
            res += i - nums[i];
        }

        return res;
    }
    public boolean find132pattern() {
       int [] nums = {3,5,0,3,4};
        int first=0;
        int last=2;
        boolean isPatternExist=false;
        while(last!=nums.length){
            int mid=(last+first)/2;
            if(nums[first]<nums[mid] && nums[mid]>nums[last] && nums[last]>nums[first]){
                isPatternExist=true;
                break;
            }
            first++;
            last++;
        }
        return isPatternExist;
    }
    public int[] searchRange() {

        int [] nums={5,7,7,8,8,10};
        int target=8;
        int []result={-1,-1};
        int first=0;
        int last=nums.length-1;
        boolean stopFirst=false;
        boolean stopLast=false;
        while(first<last){
            if(nums[first]==target){
                if(result[0]==-1){
                    result[0]=first;
                    stopFirst=true;
                }

            }

            if(nums[last]==target){
                if(result[1]==-1){
                    result[1]=last;
                    //stop last
                    stopLast=true;
                }

            }
            if(stopFirst!=true){
                first++;
            }

            if (stopLast!=true){
                last--;
            }



        }
        return result;
    }
    //finding sqrt using binary search
    public void sqrtUsingBinSerach(){
        int n=144; //Ans will be 6
        int low=1;
        int high=n;
        int result=1;
        while(low<=high){
            int mid=(low+high)/2;
            if(mid*mid==n){
                result=mid;
                break;
            } else if (mid*mid>n) {
                high=mid-1;
            }
            else low=mid+1;
        }
        System.out.println(result);
    }
        public void  candy() {
        int [] ratings ={1,0,2};
            int [] left=new int[ratings.length];
            int [] right=new int[ratings.length];
            left[0]=1;
            right[ratings.length-1]=1;
            for(int i=1;i<ratings.length;i++){
                if(ratings[i]>ratings[i-1]){
                    left[i]=left[i-1]+1;
                }
                else left[i]=1;
            }
            for(int i=ratings.length-2;i>=0;i--){
                if(ratings[i]>ratings[i+1]){
                    right[i]=right[i+1]+1;
                }
                else right[i]=1;
            }
            int count=0;
            for(int i=0;i<left.length;i++){
                left[i]=Math.max(left[i],right[i]);
                count=count+left[i];
            }
            Queue<Integer> queue=new LinkedList<Integer>();
           // queue.
        }

}
