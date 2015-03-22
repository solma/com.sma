package com.shuoma;
import java.util.*;
public class Combinations {
    public static void main(String[] args){
        new Combinations().main();
    }
    
    public void main(){
        for(ArrayList<Integer> com: combine(3,2)) 
            System.out.println();
    }
    
    //second pass
    public ArrayList<ArrayList<Integer>> combine(int n, int k) {
        return combine(n, k, 1, new ArrayList<Integer>());
    }
    
    public ArrayList<ArrayList<Integer>> combine(int n, int k, int sIdx, ArrayList<Integer> com){
        ArrayList<ArrayList<Integer>> ret=new ArrayList<ArrayList<Integer>>();
        if(com.size()==k) return ret;
        for(int i=sIdx;i<=n;i++){
            com.add(i);
            if(com.size()==k) ret.add(new ArrayList<Integer>(com));
            ret.addAll(combine(n, k, i+1, com));
            com.remove(com.size()-1);
        }
        return ret;
    }
    
    //first pass
    public ArrayList<ArrayList<Integer>> combineFirstPass(int n, int k) {
        ArrayList<ArrayList<Integer>> res=new ArrayList<ArrayList<Integer>>();
        combineRecursion(res, n, k, 0, new ArrayList<Integer>());
        return res;
    }
    
    public void combineRecursion(ArrayList<ArrayList<Integer>> res, int n, int k, int sIdx, ArrayList<Integer> com){
        if(com.size()==k+1) return;

        for(int i=sIdx;i<n; i++){
            com.add(i+1);
            System.out.println(sIdx+"  "+i+" "+com);
            if(com.size()==k)  res.add(new ArrayList<Integer>(com));
            
            combineRecursion(res, n, k, i+1, com); //tricky part, new sIdx equals to i+1 not sIdx+1 
            com.remove(com.size()-1);
        }
    }
}