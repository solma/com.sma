package com.sma.alg;

import static com.sma.annotation.Tag.Algorithm.DynamicProgramming;
import static com.sma.annotation.Tag.DataStructure.Array;
import static com.sma.annotation.Tag.DataStructure.SegmentTree;
import static com.sma.annotation.Tag.Difficulty.D3;
import static com.sma.annotation.Tag.Reference.JulyEdu;
import static com.sma.annotation.Tag.Reference.LintCode;

import com.sma.annotation.Tag;
import com.sma.ds.graph.tree.SegmentTreeNode;
import com.sma.ds.misc.Interval;

import java.util.ArrayList;
import java.util.List;

@Tag(algs = DynamicProgramming, dl = D3, dss = {Array, SegmentTree}, references = {JulyEdu, LintCode})
public class RangeMinimumQuery {

  public enum Algorithm {
    BASIC_DP("O(n^2),   O(1)"), SPACE_EFFICIENT_DP("O(nlogn), O(1)"), TREE_BASED("O(n),  O(logn)");
    private String name;

    Algorithm(String n) {
      name = n;
    }

    @Override public String toString() {
      return name;
    }
  }

  public static boolean DEBUG = false;

  public int[] getMinimum(Interval[] intervals, int[] rdmArray, Algorithm alg) {
    int[][] mins;
    int[][] ans = new int[3][];

    int n = rdmArray.length;
    switch (alg) {

      case BASIC_DP:
        // O(n^2) space and time to preprocess; O(1) to answer a single query
        mins = new int[n][n];
        for (int i = 0; i < n; i++) {
          for (int j = i; j < n; j++) {
            mins[i][j] = j == i ? rdmArray[i] : Math.min(mins[i][j - 1], rdmArray[j]);
          }
        }

        ans[0] = new int[intervals.length];
        for (int i = 0; i < intervals.length; i++) {
          Interval itvl = intervals[i];
          ans[0][i] = mins[itvl.start][itvl.end];
        }
        return ans[0];

      case SPACE_EFFICIENT_DP:
        // O(nlogn) space and time to preprocess; O(1) to answer a single query
        int logNSize = (int) Math.ceil(Math.log(n) / Math.log(2));
        mins = new int[n][logNSize];
        for (int logNLen = 0; logNLen < logNSize; logNLen++) {
          for (int i = 0; i < n; i++) {
            int len = 1 << logNLen;
            mins[i][logNLen] = len == 1 ? rdmArray[i] :
                Math.min(mins[i][logNLen - 1], mins[Math.min(n - 1, i + (len >> 1))][logNLen - 1]);
            if (DEBUG)
              System.out.println(
                  "i=" + i + " logNLen=" + logNLen + " len=" + len + " " + mins[i][logNLen]);
          }
        }

        ans[1] = new int[intervals.length];
        for (int i = 0; i < intervals.length; i++) {
          Interval itvl = intervals[i];
          int logNLen =
              (int) Math.max(0, Math.ceil(Math.log(itvl.end - itvl.start + 1) / Math.log(2)) - 1);
          ans[1][i] = Math.min(mins[itvl.start][logNLen],
              mins[Math.max(0, itvl.end + 1 - (1 << logNLen))][logNLen]);
        }
        return ans[1];

      case TREE_BASED:
        // segment tree based  O(n) space and time; O(logn) to answer a single query
        //MinSegmentTreeNode root = MinSegmentTreeNode.buildByLevel(rdmArray);
        MinSegmentTreeNode root = MinSegmentTreeNode.buildRecursively(rdmArray);

        if (DEBUG) {
          System.out.println("root=" + root);
        }
        ans[2] = new int[intervals.length];
        for (int i = 0; i < intervals.length; i++) {
          ans[2][i] = (int) root.getMin(intervals[i]);
        }
        return ans[2];
      default:
        return null;
    }
  }

  public static class MinSegmentTreeNode extends SegmentTreeNode {

    MinSegmentTreeNode left, right;

    public static MinSegmentTreeNode buildRecursively(int[] nums) {
      // build a MinSegmentTree given an array numbers
      return buildRecursively(0, nums.length - 1, nums);
    }

    static MinSegmentTreeNode buildRecursively(int sIdx, int eIdx, int[] nums) {
      if (sIdx > eIdx) return null;
      if (sIdx == eIdx) return new MinSegmentTreeNode(sIdx, eIdx, nums[sIdx]);
      int mid = (sIdx + eIdx) / 2;
      MinSegmentTreeNode left = buildRecursively(sIdx, mid, nums), right = buildRecursively(mid + 1,
          eIdx, nums);
      double leftMin = left == null ? Integer.MAX_VALUE : left.value;
      double rightMin = right == null ? Integer.MAX_VALUE : right.value;
      MinSegmentTreeNode cur = new MinSegmentTreeNode(sIdx, eIdx, Math.min(leftMin, rightMin),
          left, right);
      return cur;
    }

    public static MinSegmentTreeNode buildByLevel(int[] nums) {
      List<List<MinSegmentTreeNode>> tTree = new ArrayList<>();

      // add leaf level
      int n = nums.length;
      List<MinSegmentTreeNode> leafLvl = new ArrayList<>();
      for (int i = 0; i < n; i++) {
        leafLvl.add(new MinSegmentTreeNode(i, i, nums[i]));
      }
      tTree.add(leafLvl);

      // build tree from bottom to top
      List<MinSegmentTreeNode> nextLvL, curLvl;
      curLvl = leafLvl;
      do {
        nextLvL = new ArrayList<>();
        for (int i = 0; i < curLvl.size(); ) {
          MinSegmentTreeNode cur = curLvl.get(i);
          if (i + 1 < curLvl.size()) {
            MinSegmentTreeNode nxt = curLvl.get(i + 1);
            nextLvL.add(
                new MinSegmentTreeNode(Math.min(cur.start, nxt.start), Math.max(cur.end, nxt.end),
                    Math.min(cur.value, nxt.value), cur, nxt));
            i += 2;
          } else {
            nextLvL.add(cur);
            i++;
          }
        }
        curLvl = nextLvL;
      } while (curLvl.size() > 1);

      return curLvl.get(0);
    }

    public MinSegmentTreeNode(int start, int end, double min) {
      super(start, end, min);
    }

    public MinSegmentTreeNode(int start, int end, double min,
        MinSegmentTreeNode left, MinSegmentTreeNode right) {
      super(start, end, min);
      this.left = left;
      this.right = right;
    }

    public double getMin(Interval interval) {
      // contained by query
      if (start >= interval.start && end <= interval.end) {
        return value;
      }

      // contain query
      if (include(interval)) {
        return Math.min(left == null ? Integer.MAX_VALUE : left.getMin(interval),
            right == null ? Integer.MAX_VALUE : right.getMin(interval));
      }

      // overlap
      if (overlap(interval)) {
        return getMin(new Interval(Math.max(start, interval.start), Math.min(end, interval.end)));
      }

      // disjoint
      return Integer.MAX_VALUE;
    }

    @Override
    public String toString() {
      return toString(true);
    }
  }
}
