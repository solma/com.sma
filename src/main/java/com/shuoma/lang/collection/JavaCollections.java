package com.shuoma.lang.collection;

import java.util.Arrays;

public class JavaCollections {
  public static void main(String[] args) {
    new JavaCollections().main();
  }

  void main() {
    binarySearch();
  }

  void binarySearch() {
    int[] a = {1, 3, 5, 7};
    int key = 2;
    System.out.println("a[" + ~Arrays.binarySearch(a, key) + "] is the first element in the array "
        + "that is larger than " + key);
  }
}
