package com.shuoma.alg;

import static com.shuoma.annotation.Tag.DataStructure.Array;
import static com.shuoma.annotation.Tag.DataStructure.HashTable;
import static com.shuoma.annotation.Tag.Trick.InplaceSwap;

import com.shuoma.annotation.Tag;
import com.shuoma.util.ArrayUtil;
import com.shuoma.util.RandomUtil;

// source: http://www.careercup.com/question?id=4669539153346560
import java.util.Arrays;

// elements-of-programming-interviews P6.13

/**
 * The input is a sequence x1,x2,...,xn of integers in an arbitrary order, and another sequence
 * a1,a2,..,an of distinct integers from 1 to n (namely a1,a2,...,an is a permutation of 1, 2,...,
 * n). Both sequences are given as arrays. Design an 0(n logn) algorithm to order the first sequence
 * according to the order imposed by the permutation. In other words, for each i, Xi should appear
 * in the position given in ai. For example, if x = 17, 5, 1,9, and a = 3, 2, 4, 1, then the outcome
 * should be x = 9, 5, 17, 1. The algorithm should be in-place, so you cannot use an additional
 * array.
 */
@Tag(dss = {Array, HashTable}, tricks = InplaceSwap)
public class InPlaceRearrangeViaShadowIndex {

  public static void permuteConstantSpace(int[] perm, int[] A) {
    for (int i = 0; i < A.length; ++i) {
      // Traverse the cycle to see if i is the min element.
      // boolean isMin = true; //i is the smallest idx in the cycle
      int j = perm[i];
      while (j != i) {
        if (j < i) {
          // isMin = false;
          break;
        }
        j = perm[j];
      }

      //System.out.println("i=" + i);
      if (i == j
      // isMin
      ) {
        int curIdx = i;
        int curValue = A[curIdx];
        do {
          int nextIdx = perm[curIdx];
          int nextValue = A[nextIdx];
          A[nextIdx] = curValue;

          curIdx = nextIdx;
          curValue = nextValue;
          //System.out.println("\tcurIdx=" + curIdx + " " + Arrays.toString(A));
        } while (curIdx != i);
      }
    }
  }

  public static void permuteLinearSpace(int[] perm, int[] A) {
    for (int i = 0; i < A.length; ++i) {
      if (perm[i] >= 0) {
        int a = i;
        int temp = A[i];
        do {
          int next_a = perm[a];
          int next_temp = A[next_a];
          A[next_a] = temp;
          // Mark a as visited by using the sign bit.
          perm[a] -= perm.length;
          a = next_a;
          temp = next_temp;
        } while (a != i);
      }
    }

    // Restore perm back.
    int size = perm.length;
    for (int i = 0; i < perm.length; i++) {
      perm[i] += size;
    }
  }

  static void arrange(int[] pos, int[] num) {
    int swapCnt = 0;
    for (int i = 0; i < num.length; ) {
      if (pos[i] == i) {
        i++;
        continue;
      }
      swapCnt++;
      ArrayUtil.swap(num, i, pos[i]);
      ArrayUtil.swap(pos, i, pos[i]);
      //System.out.println(i + " num = " + Arrays.toString(num) + " pos = " + Arrays.toString(pos));
    }
    System.out.println("swapCnt = " + swapCnt);
  }

  // place arr[i] at index[i]
  public static void main(String[] args) {

    for(int j = 0; j < 1; j++) {
      int size = RandomUtil.r.nextInt(5) + 5;
      //int[] arr = RandomUtil.genRandomArray(size, 10, false, false);
      //int[] index = ArrayUtil.getNaturalArray(size);
      //RandomUtil.shuffle(index);
      int[] arr = {0, 1, 2, 3, 4, 5, 6, 7};

      //{0, 2, 4, 6, 1, 3, 5, 7}
      int[] index = {1, 3, 5, 4, 6, 0, 7, 2};

      int[][] cpy = new int[3][];
      for(int i = 0; i < cpy.length; i++)
        cpy[i] = Arrays.copyOf(arr, arr.length);

      permuteConstantSpace(Arrays.copyOf(index, index.length), cpy[0]);
      permuteLinearSpace(Arrays.copyOf(index, index.length), cpy[1]);
      arrange(Arrays.copyOf(index, index.length), cpy[2]);
      //if (! (ArrayUtil.equals(cpy[0], cpy[1]) && ArrayUtil.equals(cpy[0], cpy[2]))) {
        System.out.println(" array: " + Arrays.toString(arr));
        System.out.println(" index: " + Arrays.toString(index));
        System.out.println(" cpy[0]: " + Arrays.toString(cpy[0]));
        System.out.println(" cpy[1]: " + Arrays.toString(cpy[1]));
        System.out.println(" cpy[2]: " + Arrays.toString(cpy[2]));
      //}
    }
  }
}
