package com.shuoma.alg;

import static com.shuoma.annotation.Tag.Algorithm.DynamicProgramming;
import static com.shuoma.annotation.Tag.DataStructure.Array;

import com.shuoma.annotation.Tag;
import com.shuoma.util.ArrayUtil;
import com.shuoma.util.CollectionsUtil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * You have been given a list of jewelry items that must be split amongst two people: Frank and Bob.
 * Frank likes very expensive jewelry. Bob doesn't care how expensive the jewelry is, as long as
 * he gets a lot of jewelry.
 *
 * Based on these criteria you have devised the following policy:
 * 1) Each piece of jewelry given to Frank must be valued greater than or equal to each piece of
 * jewelry given to Bob. In other words, Frank's least expensive piece of jewelry must be valued
 * greater than or equal to Bob's most expensive piece of jewelry.
 * 2) The total value of the jewelry given to Frank must exactly equal the total value of the
 * jewelry given to Bob.
 * 3) There can be pieces of jewelry given to neither Bob nor Frank.
 * 4) Frank and Bob must each get at least 1 piece of jewelry.
 * Given the value of each piece, you will determine the number of different ways you can allocate
 * the jewelry to Bob and Frank following the above policy.
 *
 * For example:
 * values = {1,2,5,3,4,5}
 * Valid allocations are:
 * Bob      Frank
 * 1,2         3
 * 1,3         4
 * 1,4         5  (first 5)
 * 1,4         5  (second 5)
 * 2,3         5  (first 5)
 * 2,3         5  (second 5)
 * 5(first 5)  5  (second 5)
 * 5(second 5) 5  (first 5)
 * 1,2,3,4     5,5
 * Note that each '5' is a different piece of jewelry and needs to be accounted for separately.
 * There are 9 legal ways of allocating the jewelry to Bob and Frank given the policy, so your method would return 9.
 */

@Tag(algs = DynamicProgramming, dss = Array)
public class JewelrySplit {

  List<List<List<Integer>>> split(int[] jewelries) {
    Arrays.sort(jewelries);
    List<List<List<Integer>>> solutions = new LinkedList<>();

    int n = jewelries.length;
    assert(n >= 2);
    int sum = ArrayUtil.sum(jewelries);
    boolean[] forbidden = new boolean[n];
    for (int s = jewelries[1]; s <= sum / 2; s++) {
      for (List<Integer> bob : FindSum.findSum(jewelries, s, forbidden)) {
        boolean[] tabu = toIndex(bob, n);
        for (List<Integer> frank :  FindSum.findSum(jewelries, s, tabu)) {
          if (bob.get(0) <= frank.get(frank.size() - 1)) {
            continue;
          }
          List<List<Integer>> split = new ArrayList<>(2);
          split.add(CollectionsUtil.toValue(jewelries, bob));
          split.add(CollectionsUtil.toValue(jewelries, frank));
          solutions.add(split);
        }
      }
    }
    return solutions;
  }

  boolean[] toIndex(List<Integer> chosen, int n) {
    boolean[] forbidden = new boolean[n];
    for (int i : chosen) {
      forbidden[i] = true;
    }
    return forbidden;
  }
}
