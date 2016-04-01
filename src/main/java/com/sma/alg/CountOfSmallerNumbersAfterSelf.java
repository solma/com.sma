package com.sma.alg;

import com.sma.annotation.Tag;
import com.sma.util.ArrayUtil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.sma.annotation.Tag.DataStructure.Array;
import static com.sma.annotation.Tag.DataStructure.BinarySearchTree;
import static com.sma.annotation.Tag.Reference.LeetCode;

/**
 *  Compute the inversion array:
 *  having a int array A[], generate another int array B[], B[i] is the count of elements
 *  in A[i+1] ~ A[n-1] which is smaller than A[i]. Time complexity: O(nlgn)
 */

@Tag(dss = {Array, BinarySearchTree}, references = {LeetCode})
public class CountOfSmallerNumbersAfterSelf {
  int[] countInversion(int[] arr) {
    BSTWithSize bst = new BSTWithSize(arr);
    return ArrayUtil.integerListToIntArray(bst.result);
  }

  public static class BSTWithSize {
    List<Integer> result;
    BSTNodeWithSize root;

    public BSTWithSize(int[] keys) {
      result = new ArrayList<>();
      // build it reversely
      for (int i : ArrayUtil.reverse(Arrays.copyOf(keys, keys.length))) {
        root = insert(root, i, 0);
      }
    }

    BSTNodeWithSize insert(BSTNodeWithSize cur, int key, int smallerNodeCnt) {
      if (cur == null) return new BSTNodeWithSize(key, smallerNodeCnt, result);
      if (cur.value >= key) cur.left = insert(cur.left, key, smallerNodeCnt);
      else cur.right = insert(cur.right, key, smallerNodeCnt + ((cur.left == null) ? 0 : cur.left.size) + 1);
      cur.size++;
      return cur;
    }
  }

  public static class BSTNodeWithSize {
    int value;
    int size;
    int smallerItemCnt;
    BSTNodeWithSize left, right;

    public BSTNodeWithSize(int value, int smallerNodeCnt, List<Integer> res) {
      this.value = value;
      this.smallerItemCnt = smallerNodeCnt;
      this.size = 1;
      res.add(0, smallerNodeCnt);
    }

    @Override
    public String toString() {
      return String.valueOf(value);
    }
  }
}
