package com.sma.lang.thread.tutorial.part4;// Clock1.java

// Type Ctrl+C (or equivalent keystroke combination on non-Windows platform)
// to terminate.

import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

public class Clock1 {
  public static void main(String[] args) {
    Timer t = new Timer();

    t.schedule(new TimerTask() {
                 public void run() {
                   System.out.println(new Date().toString());
                 }
               }, 0, 1000);
  }
}
