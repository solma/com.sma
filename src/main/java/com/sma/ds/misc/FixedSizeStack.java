package com.sma.ds.misc;

import java.util.Arrays;

public class FixedSizeStack {

  private Object[] Q;
  private final int capa;
  private int top = 0;

  public FixedSizeStack(int capacity){
    capa = capacity;
    Q = new Object[capa];
  }

  public int size() {
    return top;
  }

  public boolean isEmpty() {
    return top == 0;
  }

  public boolean isFull() {
    return top == capa;
  }

  /**
   * Push as many as possible, discard left data once the stack is full.
   * @param data
   * @return
   */
  public int push(Object[] data){
    if(isFull()){ return 0; }

    int writeLen = Math.min(data.length, capa - top);
    for (int i = 0; i < writeLen; i++) {
      Q[top + i] = data[i];
    }
    top += writeLen;
    return writeLen;
  }

  public Object[] poll(int n) {
    if(isEmpty()){ return new Object[0]; }

    int readLen = Math.min(n, top);
    Object[] ret = new Object[readLen];
    for (int i = 0; i < readLen; i++) {
      ret[i] = Q[top - 1 - i];
    }
    top -= readLen;
    return ret;
  }

  @Override
  public String toString() {
    return "top: " + top + " " + Arrays.toString(Q);
  }
}
