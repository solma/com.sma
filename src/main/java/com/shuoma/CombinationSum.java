package com.shuoma;
import java.util.*;
//each element can be used multiple times

public class CombinationSum {
    public static void main(String[] args){
        new CombinationSum().main();
    }
    
    public void main(){
        int[] candidates=new int[]{1,5, 10};
        int target= 16;
        for(int[] com: combinationSum(candidates, target)){
            System.out.println(Arrays.toString(com) );
        }
        //System.out.println(combinationSum(candidates, target));
    }
    
    
    public ArrayList<int[]> combinationSum(int[] candidates, int target) {
        //Arrays.sort(candidates);
        //return combinationSum(candidates, target, 0,new ArrayList<Integer>());
        return combinationSum(candidates, target, 0, new ArrayList<Integer>(), new int[candidates.length] );
    }
    
    public ArrayList<int[]> combinationSum(int[] candidates, int target, int sIdx, ArrayList<Integer> com, int[] countArr) {
        //ArrayList<ArrayList<Integer>> ret=new ArrayList<ArrayList<Integer>>();
        ArrayList<int[]> ret=new ArrayList<int[]>();
        //int ret=0;
    	
        if(target<=0){
            if(target==0){
            	//ret.add(new ArrayList<Integer>(com));
            	ret.add(Arrays.copyOf(countArr, countArr.length));
          //  	ret++;
            }
            return ret;
        } 
        for(;sIdx<candidates.length;sIdx++){
        	if(countArr[sIdx]>11) continue;
            com.add(candidates[sIdx]);
            countArr[sIdx]++;
            //System.out.println(sIdx+"  "+i+" "+target+" "+com); 
            ret.addAll(combinationSum(candidates, target-candidates[sIdx], sIdx, com, countArr) );
//            ret+=combinationSum(candidates, target-candidates[sIdx], sIdx, com, countArr);
            com.remove(com.size()-1);
            countArr[sIdx]--;
        }
        return ret;
    }
    
}