package com.shuoma.util;

public class ArrayUtil {
  public static boolean equals(int[] a, int[] b) {
    if (a == null && b == null) return true;
    if (a == null || b == null) return false;
    int la = a.length, lb = b.length;
    if (la != lb) return false;
    for (int i = 0; i < la; i++) {
      if (a[i] != b[i]) return false;
    }
    return true;
  }

  public static int[] getNaturalArray(int size) {
    int[] res = new int[size];
    for(int i = 0; i < size; i++) res[i] = i;
    return res;
  }

  public static void reverse(char[] array) {
    int l = 0, r = array.length - 1;
    while (l < r) {
      swap(array, l++, r--);
    }
  }

  public static void swap(char[] array, int i, int j) {
    if (i == j) return;
    char swap;
    swap = array[i];
    array[i] = array[j];
    array[j] = swap;
  }

  public static void swap(int[] a, int i, int j) {
    int n = a.length;
    if (i == j || i < 0 || j < 0 || i >= n || j >= n) return;
    int tmp = a[i];
    a[i] = a[j];
    a[j] = tmp;
  }
}