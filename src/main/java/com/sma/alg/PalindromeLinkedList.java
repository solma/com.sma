package com.sma.alg;

import static com.sma.annotation.Tag.DataStructure.LinkedListT;
import static com.sma.annotation.Tag.Reference.LeetCode;
import static com.sma.annotation.Tag.Trick.TwoOrMorePointers;

import com.sma.annotation.Tag;
import com.sma.ds.linkedlist.ListNode;

@Tag(dss = LinkedListT, references = LeetCode, tricks = TwoOrMorePointers)
public class PalindromeLinkedList {

  // O(n) time O(1) space
  public boolean isPalindrome(ListNode head) {
    int cnt = 0;
    ListNode cur = head;
    while (cur != null) {
      cnt++;
      cur = cur.next;
    }
    if (cnt <= 1) return true;

    ListNode prev = null;
    cur = head;
    for (int i = 0; i < cnt / 2; i++) {
      prev = cur;
      cur = cur.next;
    }
    if (prev != null) {
      prev.next = null;
    }
    ListNode secondHalfHead = (cnt & 1) == 0 ? cur : cur.next;

    ListNode reversedSecondHalfHead = reverse(secondHalfHead);
    return equal(head, reversedSecondHalfHead);
  }

  ListNode reverse(ListNode head) {
    ListNode prev = null, cur = head;
    while (cur != null) {
      ListNode next = cur.next;
      cur.next = prev;
      prev = cur;
      cur = next;
    }
    return prev;
  }

  boolean equal(ListNode l1, ListNode l2) {
    while (!(l1 == null && l2 == null)) {
      if (l1 == null || l2 == null || l1.val != l2.val) return false;
      l1 = l1.next;
      l2 = l2.next;
    }
    return true;
  }
}
