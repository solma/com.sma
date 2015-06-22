package com.shuoma.alg;

import static com.shuoma.annotation.Tag.DataStructure.LinkedList;
import static com.shuoma.annotation.Tag.Reference.CrackingTheCodeInterview;

import com.shuoma.annotation.Tag;

// delete duplicates in the linked list without extra space
@Tag(dss = LinkedList, references = CrackingTheCodeInterview)
public class RemoveDuplicatesFromLinkedList {
  public static void main(String[] args) {
    com.shuoma.ds.linkedlist.ListNode
        n = com.shuoma.ds.linkedlist.ListNode.buildList(new int[] {2, 0, 2, 1, 1, 1});
    removeDuplicates(n);
    printList(n);
  }

  static void printList(com.shuoma.ds.linkedlist.ListNode head) {
    while (head != null) {
      System.out.println(head.val);
      head = head.next;
    }
  }

  static void removeDuplicates(com.shuoma.ds.linkedlist.ListNode head) {
    com.shuoma.ds.linkedlist.ListNode curHead = head;
    while (curHead != null) {
      com.shuoma.ds.linkedlist.ListNode prev = curHead, cur = curHead.next;
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
