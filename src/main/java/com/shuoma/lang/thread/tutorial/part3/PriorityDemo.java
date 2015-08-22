package com.shuoma.lang.thread.tutorial.part3;
// PriorityDemo.java

public class PriorityDemo
{
   public static void main (String [] args)
   {
      BlockingThread bt = new BlockingThread ();
      bt.setPriority (Thread.NORM_PRIORITY + 1);

      CalculatingThread ct = new CalculatingThread ();

      bt.start ();
      ct.start ();

      try
      {
          Thread.sleep (10000);
      }
      catch (InterruptedException e)
      {
      }

      bt.setFinished (true);
      ct.setFinished (true);
   }
}

class BlockingThread extends Thread
{
   private boolean finished = false;

   public void run ()
   {
      while (!finished)
      {
         try
         {
            int i;

            do
            {
               i = System.in.read ();
               System.out.print (i + " ");
            }
            while (i != '\n');

            System.out.print ('\n');
         }
         catch (java.io.IOException e)
         {
         }
      }
   }

   public void setFinished (boolean f)
   {
      finished = f;
   }
}

class CalculatingThread extends Thread
{
   private boolean finished = false;

   public void run ()
   {
      int sum = 0;

      while (!finished)
         sum++;
   }

   public void setFinished (boolean f)
   {
      finished = f;
   }
}
