package com.shuoma.alg;

import static com.shuoma.annotation.Tag.Algorithm.BinarySearch;
import static com.shuoma.annotation.Tag.Algorithm.Searching;
import static com.shuoma.annotation.Tag.DataStructure.Array;
import static com.shuoma.annotation.Tag.Reference.LeetCode;

import com.shuoma.annotation.Tag;

@Tag(algs = {BinarySearch, Searching}, dss = {Array}, references = LeetCode)
public class Searching {

  public enum SearchingAlgorithm {
    BIN_FIRST_SORTED,
    BIN_FIRST_SORTED_1,
    BIN_LAST_SORTED,
    BIN_ROTATED,
    LINEAR_FIRST,
    LINEAR_LAST,
    ;
  }

  public static int binarySearchFirstAppear(int[] a, int key) {
    int l = -1, r = a.length, m;
    while (l + 1 < r) {
      m = l + (r - l) / 2;
      if (a[m] < key) l = m;
      else r = m;
    }
    if (r >= a.length || a[r] != key) return -1; // this is the tricky line
    return r;
  }

  // this is not O(nlogn) worst case is O(n)
  public static int binarySearchFirstAppear1(int[] a, int key) {
    int l = 0, r = a.length - 1, m;
    while (l <= r) {
      m = l + ((r - l) >>> 1);
      if (a[m] == key) {
        while (m >= 0 && a[m] == key) m--; // linear backup
        return ++m;
      }
      if (a[m] < key) l = m + 1;
      else r = m - 1;
      // if (low == mid && high == mid) break;
    }
    return -1;
  }

  public static int binarySearchLastAppear(int[] a, int key) {
    int l = -1, r = a.length, m;
    while (l + 1 < r) {
      m = l + (r - l) / 2;
      if (a[m] > key)
        r = m;
      else
        l = m;
    }
    if (l < 0 || a[l] != key) l = -1;// this is the tricky line
    return l;
  }

  public static int findMinElementInRotatedArrayBinary(int[] array) {
    int l = 0;
    int r = array.length - 1;
    while (l < r) {
      while (l < r && array[l] == array[r]) r--;
      if (l >= r) break;

      int mid = l + (r - l) / 2;
      if (array[mid] > array[mid + 1])
        return mid + 1;
        // or A[mid] >= A[low] (= is necessary for dups, e.g. [3,3,3,1])
      else if (array[mid] > array[r])
        l = mid + 1;
      else
        r = mid;
    }
    return 0;
  }

  public static int findMinElementInRotatedArrayLinear(int[] array) {
    int pivot = array[0];
    int ret = 0;
    int n = array.length;
    for (int i = 0; i < n; i++)
      if (array[i] <= array[(i + 1) % n] && array[i] <= array[(i - 1 + n) % n]) {
        if (array[i] < pivot) {
          ret = i;
          pivot = array[i];
        }
      }
    return ret;
  }

  public static int linearSearchFirstAppear(int[] a, int key) {
    for (int i = 0; i < a.length; i++) {
      if (a[i] == key) return i;
    }
    return -1;
  }

  public static int linearSearchLastAppear(int[] a, int key) {
    for (int i = a.length - 1; i >= 0; i--) {
      if (a[i] == key) return i;
    }
    return -1;
  }

  public static int searchInARotatedArray(int[] array, int key) {
    int n = array.length;
    int l = 0;
    int r = n - 1;

    while (l <= r) {
      int m = l + ((r - l) / 2);
      if (array[m] == key) return m;

      if (array[l] < array[m]) {
        // the left half is sorted
        if (array[l] <= key && key < array[m])
          r = m - 1;
        else
          l = m + 1;
      }
      else {
        // the right half is sorted
        if (array[l] > array[m]) {
          if (array[m] < key && key <= array[r])
            l = m + 1;
          else
            r = m - 1;
        } else {// A[L]==A[M]
          // L=M;
          l++;
        }
      }
    }
    return -1;
  }
}
