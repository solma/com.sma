package com.shuoma.alg;

import static com.shuoma.annotation.Tag.DataStructure.LinkedList;
import static com.shuoma.annotation.Tag.Difficulty.D2;
import static com.shuoma.annotation.Tag.Reference.LeetCode;

import com.shuoma.annotation.Tag;
import com.shuoma.ds.linkedlist.ListNode;

import java.util.ArrayList;

@Tag(dl = D2, dss = LinkedList, references = LeetCode)
public class MergeKSortedList {
  public ListNode mergeKLists(ArrayList<ListNode> lists) {
    // Start typing your Java solution below
    // DO NOT write main() function
    int ls = lists.size();
    if (ls == 0)
      return null;
    ListNode fl = lists.get(0);
    if (ls == 1)
      return fl;
    for (int i = 1; i < ls; i++) {
      fl = mergeTwoLists(lists.get(i), fl);
    }
    return fl;
  }

  public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
    // Start typing your Java solution below
    // DO NOT write main() function
    ListNode head = l2, l2Prev = null;
    while (l1 != null && l2 != null) {
      if (l1.val >= l2.val) {
        l2Prev = l2;
        l2 = l2.next;
      } else {
        if (l2Prev != null) {
          l2Prev.next = l1;
          l1 = l1.next;   //advance l1
          l2Prev.next.next = l2;
          l2Prev = l2Prev.next; //advance l2Prev
        } else {
          l2Prev = l1; //advance l2Prev
          head = l2Prev; //change head
          l1 = l1.next; //advance l1
          l2Prev.next = l2;
        }
      }
    }
    if (l2 == null && l1 != null) {
      if (l2Prev != null)
        l2Prev.next = l1; //copy rest of l1 to l2
      else
        head = l1;
    }
    return head;
  }
}
