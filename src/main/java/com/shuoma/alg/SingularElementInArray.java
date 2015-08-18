package com.shuoma.alg;

import static com.shuoma.annotation.Tag.Algorithm.BitManipulation;

import com.shuoma.annotation.Tag;

import java.util.Arrays;

/**
 * Given an array in which each element appears K times and only one element appears once,
 * find the element.
 */
@Tag(algs = BitManipulation)
public class SingularElementInArray {

  int singularElement(int[] A, int k) {
    //k is the times that all other numbers repeat themselves
    int[] setTimes = new int[k - 1];
    int[] setTimesTemp = Arrays.copyOf(setTimes, setTimes.length);

    for (int a : A) {
      for (int i = 0; i < setTimes.length; i++) {
        // for case if a bit is not set in a
        // if any bit is not set in "a" and it has been set i times (i<=nTimes) before,
        // then after a, it remains to be set i times.
        setTimesTemp[i] = (~a & setTimes[i]);

        //for case if a bit is set in a
        int orTerm;
        if (i > 0) {
          // if any bit is set in "a" and it has been set i-1 times (i>0) before,
          // then after a, it has been set i times.
          orTerm = a & setTimes[i - 1];
        } else {
          orTerm = a;
          // if any bit is not present in any of setTimes[j], then it means that
          // it has been set k times; now with the bit is set in a,
          // the bit is considered set only once
          for (int j = 0; j < setTimes.length; j++) {
            orTerm &= ~setTimes[j];
          }
        }
        setTimesTemp[i] |= orTerm;
      }

      //update setTimes
      System.arraycopy(setTimesTemp, 0, setTimes, 0, setTimes.length);
    }
    return setTimes[0];
  }

  int singularElement1(int[] a, int k) {
    int n = k - 1;
    int[][] counters = new int[2][n];

    String[][] bins = new String[k + 1][a.length + 1];
    bins[0][0] = "number x";
    for (int i = 1; i < bins.length; i++)
      bins[i][0] = i + " times";

    for (int j = 0; j < a.length; j++) {
      int x = a[j];
      counters[1][0] = counters[0][0] ^ x;
      for (int i = 1; i < n; i++) {
        counters[1][i] = counters[0][i] | (counters[0][i - 1] & x);
      }

      // when a number appears k times, all its bits are cleared from all previous counters
      int kTimesCounter = -1;
      for (int i = 0; i < n; i++) {
        kTimesCounter &= counters[1][i];
      }
      for (int i = 0; i < n; i++) {
        counters[1][i] &= ~kTimesCounter;
      }

      counters[0] = Arrays.copyOf(counters[1], counters[1].length);
      Arrays.fill(counters[1], 0);

      // populate internal table
      bins[0][j + 1] = Integer.toBinaryString(x);
      for (int i = 1; i < bins.length - 1; i++)
        bins[i][j + 1] = Integer.toBinaryString(counters[0][i - 1]);
      bins[bins.length - 1][j + 1] = String.valueOf(~kTimesCounter);
    }

    for (String[] row : bins) {
      for (int i = 0; i < row.length; i++) {
        int width = i == 0 ? 10 : 5;
        System.out.print(String.format("%" + width + "s ", row[i]));
      }
      System.out.println();
    }

    return counters[0][0];
  }
}
