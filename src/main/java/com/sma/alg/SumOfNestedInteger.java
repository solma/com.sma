package com.sma.alg;

import com.sma.annotation.Tag;

import java.util.List;

import static com.sma.annotation.Tag.Algorithm.Recursion;
import static com.sma.annotation.Tag.DataStructure.IteratorT;
import static com.sma.annotation.Tag.Reference.LeetCode;

/**
 * Given a nested list of integers,
 * returns the sum of all integers in the list weighted by their depth
 * For example, given
 * {{1,1},2,{1,1}} return 10 (four 1's at depth 2, one 2 at depth 1)
 * {1,{4,{6}}} return 27 (one 1 at depth 1, one 4 at depth 2, and one 6 at depth 3)
 */
@Tag(algs = Recursion, dss = IteratorT, references = LeetCode)
public class SumOfNestedInteger {

  int getListSum(List<NestedInteger> lni, int depth) {
    int sum = 0;
    for (NestedInteger ni : lni) {
      if (ni.isInteger()) { sum += ni.getInteger() * depth; }
      else { sum += getListSum(ni.getList(), depth + 1); }
    }
    return sum;
  }

  public int getSum(NestedInteger ni) {
    return getListSum(ni.getList(), 1);
  }
}


interface NestedInteger {
  // Returns true if this NestedInteger holds a single integer, rather than a nested list
  boolean isInteger();

  // Returns the single integer that this NestedInteger holds, if it holds a single integer
  // Returns null if this NestedInteger holds a nested list
  Integer getInteger();

  // Returns the nested list that this NestedInteger holds, if it holds a nested list
  // Returns null if this NestedInteger holds a single integer
  List<NestedInteger> getList();
}



