package com.shuoma.alg;

// given a number n remove1 k digits such that the resulting number is minimized
// source: wechat

import static com.shuoma.annotation.Tag.Algorithm.Arithmetic;
import static com.shuoma.annotation.Tag.Algorithm.Greedy;

import com.shuoma.annotation.Tag;

@Tag(algs = {Arithmetic, Greedy})
public class RemoveToProduceSmallestNumber {

  int remove(int n, int k) {
    StringBuilder sb = new StringBuilder(String.valueOf(n));

    int nRemovedDigits = 0;
    while (nRemovedDigits < k) {
      int i;
      for (i = 0; i < sb.length() - 1; i++) {
        if (sb.charAt(i) > sb.charAt(i + 1)) {
          break;
        }
      }
      sb.deleteCharAt(i);
      nRemovedDigits++;
    }
    return sb.length() == 0 ? 0 : Integer.parseInt(sb.toString());
  }


  int remove1(int n, int k) {
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


}
