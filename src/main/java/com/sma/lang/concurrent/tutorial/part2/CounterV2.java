package com.sma.lang.concurrent.tutorial.part2;

public class CounterV2 {
  private EmulatedCAS value = new EmulatedCAS();

  public int getValue() {
    return value.getValue();
  }

  public int increment() {
    int readValue = value.getValue();
    while (value.compareAndSwap(readValue, readValue + 1) != readValue)
      readValue = value.getValue();
    return readValue + 1;
  }
}
