package com.shuoma.alg;

import static com.shuoma.annotation.Tag.Algorithm.BreadthFirstSearch;
import static com.shuoma.annotation.Tag.Algorithm.DepthFirstSearch;
import static com.shuoma.annotation.Tag.DataStructure.MatrixGraph;
import static com.shuoma.annotation.Tag.Reference.LeetCode;

import com.shuoma.annotation.Tag;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

@Tag(algs = {BreadthFirstSearch, DepthFirstSearch}, dss = MatrixGraph, references = LeetCode)
public class NumberOfIslands {
  public int numIslands(char[][] grid) {
    int m = grid.length;
    if (m == 0) {
      return 0;
    }
    int n = grid[0].length;
    if (n == 0) {
      return 0;
    }

    int[][] dirs = {{1, 0}, {0, 1}, {-1, 0}, {0, -1}};
    int islandCnt = 0;
    for (int i = 0; i < m; i++) {
      for (int j = 0; j < n; j++) {
        if (grid[i][j] == '1') {
          bfs(grid, m, n, dirs, i, j);
          islandCnt++;
        }
      }
    }
    return islandCnt;
  }

  void bfs(char[][] grid, int m, int n, int[][] dirs, int r, int c) {
    Queue<List<Integer>> queue = new LinkedList<>();
    grid[r][c] = 'x';
    queue.offer(buildList(r, c));
    while (!queue.isEmpty()) {
      List<Integer> cur = queue.poll();
      r = cur.get(0);
      c = cur.get(1);
      for (int[] dir : dirs) {
        int nr = r + dir[0];
        int nc = c + dir[1];
        if (nr >= 0 && nr < m && nc >= 0 && nc < n && grid[nr][nc] == '1') {
          grid[nr][nc] = 'x';
          queue.offer(buildList(nr, nc));
        }
      }
    }
  }

  List<Integer> buildList(int r, int c) {
    List<Integer> cur = new ArrayList<>(2);
    cur.add(r);
    cur.add(c);
    return cur;
  }

  void dfs(char[][] grid, int m, int n, int[][] dirs, int r, int c) {
    grid[r][c] = 'x';
    for (int[] dir : dirs) {
      int nr = r + dir[0];
      int nc = c + dir[1];
      if (nr >= 0 && nr < m && nc >=0 && nc < n && grid[nr][nc] == '1') {
        dfs(grid, m, n, dirs, nr, nc);
      }
    }
  }
}
