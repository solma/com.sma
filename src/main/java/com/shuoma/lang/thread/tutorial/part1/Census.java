package com.shuoma.lang.thread.tutorial.part1;// Census.java

public class Census {
  public static void main(String[] args) {
    Thread[] threads = new Thread[Thread.activeCount()];
    int n = Thread.enumerate(threads);

    for (int i = 0; i < n; i++)
      System.out.println(threads[i].toString());
  }
}
