package com.shuoma.alg.number;



// given a number n remove k digits such that the resulting number is minimized
// source: wechat

public class RemoveToProduceSmallestNumber {
  public static void main(String[] args) {
//    int n = Integer.parseInt(args[0]), k = Integer.parseInt(args[1]);
    int n = 198124, k = 3;
    remove(n, k);
  }

  static void remove(int n, int k) {
    // put n into an array
    char[] nArray = String.valueOf(n).toCharArray();

    // find/marking k digits to be removed
    int cntRemovedDigits = 0, idx = 0, preIdx = 0;
    char toBeRemoved = '+'; // any char before '0'
    while (cntRemovedDigits < k && idx < nArray.length - 1) {
      System.out.println(new String(nArray) + " " + preIdx + " " + idx);
      while (nArray[preIdx++] == toBeRemoved);
      if (nArray[idx] > nArray[idx + 1]) {
        cntRemovedDigits += 1;
        nArray[idx] = toBeRemoved;
        if (preIdx > 0) {
          preIdx--;
        }
      } else {
        idx++;
      }
    }

    // remove marked digits in place
    int storeIdx = 0;
    for (int i = 0; i < nArray.length; i++) {
      if (nArray[i] != toBeRemoved)
        nArray[storeIdx++] = nArray[i];
    }
    System.out.println(new String(nArray).substring(0, nArray.length - k));
  }
}
