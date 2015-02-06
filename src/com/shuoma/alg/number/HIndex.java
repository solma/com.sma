package com.shuoma.alg.number;

import com.shuoma.util.ArrayUtil;

import java.util.LinkedList;
import java.util.List;

/**
 * Given an array, find its H-Index, i.e. the largest number H such that at least H
 * numbers in array is no smaller than H.
 */
public class HIndex {
  public static void main(String[] args) {
    List<int[]> citations = new LinkedList<>();
    citations.add(new int[] {3, 2, 5}); //2
    citations.add(new int[]{4,2,3,5}); //3
    citations.add(new int[] {5, 2, 4, 6}); //3
    citations.add(new int[] {8, 6, 7, 5}); //4
    citations.add(new int[] {9, 7, 6, 8}); //4
    citations.add(new int[] {10, 7, 9, 8}); //4
    citations.add(new int[]{3, 13, 2, 3, 33}); //3
    for (int[] profile : citations) {
      System.out.println((new HIndex(profile)).hIndex());
    }
  }

  private int[] citations;

  HIndex(int[] arr) {
    this.citations = arr;
  }

  /*
     O(n)
     1) find the number X in array such that X is the smallest number
     that given its index in the sorted array, X > citations.length - idx
     and Y is the largest number satisfies the criteria
     return Math.max(citations.length - idx, Y);
  */
  int hIndex() {
    int l = 0, r = citations.length - 1;
    while (l < r) {
      int partition = partition(l, r);
      //System.out.println(partition + Arrays.toString(citations));
      if (citations[partition] <= citations.length - partition) {
        l = partition + 1;
        continue;
      }
      r = partition;
    }
    return Math.max(citations.length - r, l > 0 ? citations[l - 1] : 0);
  }

  int partition(int l, int r) {
    int pivot = citations[l];
    for (int i = l; i <= r; ) {
      if (citations[i] == pivot) {
        i++;
        continue;
      }
      if (citations[i] < pivot) {
        ArrayUtil.swap(citations, i++, l++);
        continue;
      }
      ArrayUtil.swap(citations, i, r--);
    }
    return r;
  }
}
