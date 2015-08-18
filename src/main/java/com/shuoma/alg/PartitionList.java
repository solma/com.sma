package com.shuoma.alg;

import com.shuoma.ds.linkedlist.ListNode;

public class PartitionList {
  public static void main(String[] args) {
    new PartitionList().main();
  }

  public void main() {
    int[] vals = new int[] {1, 4, 3, 2, 5, 2};
    ListNode n = new ListNode(vals[0]);
    ListNode prev = n;
    for (int i = 1; i < vals.length; i++) {
      ListNode c = new ListNode(vals[i]);
      prev.next = c;
      prev = c;
    }

    n = partition(n, 3);
    while (n != null) {
      System.out.println(n.val);
      n = n.next;
    }
  }

  // create two lists with pseudo head
  // keep smallHead, largeHead, and small, large
  // large.next = null and small.next = largeHead.next;
  public ListNode partition(ListNode head, int x) {
    ListNode smallHead = new ListNode(0);
    ListNode largeHead = new ListNode(0);
    ListNode small = smallHead;
    ListNode large = largeHead;
    while (head != null) {
      if (head.val < x) {
        small.next = head;
        small = small.next;
      } else {
        large.next = head;
        large = large.next;
      }
      head = head.next;
    }
    large.next = null;
    small.next = largeHead.next;
    return smallHead.next;
  }
}
