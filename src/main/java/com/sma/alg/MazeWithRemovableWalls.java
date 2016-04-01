package com.sma.alg;

import static com.sma.annotation.Tag.Algorithm.BreadthFirstSearch;
import static com.sma.annotation.Tag.DataStructure.MatrixGraph;
import static com.sma.annotation.Tag.Reference.JulyEdu;
import static com.sma.annotation.Tag.Trick.BFSWithHigherDimension;

import com.sma.annotation.Tag;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

@Tag(algs = BreadthFirstSearch, dss = MatrixGraph, tricks = BFSWithHigherDimension, references = JulyEdu)
/**
 * Given a 0/1 (0 means empty, 1 means wall) matrix, find the shortest path from
 * source to destination, subject to that you can walk through at most N walls.
 */
public class MazeWithRemovableWalls {

  void shortestPath(int[][] matrix, int si, int sj, int di, int dj, int N) {
    int bi = matrix.length;
    int bj = matrix[0].length;
    Cell ori = new Cell(si, sj, N), des = null;
    int cnt = 0;
    Queue<Cell> q = new LinkedList<>();
    q.offer(ori);
    Set<Cell> visited = new HashSet<>();
    visited.add(ori);  cnt++;
    int[][] dirs = new int[][]{{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
    while (!q.isEmpty()) {
      Cell cur = q.poll();
      visited.add(cur);
      for (int[] dir : dirs) {
        int ni = cur.i + dir[0], nj = cur.j + dir[1];
        int cr = cur.remainingWallThrough;
        if (ni < 0 || nj < 0 || ni >= bi || nj >= bj) continue; // out of bound
        if (matrix[ni][nj] == 1 && cr == 0) continue; // no available wall through
        Cell nxt = new Cell(ni, nj, matrix[ni][nj] == 1 ? cr - 1 : cr);
        if (visited.contains(nxt)) continue; // already visited
        visited.add(nxt); // set visit before adding to queue
        nxt.prev = cur; // link prev
        q.offer(nxt);
        cnt++;
        if (ni == di && nj == dj) {
          des = nxt;
          break;
        }
      }
      if (des != null) break;
    }
    System.out.println("cnt: " + cnt);

    // traceback
    Cell cur = des;
    while (cur != null && !(cur.i == si && cur.j == sj)) {
      matrix[cur.i][cur.j] = 2;
      cur = cur.prev;
    }
  }
}

class Cell {
  int i;
  int j;
  int remainingWallThrough;
  Cell prev;

  public Cell(int i, int j, int remainingWallThrough) {
    this.i = i;
    this.j = j;
    this.remainingWallThrough = remainingWallThrough;
  }

  @Override
  public boolean equals(Object that) {
    return (i == ((Cell)that).i) && (j == ((Cell)that).j)
        && (remainingWallThrough == ((Cell)that).remainingWallThrough);
  }

  @Override
  public int hashCode() {
    return i + j + remainingWallThrough;
  }

  @Override
  public String toString() {
    return i + " " + j + " " + remainingWallThrough;
  }
}
