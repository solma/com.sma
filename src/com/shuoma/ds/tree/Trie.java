package com.shuoma.ds.tree;

import org.apache.commons.math3.analysis.solvers.BracketedUnivariateSolver;


public class Trie{
	public TrieNode root;
	
	public Trie(){
		root=new TrieNode('0');
	}
	
	public void add(String s){
		if(root==null) return;

		int sLen=s.length();
		TrieNode cur=root;
		for(int i=0;i<sLen;i++){
			char c=s.charAt(i);
			if(!cur.children.containsKey(c)){
				cur.children.put(c, new TrieNode(c));
			}
			cur=cur.children.get(c);
		}
	}
	
	/**
	 * 
	 * @return longest prefix of all keys stored in the trie
	 */
	public String longestPrefix(){
		String longestPrefix="";
		TrieNode cur=root;
		while(cur.children.size()==1){
			cur=cur.children.values().iterator().next();
			longestPrefix+=cur.id;
		}
		return longestPrefix;
	}
	
	/**
	 * 
	 * @param s
	 * @return the longest key stored in the trie that is a prefix of s
	 */
	public String longestPrefix(String s){
		String longestPrefix="";
		TrieNode cur=root;
		for(int i=0;i<s.length();i++){
			char c=s.charAt(i);
			if(!cur.children.containsKey(c)){
				if(cur.children.size()==0) break;//leaf node case
				return "";
			}
			cur=cur.children.get(c);
			longestPrefix+=cur.id;
		}
		return longestPrefix;
	}
	
	/**
	 * 
	 * @param s
	 * @return the shortest prefix of s that is not stored in the trie
	 */
	public String shortestPrefix(String s){
		String shortestPrefix="";
		TrieNode cur=root;
		int i=0;
		for(;i<s.length();i++){
			char c=s.charAt(i);
			shortestPrefix+=String.valueOf(c);
			if(!cur.children.containsKey(c)){				
				break;
			}
			cur=cur.children.get(c);			
		}
		return i==s.length()?"":shortestPrefix;
	}

}
