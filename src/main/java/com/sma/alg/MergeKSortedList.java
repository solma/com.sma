package com.sma.alg;

import static com.sma.alg.MergeTwoSortedList.mergeTwoLists;
import static com.sma.annotation.Tag.DataStructure.LinkedListT;
import static com.sma.annotation.Tag.Difficulty.D2;
import static com.sma.annotation.Tag.Reference.LeetCode;

import com.sma.annotation.Tag;
import com.sma.ds.linkedlist.ListNode;

import java.util.ArrayList;

@Tag(dl = D2, dss = LinkedListT, references = LeetCode)
public class MergeKSortedList {
  public static ListNode mergeKLists(ArrayList<ListNode> lists) {
    int ls = lists.size();
    if (ls == 0) { return null; }
    ListNode fl = lists.get(0);
    if (ls == 1) { return fl; }
    for (int i = 1; i < ls; i++) {
      fl = mergeTwoLists(lists.get(i), fl);
    }
    return fl;
  }
}
