package com.sma.lang.collection;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class ListBehavior {
  public static void main(String[] args) {
    new ListBehavior().main();
  }

  public void main() {
    emptyListToArray();
  }

  void emptyListToArray() {
    List<Integer> li = new LinkedList<>();
    Integer[] a = li.toArray(new Integer[0]);
    System.out.println(Arrays.toString(a));
  }
}
