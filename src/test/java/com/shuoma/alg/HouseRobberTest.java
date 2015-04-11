package com.shuoma.alg;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class HouseRobberTest {

  @Test public void test() {
    HouseRobber ins = new HouseRobber();

    int[] properties = new int[] {10, 3, 2, 5, 7, 8};
    assertEquals(19, ins.robCircleSpaceEfficientDp(properties));
    assertEquals(ins.robCircleSpaceEfficientDp(properties), ins.robCircleStandardDp(properties));
    assertEquals(ins.robCircle(properties), ins.robCircleStandardDp(properties));
    assertEquals(23, ins.rob(properties));

    properties = new int[] {10, 9, 8, 9, 1};
    assertEquals(19, ins.robCircleSpaceEfficientDp(properties));
    assertEquals(ins.robCircleSpaceEfficientDp(properties), ins.robCircleStandardDp(properties));
    assertEquals(ins.robCircle(properties), ins.robCircleStandardDp(properties));
    assertEquals(19, ins.rob(properties));

    properties = new int[] {11, 15};
    assertEquals(15, ins.robCircleSpaceEfficientDp(properties));
    assertEquals(ins.robCircleSpaceEfficientDp(properties), ins.robCircleStandardDp(properties));
    assertEquals(ins.robCircle(properties), ins.robCircleStandardDp(properties));
    assertEquals(15, ins.rob(properties));

    properties = new int[] {7, 7, 7, 7, 7, 7, 7};
    assertEquals(21, ins.robCircleSpaceEfficientDp(properties));
    assertEquals(ins.robCircleSpaceEfficientDp(properties), ins.robCircleStandardDp(properties));
    assertEquals(ins.robCircle(properties), ins.robCircleStandardDp(properties));
    assertEquals(28, ins.rob(properties));

    properties = new int[] {1, 2, 3, 4, 5, 1, 2, 3, 4, 5};
    assertEquals(16, ins.robCircleSpaceEfficientDp(properties));
    assertEquals(ins.robCircleSpaceEfficientDp(properties), ins.robCircleStandardDp(properties));
    assertEquals(ins.robCircle(properties), ins.robCircleStandardDp(properties));
    assertEquals(17, ins.rob(properties));

    properties =
        new int[] {94, 40, 49, 65, 21, 21, 106, 80, 92, 81, 679, 4, 61, 6, 237, 12, 72, 74, 29, 95,
            265, 35, 47, 1, 61, 397, 52, 72, 37, 51, 1, 81, 45, 435, 7, 36, 57, 86, 81, 72};
    assertEquals(2926, ins.robCircleSpaceEfficientDp(properties));
    assertEquals(ins.robCircleSpaceEfficientDp(properties), ins.robCircleStandardDp(properties));
    assertEquals(ins.robCircle(properties), ins.robCircleStandardDp(properties));
    assertEquals(2982, ins.rob(properties));
  }
}
