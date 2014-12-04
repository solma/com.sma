package com.shuoma.ds.tree;

import com.shuoma.ds.graph.Node;

import java.util.HashMap;

public class TrieNode extends Node {
  public HashMap<Character, TrieNode> children;

  public int cnt; // no of words ends here
  public String path;// path from root


  public TrieNode(char c, String path) {
    id = String.valueOf(c);
    this.path = path;
    children = new HashMap<Character, TrieNode>();
  }

  public void increaseCnt() {
    this.cnt++;
  }
}
