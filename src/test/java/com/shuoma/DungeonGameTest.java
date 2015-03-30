package com.shuoma;

import junit.framework.TestCase;
import org.junit.Test;

public class DungeonGameTest extends TestCase {

  @Test
  public void testCalculateMinimumHP() throws Exception {
    int[][] dungeon = {
        {-2, -3, 3},
        {-5, -10, 1},
        {10, 30, -5}
    };
    DungeonGame ins = new DungeonGame();
    assertEquals(7, ins.calculateMinimumHP(dungeon));
  }
}
