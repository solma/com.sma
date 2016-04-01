package com.sma.lang.concurrent.tutorial.part2.exercises.answers;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class PreventDeadlockDemo {
  private final Lock lock1 = new ReentrantLock();
  private final Lock lock2 = new ReentrantLock();

  public void instanceMethod1() {
    if (lock1.tryLock())
      try {
        if (lock2.tryLock())
          try {
            System.out.println("first thread in instanceMethod1");
            // critical section guarded first by
            // lock1 and then by lock2
          } finally {
            lock2.unlock();
          }
      } finally {
        lock1.unlock();
      }
  }

  public void instanceMethod2() {
    if (lock2.tryLock())
      try {
        if (lock1.tryLock())
          try {
            System.out.println("second thread in instanceMethod2");
            // critical section guarded first by
            // lock2 and then by lock1
          } finally {
            lock1.unlock();
          }
      } finally {
        lock2.unlock();
      }
  }

  public static void main(String[] args) {
    final PreventDeadlockDemo pdld = new PreventDeadlockDemo();
    Runnable runnableA = new Runnable() {
      @Override public void run() {
        while (true) {
          pdld.instanceMethod1();
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
          pdld.instanceMethod2();
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
