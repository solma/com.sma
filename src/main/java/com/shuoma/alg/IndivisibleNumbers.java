package com.shuoma.alg;

import static com.shuoma.alg.Combination.allCombinationsRecursionFirstOrderBottomUp;
import static com.shuoma.annotation.Tag.Algorithm.Arithmetic;
import static com.shuoma.annotation.Tag.Algorithm.Recursion;
import static com.shuoma.annotation.Tag.Reference.Interview;
import static com.shuoma.util.CollectionsUtil.intListToStringList;
import static com.shuoma.util.CollectionsUtil.stringListToIntList;

import com.shuoma.annotation.Tag;

import java.util.List;

/**
 * Given a list of numbers, and a large number N, find # of numbers between
 * 1 ~ N that is indivisible by any number in the list.
 *
 * refers to "counting integer problem"
 * https://en.wikipedia.org/wiki/Inclusion%E2%80%93exclusion_principle
 */
@Tag(algs = {Arithmetic, Recursion}, references = Interview)
public class IndivisibleNumbers {

  long count(List<Integer> filterNumbers, long N) {
    long ret = N;
    for (List<String> comb :
        allCombinationsRecursionFirstOrderBottomUp(intListToStringList(filterNumbers))) {
      long num = combinationToLeastCommonMultiple(comb);
      ret -= N / num;
      //System.out.println(num + " " + N/num);
    }
    return ret;
  }

  long combinationToLeastCommonMultiple(List<String> comb) {
    List<Integer> nums = stringListToIntList(comb);
    if (nums.isEmpty()) return Long.MAX_VALUE;
    if (nums.size() == 1) return nums.get(0);

    long lcm = lcm(nums.get(0), nums.get(1));
    for (int i = 2; i < nums.size(); i++) lcm = lcm(lcm, nums.get(i));
    lcm *= (nums.size() & 1) > 0 ? 1 : -1;
    return lcm;
  }

  long gcd(long a, long b) {
    return b == 0 ? a : gcd(b, a % b);
  }

  long lcm(long a, long b) {
    return a * b / gcd(a, b);
  }
}
