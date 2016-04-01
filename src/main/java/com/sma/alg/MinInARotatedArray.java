package com.sma.alg;

import static com.sma.annotation.Tag.Algorithm.BinarySearch;
import static com.sma.annotation.Tag.DataStructure.Array;
import static com.sma.annotation.Tag.Reference.LeetCode;

import com.sma.annotation.Tag;

@Tag(algs = BinarySearch, dss = Array, references = LeetCode)
public class MinInARotatedArray {
  public static void main(String[] args) {
    System.out.println(new MinInARotatedArray().main());
  }

  public int main() {
    int[] num = {3, 4, 5, 1, 2};

    int n = num.length;
    int m, l = 0, r = n - 1;
    while (num[l] > num[r]) {
      m = l + ((r - l) >> 1);
      // equally (num[m] > num[r]) l = m+1; else r = m;
      if (num[m] < num[l])
        r = m;
      else
        l = m + 1;
    }
    return num[l];
  }
}
