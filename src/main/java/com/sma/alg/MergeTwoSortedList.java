package com.sma.alg;

import static com.sma.annotation.Tag.DataStructure.LinkedListT;
import static com.sma.annotation.Tag.Reference.LeetCode;
import static com.sma.annotation.Tag.Trick.PseudoHead;

import com.sma.annotation.Tag;
import com.sma.ds.linkedlist.ListNode;

@Tag(dss = LinkedListT, references = LeetCode, tricks = PseudoHead) public class MergeTwoSortedList {
  public static void main(String[] args) {
    new MergeTwoSortedList().main();
  }

  public void main() {
    System.out.println(mergeTwoLists(ListNode.buildList(new int[] {2, 6, 7, 9}),
        ListNode.buildList(new int[] {1, 3, 8})));
  }

  public static ListNode mergeTwoLists(ListNode l1, ListNode l2) {
    ListNode p1 = l1, p2 = l2;

    ListNode pseudoHead = new ListNode(0);
    ListNode p = pseudoHead;

    while (p1 != null && p2 != null) {
      if (p1.val <= p2.val) {
        p.next = p1;
        p1 = p1.next;
      } else {
        p.next = p2;
        p2 = p2.next;
      }

      p = p.next;
    }

    if (p1 != null) { p.next = p1; }
    if (p2 != null) { p.next = p2; }

    return pseudoHead.next;
  }
}
