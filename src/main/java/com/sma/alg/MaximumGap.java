package com.sma.alg;

import com.sma.annotation.Tag;

import java.util.Arrays;

import static com.sma.annotation.Tag.DataStructure.Hash;
import static com.sma.annotation.Tag.Difficulty.D2;
import static com.sma.annotation.Tag.Reference.LeetCode;

@Tag(dl = D2, dss = Hash, references = LeetCode)
/**
 * Given an unsorted array, find the maximum gap between two numbers that are
 * neighbors if the array is sorted.
 */
public class MaximumGap {

  int maximumGap(int[] num) {
    if (num == null || num.length < 2) { return 0; }
    int n = num.length;

    // first pass to get min and max
    int min = Integer.MAX_VALUE, max = Integer.MIN_VALUE;
    for (int i = 0; i < n; i++) {
      min = Math.min(min, num[i]);
      max = Math.max(max, num[i]);
    }

    // second pass: bucketize and get the max and min in each bucket
    // maximum gap at least = (max - min) / n - 1, otherwise contradicts that the array has a range of [min, max], so we only need to check the diff btw minOfCurBucket and maxOfPrevBucket
    int bucketGap = (int) Math.ceil((max - min + .0) / (n - 1));
    int[] maxB = new int[n];
    int[] minB = new int[n];
    Arrays.fill(maxB, Integer.MIN_VALUE);
    Arrays.fill(minB, Integer.MAX_VALUE);

    for (int e : num) {
      int bIdx = (e - min) / bucketGap;
      maxB[bIdx] = Math.max(maxB[bIdx], e);
      minB[bIdx] = Math.min(minB[bIdx], e);
    }
    //System.out.println(Arrays.toString(maxB));
    //System.out.println(Arrays.toString(minB));

    // third pass: calculate the maxGap
    int maxGap = 0, prev = min;
    for (int i = 0; i < n; i++) {
      // the bucket is unmodified, i.e. no element falls in this bucket
      // minB[i] and maxB[i] are always changed together
      if (minB[i] == Integer.MAX_VALUE && maxB[i] == Integer.MIN_VALUE) { continue; }
      maxGap = Math.max(maxGap, minB[i] - prev);
      prev = maxB[i];
    }
    return maxGap;
  }
}
