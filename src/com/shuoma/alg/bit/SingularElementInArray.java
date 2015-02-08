package com.shuoma.alg.bit;

import com.shuoma.util.ArrayUtil;
import com.shuoma.util.RandomUtil;

import java.util.Arrays;

/**
 * Given an array in which each element appears K times and only one element appears once,
 * find the element.
 */
public class SingularElementInArray {
  public static void main(String[] args) {
    int k = 5;
    int[] singularArray = generateArrayWithSingularElement(k);
    System.out.println(singularElement(singularArray, k));
  }

  static int singularElement(int[] a, int k) {
    int n = k - 1;
    int[][] counters = new int[2][n];

    String[][] bins = new String[k + 1][a.length + 1];
    bins[0][0] = "number x";
    for (int i = 1; i < bins.length; i++) bins[i][0] = i + " times";

    for (int j = 0; j < a.length; j++) {
      int x = a[j];
      counters[1][0] = counters[0][0] ^ x;
      for (int i = 1; i < n; i++) {
        counters[1][i] = counters[0][i] | (counters[0][i - 1] & x);
      }

      int allCountersNotEqualK = -1;
      for (int i = 0; i < n; i++) {
        allCountersNotEqualK &= counters[1][i];
      }
      allCountersNotEqualK = ~allCountersNotEqualK;
      for (int i = 0; i < n; i++) {
        counters[1][i] &= allCountersNotEqualK;
      }

      counters[0] = Arrays.copyOf(counters[1], counters[1].length);
      Arrays.fill(counters[1], 0);

      // populate internal table
      bins[0][j + 1] = Integer.toBinaryString(x);
      for(int i = 1; i < bins.length - 1; i++)
        bins[i][j + 1] = Integer.toBinaryString(counters[0][i - 1]);
      bins[bins.length - 1][j + 1] = String.valueOf(allCountersNotEqualK);
    }

    for(String[] row : bins) {
      for (int i = 0; i < row.length; i++) {
        int width = i == 0 ? 10 : 5;
        System.out.print(String.format("%" + width + "s ", row[i]));
      }
      System.out.println();
    }

    return counters[0][0];
  }

  static int[] generateArrayWithSingularElement(int k) {
    int[] naturalArray = ArrayUtil.getNaturalArray(10);
    naturalArray = RandomUtil.shuffle(naturalArray);
    System.out.println("singular element: " + naturalArray[0]);

    int[] result = new int[(naturalArray.length - 1) * k + 1];
    for (int i = 0; i < k; i++)
      System.arraycopy(naturalArray, 1, result, (naturalArray.length - 1) * i, naturalArray.length - 1);
    result[result.length - 1] = naturalArray[0];
    result = RandomUtil.shuffle(result);
    return result;
  }
}
