package com.shuoma.alg;

import static com.shuoma.annotation.Tag.Algorithm.DynamicProgramming;
import static com.shuoma.annotation.Tag.DataStructure.StringT;

import com.shuoma.annotation.Tag;

import java.util.Arrays;

// You are provided with four possible operations that can be done on the editor(each operation
// requires
// 1 keyboard hit)
// 1. A
// 2. Ctrl+A
// 3. Ctrl+C
// 4. Ctrl+V
// Now you can hit the keyboard N times and you need to find the maximum number of A's that can be
// printed. Also print the sequence of keyboard hits.


// reference : http://4remembrance.blogspot.com/2013/01/longest-text-producible-in-editor.html

@Tag(algs = DynamicProgramming, dss = StringT)
public class LongestTestProducibleInEditor {
  public static void main(String[] args) {
    System.out.println(longestText(80));
  }

  static String[] OPERATIONS = {"A", "CtrA", "CtrC", "CtrV"};

  static int longestText(int n) {
    int[] res = new int[n + 1];
    int[] opt = new int[n + 1];

    for (int i = 0; i < res.length; i++) {
      res[i] = i; // all A's
      for (int j = 2; j <= i - 3; j++) { //j: block size of continuous A's
        int size = 2 * j  * (i / (j + 3)) + res[i % (j + 3)];
        if (size > res[i]) {
          res[i] = size;
          opt[i] = j;
        }
      }
    }

    System.out.println(Arrays.toString(opt));
    System.out.println(printSequence(n, res, opt));
    return res[n];
  }

  static String printSequence(int idx, int[] len, int[] opt) {
    StringBuilder sb = new StringBuilder();
    if (opt[idx] > 0) {
      sb.append("(" + opt[idx] + OPERATIONS[0] + " " + OPERATIONS[1] + "-" + OPERATIONS[2] + "-" + OPERATIONS[3] + ")x" + idx / (opt[idx] + 3) + " ");
      sb.append(printSequence(idx % (opt[idx] + 3) ,len, opt));
    } else {
      if (idx > 0)
        sb.append(len[idx] + OPERATIONS[0]);
    }
    return sb.toString();
  }
}
