package com.shuoma.alg;

import static com.shuoma.annotation.Tag.DataStructure.Array;
import static com.shuoma.annotation.Tag.DataStructure.HashTable;
import static com.shuoma.annotation.Tag.Trick.InplaceSwap;

import com.shuoma.annotation.Tag;
import com.shuoma.util.ArrayUtil;

import java.util.Arrays;

/** Given [a1, a2, ..., an, b1, b2, ..., bn], transform it to [a1, b1, a2, b2, ..., an, bn].*/
@Tag(dss = {Array, HashTable}, tricks = InplaceSwap)
public class InplaceInterleavingArray {

  public static void main(String[] args) {
    InplaceInterleavingArray ins = new InplaceInterleavingArray();
    System.out.println(Arrays.toString(ins.interleave1(new int[] {1, 2, 3, 9, 8, 7})));
  }

  // space O(logn), time O(nlogn)
  int[] interleave1(int[] array) {
    int n = array.length / 2;
    if (n % 2 == 0) interleave1(array, 0, array.length - 1);
    else {
      // move a[n - 1] to the end
      moveToEnd(array, n);
      interleave1(array, 0, array.length - 3);
    }
    return array;
  }

  void moveToEnd(int[] array, int n) {
    int tmp = array[n - 1];
    int i;
    for (i = n; i < array.length - 1; i++) {
      array[i - 1] = array[i];
    }
    array[array.length - 2] = tmp;
  }

  void interleave1(int[] array, int low, int high) {
    if (high - low < 2) return;
    int m = swap2ndWith3rdQuarter(array, low, high);
    interleave1(array, low, m);
    interleave1(array, m + 1, high);
  }

  int swap2ndWith3rdQuarter(int[] array, int low, int high) {
    int len = high - low + 1;
    int secondQuarterStart = low + len / 4;
    int secondQuarterEnd = low + len / 2  - 1;
    int thirdQuarterEnd = high - len / 4;
    ArrayUtil.reverse(array, secondQuarterStart, thirdQuarterEnd);
    ArrayUtil.reverse(array, secondQuarterStart, secondQuarterEnd);
    ArrayUtil.reverse(array, secondQuarterEnd + 1, thirdQuarterEnd);
    return secondQuarterEnd;
    }

  private void reverse(int[] array, int low, int high){
    for(int i = 0; i < (high - low + 1)/2; i++){
      int temp = array[low + i];
      array[low + i] = array[high - i];
      array[high - i] = temp;
    }
  }

  // space O(n), time O(n)
  int[] interleave(int[] array) {
    int n = array.length;
    int[] copy = new int[n];

    for (int i = 0; i < n; i++) {
      copy[(i % (n / 2)) * 2 + (i >= (n / 2) ? 1 : 0)] = array[i];
    }

    return copy;
  }
}
