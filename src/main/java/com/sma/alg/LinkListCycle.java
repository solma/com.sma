package com.sma.alg;

import static com.sma.annotation.Tag.DataStructure.LinkedListT;
import static com.sma.annotation.Tag.Reference.LeetCode;
import static com.sma.annotation.Tag.Trick.TortoiseAndHare;
import static com.sma.annotation.Tag.Trick.TwoOrMorePointers;

import com.sma.annotation.Tag;
import com.sma.ds.linkedlist.ListNode;

@Tag(dss = LinkedListT, references = LeetCode, tricks = {TwoOrMorePointers, TortoiseAndHare})
public class LinkListCycle {
  public boolean hasCycle(ListNode head) {
    ListNode ptr1 = head, ptr2 = head;

    while (true) {
      for (int i = 0; i < 2; i++) {
        if (ptr2 == null) { return false; }
        ptr2 = ptr2.next;
      }
      ptr1 = ptr1.next;
      if (ptr2 == ptr1) return true;
    }
  }
}
