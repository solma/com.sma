package com.shuoma.alg;

import com.shuoma.ds.misc.Interval;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * Given a list of sorted disjoint intervals, and a period
 * find all the intervals that overlap with the period.
 * O(logN) time
 */
public class OverlappedIntervals {

  List<Interval> getOverlapped(List<Interval> li, Interval query) {
    TreeMap<Integer, Integer> sortedByStartTime = new TreeMap<>();
    TreeMap<Integer, Integer> sortedByEndTime = new TreeMap<>();
    for (int i = 0; i < li.size(); i++) {
      Interval interval = li.get(i);
      sortedByStartTime.put(interval.start, i);
      sortedByEndTime.put(interval.end, i);
    }

    Map.Entry<Integer, Integer> start = sortedByEndTime.lowerEntry(query.start);
    int startIdx = start != null ? (start.getValue() + 1) : 0;
    Map.Entry<Integer, Integer> end = sortedByStartTime.higherEntry(query.end);
    int endIdxExclusive = end != null ? end.getValue() : li.size();
    return li.subList(startIdx, endIdxExclusive);
  }
}
