package com.sma.alg;

import static com.sma.annotation.Tag.Algorithm.Recursion;
import static com.sma.annotation.Tag.DataStructure.StringT;
import static com.sma.annotation.Tag.Reference.LeetCode;

import com.sma.annotation.Tag;

import java.util.*;

@Tag(algs = Recursion, dss = StringT, references = LeetCode)
public class PermutationsII {
  public static void main(String[] args) {
    new PermutationsII().main();
  }

  public void main() {
    //        int A[]=new int[]{-1,2,-1,2,1,-1,2,1};
    int A[] = new int[] {1, 2, 1};
    for (List<Integer> p : permuteUnique(A)) {
      for (Integer i : p)
        System.out.print(i + " ");
      System.out.println();
    }
  }

  public List<List<Integer>> permuteUnique(int[] num) {
    if (num == null) {
      return null;
    }
    Arrays.sort(num);
    return perm(num, 0, new boolean[num.length]);
  }

  public List<List<Integer>> perm(int[] num, int l, boolean[] used) {
    List<List<Integer>> rl = new ArrayList<>();
    if (l == num.length)
      rl.add(new ArrayList<Integer>());
    else
      for (int i = 0; i < num.length; i++) {
        if (used[i] || (i != 0 && num[i] == num[i - 1] && used[i - 1]))
          continue;
        used[i] = true;
        for (List<Integer> x : perm(num, l + 1, used)) {
          x.add(0, num[i]);
          rl.add(x);
        }
        used[i] = false;
      }
    return rl;
  }
}
