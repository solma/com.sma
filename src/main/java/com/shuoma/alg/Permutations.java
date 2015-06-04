package com.shuoma.alg;

import static com.shuoma.annotation.Tag.Algorithm.Recursion;
import static com.shuoma.annotation.Tag.DataStructure.String;
import static com.shuoma.annotation.Tag.Reference.LeetCode;

import com.shuoma.annotation.Tag;

import java.util.ArrayList;
import java.util.List;

@Tag(algs = Recursion, dss = String, reference = LeetCode)
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
