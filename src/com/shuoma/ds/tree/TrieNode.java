package com.shuoma.ds.tree;

import java.util.HashMap;

import com.shuoma.ds.graph.Node;

public class TrieNode extends Node{
	public HashMap<Character, TrieNode> children;
	
	
	public TrieNode(char c){
		id=String.valueOf(c);
		children=new HashMap<Character, TrieNode>();
	}
}