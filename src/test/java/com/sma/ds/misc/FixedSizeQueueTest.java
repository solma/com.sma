package com.sma.ds.misc;

import junit.framework.TestCase;
import org.junit.Test;

import java.util.Arrays;

public class FixedSizeQueueTest extends TestCase {

  @Test
  public void test() {
    FixedSizeQueue queue = new FixedSizeQueue(3);
    queue.enqueue(new Object[] {1, 2});
    System.out.println(Arrays.asList(queue.dequeue(2)));
    queue.enqueue(new Object[] {1, 2, 3});
    System.out.println(Arrays.asList(queue.dequeue(2)));
    System.out.println(Arrays.asList(queue.dequeue(1)));
    System.out.println(Arrays.asList(queue.dequeue(1)));
  }
}
