package com.shuoma.alg.combinatorics;
import java.util.*;


public class Permutaion {
	public static void main(String[] args){
		new Permutaion().main();
	}
	
	public void main(){
		String curPermutation="dcba";
		System.out.println(nextPermutation(curPermutation));
		System.out.println(prevPermutation(curPermutation));
	}
	
	public ArrayList<String> permutationsByRecursion(String input, StringBuilder perm){
        ArrayList<String> ret=new ArrayList<String>();
        for(int i=0; i<input.length(); i++){
            if(perm.toString().contains(input.substring(i,i+1))) continue;
            perm.append(input.charAt(i));
            if(perm.length()==input.length()) ret.add(perm.toString());
            for(String s: permutationsByRecursion(input, perm)) ret.add(s);
            perm.deleteCharAt(perm.length()-1);
        }
        return ret;
    }
    
	public ArrayList<String> firstKPermutationsByRecursion(String input, StringBuilder perm, int K){
        //firt K permutatins in alphabetical order 
        ArrayList<String> ret=new ArrayList<String>();
        for(int i=0; i<input.length(); i++){
            if(perm.toString().contains(input.substring(i,i+1))) continue;
            perm.append(input.charAt(i));
            if(perm.length()==input.length())  ret.add(perm.toString());            
            for(String s: firstKPermutationsByRecursion(input, perm, K)){
                if(ret.size()>=K) break;
                ret.add(s);
            }
            perm.deleteCharAt(perm.length()-1);
        }
        return ret;
    }
    
	/**
	 * 
	 * @param curPermutation
	 * @return
	 */
	public String nextPermutation(String curPermutation){
		char[] cur=curPermutation.toCharArray();
		
		//the first increasing pair from backward 
		int i;
		for(i=cur.length-1;i>0;i--){
			if(cur[i-1]<cur[i]) break;
		}
		if(i==0){
			Arrays.sort(cur);
			return new String(cur);
		}
		i-=1;
		//the first one larger than cur[i] from backward
		int j;
		for(j=cur.length-1;j>i;j--){
			if(cur[j]>cur[i]) break;
		}
		swap(cur, i, j);
		//reverse the sequence after i
		i+=1;
		while(i<j){
			swap(cur, i++, j--);
		}
		return new String(cur);
	}
	
	/**
	 * 
	 * @param curPermutation
	 * @return
	 */
	public String prevPermutation(String curPermutation){
		char[] cur=curPermutation.toCharArray();
		
		//the first decreasing pair from backward 
		int i;
		for(i=cur.length-1;i>0;i--){
			if(cur[i-1]>cur[i]) break;
		}
		if(i==0){
			Arrays.sort(cur);
			int lo=0, hi=cur.length-1;
			while(lo<hi)swap(cur, lo++, hi--);
			return new String(cur);
		}
		i-=1;
		//the first one smaller than cur[i] from backward
		int j;
		for(j=cur.length-1;j>i;j--){
			if(cur[j]<cur[i]) break;
		}
		swap(cur, i, j);
		//reverse the sequence after i
		i+=1;
		while(i<j){
			swap(cur, i++, j--);
		}
		return new String(cur);
	}
	
	void swap(char[] array, int i, int j){
		if(i==j) return;
		char swap;
		swap=array[i];
		array[i]=array[j];
		array[j]=swap;
	}
}
