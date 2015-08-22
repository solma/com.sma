package com.shuoma.lang.concurrent.tutorial.part2;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class RWLockDemo {
  final static int DELAY = 80;
  final static int NUMITER = 5;

  public static void main(String[] args) {
    final Names names = new Names();

    class NamedThread implements ThreadFactory {
      private String name;

      NamedThread(String name) {
        this.name = name;
      }

      @Override public Thread newThread(Runnable r) {
        return new Thread(r, name);
      }
    }

    ExecutorService writer;
    writer = Executors.newSingleThreadExecutor(new NamedThread("writer"));
    Runnable wrunnable = new Runnable() {
      @Override public void run() {
        for (int i = 0; i < NUMITER; i++) {
          names.add(Thread.currentThread().getName(), "A" + i);
          try {
            Thread.sleep(DELAY);
          } catch (InterruptedException ie) {
          }
        }
      }
    };
    writer.submit(wrunnable);

    ExecutorService reader1;
    reader1 = Executors.newSingleThreadExecutor(new NamedThread("reader1"));
    ExecutorService reader2;
    reader2 = Executors.newSingleThreadExecutor(new NamedThread("reader2"));
    Runnable rrunnable = new Runnable() {
      @Override public void run() {
        for (int i = 0; i < NUMITER; i++)
          names.dump(Thread.currentThread().getName());
      }
    };
    reader1.submit(rrunnable);
    reader2.submit(rrunnable);

    reader1.shutdown();
    reader2.shutdown();
    writer.shutdown();
  }
}


class Names {
  private final List<String> names;

  private final ReentrantReadWriteLock lock;
  private final Lock readLock, writeLock;

  Names() {
    names = new ArrayList<>();
    lock = new ReentrantReadWriteLock();
    readLock = lock.readLock();
    writeLock = lock.writeLock();
  }

  void add(String threadName, String name) {
    writeLock.lock();
    try {
      System.out.printf("%s: num waiting threads = %d%n", threadName, lock.getQueueLength());
      names.add(name);
    } finally {
      writeLock.unlock();
    }
  }

  void dump(String threadName) {
    readLock.lock();
    try {
      System.out.printf("%s: num waiting threads = %d%n", threadName, lock.getQueueLength());
      Iterator<String> iter = names.iterator();
      while (iter.hasNext()) {
        System.out.printf("%s: %s%n", threadName, iter.next());
        try {
          Thread.sleep((int) (Math.random() * 100));
        } catch (InterruptedException ie) {
        }
      }
    } finally {
      readLock.unlock();
    }
  }
}
