package com.sma.alg;

import static com.sma.annotation.Tag.Algorithm.Recursion;
import static com.sma.annotation.Tag.DataStructure.StringT;
import static com.sma.annotation.Tag.Reference.LeetCode;

import com.sma.annotation.Tag;

import java.util.ArrayList;
import java.util.List;

@Tag(algs = Recursion, dss = StringT, references = LeetCode)
public class Permutations {
  public List<List<Integer>> permute(int[] num) {
    List<List<Integer>> res = new ArrayList<>();
    permuteRecursion(res, num, new ArrayList<Integer>());
    return res;
  }

  public void permuteRecursion(List<List<Integer>> res, int[] num, List<Integer> perm) {
    if (perm.size() == num.length) {
      res.add(new ArrayList<>(perm));
    }
    for (int i = 0; i < num.length; i++) {
      if (!perm.contains(num[i])) {
        perm.add(num[i]);
        permuteRecursion(res, num, perm);
        perm.remove(perm.size() - 1);
      }
    }
  }
}
