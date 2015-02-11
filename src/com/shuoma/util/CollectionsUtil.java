package com.shuoma.util;

import java.util.Collection;
import java.util.Map;

public class CollectionsUtil {

  /**
   * Add {@code value} to collection {@code map}.get({@code key}). If {@code key} does not exists
   * yet, set it to {@code emptyCollection} before addition.
   */
  public static <K, E> void upsert(Map<K, Collection<E>> map, K key, E value, 
      Collection<E> emptyCollection) {
    Collection<E> collection = map.get(key);
    if (collection == null) {
      collection = emptyCollection;
    }
    collection.add(value);
    map.put(key, collection);
  }
}
