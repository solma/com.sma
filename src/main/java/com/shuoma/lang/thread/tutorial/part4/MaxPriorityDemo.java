package com.shuoma.lang.thread.tutorial.part4;// MaxPriorityDemo.java

public class MaxPriorityDemo {
  public static void main(String[] args) {
    ThreadGroup tg = new ThreadGroup("A");
    System.out.println("tg maximum priority = " + tg.getMaxPriority());

    Thread t1 = new Thread(tg, "X");
    System.out.println("t1 priority = " + t1.getPriority());

    t1.setPriority(Thread.NORM_PRIORITY + 1);
    System.out.println("t1 priority after setPriority() = " + t1.getPriority());

    tg.setMaxPriority(Thread.NORM_PRIORITY - 1);
    System.out.println("tg maximum priority after setMaxPriority() = " + tg.getMaxPriority());

    System.out.println("t1 priority after setMaxPriority() = " + t1.getPriority());

    Thread t2 = new Thread(tg, "Y");
    System.out.println("t2 priority = " + t2.getPriority());

    t2.setPriority(Thread.NORM_PRIORITY);
    System.out.println("t2 priority after setPriority() = " + t2.getPriority());
  }
}
