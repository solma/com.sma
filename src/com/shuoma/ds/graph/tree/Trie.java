package com.shuoma.ds.graph.tree;

import java.util.ArrayList;

public class Trie {
  public TrieNode root;

  public Trie() {
    root = new TrieNode('0', "");
  }

  public Trie(String[] words) {
    root = new TrieNode('0', "");
    for (String word : words)
      add(word);
  }

  public void add(String s) {
    if (root == null) return;

    int sLen = s.length();
    TrieNode cur = root;
    for (int i = 0; i < sLen; i++) {
      char c = s.charAt(i);
      if (!cur.children.containsKey(c)) {
        cur.children.put(c, new TrieNode(c, s.substring(0, i + 1)));
      }
      cur = cur.children.get(c);
      if (i == sLen - 1) cur.increaseCnt();// increase cnt if this is the last ch of a word
    }
  }

  /**
   *
   * @return for each node return the path to the node
   */
  public ArrayList<TrieNode> levelTravesal() {
    ArrayList<TrieNode> allNodes = new ArrayList<TrieNode>();
    ArrayList<TrieNode> curLvl = new ArrayList<TrieNode>(), nxtLvl;
    curLvl.add(root);
    while (!curLvl.isEmpty()) {
      nxtLvl = new ArrayList<TrieNode>();
      while (!curLvl.isEmpty()) {
        TrieNode first = curLvl.remove(0);
        allNodes.add(first);
        for (TrieNode child : first.children.values()) {
          nxtLvl.add(child);
        }
      }
      curLvl = nxtLvl;
    }
    return allNodes;
  }

  /**
   *
   * @return longest prefix of all keys stored in the trie
   */
  public String longestPrefix() {
    String longestPrefix = "";
    TrieNode cur = root;
    while (cur.children.size() == 1) {
      cur = cur.children.values().iterator().next();
      longestPrefix += cur.id;
    }
    return longestPrefix;
  }

  /**
   *
   * @param s
   * @return the longest key stored in the trie that is a prefix of s
   */
  public String longestPrefix(String s) {
    String longestPrefix = "";
    TrieNode cur = root;
    for (int i = 0; i < s.length(); i++) {
      char c = s.charAt(i);
      if (!cur.children.containsKey(c)) {
        if (cur.children.size() == 0) break;// leaf node case
        return "";
      }
      cur = cur.children.get(c);
      longestPrefix += cur.id;
    }
    return longestPrefix;
  }

  /**
   *
   * @param s
   * @return the shortest prefix of s that is not stored in the trie
   */
  public String shortestPrefix(String s) {
    String shortestPrefix = "";
    TrieNode cur = root;
    int i = 0;
    for (; i < s.length(); i++) {
      char c = s.charAt(i);
      shortestPrefix += String.valueOf(c);
      if (!cur.children.containsKey(c)) {
        break;
      }
      cur = cur.children.get(c);
    }
    return i == s.length() ? "" : shortestPrefix;
  }

}
