package com.shuoma.alg;

import static com.shuoma.annotation.Tag.Algorithm.Recursion;
import static com.shuoma.annotation.Tag.DataStructure.String;
import static com.shuoma.annotation.Tag.Reference.LeetCode;

import com.shuoma.annotation.Tag;

import java.util.*;

@Tag(algs = Recursion, dss = String, references = LeetCode)
public class SubsetsII {
  public static void main(String[] args) {
    for (List<Integer> s : new SubsetsII().subsetsWithDup(new int[] {2, 1, 2}))
      System.out.println(s);
  }

  public List<List<Integer>> subsetsWithDup(int[] S) {
    Arrays.sort(S);
    Set<List<Integer>> res = new HashSet<>();
    res.add(new ArrayList<Integer>());
    combineRecursion(res, S, 0, new ArrayList<Integer>());

    List<List<Integer>> ret = new ArrayList<>();
    ret.addAll(res);
    return ret;
  }

  public void combineRecursion(Set<List<Integer>> res, int[] S, int sIdx, List<Integer> com) {
    for (int i = sIdx; i < S.length; i++) {
      com.add(S[i]);
      res.add(new ArrayList<>(com));
      //System.out.println(sIdx+"  "+i+"  "+com);
      combineRecursion(res, S, i + 1, com); //tricky part, new sIdx equals to i+1 not sIdx+1
      com.remove(com.size() - 1);
    }
  }
}
