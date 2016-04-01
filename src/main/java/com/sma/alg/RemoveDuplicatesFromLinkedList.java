package com.sma.alg;

import static com.sma.annotation.Tag.DataStructure.LinkedListT;
import static com.sma.annotation.Tag.Reference.CrackingTheCodeInterview;

import com.sma.annotation.Tag;
import com.sma.ds.linkedlist.ListNode;

// delete duplicates in the linked list without extra space
@Tag(dss = LinkedListT, references = CrackingTheCodeInterview)
public class RemoveDuplicatesFromLinkedList {
  public static void main(String[] args) {
    ListNode n = ListNode.buildList(new int[] {2, 0, 2, 1, 1, 1});
    removeDuplicates(n);
    printList(n);
  }

  static void printList(ListNode head) {
    while (head != null) {
      System.out.println(head.val);
      head = head.next;
    }
  }

  static void removeDuplicates(ListNode head) {
    ListNode cur = head;
    while (cur != null) {
      ListNode prev = cur, comp = cur.next;
      while (comp != null) {
        if (cur.val == comp.val) {
          prev.next = comp.next; // delete duplicate
        }
        else {
          prev = comp;
        }
        comp = comp.next;// advance cur
      }
      cur = cur.next;
    }
  }
}
