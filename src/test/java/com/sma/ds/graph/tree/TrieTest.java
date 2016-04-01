package com.sma.ds.graph.tree;

import static com.sma.ds.graph.tree.TrieNode.buildTree;
import static com.sma.ds.graph.tree.TrieNode.longestPrefix;
import static com.sma.ds.graph.tree.TrieNode.shortestPrefix;

import junit.framework.TestCase;
import org.junit.Test;

public class TrieTest extends TestCase {

  @Test
  public void testTrie() {
    // "google", "goamazon", "gomicrosoft", "gohere", "gomoto", "gomotot"
    // "dog","be","cut", "car", "cat"
    String[] dict =
        new String[] {"google", "goamazon", "gomicrosoft", "gohere", "gomoto", "gomotot"};

    TrieNode root = buildTree(dict);
    assertEquals("go", longestPrefix(root));
    assertEquals("gomotot", longestPrefix(root, "gomotot"));
    assertEquals("c", shortestPrefix(root, "ct"));
  }
}
