package com.sma.ds.linkedlist;

import static com.sma.ds.linkedlist.ListNode.buildList;
import static com.sma.ds.linkedlist.ListNode.interweave;
import static com.sma.ds.linkedlist.ListNode.reverseInKGroupR;
import static com.sma.ds.linkedlist.ListNode.reverseR;
import static org.junit.Assert.assertTrue;

import com.sma.util.ArrayUtil;
import org.junit.Test;

public class ListNodeTest {

  @Test public void testReverse() throws Exception {
    int[][] lists = new int[][] {
        {1, 2, 3, 4, 5, 6, 7, 8, 9},
        {1}
    };
    for (int[] li : lists) {
      ListNode head = buildList(li);
      ListNode reverse = buildList(ArrayUtil.reverse(li));
      assertTrue(reverse.equals(reverseR(head)));
    }
  }

  @Test public void testReverseInKGroupR() throws Exception {
    int[][] lists = new int[][] {
        {1, 2, 3, 4, 5, 6, 7, 8, 9},
        {1}
    };
    int[] Ks = {1, 2, 3};
    for (int[] li : lists) {
      for (int k : Ks) {
        ListNode head = buildList(li);
        ListNode reverseInKGroup = reverseInKGroupR(head, k);
        assertTrue(buildList(ArrayUtil.reverse(li, k)).equals(reverseInKGroup));
      }
    }
  }

  @Test
  public void testInterweave() throws Exception {
    int[][] lists = new int[][] {
        {5, 1, 3, 7, 9},
        {2, 6, 8},
        {5, 2, 1, 6, 3, 8, 7, 9}
    };
    assertTrue(buildList(lists[2]).equals(
        interweave(buildList(lists[0]), buildList(lists[1]))
    ));
  }
}
