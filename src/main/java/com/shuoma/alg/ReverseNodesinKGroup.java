package com.shuoma.alg;

import static com.shuoma.annotation.Tag.DataStructure.LinkedList;
import static com.shuoma.annotation.Tag.Difficulty.D2;
import static com.shuoma.annotation.Tag.Reference.LeetCode;

import com.shuoma.annotation.Tag;
import com.shuoma.ds.linkedlist.ListNode;

@Tag(dl = D2, dss = LinkedList, references = LeetCode)
public class ReverseNodesinKGroup {

  public static void main(String[] args) {
    new ReverseNodesinKGroup().main();
  }

  public void main() {
    int[] vals = new int[] {1, 4, 3, 2, 5, 6};
    ListNode n = new ListNode(vals[0]);
    ListNode prev = n;
    for (int i = 1; i < vals.length; i++) {
      ListNode c = new ListNode(vals[i]);
      prev.next = c;
      prev = c;
    }

    n = reverseKGroup(n, 3);
    System.out.println(n);
  }

  ListNode reverseKGroup(ListNode head, int k) {
    if (head == null || k <= 0)
      return null;
    ListNode dummy = new ListNode(0);
    dummy.next = head;
    head = dummy;
    for (int i = 0; head.next != null; head = head.next)
      if (i++ % k == 0) {
        System.out.print("Before Reverse: i=" + i + " head=" + head.val + " next=");
        if (head.next == null)
          System.out.print("null");
        else
          System.out.print(head.next.val);
        System.out.println(head);

        head.next = reverse(head.next, k);

        System.out.print("After Reverse: i=" + i + " head=" + head.val + " next=");
        if (head.next == null)
          System.out.print("null");
        else
          System.out.print(head.next.val);
        System.out.println(head);
      }
    return dummy.next;
  }

  //reverse k nodeMap starting from node
  ListNode reverse(ListNode node, int k) {
    ListNode ptr = node;
    int i = 0;
    for (; ptr != null && i < k; i++)
      ptr = ptr.next;
    if (i < k)
      return node;
    //System.out.println(ptr+" "+node);
    ListNode prev = null;
    ListNode cur = node;

    for (; i > 0; i--) {
      ListNode tmp = cur;
      cur = cur.next;
      tmp.next = prev;
      prev = tmp;
    }
    node.next = ptr;
    //System.out.println(" "+node);
    return prev;
  }
}
