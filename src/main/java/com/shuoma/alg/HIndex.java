package com.shuoma.alg;

import static com.shuoma.annotation.Tag.Algorithm.BinarySearch;
import static com.shuoma.annotation.Tag.DataStructure.Array;
import static com.shuoma.annotation.Tag.Reference.LeetCode;
import static com.shuoma.util.ArrayUtil.partition;

import com.shuoma.annotation.Tag;

import java.util.Arrays;

/**
 * Given an (unsorted) array, find its H-Index, i.e. the largest number H such that
 * at least H numbers in array is no smaller than H.
 */

@Tag(algs = BinarySearch, dss = Array, references = LeetCode)
public class HIndex {

  // O(n)
  int hIndex(int[] citations) {
    int n = citations.length;
    int l = 0, r = n - 1;
    int firstElement = n;
    while (l <= r) {
      int partition = partition(citations, Math.max(0, l), Math.min(r, n - 1), l + (r - l) / 2);
      if (citations[partition] <= n - partition) {
        l = partition + (l == partition ? 1 : 0);
      } else { // the first element that value > citations.length - partition
        firstElement = partition;
        r = partition + (r == partition ? -1 : 0);
      }
      //System.out.println(partition + " " + firstElement + " " + Arrays.toString(citations) + " " + l + " " + r);
    }

    return Math.max(n - firstElement, firstElement > 0 ? citations[firstElement - 1] : 0);
  }

  // citations are sorted
  // O(logn)
  int hIndex1(int[] citations) {
    Arrays.sort(citations);

    int n = citations.length;
    int l = 0, r = n - 1;
    int firstElement = n;
    while (l <= r) {
      int m = l + (r - l) / 2;
      if (citations[m] <= n - m) {
        l = m + (l == m ? 1 : 0);
      } else { // the first element that value > citations.length - partition
        firstElement = m;
        r = m + (r == m ? -1 : 0);
      }
      //System.out.println(partition + " " + firstElement + " " + Arrays.toString(citations) + " " + l + " " + r);
    }

    return Math.max(n - firstElement, firstElement > 0 ? citations[firstElement - 1] : 0);
  }
}
