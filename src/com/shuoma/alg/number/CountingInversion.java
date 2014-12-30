package com.shuoma.alg.number;

import com.shuoma.util.ArrayUtil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


// Having a int array A[], generate another int array B[], B[i] is the count of elements in A[i+1] ~
// A[n-1] which
// is smaller than A[i]. Time complexity: O(nlgn)
public class CountingInversion {
  public static void main(String[] args) {
    for (int i = 0; i < 1; i++) {
      int [] arr = {3, 4, 0, 2, 1};
      // int[] arr = RandomUtil.shuffle(ArrayUtil.getNaturalArray(RandomUtil.r.nextInt(6) + 5));
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
      for (int i : ArrayUtil.reverse(keys)) {
        insert(root, i, 0);
      }
    }

    void insert(BSTNodeWithSize cur, int key, int smallerNodeCnt) {
      if (cur == null) {
        root = new BSTNodeWithSize(key, smallerNodeCnt, result);
      } else {
        if (cur.value >= key) {
          if (cur.left != null) {
            insert(cur.left, key, smallerNodeCnt);
          } else {
            cur.left = new BSTNodeWithSize(key, smallerNodeCnt, result);
          }
          cur.leftSize++;
        } else {
          if (cur.right != null) {
            insert(cur.right, key, smallerNodeCnt + cur.leftSize + 1);
          } else {
            cur.right = new BSTNodeWithSize(key, smallerNodeCnt + cur.leftSize + 1, result);
          }
        }
      }
    }
  }

  public static class BSTNodeWithSize {
    int value;
    int leftSize; // size of the left child tree
    int smallerItemCnt;
    BSTNodeWithSize parent, left, right;

    public BSTNodeWithSize(int value, int smallerNodeCnt, List<Integer> res) {
      this.value = value;
      this.smallerItemCnt = smallerNodeCnt;
      res.add(0, smallerNodeCnt);
    }

    @Override
    public String toString() {
      return String.valueOf(value);
    }
  }
}
