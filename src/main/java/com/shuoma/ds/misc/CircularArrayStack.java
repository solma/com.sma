package com.shuoma.ds.misc;

import java.util.Arrays;

public class CircularArrayStack {

  private Object[] Q;
  private final int capa;
  private int nWIdx = 0; // next write
  private int size = 0;

  public CircularArrayStack(int capacity){
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

  public int push(Object[] data){
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

  public Object[] poll(int n) {
    if(isEmpty()){ return new Object[0]; }

    int readLen = Math.min(n, size);
    Object[] ret = new Object[readLen];
    for (int i = 0; i < readLen; i++) {
      ret[i] = Q[(nWIdx - 1 - i + capa) % capa];
    }
    size -= readLen;

    // shift remaining elements to nWIdx
    for (int i = size - 1; i >= 0; i--) {
      int oldIdx = (nWIdx - (size + readLen) + i + capa) % capa;
      Q[(oldIdx + readLen) % capa] = Q[oldIdx];
    }
    //System.out.println(this);
    return ret;
  }

  @Override
  public String toString() {
    return "writeIdx: " + nWIdx + " size: " + size + " " + Arrays.toString(Q);
  }
}
