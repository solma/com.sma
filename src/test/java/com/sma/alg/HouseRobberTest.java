package com.sma.alg;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class HouseRobberTest {

  @Test public void test() {
    HouseRobber ins = new HouseRobber();

    int[][] houseSets =
        new int[][] {{10, 3, 2, 5, 7, 8}, {10, 9, 8, 9, 1}, {11, 15}, {7, 7, 7, 7, 7, 7, 7},
            {1, 2, 3, 4, 5, 1, 2, 3, 4, 5},
            {94, 40, 49, 65, 21, 21, 106, 80, 92, 81, 679, 4, 61, 6, 237, 12, 72, 74, 29, 95, 265,
                35, 47, 1, 61, 397, 52, 72, 37, 51, 1, 81, 45, 435, 7, 36, 57, 86, 81, 72},
            {1, 1, 1, 2}, {2, 2, 4, 3, 2, 5}, {2, 1, 2, 6, 1, 8, 10, 10}};

    // 0 is cycle, 1 is not cycle
    int[][] results =
        new int[][] {{19, 23}, {19, 19}, {15, 15}, {21, 28}, {16, 17}, {2926, 2982}, {3, 3},
            {10, 11}, {25, 26}};

    for (int i = 0; i < houseSets.length; i++) {
      int[] properties = houseSets[i];
      //System.out.println("\n" + Arrays.toString(properties));
      assertEquals(results[i][1], ins.rob1(properties, 0, properties.length - 1));
      assertEquals(results[i][1], ins.rob2(properties, 0, properties.length - 1));
      assertEquals(results[i][1], ins.rob3(properties, 0, properties.length - 1));
      assertEquals(results[i][0], ins.robCircle(properties));
      assertEquals(results[i][0], ins.rob1Circle(properties, 0, properties.length - 1));
    }
  }
}
