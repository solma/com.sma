package com.shuoma.alg;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import java.util.Arrays;

public class CourseScheduleTest {

  @Test public void testCanFinish() throws Exception {
    CourseSchedule ins = new CourseSchedule();
    //System.out.println(Arrays.toString(ins.findOrder(3, new int[][] {{0, 2}, {1, 2}})));

    int[] ary = ins.findOrder(3, new int[][] {{1, 0}, {2, 0}});
    assertTrue(Arrays.equals(new int[] {1, 2, 0}, ary) || Arrays.equals(new int[] {2, 1, 0}, ary));

    assertArrayEquals(new int[0], ins.findOrder(2, new int[][] {{1, 0}, {0, 1}}));
    assertArrayEquals(new int[] {1, 0}, ins.findOrder(2, new int[][] {{1, 0}}));
  }
}
