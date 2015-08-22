package com.shuoma.lang.thread.tutorial.part3;
// ThreadInterruptionDemo.java


public class ThreadInterruptionDemo {
  public static void main(String[] args) {
    ThreadB thdb = new ThreadB();
    thdb.setName("B");

    ThreadA thda = new ThreadA(thdb);
    thda.setName("A");

    thdb.start();
    thda.start();
  }
}


class ThreadA extends Thread {
  private Thread thdOther;

  ThreadA(Thread thdOther) {
    this.thdOther = thdOther;
  }

  public void run() {
    int sleepTime = (int) (Math.random() * 10000);

    System.out.println(getName() + " sleeping for " + sleepTime +
        " milliseconds.");

    try {
      Thread.sleep(sleepTime);
    } catch (InterruptedException e) {
    }

    System.out.println(getName() + " waking up, interrupting other " +
        "thread and terminating.");
    thdOther.interrupt();
  }
}


class ThreadB extends Thread {
  int count = 0;

  public void run() {
    while (!isInterrupted()) {
      try {
        Thread.sleep((int) (Math.random() * 10));
      } catch (InterruptedException e) {
        System.out.println(getName() + " about to terminate...");

        // Because the Boolean flag in the consumer thread's thread
        // object is clear, we call interrupt() to set that flag.
        // As a result, the next consumer thread call to isInterrupted()
        // retrieves a true value, which causes the while loop statement
        // to terminate.

        interrupt();
      }

      System.out.println(getName() + " " + count++);
    }
  }
}
