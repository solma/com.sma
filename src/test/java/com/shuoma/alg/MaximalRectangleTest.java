package com.shuoma.alg;

import com.shuoma.alg.MaximalRectangle;
import junit.framework.TestCase;
import org.junit.Test;

public class MaximalRectangleTest extends TestCase {

  @Test
  public void testMaximalRectangle() throws Exception {
    MaximalRectangle ins = new MaximalRectangle();
    char[][] matrix = {{'0', '1'}, {'1', '0'}, {'1', '1'}};
    assertEquals(2, ins.maximalRectangle(matrix));
  }
}
