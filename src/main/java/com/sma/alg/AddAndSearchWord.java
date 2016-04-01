package com.sma.alg;

import static com.sma.annotation.Tag.DataStructure.Hash;
import static com.sma.annotation.Tag.DataStructure.Trie;
import static com.sma.annotation.Tag.Reference.LeetCode;

import com.sma.annotation.Tag;

import java.util.HashMap;
import java.util.Map;

@Tag(dss = {Hash, Trie}, references = LeetCode) public class AddAndSearchWord {

  public static class WordDictionary {

    private static final char WILDCARD = '.';

    private final TrieNode root = new TrieNode();

    // Adds a word into the data structure.
    public void addWord(String word) {
      insertIterative(root, word);
    }

    // Returns if the word is in the data structure. A word could
    // contain the dot character '.' to represent any one letter.
    public boolean search(String word) {
      return search(root, word, 0);
    }

    void insertIterative(TrieNode root, String word) {
      TrieNode node = root;
      for (int ind = 0; ind < word.length(); ind++) {
        final char c = word.charAt(ind);
        if (!node.children.containsKey(c)) {
          node.children.put(c, new TrieNode());
        }
        node = node.children.get(c);
      }
      node.isWordEnd = true;
    }

    void insertRecursive(TrieNode cur, String word) {
      if (cur == null || word == null) return;
      char firstChar = word.charAt(0);
      if (!cur.children.containsKey(firstChar)) {
        TrieNode child = new TrieNode();
        if (word.length() == 1) child.isWordEnd = true;
        cur.children.put(firstChar, child);
      }
      insertRecursive(cur.children.get(firstChar), word.substring(1));
    }

    boolean search(TrieNode node, String word, int len) {
      if (node == null) {
        return false;
      }
      if (len == word.length()) {
        return node.isWordEnd;
      }
      final char c = word.charAt(len);
      if (isWildcard(c)) {
        for (TrieNode next : node.children.values()) {
          if (search(next, word, len + 1)) {
            return true;
          }
        }
        return false;
      } else {
        return search(node.children.get(c), word, len + 1);
      }
    }

    private boolean isWildcard(char c) {
      return c == WILDCARD;
    }
  }
}

class TrieNode {
  final Map<Character, TrieNode> children = new HashMap<>();
  boolean isWordEnd;
}
