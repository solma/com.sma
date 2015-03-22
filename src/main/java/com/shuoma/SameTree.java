package com.shuoma;
/**
 * Definition for binary tree
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
public class SameTree {
    //second pass
    public boolean isSameTree(TreeNode p, TreeNode q) {
        if(p==null&&q==null) return true;
        else{
            if(p!=null&&q!=null) return (p.val==q.val)&&isSameTree(p.left, q.left)&&isSameTree(p.right, q.right);
            else return false;
        }
    }
    
    //first pass
    public boolean isSameTreeFirstPass(TreeNode p, TreeNode q) {
        if(p==null&&q==null) return true;
        if(p!=null&&q!=null&&p.val==q.val) return isSameTree(p.left, q.left) && isSameTree(p.right, q.right) ;
        return false;
    }
}