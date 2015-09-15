package com.shuoma.alg;

import static com.shuoma.annotation.Tag.Algorithm.BinarySearch;
import static com.shuoma.annotation.Tag.DataStructure.Array;
import static com.shuoma.annotation.Tag.Reference.LeetCode;

import com.shuoma.annotation.Tag;

/**
 * You are a product manager and currently leading a team to develop a new product. Unfortunately, the latest version of your product fails the quality check. Since each version is developed based on the previous version, all the versions after a bad version are also bad.
 * Suppose you have n versions [1, 2, ..., n] and you want to find out the first bad one, which causes all the following ones to be bad.
 * You are given an API bool isBadVersion(version) which will return whether version is bad. Implement a function to find the first bad version. You should minimize the number of calls to the API.
 */
@Tag(algs = BinarySearch, dss = Array, references = LeetCode)
public class FirstBadVersion {
  public int firstBadVersion(int n) {
    int l = -1, r = n + 1;
    while (l + 1 != r) {
      int m = l + ((r - l) >>> 1);
      if (!isBadVersion(m)) { l = m; }
      else { r = m; }
    }
    return r > n ? -1 : r;
  }

  /* The isBadVersion API is defined in the parent class VersionControl. */
  boolean isBadVersion(int version) {
    return true;
  }
}
