package com.shuoma.lang.concurrent.tutorial.part2.exercises.answers;

public class UseFIFOMutex {
  public static void main(String[] args) {
    final FIFOMutex mutex = new FIFOMutex();

    Runnable runnable;
    runnable = new Runnable() {
      @Override public void run() {
        String name = Thread.currentThread().getName();
        for (int i = 0; i < 100; i++) {
          mutex.lock();
          System.out.printf("Thread %s has aquired the lock%n", name);
          try {
            Thread.sleep(30);
          } catch (InterruptedException ie) {
            assert false;
          }
          System.out.printf("Thread %s is releasing the lock%n", name);
          mutex.unlock();
        }
      }
    };
    Thread thdA = new Thread(runnable);
    Thread thdB = new Thread(runnable);
    Thread thdC = new Thread(runnable);
    Thread thdD = new Thread(runnable);
    Thread thdE = new Thread(runnable);
    thdA.start();
    thdB.start();
    thdC.start();
    thdD.start();
    thdE.start();
  }
}
