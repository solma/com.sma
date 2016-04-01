package com.sma.lang.concurrent.tutorial.part2;

import java.util.concurrent.atomic.AtomicInteger;

public class CounterV3
{
   private AtomicInteger value = new AtomicInteger();

   public int getValue()
   {
      return value.get();
   }

   public int increment()
   {
      int readValue = value.get();
      while (!value.compareAndSet(readValue, readValue+1))
         readValue = value.get();
      return readValue+1;
   }
}
