package com.shuoma.alg;

import static com.shuoma.annotation.Tag.Algorithm.Greedy;
import static com.shuoma.annotation.Tag.DataStructure.Interval;

import com.shuoma.annotation.Tag;
import com.shuoma.ds.misc.Interval;
import com.shuoma.util.RandomUtil;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

@Tag(algs = Greedy, dss = Interval)
public class IntervalScheduling {

  /**
   * @param args
   */
  public static void main(String[] args) {
    List<Interval> list = RandomUtil.genRandomListOfWeightedIntervals(10, 10, 10);
    schedule(list);

  }

  public static List<Interval> schedule(List<Interval> input) {
    List<Interval> ret = new ArrayList<>();
    int n = input.size();
    if (n < 1) return ret;
    if (n < 2) return input;
    Collections.sort(input, new Comparator<Interval>() {
      @Override public int compare(Interval o1, Interval o2) {
        return o1.end == o2.end ? (o1.start - o2.start) : (o1.end - o2.end);
      }
    });
    System.out.println("input: \n" + input);
    Interval prev = input.get(0);
    ret.add(prev);
    for (int i = 1; i < n; i++) {
      Interval cur = input.get(i);
      if (cur.start >= prev.end) {
        ret.add(cur);
        prev = cur;
      }
    }
    System.out.println("ret: \n" + ret);
    return ret;
  }
}
