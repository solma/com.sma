package com.sma.lang.concurrent.tutorial.part2.exercises.answers;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class UseConcurrentQueue {
  public static void main(String[] args) {
    final ConcurrentQueue<Integer> cq = new ConcurrentQueue<>(5);
    Runnable r1 = new Runnable() {
      @Override public void run() {
        int counter = 0;
        while (true)
          try {
            cq.add(counter++);
          } catch (InterruptedException ie) {
          }
      }
    };
    ExecutorService e1 = Executors.newSingleThreadExecutor();
    Runnable r2 = new Runnable() {
      @Override public void run() {
        while (true)
          try {
            System.out.printf("counter = %d%n", cq.remove());
          } catch (InterruptedException ie) {
          }
      }
    };
    ExecutorService e2 = Executors.newSingleThreadExecutor();
    e1.submit(r1);
    e2.submit(r2);
  }
}
