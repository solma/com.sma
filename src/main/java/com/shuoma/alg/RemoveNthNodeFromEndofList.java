package com.shuoma.alg;


import static com.shuoma.annotation.Tag.DataStructure.LinkedList;
import static com.shuoma.annotation.Tag.Reference.LeetCode;

import com.shuoma.annotation.Tag;

@Tag(dss = LinkedList, references = LeetCode)
public class RemoveNthNodeFromEndofList {
  //second pass
  public ListNode removeNthFromEnd(ListNode head, int n) {

    ListNode pt1 = head;
    for (int i = 0; i < n; i++) {
      pt1 = pt1.next;
    }

    ListNode pt2 = head, prev = null;
    while (pt1 != null) {
      pt1 = pt1.next;

      prev = pt2;
      pt2 = pt2.next;
    }
    if (prev != null)
      prev.next = pt2.next;
    else
      head = head.next;
    return head;
  }

  // first pass
  public ListNode removeNthFromEndFirstPass(ListNode head, int n) {
    ListNode probe = head;

    int step = n;
    while (step > 0 && probe != null) {
      probe = probe.next;
      step--;
    }

    ListNode prev = null, cur = head;
    if (probe != null) {
      while (probe != null) {
        probe = probe.next;
        prev = cur;
        cur = cur.next;
      }
      if (prev != null && cur != null)
        prev.next = cur.next;
      return head;
    } else
      return head.next;
  }
}
