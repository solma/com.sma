package com.shuoma.lang.concurrent.tutorial.part2.exercises.answers;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ConcurrentQueue<E> {
  private final E[] items;
  private final int capacity;

  private volatile int head, tail, numItems;

  private final Lock lock = new ReentrantLock();

  private final Condition lessThanFull = lock.newCondition();
  private final Condition moreThanEmpty = lock.newCondition();

  @SuppressWarnings("unchecked") public ConcurrentQueue(int capacity) {
    this.capacity = capacity;
    items = (E[]) new Object[capacity];
    head = 0;
    tail = 0;
    numItems = 0;
  }

  public void add(E item) throws InterruptedException {
    lock.lock();
    try {
      while (numItems == capacity) // full
      {
        System.out.println("waiting on lessThanFull condition");
        lessThanFull.await();
      }

      items[tail] = item;
      tail = (tail + 1) % capacity;
      numItems++;
      System.out.println("signalling moreThanEmpty");
      moreThanEmpty.signal();
    } finally {
      lock.unlock();
    }
  }

  public E remove() throws InterruptedException {
    lock.lock();
    try {
      while (numItems == 0) // empty
      {
        System.out.println("waiting on moreThanEmpty condition");
        moreThanEmpty.await();
      }

      E result = items[head];
      head = (head + 1) % capacity;
      numItems--;
      System.out.println("signalling lessThanFull");
      lessThanFull.signal();
      return result;
    } finally {
      lock.unlock();
    }
  }
}
