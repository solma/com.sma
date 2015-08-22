package com.shuoma.alg;

import static com.shuoma.annotation.Tag.DataStructure.IntervalT;
import static com.shuoma.annotation.Tag.Reference.LeetCode;

import com.shuoma.annotation.Tag;
import com.shuoma.ds.misc.Interval;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

@Tag(dss = IntervalT, references = LeetCode)
public class InsertInterval {
  //second pass
  public List<Interval> insert(List<Interval> intervals, Interval newInterval) {
    List<Interval> ret = new ArrayList<>();

    if (newInterval == null)
      return intervals;
    if (intervals.size() == 0) {
      ret.add(newInterval);
      return ret;
    }

    //insert the newInterval to the right position using the start time
    int i;
    for (i = 0; i < intervals.size(); i++) {
      if (intervals.get(i).start >= newInterval.start)
        break;
    }
    intervals.add(i, newInterval);

    // merge intervals if necessary
    Interval open = intervals.get(0);
    for (int j = 1; j < intervals.size(); j++) {
      Interval next = intervals.get(j);
      if (next.start > open.end) {
        ret.add(open);
        open = next;
      } else {
        if (next.end > open.end)
          open.end = next.end;
      }
    }
    ret.add(open);
    return ret;
  }

  //fist pass
  public ArrayList<Interval> insert1(ArrayList<Interval> intervals, Interval newInterval) {
    int i;
    for (i = 0; i < intervals.size(); i++) {
      if (intervals.get(i).start >= newInterval.start)
        break;
    }
    intervals.add(i, newInterval);

    int n = intervals.size();
    if (n < 2)
      return intervals;
    Collections.sort(intervals, new CustomComparator());

    Interval openInt = intervals.get(0);
    ArrayList<Interval> res = new ArrayList<>();

    for (i = 1; i < n; i++) {
      Interval cur = intervals.get(i);
      if (openInt.end < cur.start) {
        res.add(openInt);
        openInt = cur;
      } else {
        if (openInt.end < cur.end) {
          openInt.end = cur.end;
        }
      }
    }
    res.add(openInt);

    return res;
  }

  public class CustomComparator implements Comparator<Interval> {
    @Override public int compare(Interval o1, Interval o2) {
      return o1.start - o2.start;
    }
  }
}
