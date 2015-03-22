package com.shuoma;
import java.util.ArrayList;



public class BinaryTreeLevelOrderTraversal {
    public ArrayList<ArrayList<Integer>> levelOrder(TreeNode root) {
        ArrayList<ArrayList<Integer>> ret=new ArrayList<ArrayList<Integer>>();
        if(root==null)   return ret;

        ArrayList<TreeNode> currentLvl=new ArrayList<TreeNode>();
        ArrayList<TreeNode> nextLvl=new ArrayList<TreeNode>();
        nextLvl.add(root);
        while(!nextLvl.isEmpty()){
            currentLvl=nextLvl;
            nextLvl=new ArrayList<TreeNode>();
            ArrayList<Integer> lvl=new ArrayList<Integer>();
            while(!currentLvl.isEmpty()){
                TreeNode cur=currentLvl.remove(0);
                lvl.add(cur.val);
                //System.out.print(cur+"\t");
                if(cur.left!=null) nextLvl.add(cur.left);
                if(cur.right!=null) nextLvl.add(cur.right);                
            }
            ret.add(lvl);            
        }
        return ret;
    }
}