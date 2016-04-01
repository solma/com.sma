package com.sma.alg;

import com.sma.util.ArrayUtil;
import junit.framework.TestCase;
import org.junit.Test;

import java.util.Arrays;

public class GeneratingRandNUsingRand2Test extends TestCase {

  GeneratingRandNUsingRand2 ins = new GeneratingRandNUsingRand2();
  static int TIMES = 200000;

  @Test
  public void testRand3() throws Exception {
    double[] ret = new double[3];
    for (int i = 0; i < TIMES; i++) {
      ret[ins.rand3()]++;
    }
    System.out.println(Arrays.toString(ret) + " " + error(ret) + "%");
  }

  @Test
  public void testRand4() throws Exception {
    double[] ret = new double[4];
    for (int i = 0; i < TIMES; i++) {
      ret[ins.rand4()]++;
    }
    System.out.println(Arrays.toString(ret) + " " + error(ret) + "%");
  }

  @Test
  public void testRandN() throws Exception {
    int N = 13;
    double[] ret = new double[N];
    for (int i = 0; i < TIMES; i++) {
      ret[ins.randN(N)]++;
    }
    System.out.println(Arrays.toString(ret) + " " + error(ret) + "%");
  }

  double error(double[] ary) {
    double[] maxAndMin = ArrayUtil.minAndMax(ary);
    return (maxAndMin[1] - maxAndMin[0]) / maxAndMin[0] * 100;
  }
}
