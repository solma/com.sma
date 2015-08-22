package com.shuoma.lang.concurrent.tutorial.part2;

import java.util.Arrays;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveAction;

public class ForkJoinDemo
{
   static class SortTask extends RecursiveAction
   {
      private final long[] array;
      private final int lo, hi;

      SortTask(long[] array, int lo, int hi)
      {
         this.array = array;
         this.lo = lo;
         this.hi = hi;
      }

      SortTask(long[] array)
      {
         this(array, 0, array.length);
      }

      private final static int THRESHOLD = 1000;

      @Override
      protected void compute()
      {
         if (hi-lo < THRESHOLD)
            sortSequentially(lo, hi);
         else
         {
            int mid = (lo+hi) >>> 1;
            invokeAll(new SortTask(array, lo, mid),
                      new SortTask(array, mid, hi));
            merge(lo, mid, hi);
         }
      }

      private void sortSequentially(int lo, int hi)
      {
         Arrays.sort(array, lo, hi);
      }

      private void merge(int lo, int mid, int hi)
      {
         long[] buf = Arrays.copyOfRange(array, lo, mid);
         for (int i = 0, j = lo, k = mid; i < buf.length; j++)
            array[j] = (k == hi || buf[i] < array[k]) ? buf[i++] : array[k++];
      }
   }

   public static void main(String[] args)
   {
      long[] array = new long[300000];
      for (int i = 0; i < array.length; i++)
         array[i] = (long) (Math.random()*10000000);
      long[] array2 = new long[array.length];
      System.arraycopy(array, 0, array2, 0, array.length);

      long startTime = System.currentTimeMillis();
      Arrays.sort(array, 0, array.length-1);
      System.out.printf("sequential sort completed in %d millis%n",
                        System.currentTimeMillis()-startTime);
      for (int i = 0; i < array.length; i++)
         System.out.println(array[i]);

      System.out.println();

      ForkJoinPool pool = new ForkJoinPool();
      startTime = System.currentTimeMillis();
      pool.invoke(new SortTask(array2));
      System.out.printf("parallel sort completed in %d millis%n",
                        System.currentTimeMillis()-startTime);
      for (int i = 0; i < array2.length; i++)
         System.out.println(array2[i]);
   }
}
