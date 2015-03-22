package com.shuoma;
import java.util.ArrayList;

public class Permutations{
    public ArrayList<ArrayList<Integer>> permute(int[] num) {
        ArrayList<ArrayList<Integer>> res=new ArrayList<ArrayList<Integer>>();
        permuteRecursion(res, num, new ArrayList<Integer>());        
        return res;
    }
    
    public void permuteRecursion(ArrayList<ArrayList<Integer>> res, int[] num, ArrayList<Integer> perm){
        if(perm.size()==num.length) res.add(new ArrayList<Integer>(perm));
        for(int i=0; i<num.length;i++){
            if(!perm.contains(num[i])){
                perm.add(num[i]);
                permuteRecursion(res, num, perm);
                perm.remove(perm.size()-1);
            }
        }        
    }
}