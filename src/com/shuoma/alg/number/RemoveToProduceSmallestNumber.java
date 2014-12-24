package com.shuoma.alg.number;

import com.shuoma.alg.recursion.Combination;
import com.shuoma.util.RandomUtil;

import java.util.Arrays;

// given a number n remove k digits such that the resulting number is minimized
// source: wechat

public class RemoveToProduceSmallestNumber {
  public static void main(String[] args) {
    int[] res = new int[2];
    for (int i = 0; i < 1000; i++) {
      int n = 123 + RandomUtil.r.nextInt(100000), k = 1 + RandomUtil.r.nextInt(3);
      //int n = 69611, k = 3;
      res[0] = remove(n, k);
      res[1] = removeBase(n, k);
      if (res[0] != res[1]) {
        System.out.println(n + ":" + k + "   " + Arrays.toString(res));
      }
    }
  }

  static int remove(int n, int k) {
    // put n into an array
    char[] nArray = String.valueOf(n).toCharArray();

    if (k >= nArray.length) return 0;

    // mark k digits to be removed
    char toBeRemoved = '+'; // value can be any char before '0'
    int curIdx = 1, preIdx = 0, hasRemoved = 0;
    while (curIdx < nArray.length && hasRemoved < k) {
      if (nArray[curIdx] < nArray[preIdx]) {
        nArray[preIdx] = toBeRemoved;
        hasRemoved++;
        if (preIdx > 0)
          while(preIdx > 0 && nArray[--preIdx] == toBeRemoved); // trace back
        else {
          preIdx = curIdx;
          curIdx++;
        }
      } else {
        while(nArray[++preIdx] == toBeRemoved);
        curIdx++;
      }
    }

    // remove marked digits in place
    int storeIdx = 0;
    for (int i = 0; i < nArray.length; i++) {
      if (nArray[i] != toBeRemoved)
        nArray[storeIdx++] = nArray[i];
    }
    return Integer.parseInt((new String(nArray).substring(0, nArray.length - k)));
  }

  static int removeBase(int n, int k) {
    String s = String.valueOf(n);
    if (k >= s.length()) return 0;
    int min = n;
    for (String com : Combination.combinationsOfSizeN(s, s.length() - k)) {
      min = Math.min(Integer.parseInt(com), min);
    }
    return min;
  }
}
