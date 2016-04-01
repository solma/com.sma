package com.sma.alg;

import static com.sma.annotation.Tag.Algorithm.BreadthFirstSearch;
import static com.sma.annotation.Tag.DataStructure.Array;
import static com.sma.annotation.Tag.DataStructure.Trie;
import static com.sma.annotation.Tag.Difficulty.D3;
import static com.sma.annotation.Tag.Reference.LeetCode;

import com.sma.annotation.Tag;
import com.sma.ds.graph.tree.TrieNode;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

@Tag(algs = {BreadthFirstSearch}, dl = D3, dss = {Array, Trie}, references = LeetCode)
public class WordSearchII {
  public static void main(String[] args) {
    new WordSearchII().main();
  }

  public void main() {
    char[][] board = new char[][] {
        {'b', 'a', 'b', 'b', 'a'}
    };
    String[] words = {"baa","abba","baab","aba"};
    System.out.println(findWords(board, words));
  }

  public List<String> findWords(char[][] board, String[] words) {
    List<String> ret = new LinkedList<>();
    int n = board.length;
    if (n == 0) return ret;
    int m = board[0].length;
    if (m == 0) return ret;

    TrieNode root = TrieNode.buildTree(words);
    System.out.println(root);
    return searchTrie(board, n, m, root);
  }

  List<String> searchTrie(char[][] board, int n, int m, TrieNode root) {
    Set<String> found = new HashSet<>();
    boolean[][] visited = new boolean[n][m];
    for (int i = 0; i < n; i++) {
      for (int j = 0; j < m; j++) {
        found.addAll(dfs(board, visited, n, m, i, j, root));
      }
    }
    return new LinkedList<>(found);
  }

  List<String> dfs(char[][] board, boolean[][] visited, int n, int m, int si, int sj, TrieNode curNode) {
    List<String> found = new LinkedList<>();
    char curChar = board[si][sj];
    visited[si][sj] = true;
    if (curNode.children.containsKey(curChar)) {
      TrieNode nextNode = curNode.children.get(curChar);
      // a word-node
      if (nextNode.cnt > 0) {
        found.add(nextNode.path);
      }
      if (si + 1 < n && !visited[si + 1][sj]) {
        found.addAll(dfs(board, visited, n, m, si + 1, sj, nextNode));
      }
      if (si - 1 >= 0 && !visited[si - 1][sj]) {
        found.addAll(dfs(board, visited, n, m, si - 1, sj, nextNode));
      }
      if (sj + 1 < m && !visited[si][sj + 1]) {
        found.addAll(dfs(board, visited, n, m, si, sj + 1, nextNode));
      }
      if (sj - 1 >= 0 && !visited[si][sj - 1]) {
        found.addAll(dfs(board, visited, n, m, si, sj - 1, nextNode));
      }
    }
    visited[si][sj] = false;
    return found;
  }
}
