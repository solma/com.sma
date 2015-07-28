package com.shuoma.ds.graph.tree;

import static com.shuoma.annotation.Tag.DataStructure.BTree;
import static com.shuoma.annotation.Tag.Reference.Interview;

import com.shuoma.annotation.Tag;
import com.shuoma.util.ArrayUtil;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/** BTree node. */
@Tag(dss = BTree, references = Interview)
public class BTreeNode {
  List<Integer> values;
  List<BTreeNode> children;

  public BTreeNode(List<Integer> values) {
    this.values = values;
    children = new LinkedList<>();
  }

  // build a BTreeNode recursively
  public static BTreeNode buildBTree(int[][] values) {
    List<BTreeNode> queue = new LinkedList<>();
    BTreeNode cur;
    for (int[] node : values) {
      cur = new BTreeNode(ArrayUtil.intArrayToIntegerList(node).subList(1, node.length));
      queue.add(cur);
      // first element indicates the parent
      if (node[0] == -1) continue;
      queue.get(node[0]).children.add(cur);
      }
    return queue.get(0);
  }

  /** Check if a BTree is a valid one. */
  public boolean isValid() {
    return isValid(Arrays.asList(Integer.MIN_VALUE, Integer.MAX_VALUE));
  }

  boolean isValid(List<Integer> range) {
    int n = values.size();
    // values should be sorted
    for (int i = 0; i < n; i++) {
      if (i == 0) {
        if (values.get(i) < range.get(0)) return false;
        continue;
      }
      if (i == n - 1) {
        if (values.get(i) > range.get(1) || values.get(i) < values.get(i - 1)) return false;
        continue;
      }
      if (values.get(i) < values.get(i - 1)) return false;
    }

    if (children.isEmpty()) return true;

    // subtrees should be valid
    if (children.size() != values.size() + 1) return false;
    n = children.size();
    for (int i = 0; i < n; i++) {
      if (i == 0) {
        if (!children.get(i).isValid(Arrays.asList(range.get(0), values.get(i) - 1))) {
          return false;
        }
        continue;
      }
      if (i == n - 1) {
        if (!children.get(i).isValid(Arrays.asList(values.get(i - 1) + 1, range.get(1)))) {
          return false;
        }
        continue;
      }
      if (!children.get(i).isValid(Arrays.asList(values.get(i - 1) + 1, values.get(i) - 1))) {
        return false;
      }
    }
    return true;
  }
}
