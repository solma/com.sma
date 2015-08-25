package com.shuoma.alg;

import static com.shuoma.annotation.Tag.DataStructure.Hash;

import com.shuoma.annotation.Tag;

import java.util.HashMap;

/**
 * Implement a RandomPool with O(1) insert(key), delete(key) getRandom() operations.
 */
@Tag(dss = Hash)
public class RandomPool<K> {
  private HashMap<K, Integer> keyIndexMap;
  // maintain a index to key map for O(1) random
  private HashMap<Integer, K> indexKeyMap;
  private int size;

  public RandomPool() {
    this.keyIndexMap = new HashMap<>();
    this.indexKeyMap = new HashMap<>();
    this.size = 0;
  }

  public void insert(K key) {
    if (!this.keyIndexMap.containsKey(key)) {
      this.keyIndexMap.put(key, this.size);
      this.indexKeyMap.put(this.size++, key);
    }
  }

  // replace the removed key with the last inserted key
  public void delete(K key) {
    if (this.keyIndexMap.containsKey(key)) {
      int deleteIndex = this.keyIndexMap.get(key);
      int lastIndex = --this.size;
      K lastKey = this.indexKeyMap.get(lastIndex);
      this.keyIndexMap.put(lastKey, deleteIndex);
      this.indexKeyMap.put(deleteIndex, lastKey);
      this.keyIndexMap.remove(key);
      this.indexKeyMap.remove(lastIndex);
    }
  }

  public K getRandom() {
    if (this.size == 0) { return null; }
    int randomIndex = (int) (Math.random() * this.size);
    return this.indexKeyMap.get(randomIndex);
  }
}
