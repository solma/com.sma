package com.sma.alg;

import com.sma.util.ArrayUtil;
import org.junit.Test;

public class MazeWithRemovableWallsTest {

  @Test public void testShortestPath() throws Exception {
    MazeWithRemovableWalls ins = new MazeWithRemovableWalls();
    int[][] maze = new int[][] {
        {0, 1, 0, 1, 0, 1, 0, 1, 0},
        {0, 0, 0, 1, 0, 0, 0, 1, 0},
        {0, 0, 0, 0, 0, 0, 0, 0, 0}
    };
    ArrayUtil.print(maze);
    ins.shortestPath(maze, 0, 0, 0, 4, 1);
    ArrayUtil.print(maze);
  }
}
