package dsapatternsseventyquestions;

import java.util.ArrayList;
import java.util.List;

public class BacktrackingPattern {
    //TODO Good level question
    //Question -22 Leet Code 784. Letter Case Permutation
    //Leet Code URL - > https://leetcode.com/problems/letter-case-permutation/description/
    //Leet Code solution Recursive tree URl -> https://leetcode.com/problems/letter-case-permutation/solutions/1068812/java-backtracking-with-recursion-tree-diagram-1ms-beats-100
    public List<String> letterCasePermutation(String S) {
        List<String> result = new ArrayList<>();
        recurse(S.toCharArray(), 0, result); //start DFS
        return result;
    }
    void recurse(char[] str, int pos, List<String> result) {
        //If we have reached a leaf in the recursion tree, save the result.
        if (pos == str.length) {
            result.add(new String(str));
            return;
        }
        //if the pos is a character then we have 2 cases like below
        if (Character.isLetter(str[pos])) {
            //If uppercase char, we make it lower case, and recurse.
            if (Character.isUpperCase(str[pos])) {
                str[pos] = Character.toLowerCase(str[pos]);
                //Start a new branch in the recursion tree, exploring options that are possible only if we had changed the case.
                recurse(str, pos + 1, result);
                //Backtracking. We undo the change so that we can start a new branch in the recursion tree.
                str[pos] = Character.toUpperCase(str[pos]);
            }
            //If lowercase, then we make it upper case, and recurse.
            else {
                str[pos] = Character.toUpperCase(str[pos]);
                recurse(str, pos + 1, result);
                //Backtracking as explained above.
                str[pos] = Character.toLowerCase(str[pos]);
            }
        }
       //in case of pos is number as well as in case of character case also we will move to next level with same character and pos+1
        //because as per question we want the permutation so
        recurse(str, pos + 1, result);
    }

    //TODO Learn Backtracking Chapter and then solve below questions
    //Question -23 Leet Code 78. Subsets
    //Leet Code URL - > https://leetcode.com/problems/subsets/

    //Question -24 Leet Code  Combinations
    //Leet Code URL - >

    //Question -25 Leet Code Permutations
    //Leet Code URL - >





    }
