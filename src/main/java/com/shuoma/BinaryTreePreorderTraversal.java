package com.shuoma;
import java.util.ArrayList;


public class BinaryTreePreorderTraversal {
    public ArrayList<Integer> preorderTraversal(TreeNode root) {
        ArrayList<Integer> ret=new ArrayList<Integer>();
       if(root==null) return ret;
       ret.add(root.val);
       ret.addAll(preorderTraversal(root.left));
       ret.addAll(preorderTraversal(root.right));
       return ret;
    }
}