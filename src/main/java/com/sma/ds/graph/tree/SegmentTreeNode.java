package com.sma.ds.graph.tree;

import com.sma.ds.misc.Interval;

public class SegmentTreeNode extends Interval {
  public SegmentTreeNode left, right;

  /**
   *@param start, end: Denote an segment / interval
   *@return: The root of Segment Tree
   */
  public static SegmentTreeNode build(int start, int end) {
    if (start > end) return null;

    SegmentTreeNode cur = new SegmentTreeNode(start, end);
    if (start == end) {
      return cur;
    }

    int mid = (start + end) >>> 1;
    cur.left = build(start, mid);
    cur.right = build(mid + 1, end);
    return cur;
  }


  public SegmentTreeNode(int start, int end) {
    super(start, end);
    this.left = this.right = null;
  }

  public SegmentTreeNode(int start, int end, double value) {
    super(start, end, value);
    this.left = this.right = null;
  }
}
