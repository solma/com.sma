package com.shuoma.alg;

import static com.shuoma.annotation.Tag.DataStructure.LinkedList;
import static com.shuoma.annotation.Tag.Reference.LeetCode;

import com.shuoma.annotation.Tag;

@Tag(dss = LinkedList, reference = LeetCode)
public class MergeTwoSortedList {
  public static void main(String[] args) {
    new MergeTwoSortedList().main();
  }

  public void main() {
    System.out.println(mergeTwoLists(ListNode.buildList(new int[] {2, 6, 7, 9}),
        ListNode.buildList(new int[] {1, 3, 8})));
  }

  //second pass
  public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
    if (l1 == null)
      return l2;
    if (l2 == null)
      return l1;

    ListNode cur = l1, prev = null;
    while (cur != null && l2 != null) {
      if (cur.val >= l2.val) { //insert l2 between prev and cur
        if (prev == null) {
          l1 = l2;
        } else {
          prev.next = l2;
        }

        prev = l2;
        l2 = l2.next; //advance l2

        prev.next = cur;
      } else {
        prev = cur;
        cur = cur.next;
      }
    }
    if (l2 != null && prev != null) {
      prev.next = l2;
    }

    return l1;
  }

  //first pass
  public ListNode mergeTwoListsFirstPass(ListNode l1, ListNode l2) {
    ListNode head = l2, l2Prev = null;
    while (l1 != null && l2 != null) {
      if (l1.val >= l2.val) {
        l2Prev = l2;
        l2 = l2.next;
      } else {
        if (l2Prev != null) {
          l2Prev.next = l1;
          l1 = l1.next;   //advance l1
          l2Prev.next.next = l2;
          l2Prev = l2Prev.next; //advance l2Prev
        } else {
          l2Prev = l1; //advance l2Prev
          head = l2Prev; //change head
          l1 = l1.next; //advance l1
          l2Prev.next = l2;
        }
      }
    }
    if (l2 == null && l1 != null) {
      if (l2Prev != null)
        l2Prev.next = l1; //copy rest of l1 to l2
      else
        head = l1;
    }
    return head;
  }

}
