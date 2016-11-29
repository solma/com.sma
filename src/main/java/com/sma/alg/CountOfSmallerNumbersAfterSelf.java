package com.sma.alg;

import com.sma.annotation.Tag;
import com.sma.util.ArrayUtil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.sma.annotation.Tag.Algorithm.DivideConquer;
import static com.sma.annotation.Tag.DataStructure.Array;
import static com.sma.annotation.Tag.DataStructure.BinarySearchTree;
import static com.sma.annotation.Tag.Reference.LeetCode;

/**
 *  Compute the inversion array:
 *  having a int array A[], generate another int array B[], B[i] is the count of elements
 *  in A[i+1] ~ A[n-1] which is smaller than A[i]. Time complexity: O(nlgn)
 */

@Tag(algs = {DivideConquer}, dss = {Array, BinarySearchTree}, references = {LeetCode})
public class CountOfSmallerNumbersAfterSelf {
  public static void main(String[] args) {
    int[] arr = {1, 4, 3, 2};
    CounterInversionDivideConquer ins = new CounterInversionDivideConquer();
    System.out.println(ins.count(arr));
  }

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

class CounterInversionDivideConquer {
  int count(int[] arr) {
    return count(arr, 0 , arr.length - 1);
  }

  private int count(int[] arr, int s, int e) {
    assert(s <= e);
    if (s == e) { return 0; }
    int m = (s + e) >> 1;
    int inversion;
    inversion = count(arr, s, m);
    if (m < e) {
      inversion += count(arr, m + 1, e) + merge(arr, s, m, e);
//      System.out.println(s + " " + m + " " + e + " " + inversion);
    }
    return inversion;
  }

  private int merge(int[] arr, int s, int m, int e) {
    // merge [s, m] and [m + 1, e] and return inversion between two subarrays
    int[] tmp = new int[e - s + 1];
    int i = s, j = m + 1, k = 0, inversion = 0;
    while (i <= m && j <= e) {
      if (arr[i] <= arr[j]) {
        tmp[k++] = arr[i++];
      } else {
        tmp[k++] = arr[j++];
        inversion += m - i + 1;
      }
    }
    if (i == m + 1) {
      for (; j <= e; j++) {
        tmp[k++] = arr[j];
      }
    } else {
      for (; i <= m; i++) {
        tmp[k++] = arr[i];
      }
    }
    System.arraycopy(tmp, 0, arr, s, tmp.length);
    return inversion;
  }
}
