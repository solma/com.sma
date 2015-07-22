package com.shuoma.alg;

import static com.shuoma.annotation.Tag.DataStructure.Array;
import static com.shuoma.annotation.Tag.DataStructure.Hash;
import static com.shuoma.annotation.Tag.Reference.Interview;
import static com.shuoma.annotation.Tag.Trick.InplaceSwap;

import com.shuoma.annotation.Tag;
import com.shuoma.util.ArrayUtil;

// reference: http://www.careercup.com/question?id=4669539153346560

// elements-of-programming-interviews P6.13

/**
 * The intervals is a sequence x1,x2,...,xn of integers in an arbitrary order, and another sequence
 * a1,a2,..,an of distinct integers from 1 to n (namely a1,a2,...,an is a permutation of 1, 2,...,
 * n). Both sequences are given as arrays. Design an 0(n logn) algorithm to order the first sequence
 * according to the order imposed by the permutation. In other words, for each i, Xi should appear
 * in the position given in ai. For example, if x = 17, 5, 1,9, and a = 3, 2, 4, 1, then the outcome
 * should be x = 9, 5, 17, 1. The algorithm should be in-place, so you cannot use an additional
 * array.
 */
@Tag(dss = {Array, Hash}, tricks = InplaceSwap, references = {Interview})
public class InPlaceRearrangeViaShadowIndex {

  void rearrange(int[] perm, int[] A) {
    int swapCnt = 0;
    for (int i = 0; i < A.length; ) {
      if (perm[i] == i) {
        i++;
        continue;
      }
      swapCnt++;
      ArrayUtil.swap(A, i, perm[i]);
      ArrayUtil.swap(perm, i, perm[i]);
      //System.out.println(i + " num = " + Arrays.toString(num) + " pos = " + Arrays.toString(pos));
    }
    System.out.println("swapCnt = " + swapCnt);
  }

  void rearrange1(int[] perm, int[] A) {
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

  void rearrange2(int[] perm, int[] A) {
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
}
