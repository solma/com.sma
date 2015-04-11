package com.shuoma.lang.array;

import java.util.Arrays;

public class ArrayBehavior {
  public static void main(String[] args) {
    new ArrayBehavior().main();
  }

  void main() {
    //copyArray();
    passInArray();
  }

  void copyArray() {
    int[] arr = {3, 1, 2};
    arr[0]++;
    arr[1]--;
    System.out.println(Arrays.toString(Arrays.copyOf(arr, 5)));
    System.out.println(Arrays.toString(arr) + " ");
  }

  // Array variables are references
  void passInArray() {
    boolean[] copy = new boolean[2];
    Integer i = new Integer(1);
    callee(copy, i);
    System.out.println(Arrays.toString(copy));
    System.out.println(i);
  }

  void callee(boolean[] copy, Integer i) {
    boolean[] values = {true, false};
    copy = Arrays.copyOf(values, values.length);
    //System.arraycopy(values, 0, copy, 0, values.length);
    i = new Integer(10);
  }
}
