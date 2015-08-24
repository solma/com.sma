package com.shuoma.alg;

import static com.shuoma.annotation.Tag.Algorithm.BreadthFirstSearch;
import static com.shuoma.annotation.Tag.DataStructure.MatrixGraph;
import static com.shuoma.annotation.Tag.Reference.Interview;
import static com.shuoma.annotation.Tag.Trick.BFSWithMultipleSource;

import com.shuoma.annotation.Tag;

import java.util.Arrays;
import java.util.LinkedList;

/**
 * Given a N*N matrix grid, have N^2 house, if the value in house is 1 means the house it locked, you can't pass, if
 * the value is 0 means the house it opened, if the value is 2, means there is a police in the house.
 * Write code to get a matrix minDistance[][] is the min Manhattan distance any police can get this house.
 */
@Tag(algs = BreadthFirstSearch, dss = MatrixGraph, tricks = BFSWithMultipleSource, references = Interview)
public class MinDistanceFromPolice {
  public static void main(String[] args) {
    int[][] roads = {{1, 0, 0, 2, 0, 0, 1, 2}, {0, 1, 0, 1, 0, 0, 0, 0}, {1, 0, 0, 1, 0, 0, 1, 0}};

    new MinDistanceFromPolice().minDis(roads);
  }

  class Cell {
    int i;
    int j;
    int dis;

    public Cell(int i, int j, int dis) {
      this.i = i;
      this.j = j;
      this.dis = dis;
    }

    @Override public String toString() {
      return "i=" + i + " j=" + j + " d=" + dis;
    }
  }


  void minDis(int[][] roads) {
    LinkedList<Cell> q = new LinkedList<>();

    for (int i = 0; i < roads.length; i++) {
      for (int j = 0; j < roads[0].length; j++) {
        if (roads[i][j] == 2) {
          q.offer(new Cell(i, j, 0));
        } else if (roads[i][j] == 1) {
          roads[i][j] = -1;
        } else {
          roads[i][j] = Integer.MAX_VALUE;
        }
      }
    }

    int[][] dirs = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
    int cnt = 0;
    // multiple reference bfs
    while (!q.isEmpty()) {
      Cell cur = q.poll();
      //roads[cur.i][cur.j] = cur.dis;
      for (int[] dir : dirs) {
        int i = cur.i + dir[0], j = cur.j + dir[1];
        if (i >= 0 && i < roads.length && j >= 0 && j < roads[0].length
            && roads[i][j] > cur.dis + 1) {
          roads[i][j] = cur.dis + 1; // set visit before adding to queue
          q.add(new Cell(i, j, cur.dis + 1));
          cnt++;
        }
      }
    }
    System.out.println("cnt: " + cnt);

    for (int i = 0; i < roads.length; i++) {
      for (int j = 0; j < roads[0].length; j++) {
        if (roads[i][j] == Integer.MAX_VALUE)
          roads[i][j] = -1;
      }
    }

    for (int i = 0; i < roads.length; i++) {
      System.out.println(Arrays.toString(roads[i]));
    }
  }
}
