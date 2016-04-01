package com.sma.alg;

import static com.sma.annotation.Tag.Algorithm.Concurrent;
import static com.sma.annotation.Tag.DataStructure.ThreadT;
import static com.sma.annotation.Tag.Reference.Interview;

import com.sma.annotation.Tag;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

/**
 Write code to simulate a H2O from 2 H atom and 1 O atom in multi-thread environment.
 Having two method H() and O(), If two thread is calling H() and one is calling O(),
 generate a H2O(Sleep 1 second), other method are blocking.
 */
@Tag(algs = Concurrent, dss = {ThreadT}, references = Interview)
public class H2oSimulator {
  public static void main(String[] args) throws InterruptedException {
    ExecutorService es = Executors.newCachedThreadPool();
    H2O generator = new H2O(); // shared by multiple threads
    for (int i = 0; i < 3; i++) {
      es.execute(new HTask(i, generator));
      es.execute(new OTask(i, generator));
    }
    TimeUnit.SECONDS.sleep(10);
    System.exit(0);
  }
}

class H2O {
  Semaphore h = new Semaphore(2);
  Semaphore o = new Semaphore(1);
  List<Integer> hIds = new LinkedList<>();
  int oId = -1;

  public void H(int id) {
    try {
      h.acquire();
      hIds.add(id);
      h2o();
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }

  public void O(int id) {
    try {
      o.acquire();
      oId = id;
      h2o();
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }

  void h2o() {
    if (hIds.size() != 2 || oId == -1) return;
    System.out.println("generating a h2o from H(" + hIds + "), O(" + oId + ")");
    try {
      TimeUnit.SECONDS.sleep(1);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
    hIds.clear();
    h.release(2);
    oId = -1;
    o.release();
  }
}

class HTask implements Runnable {
  int id;
  H2O generator;

  public HTask(int id, H2O h2o) {
    this.id = id;
    this.generator = h2o;
  }

  @Override
  public void run() {
    while (true) {
      generator.H(id);
      try {
        Thread.sleep(2);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }
  }
}

class OTask implements Runnable {
  int id;
  H2O generator;

  public OTask(int id, H2O h2o) {
    this.id = id;
    this.generator = h2o;
  }

  @Override
  public void run() {
    while (true) {
      generator.O(id);
      try {
        Thread.sleep(2);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }
  }
}
