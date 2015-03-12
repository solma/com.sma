package com.shuoma.ds.geometry;

import junit.framework.TestCase;
import org.junit.Test;

public class RectangleTest extends TestCase {

  @Test
  public void testIsOveralapped() throws Exception {
    Rectangle rec1 = new Rectangle(new int[]{0, 5}, new int[]{5, 0});
    Rectangle rec2 = new Rectangle(new int[]{3, 3}, new int[]{8, 2});
    assertEquals(true, rec1.isOverlapped(rec2));
    Rectangle rec3 = new Rectangle(new int[]{6, -1}, new int[]{8, -2});
    assertEquals(false, rec1.isOverlapped(rec3));
  }
}
