package com.shuoma.ds.misc;

import junit.framework.TestCase;
import org.junit.Test;

import java.util.Arrays;

public class CircularArrayStackTest extends TestCase {

  @Test
  public void test() {
    CircularArrayStack stack = new CircularArrayStack(3);
    stack.push(new Object[] {1, 2});
    System.out.println(Arrays.asList(stack.poll(2)));
    stack.push(new Object[] {1, 2, 3});
    System.out.println(Arrays.asList(stack.poll(2)));
    System.out.println(Arrays.asList(stack.poll(1)));
    System.out.println(Arrays.asList(stack.poll(1)));
  }
}
