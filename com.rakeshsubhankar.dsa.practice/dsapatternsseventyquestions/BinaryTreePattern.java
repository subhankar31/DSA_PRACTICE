package dsapatternsseventyquestions;

import com.sun.source.tree.Tree;
import dsapatternsseventyquestions.util.PairTwo;
import dsapatternsseventyquestions.util.TreeNode;
import dsapatternsseventyquestions.util.TreeNodeLevelPair;

import java.util.*;

public class BinaryTreePattern {
    //Question -40 Leet Code -> 637. Average of Levels in Binary Tree
    //Leet Code URL - > https://leetcode.com/problems/average-of-levels-in-binary-tree/
    public List<Double> averageOfLevels(TreeNode root) {
        List<Double> res = new ArrayList<>();
        if (root == null) return res;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            int level = queue.size();
            double sum = 0.0;

            for (int i = 0; i < level; i++) {
                TreeNode curr = queue.poll();
                sum += curr.val;
                if (curr.left != null) queue.offer(curr.left);
                if (curr.right != null) queue.offer(curr.right);
            }
            res.add(sum / level);
        }
        return res;
    }

    //Question -41 Leet Code 111. Minimum Depth of Binary Tree
    //Leet Code URL - > https://leetcode.com/problems/minimum-depth-of-binary-tree/description/
    //This below approach is solved suing level order (BFS) and in Leet Code 2nd previous submitted solution will be in DFS
    public int minDepth(TreeNode root) {
        if(root==null){
            return 0;
        }
       Queue<TreeNodeLevelPair> queue=new LinkedList<>();
        queue.add(new TreeNodeLevelPair(root,1));
        while (!queue.isEmpty()){
            TreeNodeLevelPair pair=queue.poll();
            TreeNode node=pair.node;
            int level=pair.level;
            if(node.left==null && node.right==null){
                return level;
            }
            if(node.left!=null) {
                queue.add(new TreeNodeLevelPair(node.left,level+1));
            }
            if(node.right!=null) {
                queue.add(new TreeNodeLevelPair(node.right,level+1));
            }
        }
        return 0;
    }
    //Question -42 Leet Code 104. Maximum Depth of Binary Tree
    //Leet Code URL - > https://leetcode.com/problems/maximum-depth-of-binary-tree/
    public int maxDepth(TreeNode root) {
        //if we reached leaf node case of Tree itself is null
        if (root==null) return 0;

        //calculate the left and right sub tree
        int lh=maxDepth(root.left);
        int rh=maxDepth(root.right);

        //as we want total depth/height so we only consider the max height subtree and
        //add it with the root node so we added 1+
        return 1+Math.max(lh,rh);

    }
    //Question -43 Leet Code Not avilable
    //Concept -> to find min and max value in Binary tree , Soln - traverse all and update min and max on the fly

    //Question -44 Leet Code 102. Binary Tree Level Order Traversal
    //Leet Code URL  -> https://leetcode.com/problems/binary-tree-level-order-traversal/description/
    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> ans=new ArrayList<>();
        Queue<TreeNode> queue=new LinkedList<TreeNode>();
        if(root==null) return ans;
        queue.add(root);
        while(!queue.isEmpty()){
            int queueSize=queue.size();
            List<Integer> subans=new ArrayList<>();
            for(int i=0;i<queueSize;i++){
                TreeNode node=queue.poll();
                if(node.left!=null) queue.add(node.left);
                if(node.right!=null) queue.add(node.right);
                subans.add(node.val);
            }
            ans.add(subans);
        }

        return ans;
    }
    //Question -45 Leet Code 100. Same Tree
    //Leet Code URL  -> https://leetcode.com/problems/same-tree/description/
    public boolean isSameTree(TreeNode p, TreeNode q) {
        if(p==null && q==null){
            return true;
        } else if(p==null || q==null || p.val != q.val){
            return false;
        }
        return (isSameTree(p.left, q.left) && isSameTree(p.right, q.right));
    }
    //Question -46 Leet Code 112. Path Sum
    //Leet Code URL  -> https://leetcode.com/problems/path-sum/description/
    public boolean hasPathSum(TreeNode root, int targetSum) {
        Stack< PairTwo> stack=new Stack<>();
        stack.add(new PairTwo(root, root.val));
        while (!stack.isEmpty()){
            PairTwo pair=stack.pop();
            TreeNode node=pair.node;
            int pathSum= pair.pathSum;
            if (node.left==null && node.right==null && pathSum==targetSum){
                return true;
            }
            if(node.right!= null) stack.add(new PairTwo(node.right,pathSum+node.right.val));
            if(node.left!= null) stack.add(new PairTwo(node.left,pathSum+node.left.val));

        }
        return false;
    }
    //Below solution uses DFS
    public boolean hasPathSumUsingDFS(TreeNode root, int targetSum) {
        if (root == null) return false;

        if (root.left == null && root.right == null) {
            return root.val == targetSum;
        }

        return hasPathSum(root.left, targetSum - root.val)
                || hasPathSum(root.right, targetSum - root.val);
    }
    //Question -47 Leet Code 543. Diameter of Binary Tree
    //Leet Code URL  -> https://leetcode.com/problems/diameter-of-binary-tree/
    int maxDiameter=0;
    public int diameterOfBinaryTree(TreeNode root) {
        int height=maxDiam(root);
        return maxDiameter;
    }
    int maxDiam(TreeNode node){
        if(node==null) return 0;
        int lh=maxDiam(node.left);
        int rh=maxDiam(node.right);
        maxDiameter=Math.max(maxDiameter,lh+rh);

        return 1+Math.max(lh,rh);
    }

    //Question -48 Leet Code 226. Invert Binary Tree
    //Leet Code URL  -> https://leetcode.com/problems/invert-binary-tree/description/
    public TreeNode invertTree(TreeNode root) {
        if(root == null)    return null;

        TreeNode l = invertTree(root.left);
        TreeNode r = invertTree(root.right);

        root.left = r;
        root.right = l;

        return root;
    }
    //Question -49 Leet Code 236. Lowest Common Ancestor of a Binary Tree
    //Leet Code URL  -> https://leetcode.com/problems/lowest-common-ancestor-of-a-binary-tree/description/
    //Use Rough practice to understand or watch video
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if(root==null || root==p || root==q){
            return root;
        }
        TreeNode left=lowestCommonAncestor(root.left,p,q);
        TreeNode right=lowestCommonAncestor(root.right,p,q);

        if(left==null) return right;
        else if(right==null) return left;
        else return root;
    }



}
