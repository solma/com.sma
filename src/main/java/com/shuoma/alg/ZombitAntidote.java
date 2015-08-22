package com.shuoma.alg;

import static com.shuoma.annotation.Tag.Algorithm.Greedy;
import static com.shuoma.annotation.Tag.DataStructure.IntervalT;
import static com.shuoma.annotation.Tag.Reference.GoogleCodeChallengeFoobar;

import com.shuoma.annotation.Tag;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * Forget flu season. Zombie rabbits have broken loose and are terrorizing Silicon Valley residents! Luckily, you've managed to steal a zombie antidote and set up a makeshift rabbit rescue station. Anyone who catches a zombie rabbit can schedule a meeting at your station to have it injected with the antidote, turning it back from a zombit to a fluffy bunny. Unfortunately, you have a limited amount of time each day, so you need to maximize these meetings. Every morning, you get a list of requested injection meetings, and you have to decide which to attend fully. If you go to an injection meeting, you will join it exactly at the start of the meeting, and only leave exactly at the end.
 * Can you optimize your meeting schedule? The world needs your help!
 * Write a method called answer(meetings) which, given a list of meeting requests, returns the maximum number of non-overlapping meetings that can be scheduled. If the start time of one meeting is the same as the end time of another, they are not considered overlapping.
 * meetings will be a list of lists. Each element of the meetings list will be a two element list denoting a meeting request. The first element of that list will be the start time and the second element will be the end time of that meeting request.
 * All the start and end times will be non-negative integers, no larger than 1000000.
 * The start time of a meeting will always be less than the end time.
 * The number of meetings will be at least 1 and will be no larger than 100.
 * The list of meetings will not necessarily be ordered in any particular fashion.
 */
@Tag(algs = Greedy, dss = IntervalT, references = GoogleCodeChallengeFoobar)
public class ZombitAntidote {
  public static void main(String[] args) {
    System.out.println(answer(new int[][] {{0, 1}, {1, 2}, {2, 3}, {4, 5}, {3, 5}}));
    System.out.println(answer(new int[][] {{0, 1000000}, {42, 43}, {0, 1000000}, {42, 43}}));
    System.out.println(answer(new int[][] {{0, 5}}));
  }

  public static int answer(int[][] meetings) {
    List<Interval> intervals = new LinkedList<>();
    for (int[] meeting : meetings) {
      intervals.add(new Interval(meeting));
    }
    Collections.sort(intervals);

    int cnt = 0;
    Interval prev = null;
    for (Interval interval : intervals) {
      if (prev == null || (prev.end <= interval.start && !prev.equals(interval))) {
        cnt++;
      }
      prev = interval;
    }
    return cnt;
  }

  public static class Interval implements Comparable<Interval> {
    int start, end;

    public Interval(int[] meeting) {
      start = meeting[0];
      end = meeting[1];
    }

    @Override public int compareTo(Interval that) {
      return this.end - that.end;
    }

    @Override public boolean equals(Object that) {
      return start == ((Interval) that).start && end == ((Interval) that).end;
    }
  }
}
