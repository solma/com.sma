package com.shuoma;
import java.util.*;


public class SubsetsII {
    public static void main(String[] args){
        for(ArrayList<Integer> s: new SubsetsII().subsetsWithDup(new int[]{2,1,2}) )
            System.out.println(s);
    }
    
    public ArrayList<ArrayList<Integer>> subsetsWithDup(int[] S) {
        // Start typing your Java solution below
        // DO NOT write main() function
        Arrays.sort(S);
        HashSet<ArrayList<Integer>> res=new HashSet<ArrayList<Integer>>();
        res.add(new ArrayList<Integer>());
        combineRecursion(res, S, 0, new ArrayList<Integer>());
        
        ArrayList<ArrayList<Integer>> ret=new ArrayList<ArrayList<Integer>>();
        ret.addAll(res);
        return ret;
    }
    
    public void combineRecursion(HashSet<ArrayList<Integer>> res, int[] S, int sIdx, ArrayList<Integer> com){
        for(int i=sIdx;i<S.length; i++){
            com.add(S[i]);
            res.add(new ArrayList<Integer>(com));
            //System.out.println(sIdx+"  "+i+"  "+com);
            combineRecursion(res, S, i+1, com); //tricky part, new sIdx equals to i+1 not sIdx+1 
            com.remove(com.size()-1);
        }
    }
}