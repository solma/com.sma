package com.shuoma.lang.thread.tutorial.part3;
// ProdCons1.java


public class ProdCons1 {
  public static void main(String[] args) {
    Shared s = new Shared();
    new Producer(s).start();
    new Consumer(s).start();
  }

  static class Shared {
    private char c = '\u0000';

    void setSharedChar(char c) {
      this.c = c;
    }

    char getSharedChar() {
      return c;
    }
  }


  static class Producer extends Thread {
    private Shared s;

    Producer(Shared s) {
      this.s = s;
    }

    public void run() {
      for (char ch = 'A'; ch <= 'Z'; ch++) {
        try {
          Thread.sleep((int) (Math.random() * 4000));
        } catch (InterruptedException e) {}

        s.setSharedChar(ch);
        System.out.println(ch + " produced by producer.");
      }
    }
  }


  static class Consumer extends Thread {
    private Shared s;

    Consumer(Shared s) {
      this.s = s;
    }

    public void run() {
      char ch;

      do {
        try {
          Thread.sleep((int) (Math.random() * 4000));
        } catch (InterruptedException e) {}

        ch = s.getSharedChar();
        System.out.println(ch + " consumed by consumer.");
      } while (ch != 'Z');
    }
  }
}




