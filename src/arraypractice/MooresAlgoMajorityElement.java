package arraypractice;

import java.util.*;

public class MooresAlgoMajorityElement {
    //Find the Majority Element that occurs more than N/2 times
    public void majorityElementOptimizedSoln(){
        int[] arr = {2, 2, 1, 1, 1, 2, 2};
        int n = arr.length;
        int cnt = 0; // count
        int el = 0; // Element

        //applying the algorithm:
        for (int i = 0; i < n; i++) {
            if (cnt == 0) {
                cnt = 1;
                el = arr[i];
            } else if (el == arr[i]) cnt++;
            else cnt--;
        }

        int cnt1 = 0;
        for (int i = 0; i < n; i++) {
            if (arr[i] == el) cnt1++;
        }

        if (cnt1 > (n / 2)) System.out.println(el);
    }

    //Q-2 // for Majority Element >N/3
    public List<Integer> majorityElement(int[] nums) {
        int countOne=0;
        int countTwo=0;
        int elementOne=Integer.MIN_VALUE;
        int elementTwo=Integer.MIN_VALUE;
        for(int i=0;i<=nums.length-1;i++){
            if(countOne==0 && nums[i]!=elementTwo){
                countOne=1;
                elementOne=nums[i];
            }
            else if(countTwo==0 && nums[i]!=elementOne){
                countTwo=1;
                elementTwo=nums[i];
            }
            else if(nums[i]==elementOne){
                countOne++;
            }
            else if (nums[i]==elementTwo){
                countTwo++;
            }
            else{
                countOne--;
                countTwo--;
            }
        }
        int finalCountOne=0;
        int finalCountTwo=0;
        for(int i=0;i<=nums.length-1;i++){
            if(nums[i]==elementOne){
                finalCountOne++;
            }
            if(nums[i]==elementTwo){
                finalCountTwo++;
            }
        }
        List<Integer> finalList=new ArrayList<>();
        int n=nums.length;
        int calc= (int)(n / 3) + 1;
        if(finalCountOne>=calc){
            finalList.add(elementOne);
        }
        if(finalCountTwo>=calc){
            finalList.add(elementTwo);
        }
        return finalList;
    }
    public boolean isPowerOfTwo(int n) {
        boolean checkFlag=false;
        for(int i=0;i<=31;i++){
            if(n==Math.pow(2,i)){
                checkFlag=true;
            }
        }

        return checkFlag;

    }

}
