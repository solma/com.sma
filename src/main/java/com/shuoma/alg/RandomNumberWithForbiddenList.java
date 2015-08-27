package com.shuoma.alg;

import static com.shuoma.annotation.Tag.Algorithm.RandomT;
import static com.shuoma.annotation.Tag.DataStructure.Array;
import static com.shuoma.annotation.Tag.Reference.Interview;
import static com.shuoma.util.RandomUtil.r;

import com.shuoma.annotation.Tag;

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
