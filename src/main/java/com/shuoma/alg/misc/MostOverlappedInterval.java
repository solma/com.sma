package com.shuoma.alg.misc;

import static com.shuoma.ds.graph.tree.BST.BinarySearchTree;
import com.shuoma.ds.misc.Interval;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

/** Give a set of intervals, find the one with most overlapped intervals. */
public class MostOverlappedInterval {

  int[] mostOverlapped(List<Interval> intervals) {
    Collections.sort(intervals, new Comparator<Interval>() {
      @Override public int compare(Interval o1, Interval o2) {
        return o1.start == o2.start ? (o1.end - o2.end) : (o1.start - o2.start);
      }
    });

    int n = intervals.size();
    PriorityQueue<Interval> overlappedIntervals = new PriorityQueue<>(n, new Comparator<Interval>() {
      @Override public int compare(Interval o1, Interval o2) {
        return o1.end == o2.end ? (o1.start - o2.start) : (o1.end - o2.end);
      }
    });


    BinarySearchTree bst = new BinarySearchTree();
    int[] result = new int[3];
    for (int i = 0; i <= n; i++) {
      double start = i == n ? Integer.MAX_VALUE : intervals.get(i).start;

      // overlappedIntervals contains all intervals that overlap each other
      while (!overlappedIntervals.isEmpty() && overlappedIntervals.peek().end < start) {
        Interval top = overlappedIntervals.poll();
        // count number of overlapped intervals for top
        // bst.size() - bst.get(): number of popped out intervals whose end
        int count = overlappedIntervals.size() + (bst.size() - bst.rankOf(String.valueOf(top.start)));
        bst.insert(top.end);
        if (count > result[0]) {
          result[0] = count;
          result[1] = top.start;
          result[2] = top.end;
        }

      }
      if (i < n) {
        overlappedIntervals.add(intervals.get(i));
      }
    }

    return result;
  }
}