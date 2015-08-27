package com.shuoma.alg;

import static com.shuoma.annotation.Tag.Algorithm.Concurrent;
import static com.shuoma.annotation.Tag.DataStructure.ThreadT;
import static com.shuoma.annotation.Tag.Reference.Interview;

import com.shuoma.annotation.Tag;

import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

/**
 * Two thread one prints even numbers and the other prints odd numbers.
 * Two outputs are synchronized to follow the natural order.
 */
@Tag(algs = Concurrent, dss = {ThreadT}, references = Interview)
public class OddEvenPrinter {

  public static void main(String[] args) throws InterruptedException {
    Context context = new Context();
    new Thread(new OddPrinter(context)).start();
    new Thread(new EvenPrinter(context)).start();

    TimeUnit.SECONDS.sleep(60);
  }

  static class Context {
    Semaphore evenPrinted = new Semaphore(1);
    Semaphore oddPrinted = new Semaphore(1);

    public Context() {
      try {
        oddPrinted.acquire();
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }
  }

  static class OddPrinter implements Runnable {
    Context context;
    int cur = 1;

    public OddPrinter(Context context) {
      this.context = context;
    }

    @Override public void run() {
      while (true) {
        try {
          context.evenPrinted.acquire();
          System.out.println(cur);
          cur += 2;
          TimeUnit.SECONDS.sleep(1);
          context.oddPrinted.release();
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
      }
    }
  }

  static class EvenPrinter implements Runnable {
    Context context;
    int cur = 2;

    public EvenPrinter(Context context) {
      this.context = context;
    }

    @Override public void run() {
      while (true) {
        try {
          context.oddPrinted.acquire();
          System.out.println(cur);
          cur += 2;
          TimeUnit.SECONDS.sleep(1);
          context.evenPrinted.release();
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
      }
    }
  }
}
