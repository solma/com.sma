package com.shuoma.alg;

import static com.shuoma.annotation.Tag.DataStructure.LinkedListT;
import static com.shuoma.annotation.Tag.Reference.LeetCode;

import com.shuoma.annotation.Tag;
import com.shuoma.ds.linkedlist.ListNode;

@Tag(dss = LinkedListT, references = LeetCode)
public class IntersectionOfTwoLinkedList {
  public static void main(String[] args) {
    new IntersectionOfTwoLinkedList().main();
  }

  public void main() {
    ListNode lA = new ListNode(2);
    ListNode lB = new ListNode(3);

    ListNode c = new ListNode(10);
    lA.next = new ListNode(7);
    lA.next.next = c;
    lB.next = c;
    System.out.println(getIntersectionNode(lB, lA));
  }

  public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
    int cntA = count(headA), cntB = count(headB);
    int diff = cntA - cntB;
    if (diff != 0) {
      if (diff > 0) { headA = move(headA, diff); }
      else { headB = move(headB, -diff); }
    }

    System.out.println(cntA + " " + cntB + " " + headA + " " + headB);
    while (headA != null && headB != null && !headA.equals(headB)) {
      headA = headA.next;
      headB = headB.next;
    }
    if (headA == null || headB == null) { return null; }
    else { return headA; }
  }

  // move head forward n steps
  public ListNode move(ListNode head, int n) {
    for (int i = 0; i < n; i++) {
      head = head.next;
      if (head == null)
        break;
    }
    return head;
  }

  public int count(ListNode head) {
    ListNode cur = head;
    int cnt = 0;
    while (cur != null) {
      cur = cur.next;
      cnt++;
    }
    return cnt;
  }
}
