package com.shuoma.ds.graph.tree;

import junit.framework.TestCase;
import org.junit.Test;


public class TrieTest extends TestCase {

  @Test
  public void testTrie() {
    // "google", "goamazon", "gomicrosoft", "gohere", "gomoto", "gomotot"
    // "dog","be","cut", "car", "cat"
    String[] dict =
        new String[] {"google", "goamazon", "gomicrosoft", "gohere", "gomoto", "gomotot"};

    Trie trie = new Trie(dict);
    assertEquals("go", trie.longestPrefix());
    assertEquals("gomotot", trie.longestPrefix("gomotot"));
    assertEquals("c", trie.shortestPrefix("ct"));
  }
}
