package com.sma.alg;

import static com.sma.annotation.Tag.DataStructure.Hash;

import com.sma.annotation.Tag;

import java.util.HashMap;

/***
 * Implement a hash table with O(1) setAll() operation
 */
@Tag(dss = Hash)
public class HashTableWithSetAll<K, V> {

  private HashMap<K, MyValue<V>> baseMap;
  private long time;
  private MyValue<V> setAll;

  public HashTableWithSetAll() {
    this.baseMap = new HashMap<>();
    this.time = 0;
    this.setAll = new MyValue<>(null, -1);
  }

  public boolean containsKey(K key) {
    return this.baseMap.containsKey(key);
  }

  public void put(K key, V value) {
    this.baseMap.put(key, new MyValue<>(value, this.time++));
  }

  public void setAll(V value) {
    this.setAll = new MyValue<>(value, this.time++);
  }

  public V get(K key) {
    if (this.containsKey(key)) {
      if (this.baseMap.get(key).getVersion() > this.setAll.getVersion()) {
        return this.baseMap.get(key).getValue();
      } else {
        return this.setAll.getValue();
      }
    } else {
      return null;
    }
  }
}

class MyValue<V> {
  private V value;
  // each value has a version associated with it
  private long version;

  public MyValue(V value, long countTime) {
    this.value = value;
    this.version = countTime;
  }

  public V getValue() {
    return this.value;
  }

  public long getVersion() {
    return this.version;
  }
}
