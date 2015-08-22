package com.shuoma.lang.thread.tutorial.part1;// UncaughtExceptionDemo.java

public class UncaughtExceptionDemo {
  public static void main(String[] args) {
    MyGroup mg = new MyGroup("My Group");

    Worker w = new Worker();

    new Thread(mg, w).start();
  }
}


class MyGroup extends ThreadGroup {
  MyGroup(String name) {
    super(name);
  }

  public void uncaughtException(Thread t, Throwable e) {
    if (e instanceof ArithmeticException)
      System.out.println("Problems with arithmetic.");
    else
      super.uncaughtException(t, e);
  }
}


class Worker implements Runnable {
  public void run() {
    System.out.println(1 / 0);
  }
}

