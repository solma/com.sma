package com.shuoma.alg;

import static com.shuoma.annotation.Tag.DataStructure.LinkedList;
import static com.shuoma.annotation.Tag.Reference.LeetCode;

import com.shuoma.annotation.Tag;

@Tag(dss = LinkedList, references = LeetCode)
public class RotateList {
  public ListNode rotateRight(ListNode head, int n) {
    if (head == null || n == 0)
      return head;
    int len = 1;

    ListNode tmp = head;
    while (tmp.next != null) {
      tmp = tmp.next;
      len++;
    }
    n = len - n % len;
    tmp.next = head;//loop the linkedlist
    while (n > 0) {
      tmp = tmp.next;
      n--;
    }
    head = tmp.next;
    tmp.next = null;
    return head;

  }



  //TLE
  public ListNode rotateRightTLE(ListNode head, int n) {
    //get the size of list
    int len = 0;
    if (head == null || n == 0)
      return head;
    ListNode tmp = head;
    while (tmp != null) {
      tmp = tmp.next;
      len++;
    }
    n %= len;
    head = reverseBetween(head, 1, len);
    head = reverseBetween(head, 1, n);
    return reverseBetween(head, n + 1, len);
  }

  public ListNode reverseBetween(ListNode head, int m, int n) {
    if (m >= n || head == null || head.next == null)
      return head;

    ListNode prev = new ListNode(0);
    prev.next = head;
    head = prev;
    ListNode n1 = head;
    int k = m - 1;
    while (k > 0) {
      n1 = n1.next;
      k--;
    }

    prev = n1;
    n1 = n1.next;
    k = n - m;
    while (n1.next != null && k > 0) {
      ListNode temp = n1.next;
      n1.next = temp.next;
      temp.next = prev.next;
      prev.next = temp;
      k--;
    }
    return head.next;

  }
}
