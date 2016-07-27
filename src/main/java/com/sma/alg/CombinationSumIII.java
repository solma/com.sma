package com.sma.alg;

import static com.sma.annotation.Tag.Algorithm.Recursion;
import static com.sma.annotation.Tag.DataStructure.Array;
import static com.sma.annotation.Tag.Reference.LeetCode;

import com.sma.annotation.Tag;

import java.util.LinkedList;
import java.util.List;

/**
 Find all possible combinations of k numbers that add up to a number n,
 given that only numbers from 1 to 9 can be used and each combination should
 be a unique set of numbers.

 Example 1:
 Input: k = 3, n = 7
 Output:
 [[1,2,4]]

 Example 2:
 Input: k = 3, n = 9
 Output:
 [[1,2,6], [1,3,5], [2,3,4]]
 */
@Tag(algs = Recursion, dss = Array, references = LeetCode)
public class CombinationSumIII {

  public static void main(String[] args) {
    CombinationSumIII ins = new CombinationSumIII();
    System.out.println(ins.combinationSum3(3, 7));
    System.out.println(ins.combinationSum3(3, 9));
    System.out.println(ins.combinationSum3(10, 10));
  }

  public List<List<Integer>> combinationSum3(int k, int n) {
    return combinationSum3Recur(k, n, 1, new LinkedList<Integer>());
  }

  public List<List<Integer>> combinationSum3Recur(int k, int n, int start,
                                                  List<Integer> set) {
    List<List<Integer>> ret = new LinkedList<>();
    if (k > 9 || k < 0 || n < 0) { return ret; }
    if (k == 0 && n == 0) { ret.add(new LinkedList<>(set)); }

    for (int i = start; i <= 9; i++) {
      if (set.contains(i)) { continue; }
      set.add(i);
      ret.addAll(combinationSum3Recur(k - 1, n - i, i + 1, set));
      set.remove(new Integer(i));
    }
    return ret;
  }
}
