package com.shuoma.alg;

import static com.shuoma.annotation.Tag.Algorithm.Recursion;
import static com.shuoma.annotation.Tag.DataStructure.String;
import static com.shuoma.annotation.Tag.Reference.LeetCode;

import com.shuoma.annotation.Tag;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Tag(algs = Recursion, dss = String, reference = LeetCode)
public class Subsets {
  public List<List<Integer>> subsets(int[] S) {
    List<List<Integer>> res = new ArrayList<>();
    Arrays.sort(S);
    res.add(new ArrayList<Integer>());
    combineRecursion(res, S, 0, new ArrayList<Integer>());
    return res;
  }

  public void combineRecursion(List<List<Integer>> res, int[] S, int sIdx, List<Integer> com) {
    for (int i = sIdx; i < S.length; i++) {
      com.add(S[i]);
      res.add(new ArrayList<>(com));
      //System.out.println(sIdx+"  "+i+"  "+com);
      combineRecursion(res, S, i + 1, com); //tricky part, new sIdx equals to i+1 not sIdx+1
      com.remove(com.size() - 1);
    }
  }
}
