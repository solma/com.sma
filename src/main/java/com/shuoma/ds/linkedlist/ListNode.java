package com.shuoma.ds.linkedlist;

import static com.shuoma.annotation.Tag.Reference.LeetCode;

import com.shuoma.annotation.Tag;

@Tag(references = LeetCode)
public class ListNode {
  public int val;
  public ListNode next, prev;

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
