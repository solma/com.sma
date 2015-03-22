package com.shuoma;
import java.util.ArrayList;


public class BinaryTreePostorderTraversal {
    public ArrayList<Integer> postorderTraversal(TreeNode root) {
       ArrayList<Integer> ret=new ArrayList<Integer>();
       if(root==null) return ret;
       ret.addAll(postorderTraversal(root.left));
       ret.addAll(postorderTraversal(root.right));
       ret.add(root.val);
       return ret;
    }
}