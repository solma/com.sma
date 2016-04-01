package com.sma.alg;

import static com.sma.annotation.Tag.DataStructure.LinkedListT;
import static com.sma.annotation.Tag.Reference.LeetCode;

import com.sma.annotation.Tag;
import com.sma.ds.linkedlist.ListNode;

@Tag(dss = LinkedListT, references = LeetCode)
public class ReverseLinkedListRecursive {
  public ListNode Reverse(ListNode list) {
    if (list == null)
      return null; // first question

    if (list.next == null)
      return list; // second question

    // third question - in Lisp this is easy, but we don't have cons
    // so we grab the second element (which will be the last after we
    // reverse it)

    ListNode secondElem = list.next;

    // bug fix - need to unlink list from the rest or you will get a cycle
    list.next = null;

    // then we reverse everything from the second element on
    ListNode reverseRest = Reverse(secondElem);

    // then we join the two lists
    secondElem.next = list;

    return reverseRest;
  }
}
