package com.sma.alg;

import static com.sma.annotation.Tag.DataStructure.LinkedListT;
import static com.sma.annotation.Tag.Reference.LeetCode;
import static com.sma.annotation.Tag.Trick.TwoOrMorePointers;

import com.sma.annotation.Tag;
import com.sma.ds.linkedlist.ListNode;

@Tag(dss = LinkedListT, references = LeetCode, tricks = TwoOrMorePointers)
public class ReorderList {

  public void reorderList(ListNode head) {
    if (head == null || head.next == null) { return; }

    // fast and slow pointers to locate the median node
    ListNode l1 = head, l2 = head;
    while (l2 != null && l2.next != null) {
      l1 = l1.next;
      l2 = l2.next.next;
    }

    l2 = l1.next;
    l1.next = null;
    l2 = reverse(l2);

    l1 = head;
    while (l1 != null && l2 != null) {
      ListNode tmp = l1.next;
      l1.next = l2;
      l1 = tmp;

      tmp = l2.next;
      l2.next = l1;
      l2 = tmp;
    }
  }

  ListNode reverse(ListNode head) {
    ListNode pre = null;
    ListNode cur = head;

    while (cur != null) {
      ListNode nxt = cur.next;
      cur.next = pre;
      pre = cur;
      cur = nxt;
    }
    return pre;
  }
}
