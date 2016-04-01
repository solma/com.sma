package com.sma.lang.thread.tutorial.part3;

public class YieldDemo extends Thread {
  static boolean finished = false;
  static int sum = 0;

  public static void main(String[] args) {
    new YieldDemo().start();
    for (int i = 1; i <= 200000; i++) {
      sum++;
      if (args.length == 0)
        Thread.yield();
    }
    finished = true;
  }

  public void run() {
    while (!finished)
      System.out.println("sum = " + sum);
  }
}
