package com.sma.alg;

import static com.sma.annotation.Tag.Algorithm.Concurrent;
import static com.sma.annotation.Tag.DataStructure.ThreadT;
import static com.sma.annotation.Tag.Reference.CrackingTheCodeInterview;
import static com.sma.annotation.Tag.Reference.Interview;

import com.sma.annotation.Tag;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

/**
 * Implement a class with three methods, where 2nd method can only be called after 1st method
 * get called and 3rd method can only be called after 2nd method get called.
 */
@Tag(algs = Concurrent, dss = {ThreadT}, references = {CrackingTheCodeInterview, Interview})
public class ThreeThread {

  public static void main(String[] args) throws InterruptedException {
    Foo foo = new Foo();
    ExecutorService executorService = Executors.newCachedThreadPool();
    for (int times = 0; times < 2; times++) {
      for (int i = 0; i < 3; i++) {
        executorService.submit(new Task(foo, i));
      }
    }
  }
}

class Foo {
  Semaphore second;
  Semaphore third;

  public Foo() throws InterruptedException {
    second = new Semaphore(1);
    third = new Semaphore(1);
    second.acquire();
    third.acquire();
  }

  public void first() throws InterruptedException {
    Thread.sleep(3000);
    System.out.println("first is called");
    second.release();
  }

  public void second() throws InterruptedException {
    second.acquire();
    Thread.sleep(2000);
    System.out.println("second is called");
    third.release();
  }

  public void third() throws InterruptedException {
    third.acquire();
    Thread.sleep(1000);
    System.out.println("third is called");
  }
}

class Task implements Runnable {
  private Foo foo;
  private int index;

  public Task(Foo foo, int index) {
    this.foo = foo;
    this.index = index;
  }

  @Override public void run() {
    System.out.println("Task " + index + " is started.");
    try {
      switch (index) {
        case 0:
          System.out.println("call first");
          foo.first();
          break;
        case 1:
          System.out.println("call second");
          foo.second();
          break;
        case 2:
          System.out.println("call third");
          foo.third();
      }
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }
}
