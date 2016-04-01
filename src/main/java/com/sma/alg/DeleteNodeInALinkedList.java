package com.sma.alg;

import static com.sma.annotation.Tag.DataStructure.LinkedListT;
import static com.sma.annotation.Tag.Reference.LeetCode;

import com.sma.annotation.Tag;
import com.sma.ds.linkedlist.ListNode;

@Tag(dss = LinkedListT, references = LeetCode)
public class DeleteNodeInALinkedList {
  public void deleteNode(ListNode node) {
    if (node == null || node.next == null) return;
    ListNode next = node.next;
    node.val = next.val;
    node.next = next.next;
  }
}
