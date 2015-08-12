package com.shuoma.ds.linkedlist;

import java.util.LinkedList;
import java.util.List;

public class DoubleLinkedList {

  public static DoubleLinkedList buildList(int[] vals) {
    DoubleLinkedList list = new DoubleLinkedList();
    for (int val : vals)
      list.append(new ListNode(val));
    return list;
  }

  ListNode head;

  DoubleLinkedList prepend(ListNode n) {
    if (head == null) {
      n.next = n;
      n.prev = n;
    } else {
      head.prev.next = n;
      n.prev = head.prev;
      n.next = head;
      head.prev = n;
    }
    head = n;
    return this;
  }

  DoubleLinkedList append(ListNode n) {
    prepend(n);
    head = head.next;
    return this;
  }

  ListNode remove(int val) {
    ListNode cur = head;
    do {
      if (cur.val == val) {
        if (cur == head && head.next == head) { // one element
          head = null;
          return cur;
        }
        cur.prev.next = cur.next;
        cur.next.prev = cur.prev;
        if (cur == head) {
          head = head.next;
        }
        return cur;
      }
      cur = cur.next;
    } while (cur != head);
    return null;
  }

  @Override public String toString() {
    ListNode cur = head;
    if (cur == null) {
      return null;
    }
    List<Integer> vals = new LinkedList<>();
    do {
      vals.add(cur.val);
      cur = cur.next;
    } while (cur != head);
    return vals.toString();
  }
}
