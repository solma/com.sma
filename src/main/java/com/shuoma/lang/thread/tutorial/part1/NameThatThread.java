package com.shuoma.lang.thread.tutorial.part1;// NameThatThread.java

public class NameThatThread {
  public static void main(String[] args) {
    MyThread mt;

    if (args.length == 0)
      mt = new MyThread();
    else
      mt = new MyThread(args[0]);

    mt.start();
  }

  static class MyThread extends Thread {
    MyThread() {
      // The compiler creates the bytecode equivalent of super ();
    }

    MyThread(String name) {
      super(name); // Pass name to Thread superclass.
    }

    public void run() {
      System.out.println("My name is: " + getName());
    }
  }
}


