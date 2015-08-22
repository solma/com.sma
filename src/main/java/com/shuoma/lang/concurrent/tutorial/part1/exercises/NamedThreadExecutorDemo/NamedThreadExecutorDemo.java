package com.shuoma.lang.concurrent.tutorial.part1.exercises.NamedThreadExecutorDemo;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class NamedThreadExecutorDemo
{
   public static void main(String[] args)
   {
      Runnable r = new Runnable()
                   {
                      @Override
                      public void run()
                      {
                         String name = Thread.currentThread().getName();
                         for (int i = 0; i < 10; i++)
                            System.out.printf("%s: %d%n", name, i);
                      }
                   };
      ExecutorService executor = Executors.newSingleThreadExecutor();
      executor.execute(r);
      executor.shutdown();
   }
}
