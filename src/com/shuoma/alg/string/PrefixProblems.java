package com.shuoma.alg.string;

import com.shuoma.ds.tree.Trie;

public class PrefixProblems {

	public static void main(String[] args) {
		new PrefixProblems().main();
	}
	
	public void main(){
		//"google", "goamazon", "gomicrosoft", "gohere", "gomoto", "gomotot"
		String[] dict=new String[]{"dog","be","cut", "car", "cat"};
		Trie trie=new Trie();
		for(String word:dict) trie.add(word);
		
		System.out.println("longest common prefix="+trie.longestPrefix());
		
		System.out.println("longest prefix in the trie is="+trie.longestPrefix("gomotot"));
		
		System.out.println("shortest prefix of s that is not in the trie is="+trie.shortestPrefix("ct"));
	}

	

}

