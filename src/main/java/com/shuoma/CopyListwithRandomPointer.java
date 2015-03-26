package com.shuoma;

import static com.shuoma.annotation.Tag.DataStructure.LinkedList;
import static com.shuoma.annotation.Tag.Difficulty.D2;
import static com.shuoma.annotation.Tag.Source.LeetCode;

import com.shuoma.annotation.Tag;

import java.util.HashMap;
import java.util.Map;

@Tag(dl = D2, dss = LinkedList, source = LeetCode)
public class CopyListwithRandomPointer {

  public RandomListNode copyRandomList(RandomListNode head) {
    if (head == null) {
      return null;
    }
    Map<RandomListNode, RandomListNode> hm = new HashMap<>();
    RandomListNode p = head;
    while (p != null) {
      RandomListNode n = new RandomListNode(p.label);
      hm.put(p, n);
      p = p.next;
    }
    p = head;
    while (p != null) {
      RandomListNode n = hm.get(p);
      n.next = null;
      n.random = null;
      if (p.next != null)
        n.next = hm.get(p.next);
      if (p.random != null)
        n.random = hm.get(p.random);
      p = p.next;
    }
    return hm.get(head);
  }
}
