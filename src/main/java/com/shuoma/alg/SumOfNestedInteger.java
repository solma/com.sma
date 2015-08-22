package com.shuoma.alg;

import static com.shuoma.annotation.Tag.Algorithm.Recursion;
import static com.shuoma.annotation.Tag.Reference.LeetCode;

import com.shuoma.annotation.Tag;

import java.util.List;

/**
 * Given a nested list of integers,
 * returns the sum of all integers in the list weighted by their depth
 * For example, given
 * {{1,1},2,{1,1}} return 10 (four 1's at depth 2, one 2 at depth 1)
 * {1,{4,{6}}} return 27 (one 1 at depth 1, one 4 at depth 2, and one 6 at depth 3)
 */
@Tag(algs = Recursion, references = LeetCode)
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
  public boolean isInteger();

  // Returns the single integer that this NestedInteger holds, if it holds a single integer
  // Returns null if this NestedInteger holds a nested list
  public Integer getInteger();

  // Returns the nested list that this NestedInteger holds, if it holds a nested list
  // Returns null if this NestedInteger holds a single integer
  public List<NestedInteger> getList();
}



