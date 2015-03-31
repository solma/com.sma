package com.shuoma;

import static com.shuoma.annotation.Tag.Algorithm.BinarySearch;
import static com.shuoma.annotation.Tag.DataStructure.Array;
import static com.shuoma.annotation.Tag.Source.LeetCode;

import com.shuoma.annotation.Tag;

@Tag(algs = BinarySearch, dss = Array, source = LeetCode)
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
