package com.shuoma.alg.array;

import com.shuoma.ds.Interval;
import com.shuoma.helper.CommonUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class RangeMinimumQuery {

  public enum Solution {
    BASIC_DP("O(n^2), O(1)"), SPACE_EFFICIENT_DP("O(nlogn), O(1)"), TREE_BASED("O(n), O(logn)");
    private String name;

    Solution(String n) {
      name = n;
    }

    @Override
    public String toString() {
      return name;
    };
  }

  public static void main(String[] args) {
    new RangeMinimumQuery().main();
  }

  public void test() {
    int[] rdmArray =
        {376, 47, 628, 540, 952, 142, 784, -454, 258, -316, -772, -886, 112, 901, 369, -484, 936,
            978};
    Interval[] queries = new Interval[1];
    queries[0] = new Interval(5, 5);
    System.out.println(Arrays.toString(getMinimum(queries, rdmArray, Solution.TREE_BASED)));

  }

  boolean DEBUG = false;

  public void main() {
    if (DEBUG) {
      test();
      return;
    }

    int[] rdmArray = CommonUtils.genRandomArrayWithMinSize(10);
    Random rand = new Random();
    int noOfQueries = rand.nextInt(10) + 10;
    Interval[] queries = new Interval[noOfQueries];


    for (int i = 0; i < noOfQueries; i++) {
      int start = rand.nextInt(rdmArray.length);
      queries[i] = new Interval(start, start + rand.nextInt(rdmArray.length - start));
    }


    System.out.println("Arrays=" + Arrays.toString(rdmArray));
    System.out.println("Ranges=" + Arrays.toString(queries));
    for (Solution alg : Solution.values()) {
      System.out.println(alg + " : " + Arrays.toString(getMinimum(queries, rdmArray, alg)));
    }
  }

  public int[] getMinimum(Interval[] intervals, int[] rdmArray, Solution alg) {
    int[][] mins;
    int[] ans;

    switch (alg) {

      case BASIC_DP:
        // O(n^2) space and time to preprocess; O(1) to answer a single query
        mins = new int[rdmArray.length][rdmArray.length];
        for (int i = 0; i < rdmArray.length; i++) {
          for (int j = i; j < rdmArray.length; j++) {
            mins[i][j] = j == i ? rdmArray[i] : Math.min(mins[i][j - 1], rdmArray[j]);
          }
        }

        ans = new int[intervals.length];
        for (int i = 0; i < ans.length; i++) {
          Interval itvl = intervals[i];
          ans[i] = mins[(int) itvl.start][(int) itvl.end];
        }
        return ans;

      case SPACE_EFFICIENT_DP:
        // O(nlogn) space and time to preprocess; O(1) to answer a single query
        int logNSize = (int) Math.ceil(Math.log(rdmArray.length) / Math.log(2));
        mins = new int[rdmArray.length][logNSize];
        for (int logNLen = 0; logNLen < logNSize; logNLen++) {
          for (int i = 0; i < rdmArray.length; i++) {
            int len = 1 << logNLen;
            mins[i][logNLen] =
                len == 1 ? rdmArray[i] : Math.min(mins[i][logNLen - 1],
                    mins[Math.min(rdmArray.length - 1, i + (len >> 1))][logNLen - 1]);
            if (DEBUG)
              System.out.println("i=" + i + " logNLen=" + logNLen + " len=" + len + " "
                  + mins[i][logNLen]);
          }
        }

        ans = new int[intervals.length];
        for (int i = 0; i < ans.length; i++) {
          Interval itvl = intervals[i];
          int logNLen =
              (int) Math.max(0, Math.ceil(Math.log(itvl.end - itvl.start + 1) / Math.log(2)) - 1);
          ans[i] =
              Math.min(mins[(int) itvl.start][logNLen],
                  mins[Math.max(0, (int) itvl.end + 1 - (1 << logNLen))][logNLen]);
        }
        return ans;

      case TREE_BASED:
        // build up tournament tree O(n) space and time; O(logn) to answer a single query
        ArrayList<ArrayList<ArrayInterval>> tTree = new ArrayList<ArrayList<ArrayInterval>>();

        // add leaf level
        ArrayList<ArrayInterval> leafLvl = new ArrayList<ArrayInterval>();
        for (int i = 0; i < rdmArray.length; i++) {
          leafLvl.add(new ArrayInterval(i, i, rdmArray[i]));
        }
        tTree.add(leafLvl);

        // build tree from bottom to top
        ArrayList<ArrayInterval> nextLvL,
        curLvl;
        curLvl = leafLvl;
        do {
          nextLvL = new ArrayList<ArrayInterval>();
          for (int i = 0; i < curLvl.size();) {
            ArrayInterval cur = curLvl.get(i);
            if (i + 1 < curLvl.size()) {
              ArrayInterval nxt = curLvl.get(i + 1);
              nextLvL.add(new ArrayInterval(Math.min(cur.start, nxt.start), Math.max(cur.end,
                  nxt.end), Math.min(cur.min, nxt.min), cur, nxt));
              i += 2;
            } else {
              nextLvL.add(cur);
              i++;
            }
          }
          curLvl = nextLvL;
        } while (curLvl.size() > 1);


        // query the tree
        ArrayInterval root = curLvl.get(0);
        if (DEBUG) System.out.println("root=" + root);
        ans = new int[intervals.length];
        for (int i = 0; i < ans.length; i++) {
          ans[i] = (int) root.getMin(intervals[i]);
        }
        return ans;
      default:
        return null;
    }
  }



  class ArrayInterval extends Interval {
    double min;
    ArrayInterval left, right;

    public ArrayInterval(double start, double end) {
      super(start, end);
    }

    public ArrayInterval(double start, double end, double min) {
      this(start, end);
      this.min = min;
      left = right = null;
    }

    public ArrayInterval(
        double start,
        double end,
        double min,
        ArrayInterval left,
        ArrayInterval right) {
      this(start, end, min);
      this.left = left;
      this.right = right;
    }

    public double getMin(Interval interval) {
      if (!isOverlapping(interval)) return Integer.MAX_VALUE;
      if (isIn(interval)) return min;

      return Math.min(left == null ? Integer.MAX_VALUE : left.getMin(interval),
          right == null ? Integer.MAX_VALUE : right.getMin(interval));
    }

    public boolean isOverlapping(Interval interval) {
      return end >= interval.start && start <= interval.end;
    }

    public boolean isIn(Interval interval) {
      return start >= interval.start && end <= interval.end;
    }

    @Override
    public String toString() {
      return start + "~" + end + ":" + min;
    }
  }

}
