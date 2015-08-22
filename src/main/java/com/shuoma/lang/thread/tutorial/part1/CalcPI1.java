package com.shuoma.lang.thread.tutorial.part1;
// CalcPI1.java


public class CalcPI1 {
  public static void main(String[] args) {
    MyThread mt = new MyThread();
    mt.start();

    try {
      Thread.sleep(10); // Sleep for 10 milliseconds.
    } catch (InterruptedException e) {
    }

    System.out.println("pi = " + mt.pi);
  }
}


class MyThread extends Thread {
  boolean negative = true;
  double pi; // initializes to 0.0, by default

  public void run() {
    for (int i = 3; i < 100000; i += 2) {
      if (negative)
        pi -= (1.0 / i);
      else
        pi += (1.0 / i);

      negative = !negative;
    }

    pi += 1.0;

    pi *= 4.0;

    System.out.println("Finished calculating PI");
  }
}
