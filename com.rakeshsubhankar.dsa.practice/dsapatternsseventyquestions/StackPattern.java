package dsapatternsseventyquestions;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

class Pair {
    int curr;
    int min;

    public Pair(int curr, int min) {
        this.curr = curr;
        this.min = min;
    }
}
public class StackPattern {
    //Question -33 Leet Code 155. Min Stack
    //Leet Code URL - > https://leetcode.com/problems/min-stack/description/
    void minStack(){
        //Here we took Stack only not array or list but changed the feature to support Min Stack
        Stack<Pair> st = new Stack<>();
    }
    public void push(int val,Stack<Pair> st) {
        if (!st.isEmpty()) {
            //we take the peak element and compare with current val to get minimum
            int minVal = Math.min(val, st.peek().min);
            st.push(new Pair(val, minVal)); //push the element as well as minimum
        } else {
            st.push(new Pair(val, val)); //else if its first time then val will be min only
        }
    }
    public void pop(Stack<Pair> st) {
        st.pop();
    }
    public int top(Stack<Pair> st) {
        return st.peek().curr;
    }
    public int getMin(Stack<Pair> st) {
        return st.peek().min;
    }

    //Question -34 Leet Code 20. Valid Parentheses
    //Leet Code URL - > https://leetcode.com/problems/valid-parentheses/description/

    /**
     * Given a string s containing just the characters '(', ')', '{', '}', '[' and ']', determine if the input string is valid.
     * An input string is valid if:
     * Open brackets must be closed by the same type of brackets.
     * Open brackets must be closed in the correct order.
     * Every close bracket has a corresponding open bracket of the same type.
     * Example 1:
     * Input: s = "()"
     * Output: true
     */
    public boolean isValid(String s) {
        Stack<Character> stack = new Stack<Character>(); // create an empty stack
        for (char c : s.toCharArray()) { // loop through each character in the string
            if (c == '(') // if the character is an opening parenthesis
                stack.push(')'); // push the corresponding closing parenthesis onto the stack
            else if (c == '{') // if the character is an opening brace
                stack.push('}'); // push the corresponding closing brace onto the stack
            else if (c == '[') // if the character is an opening bracket
                stack.push(']'); // push the corresponding closing bracket onto the stack
            else if (stack.isEmpty() || stack.pop() != c) // if the character is a closing bracket
                // if the stack is empty (i.e., there is no matching opening bracket) or the top of the stack
                // does not match the closing bracket, the string is not valid, so return false
                return false;
        }
        // if the stack is empty, all opening brackets have been matched with their corresponding closing brackets,
        // so the string is valid, otherwise, there are unmatched opening brackets, so return false
        return stack.isEmpty();
    }
    //Question -35 Leet Code 150. Evaluate Reverse Polish Notation
    //Leet Code URL - > https://leetcode.com/problems/evaluate-reverse-polish-notation/description/


    public int evalRPN(String[] tokens) {
        Stack<Long> stack = new Stack<>();
        int n = tokens.length;
        for (int i = 0; i < n; i++) {
            //check if its operator then pop 2 element from stack to do calculation
            if (tokens[i].length() == 1 && tokens[i].charAt(0) < 48) {
                long integer2 = stack.pop();
                long integer1 = stack.pop();
                char operator = tokens[i].charAt(0);
                long resolvedAns = resolves(integer1, integer2, operator);
                stack.push(resolvedAns);
            } else {
                //if its Operand then simply put to stack
                stack.push(Long.parseLong(tokens[i]));
            }
        }
        //asways stack top stores the result
        return stack.pop().intValue();
    }
    long resolves(long a, long b, char Operator) {
        if (Operator == '+') return a + b;
        else if (Operator == '-') return a - b;
        else if (Operator == '*') return a * b;
        return a / b;
    }
    //Question -36 Leet Code
    //Leet Code URL - >

}
