package com.shuoma.alg;

import static com.shuoma.annotation.Tag.Algorithm.Recursion;
import static com.shuoma.annotation.Tag.DataStructure.Array;
import static com.shuoma.annotation.Tag.Reference.LeetCode;

import com.shuoma.annotation.Tag;

import java.util.LinkedList;
import java.util.List;

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

  public List<List<Integer>> combinationSum3Recur(int k, int n, int start, List<Integer> set) {
    List<List<Integer>> ret = new LinkedList<>();
    if (k > 9 || k < 0 || n < 0) return ret;
    if (k == 0 && n == 0) {
      ret.add(new LinkedList<>(set));
    }

    for (int i = start; i <= 9; i++) {
      if (!set.contains(i)) {
        set.add(i);
        ret.addAll(combinationSum3Recur(k - 1, n - i, i + 1, set));
        set.remove(new Integer(i));
      }
    }
    return ret;
  }
}
