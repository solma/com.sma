package com.shuoma;
import java.util.ArrayList;
import java.util.LinkedList;


public class BinaryTreeLevelOrderTraversalI {
    public ArrayList<ArrayList<Integer>> levelOrderBottom(TreeNode root) {
        ArrayList<ArrayList<Integer>> ret=new ArrayList<ArrayList<Integer>>();
        if(root==null) return ret;
        LinkedList<TreeNode> curLvl=new LinkedList<TreeNode>();
        curLvl.add(root);
        LinkedList<TreeNode> nextLvl;
        while(curLvl.size()>0){
            nextLvl=new LinkedList<TreeNode>();
            
            ArrayList<Integer> lvl=new ArrayList<Integer>();
            for(TreeNode n: curLvl ){
                lvl.add(n.val);
            }
            ret.add(0, lvl);
            
            while(curLvl.size()>0){
                TreeNode cur=curLvl.poll();
                if(cur.left!=null) nextLvl.add(cur.left);
                if(cur.right!=null) nextLvl.add(cur.right);
            }
            curLvl=nextLvl;
        }
        return ret;
    }
}