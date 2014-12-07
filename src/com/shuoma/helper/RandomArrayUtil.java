package com.shuoma.helper;

import java.util.Random;

public class RandomArrayUtil {

  public static Random r = new Random();

  public static void swap(int[] a, int i, int j) {
    int n = a.length;
    if (i == j || i < 0 || j < 0 || i >= n || j >= n) return;
    int tmp = a[i];
    a[i] = a[j];
    a[j] = tmp;
  }

  public static int[] rotate(int[] a, int start) {
    int n = a.length;
    int[] ret = new int[n];
    for (int i = start; i < n; i++) {
      ret[i - start] = a[i];
    }
    for (int i = 0; i < start; i++) {
      ret[i + n - start] = a[i];
    }
    return ret;
  }


  public static int[] genRandomArrayWithMinSize(int minSize) {
    int length = r.nextInt(10) + minSize; // at least two elements
    return genRandomArray(length, 1000, false, true);
  }

  /**
   *
   * @param length: size of array
   * @param maxNumber: possible maximum value for elements
   * @param oneBased: index starting from 1
   * @param canBeNegative: elements can be negative
   * @return random array
   */
  public static int[] genRandomArray(int length, int maxNumber, boolean oneBased, boolean canBeNegative) {
    int[] ret = new int[length];
    for (int i = 0; i < length; i++) {
      ret[i] = r.nextInt(maxNumber);
      if (!oneBased) ret[i] += 1;
      if (canBeNegative) ret[i] *= (r.nextBoolean() ? 1 : -1);
    }
    return ret;
  }
}
