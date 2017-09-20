package com.sma.lang.concurrent.tutorial.part2;

public class EmulatedCAS {
  private int value;

  public synchronized int getValue() {
    return value;
  }

  public synchronized int compareAndSwap(int expectedValue, int newValue) {
    int readValue = value;
    if (readValue == expectedValue) {
      value = newValue;
    }
    return readValue;
  }
}
