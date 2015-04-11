package com.shuoma.alg;

import junit.framework.TestCase;
import org.junit.Test;

import java.util.Set;

public class NaturalNumberDecompositionTest extends TestCase {

  @Test
  public void test() {
    NaturalNumberDecomposition ins = new NaturalNumberDecomposition();
    int n = 4;
    Set[] res = new Set[2];
    res[0] = ins.bottomUpRecursionWithMemory(n);
    res[1] = ins.topDownRecursion(n);
//    for (Set s : res) {
//      System.out.println(s);
//    }
    assertEquals(res[0], res[1]);
  }
}
