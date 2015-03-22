package com.shuoma;
//TLE use NQueens Solution

import java.util.*;
public class NQueensII {
    public static void main(String[] args){
        new NQueensII().main();
    }
    
    public void main(){
       long curTime=System.currentTimeMillis();
       totalNQueens(13);        
       System.out.print((System.currentTimeMillis()-curTime));
    }
    
    public int totalNQueens(int n) {
        ArrayList<ArrayList<Integer>> perms=new ArrayList<ArrayList<Integer>>();
        permutate(n, perms,  new ArrayList<Integer>(), new ArrayList<Integer>(), new ArrayList<Integer>());        
        HashSet<Integer> diffs=new HashSet<Integer>();
        
        int num=0;
           System.out.println(perms.size());
        return perms.size();
    }
    
    //permutate with pruning
    public void permutate(int n, ArrayList<ArrayList<Integer>> perms, ArrayList<Integer> cur, ArrayList<Integer> diagonal,ArrayList<Integer> rDiagonal ){
        if(cur.size()==n){
            perms.add(new ArrayList<Integer>(cur));
            return;
        }
        int[] diffs=new int[2];
        for(int i=0;i<n;i++){
            if(!cur.contains(i)){
                diffs[0]=i-cur.size();
                diffs[1]=n-i-cur.size();
                if(!diagonal.contains(diffs[0]) && !rDiagonal.contains(diffs[1]) ){
                    cur.add(i);
                    diagonal.add(diffs[0]);
                    rDiagonal.add(diffs[1]);
                    permutate(n, perms, cur, diagonal, rDiagonal);
                    diagonal.remove(cur.size()-1);
                    rDiagonal.remove(cur.size()-1);
                    cur.remove(cur.size()-1);
                    
                }
            }
        }
    }
}