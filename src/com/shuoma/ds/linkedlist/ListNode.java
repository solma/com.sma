package com.shuoma.ds.linkedlist;

public class ListNode {
  int val;
  ListNode next;

  public ListNode(int x) {
    val = x;
    next = null;
  }


  public static ListNode buildList(int[] array) {
    ListNode pseudoHead = new ListNode(0);
    ListNode prev = pseudoHead;
    for (int i = 0; i < array.length; i++) {
      prev.next = new ListNode(array[i]);
      prev = prev.next;
    }
    return pseudoHead.next;
  }

  @Override
  public String toString() {
    ListNode cur = this;
    StringBuilder sb = new StringBuilder();
    while (cur != null) {
      sb.append(cur.val + "->");
      cur = cur.next;
    }
    sb.append("end");
    return sb.toString();
  }


}
