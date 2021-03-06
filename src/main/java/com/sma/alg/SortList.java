package com.sma.alg;

import static com.sma.annotation.Tag.DataStructure.LinkedListT;
import static com.sma.annotation.Tag.Reference.LeetCode;

import com.sma.annotation.Tag;
import com.sma.ds.linkedlist.ListNode;

@Tag(dss = LinkedListT, references = LeetCode) public class SortList {
  public ListNode sortList(ListNode head) {
    //count # of nodeMap
    ListNode cur = head;
    int cnt = 0;
    while (cur != null) {
      cur = cur.next;
      cnt++;
    }
    if (cnt == 0 || cnt == 1) { return head; }

    //split
    int i = 0, middle = cnt / 2;
    ListNode half = head, prev = null;
    while (i < middle) {
      i++;
      prev = half;
      half = half.next;
    }
    if (prev != null) { prev.next = null; }

    //recursion
    ListNode sorted = sortList(head);
    ListNode sortedSecondHalf = sortList(half);

    //merge
    cur = sorted;
    prev = null;
    while (cur != null && sortedSecondHalf != null) {
      if (cur.val >= sortedSecondHalf.val) {
        if (prev == null) { sorted = sortedSecondHalf; }
        else { prev.next = sortedSecondHalf; }
        prev = sortedSecondHalf;
        sortedSecondHalf = sortedSecondHalf.next;
        prev.next = cur;
      } else {
        prev = cur;
        cur = cur.next;
      }
    }
    if (sortedSecondHalf != null && prev != null) {
      prev.next = sortedSecondHalf;
    }

    return sorted;
  }
}
