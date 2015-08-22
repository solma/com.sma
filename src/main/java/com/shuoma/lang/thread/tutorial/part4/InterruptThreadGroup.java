package com.shuoma.lang.thread.tutorial.part4;// InterruptThreadGroup.java

public class InterruptThreadGroup {
  public static void main(String[] args) {
    MyThread mt = new MyThread();
    mt.setName("A");
    mt.start();

    mt = new MyThread();
    mt.setName("B");
    mt.start();

    try {
      Thread.sleep(2000); // Wait 2 seconds.
    } catch (InterruptedException e) {
    }

    // Interrupt all methods in the same thread group as the main
    // thread.

    Thread.currentThread().getThreadGroup().interrupt();
  }
}


class MyThread extends Thread {
  public void run() {
    synchronized ("A") {
      System.out.println(getName() + " about to wait.");

      try {
        "A".wait();
      } catch (InterruptedException e) {
        System.out.println(getName() + " interrupted.");
      }

      System.out.println(getName() + " terminating.");
    }
  }
}
