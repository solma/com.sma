package com.sma.alg;
/**
 * A sequence of numbers is called a zig-zag sequence if the differences
 * between successive numbers strictly alternate between positive and negative.
 * The first difference (if one exists) may be either positive or negative.
 * A sequence with fewer than two elements is trivially a zig-zag sequence.
 * <p/>
 * For example, 1,7,4,9,2,5 is a zig-zag sequence because the differences (6,-3,5,-7,3)
 * are alternately positive and negative. In contrast, 1,4,7,2,5 and 1,7,4,5,5 are not zig-zag sequences,
 * the first because its first two differences are positive and the second because its last difference is zero.
 * <p/>
 * Given a sequence of integers, sequence, return the length of the longest subsequence of sequence
 * that is a zig-zag sequence. A subsequence is obtained by deleting some number of elements (possibly zero)
 * from the original sequence, leaving the remaining elements in their original order.
 */
import com.sma.annotation.Tag;

import java.util.Deque;
import java.util.LinkedList;

import static com.sma.annotation.Tag.Algorithm.DynamicProgramming;
import static com.sma.annotation.Tag.Complexity.Quadratic;
import static com.sma.annotation.Tag.DataStructure.Array;
import static com.sma.annotation.Tag.Reference.Topcoder;

@Tag(algs = DynamicProgramming, timecomplexity = Quadratic, dss = Array, references = Topcoder)
public class LongestZigzagSubsequence {

  public int longestZigZagSubsequence(int[] num) {
    int n = num.length;
    if (n == 0 || n == 1 || n == 2) return n;

    // subseqThatEndAtCurIndex
    int[] len = new int[n];
    int[] prev = new int[n];
    int[] prevIsLarger = new int[n]; //-1:false; 0:could be both; 1:true

    int maxSeqLen = 0;
    int maxSeqEndIdx = -1;

    for (int i = 0; i < num.length; i++) {
      len[i] = 1;
      prev[i] = -1;
      prevIsLarger[0] = 0;

      for (int j = i - 1; j >= 0; j--) {
        if ((num[i] > num[j] && prevIsLarger[j] == 1)
            || (num[i] < num[j] && prevIsLarger[j] == -1)
            || (prevIsLarger[j] == 0 && num[i] != num[j])) {
          if (len[i] < len[j] + 1) {
            len[i] = len[j] + 1;
            if (prevIsLarger[j] == 0) {
              prevIsLarger[i] = num[j] > num[i] ? 1 : -1;
            } else {
              prevIsLarger[i] = prevIsLarger[j] * -1; // flip
            }
            prev[i] = j;

            // update global max
            if (len[i] > maxSeqLen) {
              maxSeqEndIdx = i;
              maxSeqLen = len[i];
            }
          }
        }
      }
    }

    Deque<Integer> maxZigzagSeq = new LinkedList<>();
    for (int i = maxSeqEndIdx; i >= 0; i = prev[i]) {
      maxZigzagSeq.addFirst(num[i]);
    }

    //System.out.println(maxZigzagSeq);
    return maxZigzagSeq.size();
  }
}
