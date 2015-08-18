package com.shuoma.alg;

import static com.shuoma.annotation.Tag.DataStructure.LinkedListT;
import static com.shuoma.annotation.Tag.Reference.LeetCode;

import com.shuoma.annotation.Tag;
import com.shuoma.ds.linkedlist.ListNode;

@Tag(dss = LinkedListT, references = LeetCode)
public class MergeTwoSortedList {
  public static void main(String[] args) {
    new MergeTwoSortedList().main();
  }

  public void main() {
    System.out.println(mergeTwoLists(ListNode.buildList(new int[] {2, 6, 7, 9}),
        ListNode.buildList(new int[] {1, 3, 8})));
  }

  public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
    if (l1 == null) { return l2; }
    if (l2 == null) { return l1; }

    ListNode cur = l1, prev = null;
    while (cur != null && l2 != null) {
      if (cur.val >= l2.val) { //insert l2 between prev and cur
        if (prev == null) { l1 = l2; }
        else { prev.next = l2; }

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
}
