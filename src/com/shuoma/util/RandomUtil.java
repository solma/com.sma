package com.shuoma.util;

import java.util.Random;

public class RandomUtil {

  public static final Random r = new Random();
  public static final int MAX_RANDOM_VALUE = 1000;

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
    }
    if (oneBased) {
      for (int i = 0; i < length; i++) {
        ret[i] += 1;
      }
    }
    if (canBeNegative) {
      for (int i = 0; i < length; i++) {
        ret[i] *= (r.nextBoolean() ? 1 : -1);
      }
    }
    return ret;
  }

  public static int[] genRandomArrayWithMinSize(int minSize) {
    return genRandomArrayWithMinSize(minSize, MAX_RANDOM_VALUE);
  }

  public static int[] genRandomArrayWithMinSize(int minSize ,int maxValue) {
    int length = r.nextInt(10) + minSize; // at least two elements
    return genRandomArray(length, maxValue, false, true);
  }

  public static int[] leftShift(int[] a, int start) {
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

  public static int[] shuffle(int[] arr) {
    for(int i = 0; i < arr.length; i++) {
      int swap = i + r.nextInt(arr.length - i);
      ArrayUtil.swap(arr, i, swap);
    }
    return arr;
  }
}
