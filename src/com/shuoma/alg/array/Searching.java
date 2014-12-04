package com.shuoma.alg.array;

import com.shuoma.helper.CommonUtils;

import java.util.Arrays;
import java.util.Random;

public class Searching {

  public static void main(String[] args) {
    new Searching().main();
  }

  // TODO main method
  public void main() {
    // testSearchAlgorithms();

    int[] rotatatedArray;
    for (int i = 0; i < 10000; i++) {
      rotatatedArray = generateRandomRotateArray();
      // rotatatedArray=new int[]{951, 983, -669, -647, -517, -290, 99, 99, 122, 357, 731};
      int idx = findPivotBinary(rotatatedArray);
      int idxGT = findPivotLinear(rotatatedArray);
      if (idx != idxGT) {
        System.out.println(Arrays.toString(rotatatedArray) + " idx=" + idx + "  idxGT=" + idxGT);
      }
    }
  }

  static Random r = new Random();

  public int[] generateRandomRotateArray() {
    int[] array = CommonUtils.genRandomArrayWithMinSize(2);
    Arrays.sort(array);
    int n = array.length;
    int rotatedIdx = r.nextInt(n);
    // System.out.println("rotated idx="+(n-rotatedIdx)%n );
    return CommonUtils.rotate(array, rotatedIdx);
  }

  int searchInARotatedArray(int[] array, int key) {
    int N = array.length;
    int L = 0;
    int R = N - 1;

    while (L <= R) {
      int M = L + ((R - L) / 2);
      if (array[M] == key) return M;

      // the left half is sorted
      if (array[L] < array[M]) {
        if (array[L] <= key && key < array[M])
          R = M - 1;
        else
          L = M + 1;
      }
      // the right half is sorted
      else {
        if (array[L] > array[M]) {
          if (array[M] < key && key <= array[R])
            L = M + 1;
          else
            R = M - 1;
        } else {// A[L]==A[M]
          // L=M;
          L++;
        }

      }
    }
    return -1;
  }

  public int findPivotBinary(int[] array) {
    int l = 0, r = array.length - 1, m;

    // need to start with array[l] > array[r]
    while (r >= 0 && array[r] == array[l])
      r--;

    if (r >= 0) {
      while (array[l] > array[r]) {
        m = l + (r - l) / 2;
        if (array[m] > array[r]) {
          l = m + 1;
        } else {
          if (array[m] < array[r])
            r = m;
          else
            l++;// m==r
        }
      }
    }
    return l;
  }

  public int findPivotLinear(int[] array) {
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


  public enum SearchingAlgorithm {
    BIN_FIRST_SORTED, BIN_LAST_SORTED, LINEAR_FIRST, LINEAR_LAST, BIN_ROTATED
  }

  void testSearchAlgorithms() {
    SearchingAlgorithm[] algs = {SearchingAlgorithm.BIN_ROTATED};
    for (SearchingAlgorithm sortAlgorithmChoice : algs) {
      for (int i = 0; i < 10000; i++) {
        // generate array
        int[] a;

        switch (sortAlgorithmChoice) {
          case BIN_ROTATED:
            a = generateRandomRotateArray();
            break;
          default:
            a = CommonUtils.genRandomArrayWithMinSize(10);
            Arrays.sort(a);
            break;
        }
        int[] cpy = Arrays.copyOf(a, a.length);

        // generate a search key
        int rand = r.nextInt((int) (a.length * 1.2));
        int key;
        if (rand >= 0 && rand < cpy.length)
          key = cpy[rand];// select a key out of the array
        else
          key = rand; // some rand number

        // search for the key
        int idx = -1;
        switch (sortAlgorithmChoice) {
          case BIN_FIRST_SORTED:
            idx = binarySearchFirstAppear(cpy, key);
            if (idx != linearSearchFirstAppear(cpy, key))
              System.out.println(Arrays.toString(cpy) + " " + key + " " + idx);
            break;
          case BIN_LAST_SORTED:
            idx = binarySearchLastAppear(cpy, key);
            if (idx != linearSearchLastAppear(cpy, key))
              System.out.println(Arrays.toString(cpy) + " " + key + " " + idx);
            break;
          case BIN_ROTATED:
            idx = searchInARotatedArray(cpy, key);
            if (idx != linearSearchLastAppear(cpy, key) && idx != linearSearchFirstAppear(cpy, key))
              System.out.println(Arrays.toString(cpy) + " " + key + " " + idx);
            break;
          default:
            break;
        }
      }
    }
  }

  int linearSearchFirstAppear(int[] a, int key) {
    for (int i = 0; i < a.length; i++) {
      if (a[i] == key) return i;
    }
    return -1;
  }

  int binarySearchFirstAppear(int[] a, int key) {
    // return the first appearance
    int low = -1, high = a.length, med;
    while (low + 1 != high) {
      med = low + (high - low) / 2;
      // System.out.println("high=" + high + ", low=" + low + ", med=" +
      // med);
      if (a[med] < key)
        low = med;
      else
        high = med;
    }
    if (high >= a.length || a[high] != key) return high = -1; // this is the tricky line
    return high;
  }

  int linearSearchLastAppear(int[] a, int key) {
    for (int i = a.length - 1; i >= 0; i--) {
      if (a[i] == key) return i;
    }
    return -1;
  }

  int binarySearchLastAppear(int[] a, int key) {
    // return the first appearance
    int low = -1, high = a.length, med;
    while (low + 1 != high) {
      med = low + (high - low) / 2;
      // System.out.println("high=" + high + ", low=" + low + ", med=" +
      // med);
      if (a[med] > key)
        high = med;
      else
        low = med;
    }
    if (low < 0 || a[low] != key) low = -1;// this is the tricky line
    return low;
  }

}
