package com.sma.lang.collection;

import java.util.Arrays;
import java.util.List;

public class JavaCollections {
  public static void main(String[] args) {
    new JavaCollections().main();
  }

  void main() {
    //binarySearch();
    listRemove();
  }

  void binarySearch() {
    int[] a = {1, 3, 5, 7};
    int key = 2;
    System.out.println("a[" + ~Arrays.binarySearch(a, key) + "] is the first element in the array "
        + "that is larger than " + key);
  }

  void listRemove() {
    List<Integer> li = Arrays.asList(1, 2, 3, 4, 5, 6, 7);
    try {
      while (li.size() > 1) {
        li.remove(0);
      }
      System.out.println(li);
    } catch (UnsupportedOperationException e) {
      System.out.println("cannot modify list while iterate it");
      e.printStackTrace();
    }
  }
}
