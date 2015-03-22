package com.shuoma;
import java.util.*;
public class GenerateParenthesis {
    public static void main(String[] args){
        new GenerateParenthesis().main();
    }
    
    public void main(){
    	ArrayList<String> ret=generateParenthesis(0);
    	System.out.println(ret.size());
        for(String s: ret)
             System.out.println(s);
    }
    
    //recursion
    public ArrayList<String> generateParenthesis(int n) {
    	if(n==0) return new ArrayList<String>();
    	return generateRecursion("", 0, 0, n);
    }
    
    public ArrayList<String> generateRecursion(String cur, int open, int close, int n) {
    	ArrayList<String> ret=new ArrayList<String>();
    	if(close>open||open>n) return ret;
    	if(open==n&&close==n) ret.add(cur);
    	ret.addAll(generateRecursion(cur+"(", open+1, close, n) );
    	ret.addAll(generateRecursion(cur+")", open, close+1, n) );
    	return ret;
    }
    
    
    //second pass
    public ArrayList<String> generateParenthesis1(int n) {
        HashSet<String> sets=new HashSet<String>();
        if(n==0){
            ArrayList<String> ret=new ArrayList<String>();
            ret.add("");
            return ret;
        }else{
            for(String set: generateParenthesis(n-1)){
                int len=set.length();
                
                for(int i=0;i<=len;i++){
                    StringBuilder setCpy1=new StringBuilder(set);
                    setCpy1.insert(i, '(');
                    for(int j=i+1;j<=len+1;j++){
                        StringBuilder setCpy2=new StringBuilder(setCpy1);
                        setCpy2.insert(j, ')');
                        sets.add(setCpy2.toString());
                    }
                }
            }
            ArrayList<String> ret=new ArrayList<String>();
            ret.addAll(sets);
            //System.out.println(n+" : "+ret.size());
            return ret;
        }
        
    }
    
    
    // first pass
    // public ArrayList<String> generateParenthesis(int n) {
        // ArrayList<String> allParenthesis=new ArrayList<String>();
        // generateParenthesis(allParenthesis, 2*n, new StringBuilder());
        // return allParenthesis;
    // }
    
    public boolean isValid(StringBuilder s){
        int diff=0;
        for(int i=0;i<s.length();i++){
            if(s.charAt(i)=='(') diff+=1;
            else diff-=1;
            if(diff<0) return false;
        }
        return true;
    }
    
    public boolean isMatched(StringBuilder s){
        int diff=0;
        for(int i=0;i<s.length();i++){
            if(s.charAt(i)=='(') diff+=1;
            else diff-=1;
        }
        if(diff==0) return true;
        else return false;
    }
    
    public void generateParenthesis(ArrayList<String> allParenthesis, int n, StringBuilder s){
        if(!isValid(s)) return; //compartibility check
        
        if(s.length()==n){
            if(isMatched(s)) allParenthesis.add(new String(s));
            return;
        }
        char[] ps=new char[]{'(', ')'};
        for(int i=0;i<ps.length;i++){
            s.append(ps[i]);
            generateParenthesis(allParenthesis, n, s);
            s.deleteCharAt(s.length()-1);
        }
    }
}