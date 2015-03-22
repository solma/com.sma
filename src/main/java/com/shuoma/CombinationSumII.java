package com.shuoma;
import java.util.*;
//each element can only be used once
public class CombinationSumII {
    public static void main(String[] args){
        new CombinationSumII().main();
    }
    
    public void main(){
        int[] candidates={10,1,2,7,6,1,5,3,9,8,2};
        int target=22;
        for(ArrayList<Integer> com: dpCombinationSum2(candidates, target)){
            System.out.println(com);
        }
    }
    
    // second pass
    //dp+dfs
    public ArrayList<ArrayList<Integer>> dpCombinationSum2(int[] candidates, int target) {
        //Arrays.sort(candidates);
        int n=candidates.length;
        ArrayList<ArrayList<Integer>> ret=new ArrayList<ArrayList<Integer>>();
        if(n==0) return ret;
       
        ArrayList<ArrayList<Integer>> dp=new ArrayList<ArrayList<Integer>>();
        for(int i=0;i<target+1;i++){
            dp.add(new ArrayList<Integer>());
        }
        
        for(int j=0;j<=target;j++){
        	for(int i=0;i<n;i++){
                if(candidates[i]<=j&&(dp.get(j-candidates[i]).size()>0||j==candidates[i]))
                    dp.get(j).add(i); //index of the prev num in the array
            }
        }
        
        dfs(ret, candidates, dp, target, new ArrayList<Integer>() );
        return ret;
    }
    
    public void dfs(ArrayList<ArrayList<Integer>> ret, int[] candidates, ArrayList<ArrayList<Integer>> dp, int curNode, ArrayList<Integer> idx){
        if(idx.size()==4){
	  
         if(curNode==0){
            ArrayList<Integer> sorted=new ArrayList<Integer>();
			for(Integer index: idx) sorted.add(candidates[index]);
            Collections.sort(sorted);
            if(!ret.contains(sorted)) ret.add(sorted);
		 }
            return;
        }
        for(Integer next: dp.get(curNode)){
            if(!idx.contains(next)){
                idx.add(next);
                //System.out.println("cur="+curNode+" next="+next+" c[next]="+candidates[next]+" com="+com);
                dfs(ret, candidates, dp, curNode-candidates[next], idx);
                idx.remove(idx.size()-1);
            }
        }
    }
    
    //first pass
    //recursive
    public ArrayList<ArrayList<Integer>> combinationSum2(int[] candidates, int target) {
        Arrays.sort(candidates);
        HashSet<ArrayList<Integer>> res = new HashSet<ArrayList<Integer>>();
        //res.add(new ArrayList<Integer>());
        
        ArrayList<ArrayList<Integer>> ret = new ArrayList<ArrayList<Integer>>();

        for(ArrayList<Integer> temp:         combinationClosestSum(candidates,target,0, new ArrayList<Integer>(), new int[]{target}) )
            ret.add(temp);
        return ret;
    }
    
   public HashSet<ArrayList<Integer>> combinationSum(int[] candidates, int target, int i, HashSet<ArrayList<Integer>> prev){
        HashSet<ArrayList<Integer>> res = new  HashSet<ArrayList<Integer>>();
        if(target==0){
            for(ArrayList<Integer> temp:prev){
                res.add(new ArrayList<Integer>(temp));
            }    
            return res;
        }    
        for(int j=i;j<candidates.length;j++){
            if(candidates[j]>target)    break;
            for(ArrayList<Integer> temp:prev)   temp.add(candidates[j]);    
            HashSet<ArrayList<Integer>> next = combinationSum(candidates,target-candidates[j],j+1,prev);
            if(next.size()>0)        res.addAll(next);
            for(ArrayList<Integer> temp:prev)    temp.remove(temp.size()-1);
        }
        return res;
    }
    
    //dp only return false/true
    public boolean dpCombinationSum(int[] candidates, int target) {
        Arrays.sort(candidates);
        int n=candidates.length;
        if(n==0) return false;
        boolean [] dp=new boolean[target+1];
        dp[0]=true;
        for(int i=0;i<n;i++){
            if(candidates[i]>target) break;
            for(int j=target;j>=0;j--){
                if(candidates[i]<=j) dp[j]=dp[j-candidates[i]];
            }
        }
        System.out.println(dp[target]);
        return dp[target];
    }
    
    
    
    
    
    
    
    
    //recursive
    public HashSet<ArrayList<Integer>> combinationClosestSum(int[] candidates, int target, int i, ArrayList<Integer> curSubset, int[] closestSoFar){
        HashSet<ArrayList<Integer>> ret=new HashSet<ArrayList<Integer>>();
        int diff=Math.abs(target);
        
        if(diff<=(int)closestSoFar[0]){
            if(diff<(int)closestSoFar[0]){
                closestSoFar[0]=diff;
            }            
            ret.add(new ArrayList<Integer>(curSubset));
//          System.out.println(closestSoFar[0]+" "+subsets);
        }
        int oldValue;        
        for(int j=i;j<candidates.length;j++){
            //if(target>closestSoFar[0]&&candidates[j]>=0) break;
            curSubset.add(candidates[j]);    
            
            oldValue=closestSoFar[0]; 
            HashSet<ArrayList<Integer>> nxt=combinationClosestSum(candidates,target-candidates[j],j+1,curSubset, closestSoFar); //use candidate[j]
            if(closestSoFar[0]<oldValue) ret.clear(); // clear the results in the current set
            ret.addAll(nxt);
            
            curSubset.remove(curSubset.size()-1);
            //System.out.println(closestSoFar[0]+" "+curSubset+" j="+j+" target="+target  );
            oldValue=closestSoFar[0];
            nxt=combinationClosestSum(candidates,target,j+1, curSubset, closestSoFar); //do not use candidate[j]
            if(closestSoFar[0]<oldValue) ret.clear(); // clear the results in the current set
            ret.addAll(nxt);
        }
        return ret;
    }
}
