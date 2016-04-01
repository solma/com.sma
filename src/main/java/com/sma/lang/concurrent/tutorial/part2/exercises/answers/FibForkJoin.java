package com.sma.lang.concurrent.tutorial.part2.exercises.answers;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

public class FibForkJoin {
  public static void main(String[] args) {
    ForkJoinPool pool = new ForkJoinPool();
    Fibonacci fib = new Fibonacci(45);
    System.out.println(pool.invoke(fib));
  }
}

class Fibonacci extends RecursiveTask<Integer> {
  final int n;

  Fibonacci(int n) {
    this.n = n;
  }

  @Override public Integer compute() {
    if (n <= 1)
      return n;
    Fibonacci f1 = new Fibonacci(n - 1);
    f1.fork();
    Fibonacci f2 = new Fibonacci(n - 2);
    return f2.compute() + f1.join();
  }
}
