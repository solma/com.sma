package com.shuoma.alg;

import static com.shuoma.annotation.Tag.DataStructure.LinkedListT;
import static com.shuoma.annotation.Tag.Reference.LeetCode;

import com.shuoma.annotation.Tag;
import com.shuoma.ds.linkedlist.ListNode;

@Tag(dss = LinkedListT, references = LeetCode)
public class DeleteNodeInALinkedList {
  public void deleteNode(ListNode node) {
    if (node == null || node.next == null) return;
    ListNode next = node.next;
    node.val = next.val;
    node.next = next.next;
  }
}
