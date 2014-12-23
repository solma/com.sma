package com.shuoma.alg.number;

// source: http://www.careercup.com/question?id=4669539153346560
import java.util.Arrays;

// elements-of-programming-interviews P6.13

/*
 * The input is a sequence x1,x2,...,xn of integers in an arbitrary order, and another sequence
 * a1,a2,..,an of distinct integers from 1 to n (namely a1,a2,...,an is a permutation of 1, 2,...,
 * n). Both sequences are given as arrays. Design an 0(n logn) algorithm to order the first sequence
 * according to the order imposed by the permutation. In other words, for each i, Xi should appear
 * in the position given in ai. For example, if x = 17, 5, 1,9, and a = 3, 2, 4, 1, then the outcome
 * should be x = 9, 5, 17, 1. The algorithm should be in-place, so you cannot use an additional
 * array.
 */


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

      System.out.println("i=" + i);
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
          System.out.println("\tcurIdx=" + curIdx + " " + Arrays.toString(A));
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

  // place arr[i] at index[i]
  public static void main(String[] args) {

    // 14, 27, 3, 2, 7, 21
    int[] arr = {12, 5, 13, 17};
    // 4, 7, 3, 0, 8, 2, 5, 9, 6, 1
    int[] index = {2, 0, 1, 3};
    System.out.println("Values: " + Arrays.toString(arr));
    System.out.println(" Index: " + Arrays.toString(index));

    int[] cpy = Arrays.copyOf(arr, arr.length);
    permuteConstantSpace(index, cpy);
    System.out.println("Values: " + Arrays.toString(cpy));

    cpy = Arrays.copyOf(arr, arr.length);
    // permuteLinearSpace(index, cpy);
    // System.out.println("Values: " + Arrays.toString(cpy));
  }
}
