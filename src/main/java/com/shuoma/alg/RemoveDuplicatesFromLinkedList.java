package com.shuoma.alg;

import static com.shuoma.annotation.Tag.DataStructure.LinkedList;
import static com.shuoma.annotation.Tag.Reference.CrackingTheCodeInterview;

import com.shuoma.annotation.Tag;
import com.shuoma.ds.linkedlist.ListNode;

// delete duplicates in the linked list without extra space
@Tag(dss = LinkedList, references = CrackingTheCodeInterview)
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
