package com.shuoma.alg;

import static com.shuoma.annotation.Tag.DataStructure.LinkedList;
import static com.shuoma.annotation.Tag.Reference.LeetCode;

import com.shuoma.annotation.Tag;

@Tag(dss = LinkedList, references = LeetCode)
public class RemoveLinkedListElements {

  ListNode removeElements(ListNode head, int val) {
    ListNode prev = new ListNode(-1), cur = head;
    while (cur != null) {
      if (cur.val == val) {
        prev.next = cur.next;
        if (cur == head) {
          head = cur.next;
        }
      } else {
        prev = cur;
      }
      cur = cur.next;
    }
    return head;
  }
}
