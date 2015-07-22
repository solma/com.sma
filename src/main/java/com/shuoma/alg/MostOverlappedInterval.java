package com.shuoma.alg;

import static com.shuoma.annotation.Tag.Algorithm.BinarySearch;
import static com.shuoma.annotation.Tag.DataStructure.Interval;
import static com.shuoma.annotation.Tag.DataStructure.PriorityQueue;
import static com.shuoma.annotation.Tag.Difficulty.D3;
import static com.shuoma.annotation.Tag.Reference.Interview;
import static com.shuoma.ds.graph.tree.BST.BinarySearchTree;

import com.shuoma.annotation.Tag;
import com.shuoma.ds.misc.Interval;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

/**
 * Give a set of intervals, find the one with most overlapped intervals.
 * O(nlogn) time
 */
@Tag(algs = BinarySearch, dl = D3, dss = {Interval, PriorityQueue}, references = Interview)
public class MostOverlappedInterval {

  /*
  use sweep line, sort the interval by start, then scan with a PriorityQueue (sort by end)
  hold the unfinished interval, when scan to a interval, pop intervals in queue
  which end < interval.start, the overlapped interval is heap.size() after pop().
  This can't handle intervals fully contains others, so need keep a sorted list of end of poped
  intervals. when pop a new interval, find how many interval end > current.start,
  and plus the heap.size(). Both heap and sorted list(binary search tree) in O(lgN),
  so entire O(NlgN).
   */

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
        // bst.size() - bst.get(): number of popped out intervals whose end smaller than top.start
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
