package com.shuoma.ds.misc;

import junit.framework.TestCase;
import org.junit.Test;

import java.util.Arrays;

public class FixedSizeStackTest extends TestCase {

  @Test
  public void test() {
    FixedSizeStack stack = new FixedSizeStack(3);
    stack.push(new Object[] {1, 2});
    System.out.println(Arrays.asList(stack.poll(2)));
    stack.push(new Object[] {1, 2, 3});
    System.out.println(Arrays.asList(stack.poll(2)));
    stack.push(new Object[] {5, 6, 7, 8, 9});
    System.out.println(Arrays.asList(stack.poll(1)));
    System.out.println(Arrays.asList(stack.poll(1)));
  }
}
