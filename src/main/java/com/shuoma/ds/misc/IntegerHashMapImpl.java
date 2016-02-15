package com.shuoma.ds.misc;

public class IntegerHashMapImpl {
  private static final int TABLE_SIZE = 128;

  HashEntry[] table = new HashEntry[TABLE_SIZE]; // size is usually power of 2

  public void put(int key, int value) {
    int hash = key % table.length;
    while (table[hash] != null && table[hash].key() != key) {
      hash = (hash + 1) % table.length;
    }
    table[hash] = new HashEntry(key, value);
  }

  public int get(int key) {
    int hash = key % table.length;
    while (table[hash] != null && table[hash].key() != key) {
      hash = (hash + 1) % table.length;
    }
    if (table[hash] == null) return -1;
    return table[hash].value();
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    for (HashEntry entry: table) {
      if (entry == null) continue;
      sb.append(entry + "\n");
    }
    return sb.toString();
  }

  public static void main(String[] args) {
    IntegerHashMapImpl map = new IntegerHashMapImpl();
    map.put(1, 90);
    map.put(2, 91);
    map.put(129, 92);
    map.put(1, 20);
    map.put(129, 3);
    System.out.println(map);
  }

  public class HashEntry {
    private int key;
    private int value;

    public HashEntry(int key, int value) {
      this.key = key;
      this.value = value;
    }

    public int value() {
      return value;
    }

    public int key() {
      return key;
    }

    @Override
    public String toString() {
      return key + ":" + value;
    }
  }
}
