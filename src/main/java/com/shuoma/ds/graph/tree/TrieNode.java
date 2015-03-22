package com.shuoma.ds.graph.tree;

import com.shuoma.ds.graph.basic.Node;

import java.util.HashMap;

public class TrieNode extends Node {
  public HashMap<Character, TrieNode> children;

  public int cnt; // no of words ends here
  public String path;// path from root


  public TrieNode(char c, String path) {
    super(String.valueOf(c));
    this.path = path;
    children = new HashMap<>();
  }

  public void increaseCnt() {
    this.cnt++;
  }
}
