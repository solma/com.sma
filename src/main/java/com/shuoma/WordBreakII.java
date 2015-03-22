package com.shuoma;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

//dynamic programming+dfs on dp results
//1.dp for each position in s, find matching substring and record it in dp
//eg. leetcode dict: leet, code, le, et, co, de
//        0 0 4 4  leet exists in dict, put 0 in position 5, le exists in dict, put 0 in position 3 etc.
//2.dfs search for all possible segmentations in dp results. note that we want to start searching from end of the dp list. it is more efficient. if (dp.get(s.length())==0) we do not need to search. no segmentation is available 

public class WordBreakII {
	public static void main(String[] args){
		Set<String> dict=new HashSet<String>();
		Collections.addAll(dict, "leet","code", "le","et","co","de");
				
		for(String s: wordBreak(args[0], dict)){
			System.out.println(s);
		}
	}
	
	
	
    public static ArrayList<String> wordBreak(String s, Set<String> dict) {
        ArrayList<ArrayList<Integer>> dp=new ArrayList<ArrayList<Integer>>();
        for(int i=0;i<s.length()+1;i++){
            dp.add(new ArrayList<Integer>());
        }
        
        for(int i=1;i<=s.length();i++){
        	for(String e: dict){
        		int en=e.length();
        		if( ((i>en&&dp.get(i-en).size()>0)||(i==en)) &&s.substring(i-en,i).equals(e)){
        			dp.get(i).add(i-en);
        		}
        	}
        }
        
        for(ArrayList<Integer> prev: dp){
        	System.out.println(prev);
        }
        
        ArrayList<String> results=new ArrayList<String>();
        StringBuilder re=new StringBuilder();
        helper(results,re,s,dict,dp,s.length());
        return results;
    }
    public static void helper(ArrayList<String> results,StringBuilder re, String s, Set<String> dict, ArrayList<ArrayList<Integer>> dp, int cur){
        if(cur==0){
            results.add(re.toString());
            return;
        }
        for(int p:dp.get(cur)){
            if(cur < s.length() ) re.insert(0," ");
            re.insert(0,s.substring(p,cur));
            helper(results,re,s,dict,dp,p);
            re.delete(0,cur-p);
            if(cur < s.length() ) re.deleteCharAt(0);
        }
    }
}
