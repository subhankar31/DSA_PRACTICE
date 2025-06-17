package string;

import java.util.Arrays;

public class StringPractice {
        public String reverseWords(String s) {
            String[] words = s.split("\\s+");
            StringBuilder res = new StringBuilder();

            for (int i = words.length - 1; i >= 0; i--) {
                res.append(words[i]);
                if (i != 0) {
                    res.append(" ");
                }
            }

            return res.toString().trim();
        }
    public String longestCommonPrefix() {
        String [] v={"flower","flow","flight"};
        StringBuilder ans = new StringBuilder();
        Arrays.sort(v);
        String first = v[0];
        String last = v[v.length-1];
        for (int i=0; i<Math.min(first.length(), last.length()); i++) {
            if (first.charAt(i) != last.charAt(i)) {
                return ans.toString();
            }
            ans.append(first.charAt(i));
        }
        return ans.toString();
    }
    public boolean isIsomorphic() {

        String s="paper";
        String t="title";
        // Create arrays to store the index of characters in both strings
        int[] indexS = new int[200]; // Stores index of characters in string s
        int[] indexT = new int[200]; // Stores index of characters in string t

        // Get the length of both strings
        int len = s.length();

        // If the lengths of the two strings are different, they can't be isomorphic
        if(len != t.length()) {
            return false;
        }

        // Iterate through each character of the strings
        for(int i = 0; i < len; i++) {
            // Check if the index of the current character in string s
            // is different from the index of the corresponding character in string t
            if(indexS[s.charAt(i)] != indexT[t.charAt(i)]) {
                return false; // If different, strings are not isomorphic
            }

            // Update the indices of characters in both strings
            indexS[s.charAt(i)] = i + 1; // updating index of current character
            indexT[t.charAt(i)] = i + 1; // updating index of current character
        }

        // If the loop completes without returning false, strings are isomorphic
        return true;
    }


    public boolean isAnagram() {
        String s="anagram";
        String t="nagaram";
        int[] alphabet = new int[26];
        for (int i = 0; i < s.length(); i++) alphabet[s.charAt(i) - 'a']++;
        for (int i = 0; i < t.length(); i++) alphabet[t.charAt(i) - 'a']--;
        for (int i : alphabet) if (i != 0) return false;
        return true;
    }
    public int romanToInt() {
            String s="LVIII";
        int ans = 0, num = 0;
        for (int i = s.length() - 1; i >= 0; i--) {
            switch (s.charAt(i)) {
                case 'I':
                    num = 1;
                    break;
                case 'V':
                    num = 5;
                    break;
                case 'X':
                    num = 10;
                    break;
                case 'L':
                    num = 50;
                    break;
                case 'C':
                    num = 100;
                    break;
                case 'D':
                    num = 500;
                    break;
                case 'M':
                    num = 1000;
                    break;
            }
            if (4 * num < ans)
                ans -= num;
            else
                ans += num;
        }
        return ans;

    }
    public int myAtoi() {
        String s="2";
        // Check for empty spaces
        s = s.trim();
        if(s.length()==0) return 0;
        // check for the sign
        int sign = 1;
        int i = 0;
        char c = s.charAt(0);
        if (c == '-') {
            sign = -1;
            i++;
        } else if (c == '+') {
            i++;
        }
        long sum = 0;
        while (i < s.length()) {
            char currChar = s.charAt(i);
            // check for non integer characters
            if (currChar < '0' || currChar > '9') break;
            // check for integer characters
            int a = currChar - '0';
            // formulate the answer
            sum = sum * 10 + a;
            // check for the overflow condition
            if (sign == 1 && sum > Integer.MAX_VALUE) return Integer.MAX_VALUE;
            if (sign == -1 && -sum < Integer.MIN_VALUE) return Integer.MIN_VALUE;

            i++;
        }
        // return the answer with the sign
        return (int) (sign * sum);
    }

    public String reverseWords() {
        String s="the sky is blue";
        String[] words = s.split("\\s+");
        StringBuilder res = new StringBuilder();

        for (int i = words.length - 1; i >= 0; i--) {
            res.append(words[i]);
            if (i != 0) {
                res.append(" ");
            }
        }

        return res.toString().trim();
    }


}
