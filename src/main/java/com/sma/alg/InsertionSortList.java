package com.sma.alg;

import static com.sma.annotation.Tag.DataStructure.LinkedListT;
import static com.sma.annotation.Tag.Reference.LeetCode;

import com.sma.annotation.Tag;
import com.sma.ds.linkedlist.ListNode;

@Tag(dss = LinkedListT, references = LeetCode)
public class InsertionSortList {
  public ListNode insertionSortList(ListNode head) {
    ListNode cur = head;
    ListNode sorted = null;
    while (cur != null) {
      ListNode cp = new ListNode(cur.val);
      if (sorted == null) {
        sorted = cp;
      } else {
        ListNode sortedPtr = sorted, prev = null;
        if (cp.val <= sortedPtr.val) {
          cp.next = sortedPtr;
          sorted = cp;
        } else {
          while (sortedPtr != null && sortedPtr.val < cp.val) {
            prev = sortedPtr;
            sortedPtr = sortedPtr.next;
          }
          prev.next = cp;
          cp.next = sortedPtr;
        }
      }
      cur = cur.next;
    }
    return sorted;
  }
}
