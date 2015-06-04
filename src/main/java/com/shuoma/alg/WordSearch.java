package com.shuoma.alg;

import static com.shuoma.annotation.Tag.Algorithm.BreadthFirstSearch;
import static com.shuoma.annotation.Tag.Algorithm.DepthFirstSearch;
import static com.shuoma.annotation.Tag.DataStructure.Array;
import static com.shuoma.annotation.Tag.Difficulty.D3;
import static com.shuoma.annotation.Tag.Reference.LeetCode;

import com.shuoma.annotation.Tag;

import java.util.LinkedList;
import java.util.Queue;

@Tag(algs = {BreadthFirstSearch, DepthFirstSearch}, dl = D3, dss = Array, reference = LeetCode)
public class WordSearch {
  public static void main(String[] args) {
    new WordSearch().main();
  }

  public void main() {
    char[][] board =
        new char[][] {{'a', 'b', 'c'}, {'f', 'e', 'f'}, {'j', 'i', 'e'}, {'l', 'm', 'n'}};
    System.out.println(exist(board, "eij"));
  }

  public boolean exist(char[][] board, String word) {
    if (board == null)
      return false;
    if (word == null || word.length() == 0)
      return true;
    boolean[][] visited = new boolean[board.length][board[0].length];
    for (int i = 0; i < board.length; i++)
      for (int j = 0; j < board[0].length; j++) {
        //if(DFS(board, i, j, word, 0, visited)) return true;
        if (board[i][j] == word.charAt(0) && BFS(board, i, j, word, visited))
          return true;
      }
    return false;
  }

  public boolean DFS(char[][] b, int i, int j, String word, int index, boolean[][] v) {
    if (v[i][j] || b[i][j] != word.charAt(index))
      return false;
    if (index == word.length() - 1)
      return true;
    v[i][j] = true;
    if (i != 0 && DFS(b, i - 1, j, word, index + 1, v))
      return true;
    if (i != b.length - 1 && DFS(b, i + 1, j, word, index + 1, v))
      return true;
    if (j != 0 && DFS(b, i, j - 1, word, index + 1, v))
      return true;
    if (j != b[0].length - 1 && DFS(b, i, j + 1, word, index + 1, v))
      return true;
    v[i][j] = false;
    return false;
  }


  class Node {
    int i, j;

    public Node(int i, int j) {
      this.i = i;
      this.j = j;
    }
  }

  public boolean BFS(char[][] b, int i, int j, String word, boolean[][] v) {
    int m = b.length, n = b[0].length;
    Queue<Node> curLvl = new LinkedList<>();
    curLvl.offer(new Node(i, j));
    v[i][j] = true;
    int idx = 1;

    while (!curLvl.isEmpty()) {
      Queue<Node> nextLvl = new LinkedList<>();
      while (!curLvl.isEmpty()) {
        Node cur = curLvl.poll();
        int x = cur.i, y = cur.j;

        if (x > 0 && !v[x - 1][y] && b[x - 1][y] == word.charAt(idx)) {
          nextLvl.add(new Node(x - 1, y));
          v[x - 1][y] = true;
        }
        if (y > 0 && !v[x][y - 1] && b[x][y - 1] == word.charAt(idx)) {
          nextLvl.add(new Node(x, y - 1));
          v[x][y - 1] = true;
        }
        if (x < m - 1 && !v[x + 1][y] && b[x + 1][y] == word.charAt(idx)) {
          nextLvl.add(new Node(x + 1, y));
          v[x + 1][y] = true;
        }
        if (y < n - 1 && !v[x][y + 1] && b[x][y + 1] == word.charAt(idx)) {
          nextLvl.add(new Node(x, y + 1));
          v[x][y + 1] = true;
        }
      }
      if (!nextLvl.isEmpty()) {
        if (idx == word.length() - 1)
          return true;
        idx++;
        curLvl = nextLvl;
      } else
        break;
    }

    //resetVisitStatus visit array to false;
    for (i = 0; i < m; i++)
      for (j = 0; j < n; j++)
        v[i][j] = false;
    return false;
  }


  //TLE
  public boolean existFirst(char[][] board, String word) {
    if (word == null)
      return true;
    int m = board.length;
    if (board.length == 0)
      return false;
    int n = board[0].length;

    boolean[][] visited = new boolean[m][n];
    for (int i = 0; i < m; i++) {
      for (int j = 0; j < n; j++) {
        visited[i][j] = false;
      }
    }

    int[][] dirs = new int[][] {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
    for (int i = 0; i < board.length; i++)
      for (int j = 0; j < board[0].length; j++)
        if (board[i][j] == word.charAt(0)) {
          visited[i][j] = true;
          if (existRecursion(board, word, dirs, i, j, 1, visited))
            return true;
        }
    return false;
  }

  public boolean existRecursion(char[][] board, String word, int[][] dirs, int i, int j, int idx, boolean[][] visited) {
    if (idx == word.length())
      return true;
    for (int k = 0; k < dirs.length; k++) {
      if ((i + dirs[k][0] >= 0) && (i + dirs[k][0] < board.length) && (j + dirs[k][1] >= 0) && (
          j + dirs[k][1] < board[0].length) && !visited[i + dirs[k][0]][j + dirs[k][1]]
          && board[i + dirs[k][0]][j + dirs[k][1]] == word.charAt(idx)) {
        visited[i + dirs[k][0]][j + dirs[k][1]] = true;
        if (existRecursion(board, word, dirs, i + dirs[k][0], j + dirs[k][1], idx + 1, visited))
          return true;
        visited[i + dirs[k][0]][j + dirs[k][1]] = false;
      }
    }
    return false;
  }
}
