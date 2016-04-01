package com.sma.ds.linkedlist;

import static com.sma.ds.linkedlist.DoubleLinkedList.buildList;

import org.junit.Test;

public class DoubleLinkedListTest {
  @Test
  public void test() throws Exception {
    int[] A = new int[] {1, 2, 3, 4, 5, 6, 7};
    DoubleLinkedList list = buildList(A);
    System.out.println(list);
    list.remove(4);
    System.out.println(list);
    list.append(new ListNode(8));
    System.out.println(list);
    list.prepend(new ListNode(0));
    System.out.println(list);
  }
}
