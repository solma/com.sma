package com.shuoma.alg;

import static com.shuoma.annotation.Tag.DataStructure.LinkedList;
import static com.shuoma.annotation.Tag.Reference.LeetCode;

import com.shuoma.annotation.Tag;

@Tag(dss = LinkedList, references = LeetCode)
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

  public ListNode getIntersectionNode1(ListNode headA, ListNode headB) {
    if (headA == null || headB == null) {
      return null;
    }

    ListNode pA = headA;
    ListNode pB = headB;

    ListNode tailA = null;
    ListNode tailB = null;

    while (true) {
      if (pA == null) {
        pA = headB;
      }

      if (pB == null) {
        pB = headA;
      }

      if (pA.next == null) {
        tailA = pA;
      }

      if (pB.next == null) {
        tailB = pB;
      }

      //The two links have different tails. So just return null;
      if (tailA != null && tailB != null && tailA != tailB) {
        return null;
      }

      if (pA == pB) {
        return pA;
      }

      pA = pA.next;
      pB = pB.next;
    }
  }

  public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
    int cntA = count(headA), cntB = count(headB);
    int diff = cntA - cntB;
    if (diff > 0)
      headA = move(headA, diff);
    else if (diff < 0)
      headB = move(headB, -diff);
    System.out.println(cntA + " " + cntB + " " + headA + " " + headB);
    while (headA != null && headB != null && !headA.equals(headB)) {
      headA = headA.next;
      headB = headB.next;
    }
    if (headA == null || headB == null)
      return null;
    else
      return headA;
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
