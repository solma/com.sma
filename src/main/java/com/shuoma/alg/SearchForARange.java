package com.shuoma.alg;

import static com.shuoma.annotation.Tag.Algorithm.BinarySearch;
import static com.shuoma.annotation.Tag.DataStructure.Array;
import static com.shuoma.annotation.Tag.Reference.LeetCode;

import com.shuoma.annotation.Tag;

import java.util.*;

@Tag(algs = BinarySearch, dss = Array, references = LeetCode)
public class SearchForARange {
  //second pass
  public int[] searchRange(int[] A, int target) {
    int[] ret = {-1, -1};

    int n = A.length;
    if (n < 1)
      return ret;

    //find the lower bound of the range
    int l = -1, r = n, m;
    while (l + 1 != r) {
      m = l + (r - l) / 2;
      if (A[m] < target)
        l = m;
      else
        r = m;
    }
    if (r != n && A[r] == target)
      ret[0] = r;

    //find the upper bound of the range
    l = -1;
    r = n;
    while (l + 1 != r) {
      m = l + (r - l) / 2;
      if (A[m] > target)
        r = m;
      else
        l = m;
    }
    if (l != -1 && A[l] == target)
      ret[1] = l;
    return ret;
  }

  //first pass
  public int[] searchRangeNotLogN(int[] A, int target) {
    int low = 0, high = A.length - 1, med = -1;
    while (low <= high) {
      med = low + (high - low) / 2;
      if (A[med] == target)
        break;
      else {
        if (A[med] > target)
          high = med - 1;
        else
          low = med + 1;
      }
    }
    if (low > high)
      return new int[] {-1, -1};
    else {
      int tmp = med;
      while (med >= 0 && A[med] == target)
        med -= 1;
      int[] ret = new int[2];
      ret[0] = med + 1;
      med = tmp;
      while (med < A.length && A[med] == target)
        med += 1;
      ret[1] = med - 1;
      return ret;
    }
  }

  public int[] searchRangeLogN(int[] A, int target) {
    int left, right, middle;
    //find the first element equals to target
    left = -1;
    right = A.length;
    while (left + 1 != right) {
      middle = left + (right - left) / 2;
      if (A[middle] < target)
        left = middle;
      else
        right = middle;
    }
    if (right >= A.length || A[right] != target)
      right = -1;
    if (right == -1)
      return new int[] {-1, -1};
    int[] ret = new int[2];
    ret[0] = right;

    //find the last element equals to target
    left = -1;
    right = A.length;
    while (left + 1 != right) {
      middle = left + (right - left) / 2;
      if (A[middle] > target)
        right = middle;
      else
        left = middle;
    }
    //if (left <=-1 || A[left] != target)  left = -1;
    ret[1] = left;
    return ret;
  }


  int BestBinarySearch(int array[], int n, int v) {
    int left, right, middle;
    left = -1;
    right = n;
    while (left + 1 != right) {
      middle = left + (right - left) / 2;
      if (array[middle] < v)
        left = middle;
      else
        right = middle;
    }
    if (right >= n || array[right] != v)
      right = -1;
    return right;
  }


  public static void main(String[] args) {

    new SearchForARange().main();
  }

  public void main() {
    int A[] = new int[] {2, 2, 2};
    Arrays.sort(A);
    System.out.println(Arrays.toString(searchRangeLogN(A, 2)));
  }
}
