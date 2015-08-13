package com.shuoma.alg;

import static com.shuoma.annotation.Tag.Algorithm.BinarySearch;
import static com.shuoma.annotation.Tag.DataStructure.Array;
import static com.shuoma.util.ArrayUtil.partition;

import com.shuoma.annotation.Tag;

/**
 * Given an (unsorted) array, find its H-Index, i.e. the largest number H such that
 * at least H numbers in array is no smaller than H.
 */

@Tag(algs = BinarySearch, dss = Array)
public class HIndex {

  int hIndex(int[] citations) {
    int l = -1, r = citations.length;
    while (l + 1 < r) {
      int m = l + ((r - l) >>> 1);
      int partition = partition(citations, Math.max(0, l), Math.min(r, citations.length - 1), m);
      if (citations[partition] <= citations.length - partition) {
        l = partition;
      } else { // the first element that value > citations.length - partition
        r = partition;
      }
      //System.out.println(partition + " " + Arrays.toString(citations) + " " + l + " " + r);
    }
    return Math.max(citations.length - r, r - 1 >= 0 ? citations[r - 1] : 0);
  }
}
