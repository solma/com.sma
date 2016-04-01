package com.sma.alg;

import static com.sma.ds.graph.tree.TrieNode.buildTree;

import com.sma.ds.graph.tree.TrieNode;

import java.util.LinkedList;
import java.util.List;

/**
 * Given a dictionary and a string, tokenize the string into a list of words in the dictioanry.
 */
public class TokenizeStringBasedOnDictionary {

  List<String> tokenize(String[] dict, String s) {
    TrieNode root = buildTree(dict);
    return tokenize(root, s, root, 0, new StringBuilder());
  }

  List<String> tokenize(TrieNode root, String s, TrieNode father, int idx, StringBuilder sb) {
    List<String> ret = new LinkedList<>();
    int n = s.length();
    if (idx == n) {
      ret.add(sb.toString());
      return ret;
    }

    // if cur is "word" node, we can add one word and restart
    if (father.cnt > 0) {
      sb.append(' ');
      ret.addAll(tokenize(root, s, root, idx, sb));
      sb.deleteCharAt(sb.length() - 1); //remove ' '
    }

    char c = s.charAt(idx);
    if (!father.children.containsKey(c)) {
      return ret;
    }
    sb.append(c);
    ret.addAll(tokenize(root, s, father.children.get(c), idx + 1, sb));
    sb.deleteCharAt(sb.length() - 1); //remove c
    return ret;
  }
}
