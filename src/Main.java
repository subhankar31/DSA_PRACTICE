import arraypractice.ArraysPractice;
import arraypractice.MaxSubArrayOfSum;
import arraypractice.Test;

import java.util.Arrays;
import java.util.List;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        Test test=new Test();
        test.candy();

    //ArraysPractice arraysPractice=new ArraysPractice();
    //arraysPractice.streamExample();
    //TestClassPractice testClassPractice=new TestClassPractice();
    //boolean result= testClassPractice.isPalindrome(121);
    //testClassPractice.leftRotateByOnePlace();
        /*Test testObject=new Test();
        testObject.filterOutOperation();*/



    }

    //com.mercedes.checkout.merc
    //PayPalMerchantService
    //DAO/jpa

    //14columns -->Dynamo Db
    //e commerce, paypal .
    //ACID -

    public void maxSum(){
        /**
         * Input  : arr[] = {100, 200, 300, 400}
         *          k = 2
         * Output : 700
         * Input  : arr[] = {1, 4, 2, 10, 23, 3, 1, 0, 20}
         *          k = 4
         * Output : 39
         * We get maximum sum by adding subarray {4, 2, 10, 23}
         * of size 4.
         * Input  : arr[] = {2, 3}
         *          k = 3
         * Output : Invalid
         * There is no subarray of size 3 as size of whole
         * array is 2.
         */

        int [] inputArray={100, 200, 300, 400};
        int sum=0;
        int i=0;
        int j= i+1;
        int k=2;
        while (j<=k-1){
            int currentSum=inputArray[i]+inputArray[j];
            if (sum<currentSum){
                sum=currentSum;
            }

        }
        //int k=2;
    }


}