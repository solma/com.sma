package com.sma.alg;

import static com.sma.util.ArrayUtil.intArrayToIntegerList;

import junit.framework.TestCase;
import org.junit.Test;

import java.util.List;

public class PeekingIteratorTest extends TestCase {

  @Test
  public void test() {
    List<Integer> li = intArrayToIntegerList(new int[]{1, 2, 3});
    PeekingIterator pi = new PeekingIterator(li.iterator());

    assertEquals(1, (int) pi.peek());
    assertEquals(1, (int) pi.next());
    assertEquals(2, (int) pi.next());
    assertEquals(3, (int) pi.peek());
    assertEquals(true, pi.hasNext());
    assertEquals(3, (int) pi.next());
    assertEquals(false, pi.hasNext());
  }
}
