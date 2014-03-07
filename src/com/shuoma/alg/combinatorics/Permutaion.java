package com.shuoma.alg.combinatorics;
import java.util.*;

public class Permutaion {
	ArrayList<String> permutationsByRecursion(String input, StringBuilder perm){
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
    
    ArrayList<String> firstKPermutationsByRecursion(String input, StringBuilder perm, int K){
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
}
