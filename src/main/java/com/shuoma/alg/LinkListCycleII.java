package com.shuoma.alg;

import com.shuoma.ds.linkedlist.ListNode;

public class LinkListCycleII {
  public ListNode detectCycle(ListNode head) {
    ListNode ptr1 = head, ptr2 = head;
    boolean circle;
    while (true) {
      for (int i = 0; i < 2; i++) {
        if (ptr1 == null) return null;
        ptr1 = ptr1.next;
      }
      ptr2 = ptr2.next;
      if (ptr1 == ptr2) {
        circle = true;
        break;
      }
    }

    if (!circle) { return null; }

    ptr2 = head;
    while (ptr2 != ptr1) {
      ptr2 = ptr2.next;
      ptr1 = ptr1.next;
    }
    return ptr1;
  }
}
