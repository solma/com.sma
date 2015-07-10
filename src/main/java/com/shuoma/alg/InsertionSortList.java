package com.shuoma.alg;

import static com.shuoma.annotation.Tag.DataStructure.LinkedList;
import static com.shuoma.annotation.Tag.Reference.LeetCode;

import com.shuoma.annotation.Tag;
import com.shuoma.ds.linkedlist.ListNode;

@Tag(dss = LinkedList, references = LeetCode)
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
