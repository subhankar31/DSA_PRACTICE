package dsapatternsseventyquestions;

import dsapatternsseventyquestions.util.TreeNode;
import trees.BSTIterator;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class BinarySearchTreePattern {
    //Question -50 Leet Code 700. Search in a Binary Search Tree
    //Leet Code URL  -> https://leetcode.com/problems/search-in-a-binary-search-tree/

    public TreeNode searchBST(TreeNode root, int val) {
        while(root != null && root.val != val){
            root=val<root.val?root.left:root.right;
        }
        return root;

    }
    //Question -51 Leet Code 701. Insert into a Binary Search Tree
    //Leet Code URL  -> https://leetcode.com/problems/insert-into-a-binary-search-tree/
    public TreeNode insertIntoBST(TreeNode root, int val) {
        //if no nodes are there then create new node with given value  and return
        if(root==null) return new TreeNode(val);
        //else find the perfect position.
        TreeNode newNode=new TreeNode(val); //new node crearted
        while (true){
            //move left
            if (val<=root.val){
                if (root.left!=null) root=root.left; //if more left exist then traverse
                else {
                    root.left=newNode; //else we reached at leaf node and add the new Node
                    break;
                }
            }else{ // move right
                if (root.right!=null) root=root.right;
                else {
                    root.right=newNode;
                    break;
                }
            }
        }
    return root;
    }
    //Question -52 Leet Code 108. Convert Sorted Array to Binary Search
    //Leet Code URL  -> https://leetcode.com/problems/convert-sorted-array-to-binary-search-tree/description/

    public TreeNode sortedArrayToBST(int[] nums) {
        if(nums==null || nums.length==0) return null;

        return helperFunction(nums,0,nums.length-1);
    }
    public TreeNode helperFunction(int [] nums , int start , int end){
        //This null will be added to the leaf nodes left or right which will not impact anything
        //as its normal that every leaf nodes left and right will have null
        if(start>end) return null;// This is to handle condition of cross after completing
        int mid=start+end/2; // calculate mid position as that will be root for each subtree and main BST
        TreeNode newNode=new TreeNode(nums[mid]); //create new node using the value
        newNode.left=helperFunction(nums,start,mid-1); //crerate left subtree and right subtree
        newNode.right=helperFunction(nums,mid+1,end);

        return newNode;
    }

    //Question -53 Leet Code 653. Two Sum IV - Input is a BST
    //Leet Code URL  -> https://leetcode.com/problems/two-sum-iv-input-is-a-bst/description/

    /**
     * Brute Force
     * Step-1 Generate In-Order traversal and store in array or list
     * In order will always be sorted in nature
     * Step-2 Do regular 2 sum and find the answer
     * T.C- O(n) + O(n) and S.C O(n)
     */

    //Better Solution , This takes less memory in Leet Code compared to the optimized solution and CPU
    //difference is 1ms between Better and Optimized solution
    public boolean findTarget(TreeNode root, int k) {
        Set< Integer > set = new HashSet<>();
        return find(root, k, set);
    }
    public boolean find(TreeNode root, int k, Set < Integer > set) {
        if (root == null)
            return false;
        if (set.contains(k - root.val))
            return true;
        set.add(root.val);
        return find(root.left, k, set) || find(root.right, k, set);
    }
    //Optimized Solution Uses BST iterator approach
    //Time complexity O(n), space complexity O(height)
    //TODO Not fully clear , Need revision
    public boolean findTargetOptimized(TreeNode root, int k) {
        if(root==null) return false;
        BSTIterator l=new BSTIterator(root,false); //sorted pre order || small to large
        BSTIterator r=new BSTIterator(root,true);// reverse preorder || large to small

        int i=l.next(); //two pointer approach to find 2 sum
        int j=r.next(); // i points to min and j points to max value

        //common 2 pointer loop to find 2 sum
        while(i<j){
            if(i+j==k) return true;
            else if(i+j<k){
                i=l.next();
            }else j=r.next();
        }
        return false;
    }

    //Question -54 Leet Code 235. Lowest Common Ancestor of a Binary Search Tree
    //Leet Code URL  ->  https://leetcode.com/problems/lowest-common-ancestor-of-a-binary-search-tree/description/

    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null) return null; //base case
        int cur=root.val; //extracted value for comparison

        //if searching node value is greater than current then its on right subtree
        //here we need to be careful that below we don't just do subtree recursive call rather return the result from
        //there itself , do rough work to better understand , ex - we will get result 2 in the 2nd recursive iteration for the given
        //question and from there we will return 2 which is again returned till first recursive call and we get result as 2
        //Ask chatGpt for clarity if confusion is there
        if(cur<p.val && cur<q.val){
            return lowestCommonAncestor(root.right,p,q);
        }
        if(cur>p.val && cur>q.val){
            return lowestCommonAncestor(root.left,p,q);
        }
        //that means the current root is either equal to p or q so this is LCA
        return root;

    }
    //Question -55 Leet Code 530. Minimum Absolute Difference in BST
    //Leet Code URL  ->  https://leetcode.com/problems/minimum-absolute-difference-in-bst/description/
        TreeNode prev = null;
        int min = Integer.MAX_VALUE;
        public int getMinimumDifference(TreeNode root) {
            if (root == null) return 0;
            inOrder(root);
            return min;
        }

        public void inOrder(TreeNode root) {
            if (root == null) return;
            inOrder(root.left);
            if (prev != null) {
                min = Math.min(min, Math.abs(root.val - prev.val));
            }
            prev = root;
            inOrder(root.right);
        }
    //Question -56 Leet Code 1382. Balance a Binary Search Tree
    //Leet Code URL  ->  https://leetcode.com/problems/balance-a-binary-search-tree/
    /**
     * Step-1 from Given Tree do In-Order traversal and store result
     * Step-2 Use the list and create binary tree by using previously solved algo Question -52
     */
    public TreeNode balanceBST(TreeNode root) {
        List<Integer> sortedList = new ArrayList<>();
        inorderTraversal(root, sortedList); //perform Step-1 here
        return buildBalancedBST(sortedList, 0, sortedList.size() - 1); //step-2
    }

    private void inorderTraversal(TreeNode node, List<Integer> list) {
        if (node == null) return;
        inorderTraversal(node.left, list);
        list.add(node.val); //in-order
        inorderTraversal(node.right, list);
    }

    private TreeNode buildBalancedBST(List<Integer> list, int start, int end) {
        if (start > end) return null;

        int mid = start + (end - start) / 2;
        TreeNode root = new TreeNode(list.get(mid));
        root.left = buildBalancedBST(list, start, mid - 1);
        root.right = buildBalancedBST(list, mid + 1, end);
        return root;
    }
    //Question -57 Leet Code 450. Delete Node in a BST
    //Leet Code URL  ->  https://leetcode.com/problems/delete-node-in-a-bst/
    //TODO Important and bit complex || Need revision
    /**
     * Intuition:
     * In a BST, we can locate nodes efficiently. Deleting depends on cases:
     * 0 or 1 child → replace with child.
     * 2 children → place right child at the right end of left child or vice versa
     */
    public TreeNode deleteNode(TreeNode root, int key) {
        if(root == null) return null;
        if(root.val == key) return helper(root);

        TreeNode dummy = root;

        while(root != null){
            if(root.val > key){ //then key node lies of left half
                if(root.left != null && root.left.val == key){
                    root.left = helper(root.left);
                    break;
                }else{
                    root = root.left;
                }
            }else{ //then key node lies on right half
                if(root.right != null && root.right.val == key){
                    root.right = helper(root.right);
                    break;
                }else{
                    root = root.right;
                }
            }
        }
        return dummy;
    }

    public TreeNode helper(TreeNode root){
        //if it has only single child or no child
        if(root.left == null) return root.right;
        else if(root.right == null) return root.left;
            //if it has 2 child || core logic part
        else{
            TreeNode rightChild = root.right; //take the right child
            TreeNode lastRight = findLastRight(root.left); //take the extreme right of left sub tree
            lastRight.right = rightChild; //connect
            return root.left; //this will return the deleated nodes left which will be added   from where helper was called
        }
    }

    public TreeNode findLastRight(TreeNode root){
        if(root.right == null) return root;
        return findLastRight(root.right);
    }



    //Question -58 Leet Code 230. Kth Smallest Element in a BST
    //Leet Code URL  ->  https://leetcode.com/problems/kth-smallest-element-in-a-bst/description/
    int result=-1;
    int counter=0;
    public int kthSmallest(TreeNode root, int k) {
        checkSmallest(root,k);
        return result;
    }
    void checkSmallest(TreeNode node,int k){
        if(node==null) return;
        //As In Order in BST ultimately results in sorted
        //so we traverse in-order fashion
        checkSmallest(node.left,k);
        counter++;
        //once counter ==k that means we got our kth smallest element so update result
        if(counter==k) result=node.val;
        checkSmallest(node.right,k);
    }

}
