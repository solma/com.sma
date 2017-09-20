package com.sma.lang.thread.tutorial.part4;// InheritableThreadLocalDemo.java

public class InheritableThreadLocalDemo implements Runnable {
  static InheritableThreadLocal itl = new InheritableThreadLocal();

  static ThreadLocal tl = new ThreadLocal();

  public static void main(String[] args) {
    itl.set("parent thread thread-local value passed to child thread");
    tl.set("parent thread thread-local value not passed to child thread");

    InheritableThreadLocalDemo itld;
    itld = new InheritableThreadLocalDemo();

    Thread child1 = new Thread(itld);
    Thread child2 = new Thread(itld);

    child1.start();
    child2.start();
  }

  public void run() {
    System.out.println(itl.get());
    System.out.println(tl.get());
  }
}
