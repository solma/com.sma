package com.shuoma.ds.misc;

import java.util.Arrays;

public class CircularArrayQueue {

  private Object[] Q;
  private final int capa;
  private int nWIdx = 0; // next write
  private int size = 0;

  public CircularArrayQueue(int capacity){
    capa = capacity;
    Q = new Object[capa];
  }

  public int size() {
    return size;
  }

  public boolean isEmpty() {
    return size == 0;
  }

  public boolean isFull() {
    return size == capa;
  }

  public int enqueue(Object[] data){
    if(isFull()){ return 0; }

    int writeLen = Math.min(data.length, capa - size);
    for (int i = 0; i < writeLen; i++) {
      Q[(nWIdx + i) % capa] = data[i];
    }
    nWIdx = (nWIdx + writeLen) % capa;
    size += writeLen;
    //System.out.println(this);
    return writeLen;
  }

  public Object[] dequeue(int n) {
    if(isEmpty()){ return new Object[0]; }

    int readLen = Math.min(n, size);
    Object[] ret = new Object[readLen];
    for (int i = 0; i < readLen; i++) {
      ret[i] = Q[(nWIdx - size + i + capa) % capa];
    }
    size -= readLen;
    //System.out.println(this);
    return ret;
  }

  @Override
  public String toString() {
    return "writeIdx: " + nWIdx + " size: " + size + " " + Arrays.toString(Q);
  }
}
