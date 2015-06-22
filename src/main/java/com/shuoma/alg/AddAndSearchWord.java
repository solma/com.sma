package com.shuoma.alg;

import static com.shuoma.annotation.Tag.DataStructure.HashTable;
import static com.shuoma.annotation.Tag.DataStructure.Trie;
import static com.shuoma.annotation.Tag.Reference.LeetCode;

import com.shuoma.annotation.Tag;

import java.util.HashMap;
import java.util.Map;

@Tag(dss = {HashTable, Trie}, references = LeetCode) public class AddAndSearchWord {

  public static class WordDictionary {

    private static final char WILDCARD = '.';

    private final TrieNode root = new TrieNode();

    // Adds a word into the data structure.
    public void addWord(String word) {
      insert(root, word);
    }

    // Returns if the word is in the data structure. A word could
    // contain the dot character '.' to represent any one letter.
    public boolean search(String word) {
      return search(root, word, 0);
    }

    private void insert(TrieNode root, String word) {
      TrieNode node = root;
      for (int ind = 0; ind < word.length(); ind++) {
        final char c = word.charAt(ind);
        if (!node.next.containsKey(c)) {
          node.next.put(c, new TrieNode());
        }
        node = node.next.get(c);
      }
      node.isWordEnd = true;
    }

    private boolean search(TrieNode node, String word, int len) {
      if (node == null) {
        return false;
      }
      if (len == word.length()) {
        return node.isWordEnd;
      }
      final char c = word.charAt(len);
      if (isWildcard(c)) {
        for (TrieNode next : node.next.values()) {
          if (search(next, word, len + 1)) {
            return true;
          }
        }
        return false;
      } else {
        return search(node.next.get(c), word, len + 1);
      }
    }

    private boolean isWildcard(char c) {
      return c == WILDCARD;
    }

    private static class TrieNode {

      private final Map<Character, TrieNode> next = new HashMap<>();
      private boolean isWordEnd;
    }
  }
}
