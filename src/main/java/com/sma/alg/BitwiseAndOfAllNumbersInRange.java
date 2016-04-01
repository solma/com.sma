package com.sma.alg;

import static com.sma.annotation.Tag.Algorithm.BitManipulation;
import static com.sma.annotation.Tag.Reference.LeetCode;

import com.sma.annotation.Tag;

/**
 Given a range [m, n] where 0 <= m <= n <= 2147483647, return the bitwise AND of all numbers
 in this range, inclusive.

 For example, given the range [5, 7], you should return 4.
 */
@Tag(algs = BitManipulation, references = LeetCode)
public class BitwiseAndOfAllNumbersInRange {

  //http://math.stackexchange.com/questions/1073532/how-to-find-bitwise-and-of-all-numbers-for-a-given-range
  public int rangeBitwiseAnd(int m, int n) {
    int xor = m ^ n;

    // find out the length of xor, i.e. the highest bit that m differs from n
    int cnt = 0;
    while (xor > 0) {
      xor >>= 1;
      cnt++;
    }
//    only retrieve higher bits that m and n share the same value
    return m & n & ~((1 << cnt) - 1);
  }
}
