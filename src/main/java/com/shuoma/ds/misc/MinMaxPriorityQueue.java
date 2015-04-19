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

  // poll using Min
  public Element poll() {
    Element e = min.poll();
    max.remove(e);
    return e;
  }

  public static class Element implements Comparable<Element> {
    public int key;
    public int value;

    public Element(int key, int value) {
      this.key = key;
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

