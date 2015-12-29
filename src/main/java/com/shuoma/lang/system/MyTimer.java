package com.shuoma.lang.system;

import com.google.common.base.Stopwatch;

import java.util.concurrent.TimeUnit;

public class MyTimer {
  public static void main(String[] args) {
    new MyTimer().main();
  }

  void main() {
    nanoTime();
    stopwatch();
  }

  void callee() {
    int a2 = 3;
    String a = "as";
  }

  void nanoTime() {
    long before = System.nanoTime();
    callee();
    long after = System.nanoTime();
    long delta = after - before;
    System.out.println("Nanotime measurer: execution time: " + delta);
  }

  void stopwatch() {
    final Stopwatch stopwatch = Stopwatch.createStarted();
    callee();
    stopwatch.stop();
    System.out.println("Stopwatch is the measurer: execution time: " + stopwatch.elapsed(TimeUnit.NANOSECONDS));
  }
}
