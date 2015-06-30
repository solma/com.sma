package com.shuoma.ds.graph.tree;

import static com.shuoma.annotation.Tag.DataStructure.Trie;

import com.shuoma.annotation.Tag;

import java.util.HashMap;

@Tag(dss = Trie)
public class TrieNode {

  public static TrieNode buildTree(String[] words) {
    TrieNode root = new TrieNode('0', "");
    for (String word : words) {
      add(root, word);
    }
    return root;
  }

  public static void add(TrieNode root, String s) {
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
   * @return longest prefix of all keys stored in the trie
   */
  public static String longestPrefix(TrieNode root) {
    String longestPrefix = "";
    TrieNode cur = root;
    while (cur.children.size() == 1) {
      cur = cur.children.values().iterator().next();
      longestPrefix += cur.id;
    }
    return longestPrefix;
  }

  /**
   * @return the longest key stored in the trie that is a prefix of s
   */
  public static String longestPrefix(TrieNode root, String s) {
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
   * @return the shortest prefix of s that is not stored in the trie
   */
  public static String shortestPrefix(TrieNode root, String s) {
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


  public HashMap<Character, TrieNode> children;

  public char id; // id char
  public int cnt; // no of words ends here
  public String path;// path from root

  public TrieNode(char c, String path) {
    this.id = c;
    this.path = path;
    children = new HashMap<>();
  }

  public void increaseCnt() {
    this.cnt++;
  }

  @Override
  public String toString() {
    return toString("");
  }

  String toString(String offset) {
    String sb = offset + id + " " + cnt + "\n";
    for (TrieNode child : children.values()) {
      sb += child.toString(offset + "\t");
    }
    return sb;
  }
}
