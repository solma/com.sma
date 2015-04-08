package com.shuoma.util;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public class CollectionsUtil {

  /**
   * Increase counter.get(key) by increase. If value decrease to zero, it is removed from map.
   */
  public static <K> void increaseMapCounter(Map<K, Integer> counter, K key, int increase) {
    if (!counter.containsKey(key)) {
      counter.put(key, 0);
    }
    counter.put(key, counter.get(key) + increase);
    if (counter.get(key) == 0) {
      counter.remove(key);
    }
  }

  /** Print Map, sorted by Key. */
  public static <K extends Comparable<K>, V> void printMap(Map<K, V> map) {
    List<K> keys = new ArrayList<>(map.keySet());
    Collections.sort(keys);
    for (K key : keys) {
      System.out.println(key + ":" + map.get(key));
    }
  }

  /**
   * Add {@code value} to collection {@code map}.get({@code key}). If {@code key} does not exists
   * yet, set it to {@code emptyCollection} before addition.
   */
  public static <K, V extends Collection<E>, E> void upsert(Map<K, V> map, K key, E value,
      V emptyCollection) {
    V collection = map.get(key);
    if (collection == null) {
      collection = emptyCollection;
    }
    collection.add(value);
    map.put(key, collection);
  }
}
