package trees;

class Node {
    int data;
    Node left;
    Node right;

    // Constructor to initialize
    // the node with a value
    Node(int val) {
        data = val;
        left = null;
        right = null;
    }
}

class Solution {
    // Function to check if a binary tree is balanced
    public boolean isBalanced(Node root) {
        // If the tree is empty, it's balanced
        if (root == null) {
            return true;
        }

        // Calculate the height of left and right subtrees
        int leftHeight = getHeight(root.left);
        int rightHeight = getHeight(root.right);

        // Check if the absolute difference in heights
        // of left and right subtrees is <= 1
        if (Math.abs(leftHeight - rightHeight) <= 1 &&
                isBalanced(root.left) &&
                isBalanced(root.right)) {
            return true;
        }

        // If any condition fails, the tree is unbalanced
        return false;
    }

    // Function to calculate the height of a subtree
    public int getHeight(Node root) {
        // Base case: if the current node is NULL,
        // return 0 (height of an empty tree)
        if (root == null) {
            return 0;
        }

        // Recursively calculate the height
        // of left and right subtrees
        int leftHeight = getHeight(root.left);
        int rightHeight = getHeight(root.right);

        // Return the maximum height of left and right subtrees
        // plus 1 (to account for the current node)
        return Math.max(leftHeight, rightHeight) + 1;
    }

    /**
     * Given the root of a binary tree, return the length of the diameter of the tree.
     * Leet Code URL-> https://leetcode.com/problems/diameter-of-binary-tree/description/
     */

    //to store the result
        int maxDiameter=0;
        public void diameterOfBinaryTree(Node root) {
            //variable height is just to store the return value of recursive call
            //actucal result is stored inside maxDiameter
            int height=maxDiam(root);
        }
        int maxDiam(Node node){
            //leaf node case
            if(node==null) return 0;
            //traverse till end in DFS manner
            int lh=maxDiam(node.left);
            int rh=maxDiam(node.right);
            //here we update the max at each node based on the result from lh and rh
            maxDiameter=Math.max(maxDiameter,lh+rh);

            //returns the value
            return 1+Math.max(lh,rh);
        }
    /**
     * Maximum path sum
     * LeetCode HARD
     * URL- > https://leetcode.com/problems/binary-tree-maximum-path-sum/
     */

    int maxSum=Integer.MIN_VALUE;
    public int maxPathSum(Node root) {
        int result=calcSum(root);
        return maxSum;
    }
    int calcSum(Node node){
        if(node ==null) return 0;

        int lh=calcSum(node.left);
        if(lh<0) lh=0; //this is done to avoid negative values
        int rh=calcSum(node.right);
        if(rh<0) rh=0; //this is done to avoid negative values
        maxSum=Math.max(maxSum,lh+rh+node.data); //calculating the maxSum here

        //as we are concerned regarding max sum so we only return max between both lh and rh
        //along with the current node data
        return node.data + Math.max(lh,rh);
    }

}

// Main function
public class Main {
    public static void main(String[] args) {
        // Creating a sample binary tree
        Node root = new Node(1);
        root.left = new Node(2);
        root.right = new Node(3);
        root.left.left = new Node(4);
        root.left.right = new Node(5);
        root.left.right.right = new Node(6);
        root.left.right.right.right = new Node(7);

        // Creating an instance of the Solution class
        Solution solution = new Solution();
        solution.getHeight(root);

        // Checking if the tree is balanced
        if (solution.isBalanced(root)) {
            System.out.println("The tree is balanced.");
        } else {
            System.out.println("The tree is not balanced.");
        }
    }
}
