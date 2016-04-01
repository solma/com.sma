package com.sma.alg;

import static com.sma.annotation.Tag.Algorithm.RandomT;
import static com.sma.annotation.Tag.DataStructure.Array;
import static com.sma.annotation.Tag.Reference.Interview;
import static com.sma.util.RandomUtil.r;

import com.sma.annotation.Tag;

import java.util.Arrays;

/**
 * Given N and a forbidden list with numbers 0 ~ n-1,
 * generate a random number that is not in forbidden list
 */
@Tag(algs = RandomT, dss = Array, references = Interview)
public class RandomNumberWithForbiddenList {

  int randomWithForbidden(int N, int[] forbidden) {
    int fl = forbidden.length;
    int idx = r.nextInt(N - fl);
    int searchIdx = Arrays.binarySearch(forbidden, idx);
    int noOfForbiddenNumbersNotGreaterThanIdx;
    if (searchIdx < 0) {
      noOfForbiddenNumbersNotGreaterThanIdx = ~searchIdx;
    } else {
      noOfForbiddenNumbersNotGreaterThanIdx = searchIdx + 1;
    }
    return idx + noOfForbiddenNumbersNotGreaterThanIdx;
  }
}
