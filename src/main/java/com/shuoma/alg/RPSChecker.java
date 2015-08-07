package com.shuoma.alg;

import static com.shuoma.annotation.Tag.Algorithm.Concurrent;
import static com.shuoma.annotation.Tag.DataStructure.ThreadT;
import static com.shuoma.annotation.Tag.Reference.Interview;

import com.shuoma.annotation.Tag;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

/**
 Implement a RPSChecker, it control the request processed in 1 seconds should below a limitation. Have 2 methods:
    void setlimit(int num) to set the limitation number;
    bool process(int timestamp){} return true if under the limit, false if over the limit.
 The request handler should work in concurrency environment.
 */
@Tag(algs = Concurrent, dss = {ThreadT}, references = Interview)
public class RPSChecker {
  int limitation;
  AtomicInteger counter = new AtomicInteger(0);
  AtomicLong lastTimestamp = new AtomicLong(-1);

  public void setLimit(int limitation) {
    this.limitation = limitation;
  }

  public boolean process(long timestamp) {
    if (timestamp - lastTimestamp.get() >= 1000) {
      counter.set(0);
      lastTimestamp.set(timestamp);
      return true;
    }
    if (counter.incrementAndGet() <= limitation) {
      return true;
    }
    System.out.println("request rejected");
    return false;
  }

  public static void main(String[] args) throws InterruptedException {
    RPSChecker checker = new RPSChecker();
    checker.setLimit(7);
    ExecutorService executor = Executors.newCachedThreadPool();
    for(int i = 0; i < 5; i++){
      executor.execute(new RequestSender(checker, i));
    }
    TimeUnit.SECONDS.sleep(5);
    System.out.println("Adapt RPS to 15");
    checker.setLimit(15);
    TimeUnit.SECONDS.sleep(5);
    System.exit(0);
  }
}

class RequestSender implements Runnable {
  RPSChecker checker;
  int id;

  public RequestSender(RPSChecker checker, int id) {
    this.checker = checker;
    this.id = id;
  }

  @Override
  public void run() {
    while(true) {
      String msg = id  + " request " +
          (checker.process(System.currentTimeMillis()) ? "accepted" : "rejected");
      System.out.println(msg);
      try {
        Thread.sleep(400);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }
  }
}
