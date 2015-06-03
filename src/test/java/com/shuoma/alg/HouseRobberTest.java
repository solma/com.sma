package com.shuoma.alg;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class HouseRobberTest {

  @Test public void test() {
    HouseRobber ins = new HouseRobber();

    int[][] houseSets =
        new int[][] {{10, 3, 2, 5, 7, 8}, {10, 9, 8, 9, 1}, {11, 15}, {7, 7, 7, 7, 7, 7, 7},
            {1, 2, 3, 4, 5, 1, 2, 3, 4, 5},
            {94, 40, 49, 65, 21, 21, 106, 80, 92, 81, 679, 4, 61, 6, 237, 12, 72, 74, 29, 95, 265,
                35, 47, 1, 61, 397, 52, 72, 37, 51, 1, 81, 45, 435, 7, 36, 57, 86, 81, 72},
            {1, 1, 1, 2}, {2, 2, 4, 3, 2, 5}};
    int[][] results =
        new int[][] {{19, 23}, {19, 19}, {15, 15}, {21, 28}, {16, 17}, {2926, 2982}, {3, 3},
            {10, 11}};

    for (int i = 0; i < houseSets.length; i++) {
      int[] properties = houseSets[i];
      assertEquals(results[i][0], ins.robCircleSpaceEfficientDp(properties));
      //assertEquals(ins.robCircleSpaceEfficientDp(properties), ins.robCircleStandardDp(properties));
      //assertEquals(ins.robCircle(properties), ins.robCircleStandardDp(properties));
      assertEquals(results[i][1], ins.rob(properties));
    }
  }
}
