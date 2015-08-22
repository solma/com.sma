package com.shuoma.lang.concurrent.tutorial.part2.exercises;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class DeadlockDemo {
  private Object lock1 = new Object();
  private Object lock2 = new Object();

  public void instanceMethod1() {
    synchronized (lock1) {
      synchronized (lock2) {
        System.out.println("first thread in instanceMethod1");
        // critical section guarded first by
        // lock1 and then by lock2
      }
    }
  }

  public void instanceMethod2() {
    synchronized (lock2) {
      synchronized (lock1) {
        System.out.println("second thread in instanceMethod2");
        // critical section guarded first by
        // lock2 and then by lock1
      }
    }
  }

  public static void main(String[] args) {
    final DeadlockDemo dld = new DeadlockDemo();
    Runnable runnableA = new Runnable() {
      @Override public void run() {
        while (true) {
          dld.instanceMethod1();
          try {
            Thread.sleep(30);
          } catch (InterruptedException ie) {
            assert false;
          }
        }
      }
    };
    ExecutorService executorA = Executors.newSingleThreadExecutor();
    Runnable runnableB = new Runnable() {
      @Override public void run() {
        while (true) {
          dld.instanceMethod2();
          try {
            Thread.sleep(30);
          } catch (InterruptedException ie) {
            assert false;
          }
        }
      }
    };
    ExecutorService executorB = Executors.newSingleThreadExecutor();
    executorA.submit(runnableA);
    executorB.submit(runnableB);
  }
}
