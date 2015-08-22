package com.shuoma.lang.thread.tutorial.part3;

// SchedDemo.java

public class SchedDemo
{
   public static void main (String [] args)
   {
      new CalcThread ("CalcThread A").start ();
      new CalcThread ("CalcThread B").start ();
   }
}

class CalcThread extends Thread
{
   CalcThread (String name)
   {
      // Pass name to Thread layer.

      super (name);
   }

   double calcPI ()
   {
      boolean negative = true;
      double pi = 0.0;

      for (int i = 3; i < 100000; i += 2)
      {
           if (negative)
               pi -= (1.0 / i);
           else
               pi += (1.0 / i);
           negative = !negative;
      }
      pi += 1.0;
      pi *= 4.0;

      return pi;
   }

   public void run ()
   {
      for (int i = 0; i < 5; i++)
         System.out.println (getName () + ": " + calcPI ());
   }
}
