package com.shuoma.ds.misc;

import java.util.Collections;
import java.util.PriorityQueue;

public class MinMaxPriorityQueue {
  public PriorityQueue<Element> max;
  public PriorityQueue<Element> min;

  public MinMaxPriorityQueue() {
    max = new PriorityQueue<>(10, Collections.reverseOrder());
    min = new PriorityQueue<>();
  }

  public void add(Element e) {
    min.add(e);
    max.add(e);
  }

  public Element pollMin() {
    Element e = min.poll();
    max.remove(e);
    return e;
  }

  public Element peekMax() {
    return max.peek();
  }

  public static class Element implements Comparable<Element> {
    public int idxOfOriginList;
    public int value;

    public Element(int idxOfOriginList, int value) {
      this.idxOfOriginList = idxOfOriginList;
      this.value = value;
    }

    @Override
    public int compareTo(Element other) {
      return value - other.value;
    }

    @Override
    public String toString(){
      return "" + value;
    }
  }
}

