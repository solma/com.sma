package com.shuoma.alg.number;

import com.shuoma.util.ArrayUtil;
import com.shuoma.util.RandomUtil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


/*
 *  Having a int array A[], generate another int array B[], B[i] is the count of elements
 *  in A[i+1] ~ A[n-1] which is smaller than A[i]. Time complexity: O(nlgn)
 */
public class CountingInversion {
  public static void main(String[] args) {
    for (int i = 0; i < 10; i++) {
      //int [] arr = {3, 4, 0, 2, 1};
      int[] arr = RandomUtil.shuffle(ArrayUtil.getNaturalArray(RandomUtil.r.nextInt(30) + 15));
      //System.out.println(Arrays.toString(arr));
      int[][] res = new int[2][arr.length];
      res[0] = countInversion(arr);
      res[1] = countInversionBase(arr);
      if (!ArrayUtil.equals(res[0], res[1])) {
        System.out.println(Arrays.toString(arr));
        System.out.println(Arrays.toString(res[0]));
        System.out.println(Arrays.toString(res[1]));
        System.out.println();
      }
    }
  }

  public static int[] countInversion(int[] arr) {
    BSTWithSize bst = new BSTWithSize(arr);
    return ArrayUtil.integerListToIntArray(bst.result);
  }

  public static int[] countInversionBase(int[] arr) {
    int[] res = new int[arr.length];
    for (int i = 0; i < arr.length; i++) {
      for (int j = i + 1; j < arr.length; j++) {
        if (arr[j] < arr[i]) res[i]++;
      }
    }
    return res;
  }

  public static class BSTWithSize {
    List<Integer> result;
    BSTNodeWithSize root;

    public BSTWithSize(int[] keys) {
      result = new ArrayList<>();
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
    BSTNodeWithSize parent, left, right;

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
