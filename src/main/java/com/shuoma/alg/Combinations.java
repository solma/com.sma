package com.shuoma.alg;

import static com.shuoma.annotation.Tag.Algorithm.Recursion;
import static com.shuoma.annotation.Tag.Difficulty.D2;
import static com.shuoma.annotation.Tag.Reference.LeetCode;

import com.shuoma.annotation.Tag;

import java.util.ArrayList;
import java.util.List;

@Tag(algs = Recursion, dl = D2, reference = LeetCode)
public class Combinations {
  public static void main(String[] args) {
    new Combinations().main();
  }

  public void main() {
    for (List<Integer> com : combine(3, 2))
      System.out.println();
  }

  //second pass
  public List<List<Integer>> combine(int n, int k) {
    return combine(n, k, 1, new ArrayList<Integer>());
  }

  public List<List<Integer>> combine(int n, int k, int sIdx, List<Integer> com) {
    List<List<Integer>> ret = new ArrayList<>();
    if (com.size() == k) {
      return ret;
    }
    for (int i = sIdx; i <= n; i++) {
      com.add(i);
      if (com.size() == k)
        ret.add(new ArrayList<>(com));
      ret.addAll(combine(n, k, i + 1, com));
      com.remove(com.size() - 1);
    }
    return ret;
  }

  //first pass
  public List<List<Integer>> combineFirstPass(int n, int k) {
    List<List<Integer>> res = new ArrayList<>();
    combineRecursion(res, n, k, 0, new ArrayList<Integer>());
    return res;
  }

  public void combineRecursion(List<List<Integer>> res, int n, int k, int sIdx, List<Integer> com) {
    if (com.size() == k + 1)
      return;

    for (int i = sIdx; i < n; i++) {
      com.add(i + 1);
      System.out.println(sIdx + "  " + i + " " + com);
      if (com.size() == k)
        res.add(new ArrayList<>(com));

      combineRecursion(res, n, k, i + 1, com); //tricky part, new sIdx equals to i+1 not sIdx+1
      com.remove(com.size() - 1);
    }
  }
}
