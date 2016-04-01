package com.sma.lang.thread.tutorial.part4;// ThreadLocalDemo2.java

public class ThreadLocalDemo2 {
  public static void main(String[] args) {
    MyThread mt1 = new MyThread("A");
    MyThread mt2 = new MyThread("B");
    MyThread mt3 = new MyThread("C");

    mt1.start();
    mt2.start();
    mt3.start();
  }

  static class MyThread extends Thread {
    private static ThreadLocal tl = new ThreadLocal();
    private static int sernum = 100;

    MyThread(String name) {
      super(name);
    }

    public void run() {
      synchronized ("A") {
        tl.set("" + sernum++);
      }

      for (int i = 0; i < 10; i++)
        System.out.println(getName() + " " + tl.get());
    }
  }
}


