package com.shuoma.alg;

import static com.shuoma.annotation.Tag.DataStructure.LinkedListT;
import static com.shuoma.annotation.Tag.Reference.LeetCode;
import static com.shuoma.annotation.Tag.Trick.TortoiseAndHare;
import static com.shuoma.annotation.Tag.Trick.TwoOrMorePointers;

import com.shuoma.annotation.Tag;
import com.shuoma.ds.linkedlist.ListNode;

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
