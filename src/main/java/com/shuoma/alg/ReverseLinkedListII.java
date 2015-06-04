package com.shuoma.alg;

import static com.shuoma.annotation.Tag.DataStructure.LinkedList;
import static com.shuoma.annotation.Tag.Difficulty.D2;
import static com.shuoma.annotation.Tag.Reference.LeetCode;

import com.shuoma.annotation.Tag;

@Tag(dl = D2, dss = LinkedList, reference = LeetCode)
public class ReverseLinkedListII {

  public static void main(String[] args) {
    new ReverseLinkedListII().main();
  }

  public void main() {
    ListNode n = new ListNode(-1);
    n.next = new ListNode(-3);
    reverseBetween(n, 1, 2);
  }

  public ListNode reverseBetween(ListNode head, int m, int n) {
    if (head == null || head.next == null)
      return head;

    int k = m;
    ListNode cur = head, prev = null;
    //locate the mth node, cur=mth node
    while (k > 1) {
      prev = cur;
      cur = cur.next;
      k--;
    }

    ListNode prevStart = prev;
    ListNode start = cur;
    prev = null;
    k = n - m;
    while (cur != null && k >= 0) {
      //            ListNode temp=cur;
      //            cur=cur.next;//advance cur
      //            temp.next=prev;
      //            prev=temp; //advance prev
      ListNode next = cur.next;
      cur.next = prev;
      prev = cur;
      cur = next;
      k--;
    }
    //connecting the reversed list with the before
    if (prevStart != null)
      prevStart.next = prev;
    //connecting the reversed list with the after
    start.next = cur;

    if (m > 1)
      return head;
    else
      return prev;
  }
}
