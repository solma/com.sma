package com.shuoma.alg;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class NumberOfIslandsTest {

  @Test
  public void testNumIslands() {
    NumberOfIslands ins = new NumberOfIslands();
    char[][] islands = {"1111111".toCharArray()};
    assertEquals(1, ins.numIslands(islands));
  }
}
