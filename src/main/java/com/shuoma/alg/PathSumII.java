package com.shuoma.alg;
/**
 * Definition for binary tree
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
import java.util.*;
public class PathSumII {

    //second pass
    public ArrayList<ArrayList<Integer>> pathSum(TreeNode root, int sum) {
        ArrayList<ArrayList<Integer>> ret=new ArrayList<ArrayList<Integer>>();
        if(root==null) return ret;
        if(root.val==sum&&root.left==null&&root.right==null){
            ArrayList<Integer> leaf=new ArrayList<Integer>();
            leaf.add(root.val);
            ret.add(leaf);
            return ret;
        }
        if(root.left!=null){
            ArrayList<ArrayList<Integer>> left=pathSum(root.left,sum-root.val);
            for(ArrayList<Integer> path: left){
                path.add(0, root.val);
            }
            ret.addAll(left);
        }
        if(root.right!=null){
            ArrayList<ArrayList<Integer>> right=pathSum(root.right,sum-root.val);
            for(ArrayList<Integer> path: right){
                path.add(0, root.val);
            }
            ret.addAll(right);
        }
        return ret;
    }


    public ArrayList<ArrayList<Integer>> pathSumFirstPass(TreeNode root, int sum) {
        ArrayList<ArrayList<Integer>> res=new ArrayList<ArrayList<Integer>>();
        if(root!=null) pathSum(root, sum, new ArrayList<Integer>(), res);
        return res;
    }

     public void pathSum(TreeNode node, int target, ArrayList<Integer> path, ArrayList<ArrayList<Integer>> res) {
        target-=node.val;
        path.add(node.val);
        if(node.left==null&&node.right==null){
            if(target==0) res.add(new ArrayList<Integer>(path));
            return;
        }
        if(node.left!=null){
            pathSum(node.left, target, path, res);
            path.remove(path.size()-1);
        }
        if(node.right!=null){
            pathSum(node.right, target, path, res);
            path.remove(path.size()-1);
        }


     }
}
