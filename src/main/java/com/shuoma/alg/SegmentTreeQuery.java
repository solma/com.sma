package com.shuoma.alg;

import static com.shuoma.annotation.Tag.DataStructure.SegmentTree;
import static com.shuoma.annotation.Tag.Difficulty.D2;
import static com.shuoma.annotation.Tag.Reference.LintCode;

import com.shuoma.annotation.Tag;
import com.shuoma.ds.graph.tree.SegmentTreeNode;

@Tag(dl = D2, dss = {SegmentTree}, references = LintCode)
public class SegmentTreeQuery {
  /**
   *@param root, start, end: The root of segment tree and an segment / interval
   *@return: The maximum number in the interval [start, end]
   */
  public int query(SegmentTreeNode root, int start, int end) {
    if (root == null) {
      return Integer.MIN_VALUE;
    }

    // contained
    if (root.start == start && root.end == end) {
      return (int) root.value;
    }

    // contain
    if (root.start <= start && root.end >= end) {
      return Math.max(query(root.left, start, end), query(root.right, start, end));
    }

    // overlap
    if (root.start <= end && root.end >= start) {
      return query(root, Math.max(start, root.start), Math.min(end, root.end));
    }

    // disjoint
    return Integer.MIN_VALUE;
  }
}
