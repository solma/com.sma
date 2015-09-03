package com.shuoma.alg;


import static com.shuoma.annotation.Tag.Algorithm.Concurrent;
import static com.shuoma.annotation.Tag.DataStructure.ThreadT;
import static com.shuoma.annotation.Tag.Reference.Interview;

import com.shuoma.annotation.Tag;

import java.util.Date;
import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.TimeUnit;


@Tag(algs = Concurrent, dss = {ThreadT}, references = Interview)
public class SleepingBarber {

  public static void main(String a[]) {
    Barber barber = new Barber();
    new Thread(new CustomerGenerator(barber)).start();
    new Thread(barber).start();
  }
}

class Barber implements Runnable {
  int chairCnt;
  Queue<Customer> customerQueue;

  public Barber() {
    chairCnt = 3;
    customerQueue = new LinkedList<>();
  }

  @Override
  public void run() {
    try {
      Thread.sleep(10000);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
    System.out.println("Barber started..");
    while (true) {
      serve();
    }
  }

  public void serve() {
    Customer customer;
    System.out.println("Barber waiting for lock.");
    synchronized (customerQueue) {
      while (customerQueue.size() == 0) {
        System.out.println("Barber is waiting for customer.");
        try {
          customerQueue.wait();
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
      }
      System.out.println("Barber found a customer in the queue.");
      customer = customerQueue.poll();
    }
    cutHair(customer);
  }

  private void cutHair(Customer customer) {
    long duration = 0;
    try {
      System.out.println("Cutting hair of Customer : " + customer.getName());
      duration = (long) (Math.random() * 10);
      TimeUnit.SECONDS.sleep(duration);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
    System.out.println(
        "Completed Cutting hair of Customer : " + customer.getName() + " in " + duration
            + " seconds.");
  }

  public void add(Customer customer) {
    System.out.println(
        "Customer : " + customer.getName() + " entering the shop at " + customer.getInTime());

    synchronized (customerQueue) {
      if (customerQueue.size() == chairCnt) {
        System.out.println("No chair available for customer " + customer.getName());
        System.out.println("Customer " + customer.getName() + "Exists...");
        return;
      }

      customerQueue.offer(customer);
      System.out.println("Customer : " + customer.getName() + " got the chair.");

      if (customerQueue.size() == 1) {
        customerQueue.notify();
      }
    }
  }
}

class Customer implements Runnable {
  String name;
  Date inTime;

  Barber barber;

  public Customer(Barber barber) {
    this.barber = barber;
  }

  public String getName() {
    return name;
  }

  public Date getInTime() {
    return inTime;
  }

  public void setName(String name) {
    this.name = name;
  }

  public void setInTime(Date inTime) {
    this.inTime = inTime;
  }

  @Override
  public void run() {
    goForHairCut();
  }

  private synchronized void goForHairCut() {
    barber.add(this);
  }
}


class CustomerGenerator implements Runnable {
  Barber barber;

  public CustomerGenerator(Barber barber) {
    this.barber = barber;
  }

  public void run() {
    while (true) {
      Customer customer = new Customer(barber);
      customer.setInTime(new Date());
      Thread customerThread = new Thread(customer);
      customer.setName("Customer Thread " + customerThread.getId());
      customerThread.start();

      try {
        TimeUnit.SECONDS.sleep((long) (Math.random() * 10));
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }
  }
}
