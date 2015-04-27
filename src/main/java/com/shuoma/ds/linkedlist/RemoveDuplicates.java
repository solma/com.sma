package com.shuoma.ds.linkedlist;


import static com.shuoma.annotation.Tag.DataStructure.LinkedList;
import static com.shuoma.annotation.Tag.Source.CrackingTheCodeInterview;

import com.shuoma.annotation.Tag;

// delete duplicates in the linked list without extra space
@Tag(dss = LinkedList, source = CrackingTheCodeInterview)
public class RemoveDuplicates {
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
    ListNode curHead = head;
    while (curHead != null) {
      ListNode prev = curHead, cur = curHead.next;
      while (cur != null) {
        if (curHead.val == cur.val) {
          prev.next = cur.next; // delete duplicate
        }
        else {
          prev = cur;
        }
        cur = cur.next;// advance cur
      }
      curHead = curHead.next;
    }
  }
}
