package com.shuoma;

import java.util.*;
public class PalindromePartition{
    public static void main(String[] args){
        new PalindromePartition().main();
    }
    
    public void main(){
	    String s="acabaacab";
		for(ArrayList<String> par: partition(s)) System.out.println(par);
		  
    }

	public boolean isPalindrome(String s, int sIdx, int eIdx){
	  int i=sIdx, j=eIdx;
	  while(i<j){
		if(s.charAt(i)!=s.charAt(j)) return false;
		i++; j--;		
	  }
	  return true;
	}

	public ArrayList<ArrayList<String>> partition(String s){
		ArrayList<ArrayList<String>> allPartitions=new ArrayList<ArrayList<String>>();
		partition(s, allPartitions, new ArrayList<String>(),0);
		return allPartitions;
	}

	public void partition(String s, ArrayList<ArrayList<String>> allPartitions, ArrayList<String> curPartition, int l){
	  if(l==s.length()){
		allPartitions.add(new ArrayList<String>(curPartition));
		return;
	  }
	  for(int i=l;i<s.length();i++){
		if(isPalindrome(s, l, i)){
		  curPartition.add(s.substring(l, i+1));
		  //System.out.println(l+" "+i+"  "+curPartition);
		  partition(s, allPartitions, curPartition, i+1);
		  curPartition.remove(curPartition.size()-1);
		}
	  }
	}
    
	
}
