package com.shuoma;
import java.util.ArrayList;
import java.util.Arrays;

public class Subsets {
    public ArrayList<ArrayList<Integer>> subsets(int[] S) {
        // Start typing your Java solution below
        // DO NOT write main() function
        ArrayList<ArrayList<Integer>> res=new ArrayList<ArrayList<Integer>>();
        Arrays.sort(S);
        res.add(new ArrayList<Integer>());
        combineRecursion(res, S, 0, new ArrayList<Integer>());
        return res;
    }
    
    public void combineRecursion(ArrayList<ArrayList<Integer>> res, int[] S, int sIdx, ArrayList<Integer> com){
        for(int i=sIdx;i<S.length; i++){
            com.add(S[i]);
            res.add(new ArrayList<Integer>(com));
            //System.out.println(sIdx+"  "+i+"  "+com);
            combineRecursion(res, S, i+1, com); //tricky part, new sIdx equals to i+1 not sIdx+1 
            com.remove(com.size()-1);
        }
    }
}