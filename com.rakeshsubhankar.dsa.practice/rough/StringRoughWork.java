package rough;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

public class StringRoughWork {
    //Q Remove outermost parenthesis
    //https://leetcode.com/problems/remove-outermost-parentheses/description/
    public String removeOuterParentheses(String s) {
        StringBuilder string=new StringBuilder();
        Stack<Character> st=new Stack<>();
        for (Character c:s.toCharArray()){
            if (c=='('){
                if (!st.isEmpty()){
                    string.append('(');
                }
                st.push('(');
            }else{
                st.pop();
                if (!st.isEmpty()){
                    string.append(')');
                }

            }
        }
        return string.toString();
    }
    //Reverse a word in a String
    //https://leetcode.com/problems/reverse-words-in-a-string/
    public String reverseWords(String s) {
        String [] words=s.split("\\s+");
        StringBuilder res=new StringBuilder();
        for (int i=words.length-1;i>=0;i--){
            res.append(words[i]);
            //add space
            if (i != 0) res.append(" ");
        }
        return res.toString().trim();
    }

    //Largest odd number in a String
    //https://leetcode.com/problems/largest-odd-number-in-string/
    public String largestOddNumber(String num) {
        //base check
        if (num.charAt(num.length()-1)%2==1) return num;

        int i=num.length()-2;
        while (i >= 0){
            //pass i+1 since in java end index is excluded during substring calculation
            if (num.charAt(i)%2==1) return num.substring(0,i+1);
            i--;
        }
        return "";
    }
    //https://leetcode.com/problems/longest-common-prefix/
    //Longest common sequence
    public String longestCommonPrefix(String[] v) {
        Arrays.sort(v);
        StringBuilder res=new StringBuilder();
        String first=v[0];
        String last=v[v.length-1];
        //loop from start to end and check if its same
        for (int i=0;i<Math.min(first.length(),last.length());i++){
            //if its not same return result stored till now
            if (first.charAt(i) != last.charAt(i)){
                return res.toString();
            }
            res.append(first.charAt(i));
        }
        return res.toString();
    }
    //https://leetcode.com/problems/isomorphic-strings/
    //isomorphic String
    public boolean isIsomorphic(String s, String t) {
        //base case
        if (s.length() != t.length()) return false;
        //declre array to store data
        int [] indexS=new int[200];
        int [] indexT=new int[200];

        for (int i=0;i<s.length()-1;i++){
            //if not same
            if (indexS[s.charAt(i)] != indexT[t.charAt(i)]) return false;
            //update array at each step
            indexS[s.charAt(i)]=i+1;
            indexT[t.charAt(i)]=i+1;
        }
        return true;
    }
    //Rotate String
    //https://leetcode.com/problems/rotate-string/description/
    public boolean rotateString(String s, String goal){
        if (s.length() != goal.length()) return false;
        String newS=s+s;
        return newS.contains(goal);
    }
    //Check if 2 strings are Anagram or not
    //https://leetcode.com/problems/valid-anagram/
    public boolean isAnagram(String s, String t) {
        //create an Array to track charcters
        int [] alphabet=new int[200];
        for (int i=0;i<s.length();i++) alphabet[s.charAt(i)-'a']++;
        for (int i=0;i<t.length();i++) alphabet[t.charAt(i)-'a']--;
        for (int i=0;i<alphabet.length;i++) if (alphabet[i] !=0) return false;
        return true;
    }

    //Leet Code Medium for String Chapter
    // Sort character by freequency
    //https://leetcode.com/problems/sort-characters-by-frequency/
    public String frequencySort(String s) {
        //step-1 add to freq []
        int [] frew=new int[128];
        for (int i=0;i<s.length();i++){
            frew[s.charAt(i)]++;
        }
        //step 2 add to char array and sort based on freq in decreasing order
        List<Character> list=new ArrayList<>();
        for (int i=0;i<128;i++){
            if (frew[i]>0) list.add((char)i);
        }
        list.sort((a,b)->frew[b]-frew[a]);
        //step-3parse char array and add to result string based on n.o of available fre for that char
        StringBuilder res=new StringBuilder();
        for (char c:list){
            for (int i=0;i<frew[c];i++){
                res.append(c);
            }
        }
        return res.toString();
    }
    //Roman Number
    //URL
    public int romanToInt(String s) {
        int num=0;
        int sum=0;
        for (int i = s.length()-1;i>=0;i--){
            switch (s.charAt(i)){
                case 'I' : num=1; break;
                case 'V' : num=5; break;
                case 'X': num = 10; break;
                case 'L': num = 50; break;
                case 'C': num = 100; break;
                case 'D': num = 500; break;
                case 'M': num = 1000; break;
            }
            //core logic
            if (4* num < sum){
                sum-=num;
            }else sum+=num;
        }
        return sum;
    }
    //String to Integer (ATOI)
    //https://leetcode.com/problems/string-to-integer-atoi/
    public int myAtoi(String s) {
        // first trim
        s=s.trim();
        //check sign bit
        int signBit=1;
        int i=0;
        char c=s.charAt(0);
        if (c=='-') signBit=-1; i++;
        if (s.charAt(i)=='+') i++;
        int sum=0;
        while (i < s.length()){
            char cur=s.charAt(i);
            if (cur < '0' || cur >'9') break;
            int curInt=cur - '0';
            sum=sum*10+curInt;
            //check if reaches boundry
            if (signBit == 1 && sum > Integer.MAX_VALUE) return Integer.MAX_VALUE;
            if (signBit ==-1 && sum < Integer.MIN_VALUE) return  Integer.MIN_VALUE;
            i++;
        }
        return sum*signBit;
    }
    //Sum of Beauty of all Substring
    //https://leetcode.com/problems/sum-of-beauty-of-all-substrings/description/
    public int beautySum(String s) {
        int sum=0;
        //generate all substring using 2 loops
        for (int i=0;i< s.length(); i++){
            //create new freq[ ] reach time in outer for loop
            int [] freq=new int[26]; //only lower case
            for (int j=i;j<s.length();j++){
                char c=s.charAt(j);
                freq[c-'a']++;
                //declare min and max and check through loop
                int min=Integer.MAX_VALUE;
                int max=Integer.MIN_VALUE;
                for (int k=0;k<26;k++){
                    if (freq[k] > 0){
                        min=Math.min(min,freq[k]);
                        max=Math.max(max,freq[k]);
                    }
                }
                sum+=max-min;
            }
        }
        return sum;
    }







    public static void main(String[] args) {

    }
}
