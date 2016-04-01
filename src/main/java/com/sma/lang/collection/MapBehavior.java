package com.sma.lang.collection;

import java.util.HashMap;
import java.util.Map;

public class MapBehavior {
  public static void main(String[] args) {
    new MapBehavior().main();
  }

  void main() {
    //mapCopy();
    //mapEqual();
  }

  void mapEqual() {
    Map<Integer, Integer> counter1 = new HashMap<>();
    counter1.put(2, 10);
    counter1.put(1, 10);

    Map<Integer, Integer> counter2 = new HashMap<>();
    counter2.put(1, 10);
    counter2.put(2, 10);

    System.out.println("two maps are equal : " + counter1.equals(counter2));
  }

  void mapCopy() {
    Map<Integer, Integer> counter = new HashMap<>();
    counter.put(1, 10);

    Map<Integer, Integer> cpy1 = new HashMap<>(counter);

    Map<Integer, Integer> cpy2 = new HashMap<>();
    cpy2.putAll(cpy1);

    counter.put(1, 9);

    System.out.println(counter);
    System.out.println(cpy1);
    System.out.println(cpy2);
  }
}
