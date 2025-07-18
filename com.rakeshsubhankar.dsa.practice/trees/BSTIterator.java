package trees;

import dsapatternsseventyquestions.util.TreeNode;

import java.util.Stack;

public class BSTIterator{
    private Stack<TreeNode> stack=new Stack<TreeNode>();
    boolean reversed=true;
    public BSTIterator(TreeNode root,boolean isReversed){
        reversed=isReversed;
        pushAll(root);
    }
    void pushAll(TreeNode node){
        while(node!=null){
            stack.push(node);
            if(reversed==true){
                node=node.right;
            }else node=node.left;
        }
    }
    public int next(){
        TreeNode element=stack.pop();
        if(reversed==false){
            pushAll(element.right);
        }
        else pushAll(element.left);

        return element.val;
    }
    boolean hasNext(TreeNode node){
        if(!stack.isEmpty()) return true;
        else return false;
    }
}
