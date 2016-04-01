package com.sma.ds.graph.tree;

import static com.sma.util.ArrayUtil.comparatorDoubleArray;

import java.util.Arrays;

public class KDTreeNode {

  public static void main(String[] args) {
    double[][] points = new double[][]{{2,3}, {5,4}, {9,6}, {4,7}, {8,1}, {7,2}};
    KDTreeNode root = buildTree(points);
    System.out.println(root);
  }

  public static KDTreeNode buildTree (double[][] points) {
    return buildTree(points, 0, points.length - 1, 0);
  }

  static KDTreeNode buildTree(double[][] points, int sIdx, int eIdx, int k) {
    if (sIdx > eIdx) return null;
    Arrays.sort(points, sIdx, eIdx + 1, comparatorDoubleArray(k));
    int mid = sIdx + (eIdx - sIdx + 1) / 2;
    KDTreeNode cur = new KDTreeNode(points[mid]);
    if (sIdx < eIdx) {
      int dimension = points[0].length;
      cur.left = buildTree(points, sIdx, mid - 1, (k + 1) % dimension);
      cur.right = buildTree(points, mid + 1, eIdx, (k + 1) % dimension);
    }
    return cur;
  }

  double[] point;
  KDTreeNode left, right;

  public KDTreeNode(double[] point) {
    this.point = point;
  }

  @Override
  public String toString() {
    if (point == null) return null;
    return Arrays.toString(point) + "\n" + left + "\n" + right;
  }
}
