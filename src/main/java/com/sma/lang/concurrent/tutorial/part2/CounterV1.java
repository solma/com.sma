package com.sma.lang.concurrent.tutorial.part2;

public class CounterV1
{
   private int value;

   public synchronized int getValue()
   {
      return value;
   }

   public synchronized int increment()
   {
      return ++value;
   }
}
