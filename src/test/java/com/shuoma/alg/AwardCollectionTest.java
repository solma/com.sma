package com.shuoma.alg;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class AwardCollectionTest {

  @Test
  public void testOnePerson() {
    AwardCollection ins = new AwardCollection();

    int[][] table1 = new int[][] {
        {1, 2, 3, 4},
        {5, 6, 7, 8},
        {9, 10, 11, 12},
        {13, 14, 15, 16}
    };
    assertEquals(73, ins.onePerson(table1));
    assertEquals(114, ins.twoPersons(table1));

    int[][] table2 = new int[][] {
        {1, 9},
        {8, 7},
    };
    assertEquals(17, ins.onePerson(table2));
    assertEquals(25, ins.twoPersons(table2));
  }
}
