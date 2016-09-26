/**
 Given a non-negative integer num represented as a string,
 remove k digits from the number so that the new number is the smallest possible.

 Note:
 The length of num is less than 10002 and will be >= k.
 The given num does not contain any leading zero.
 Example 1:

 Input: num = "1432219", k = 3
 Output: "1219"
 Explanation: Remove the three digits 4, 3, and 2 to form the new number 1219 which is the smallest.
 Example 2:

 Input: num = "10200", k = 1
 Output: "200"
 Explanation: Remove the leading 1 and the number is 200. Note that the output must not contain leading zeroes.
 Example 3:

 Input: num = "10", k = 2
 Output: "0"
 Explanation: Remove all the digits from the number and it is left with nothing which is 0.
 */
package com.sma.alg;

import com.sma.annotation.Tag;

import static com.sma.annotation.Tag.Algorithm.Arithmetic;
import static com.sma.annotation.Tag.Algorithm.Greedy;
import static com.sma.annotation.Tag.DataStructure.MonotonicSequence;
import static com.sma.annotation.Tag.DataStructure.StackT;
import static com.sma.annotation.Tag.Reference.Interview;
import static com.sma.annotation.Tag.Reference.LeetCode;
import static com.sma.annotation.Tag.Reference.WeChat;
import static com.sma.util.StringUtil.removePaddingCharacters;


@Tag(algs = {Arithmetic, Greedy}, dss = {MonotonicSequence, StackT},
    references = {Interview, WeChat, LeetCode})

public class RemoveKDigits {
  String remove(String num, int k) {
    StringBuilder sb = new StringBuilder(num);

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

    if (sb.length() == 0 || sb.toString().equals("0")) { return "0"; }
    else { return removePaddingCharacters(sb.toString(), '0', true); }
  }
}
