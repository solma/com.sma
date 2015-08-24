package com.shuoma.ds.linkedlist;

import static com.shuoma.annotation.Tag.Reference.LeetCode;

import com.shuoma.annotation.Tag;

@Tag(references = LeetCode)
/**
 * All static methods assume single-linked node unless otherwise stated.
 */
public class ListNode {
  public int val;
  public ListNode next, prev;

  public ListNode(int x) {
    val = x;
    next = null;
  }

  public static ListNode buildList(int[] array) {
    ListNode pseudoHead = new ListNode(0);
    ListNode prev = pseudoHead;
    for (int i = 0; i < array.length; i++) {
      prev.next = new ListNode(array[i]);
      prev = prev.next;
    }
    return pseudoHead.next;
  }

  public static ListNode reverseR(ListNode head) {
    if (head == null || head.next == null) return head;
    ListNode next = head.next;
    head.next = null;
    ListNode newHead = reverseR(next);
    next.next = head;
    return newHead;
  }

  public static ListNode reverseInKGroupR(ListNode head, int K) {
    assert (head != null);
    int cnt = 0;
    ListNode cur = head, prev = null;
    while (cur != null && cnt < K) {
      prev = cur;
      cur = cur.next;
      cnt++;
    }
    if (cur == null) return reverseR(head);

    prev.next = null;
    ListNode newHead = reverseR(head);
    head.next = reverseInKGroupR(cur, K);
    return newHead;
  }

  /**
   * @param l1
   * @param l2
   * @return l1 (interleaving l2 list to l1) e.g. l1: 5->3->1, l2: 2->4, return l1: 5->2->3->4->1
   * l1 should be not shorter than l2
   */
  public static ListNode interweave(ListNode l1, ListNode l2) {
    if (l1 == null) return l2;

    ListNode p1 = l1, p2 = l2;
    while (p1 != null && p2 != null) {
      ListNode next = p1.next;
      p1.next = p2;
      p1 = next;

      next = p2.next;
      if (p1 != null) { p2.next = p1; }
      p2 = next;
    }
    return l1;
  }

  /**
   *
   * @param head, e.g. 1->2->3->4->5
   * @return 2->4 and li becomes 1->3->5
   */
  public static ListNode splitAlternatively(ListNode head) {
    ListNode cur = head, l2Head = null;
    while (cur != null) {
      ListNode p2 = cur.next;
      if (l2Head == null) l2Head = p2;
      if (p2 != null) cur.next = p2.next;
      cur = cur.next;
      if (cur != null) p2.next = cur.next;
    }
    return l2Head;
  }

  /**
   * @param li, e.g. 1->2->3->4->5
   * @return 2->1->4->3->5
   */
  public static ListNode swapAlternatively(ListNode li) {
    if (li == null) return null;

    ListNode prev = li, pseudo = new ListNode(0);
    ListNode cur, after, before;
    before = pseudo;

    cur = prev.next;
    while (prev != null && cur != null) {
      after = cur.next;
      cur.next = prev;
      before.next = cur;
      before = prev;

      prev = after;
      if (prev != null) cur = prev.next;
    }
    if (before != null) before.next = prev;
    return pseudo.next;
  }

  @Override
  public boolean equals(Object other) {
    ListNode that = (ListNode) other;
    return val == that.val
        && ((next == null && that.next == null) || next.equals(that.next))
        && ((prev == null && that.prev == null) || prev.equals(that.prev));
  }

  @Override
  public int hashCode() {
    return val;
  }

  @Override
  public String toString() {
    ListNode cur = this;
    StringBuilder sb = new StringBuilder();
    while (cur != null) {
      sb.append(cur.val + "->");
      cur = cur.next;
    }
    sb.append("end");
    return sb.toString();
  }
}
