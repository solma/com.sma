package com.sma.alg;

import static com.sma.annotation.Tag.DataStructure.IntervalT;
import static com.sma.annotation.Tag.Difficulty.D3;
import static com.sma.annotation.Tag.Reference.LeetCode;

import com.sma.annotation.Tag;
import com.sma.ds.misc.Interval;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

@Tag(dl = D3, dss = IntervalT, references = LeetCode)
public class MergeIntervals {

  //second pass
  public class CustomComparator implements Comparator<Interval> {
    public int compare(Interval o1, Interval o2) {
      return o1.start - o2.start;
    }
  }

  public List<Interval> merge(List<Interval> intervals) {
    int n = intervals.size();
    if (n < 2)
      return intervals;
    Collections.sort(intervals, new CustomComparator());

    Interval openInt = intervals.get(0);
    List<Interval> res = new ArrayList<>();

    for (int i = 1; i < n; i++) {
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

  //first pass
  public ArrayList<Interval> mergeFirstPass(ArrayList<Interval> intervals) {
    int n = intervals.size();
    if (n < 2)
      return intervals;
    Collections.sort(intervals, new CustomComparator());

    Interval openInt = intervals.get(0);
    ArrayList<Interval> res = new ArrayList<>();

    for (int i = 1; i < n; i++) {
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
}

