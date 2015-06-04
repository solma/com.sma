package com.shuoma.alg;

import static com.shuoma.annotation.Tag.DataStructure.HashTable;
import static com.shuoma.annotation.Tag.DataStructure.Trie;
import static com.shuoma.annotation.Tag.Reference.LeetCode;

import com.shuoma.annotation.Tag;

import java.util.HashMap;
import java.util.Map;

class TrieNode {

  Map<Character, TrieNode> children = new HashMap<>();
  boolean isWord;
  char value;

  public TrieNode() {}

  public TrieNode(char value) {
    this.value = value;
  }
}

@Tag(dss = {HashTable, Trie}, reference = LeetCode)
public class ImplementTrie {
  public static void main(String[] args) {
    ImplementTrie ins = new ImplementTrie();
    ins.insert("ab");
    System.out.println(ins.search("a"));
    System.out.println(ins.startsWith("a"));
  }

  private TrieNode root;

  public ImplementTrie() {
    root = new TrieNode();
  }

  // Inserts a word into the trie.
  public void insert(String word) {
    TrieNode cur = root;
    for (int i = 0; i < word.length(); i++) {
      char c = word.charAt(i);
      if (cur.children.containsKey(c)) {
        cur = cur.children.get(c);
      } else {
        TrieNode child = new TrieNode(c);
        cur.children.put(c, child);
        cur = child;
      }
    }
    cur.isWord = true;
  }

  // Returns if the word is in the trie.
  public boolean search(String word) {
    return searchPrefix(word, true);
  }

  // return false only if searching for a word and the found path is not a word
  public boolean searchPrefix(String word, boolean requireAtLeaf) {
    TrieNode cur = root;
    for (int i = 0; i < word.length(); i++) {
      char c = word.charAt(i);
      if (!cur.children.containsKey(c)) {
        return false;
      }
      cur = cur.children.get(c);
    }
    return !requireAtLeaf || cur.isWord;
  }

  // Returns if there is any word in the trie
  // that starts with the given prefix.
  public boolean startsWith(String prefix) {
    return searchPrefix(prefix, false);
  }
}
