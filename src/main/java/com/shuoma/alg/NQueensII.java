package com.shuoma.alg;
//TLE use NQueens Solution

import static com.shuoma.annotation.Tag.Algorithm.Recursion;
import static com.shuoma.annotation.Tag.Reference.LeetCode;

import com.shuoma.annotation.Tag;

import java.util.ArrayList;
import java.util.List;

@Tag(algs = Recursion, references = LeetCode) public class NQueensII {
  public static void main(String[] args) {
    new NQueensII().main();
  }

  public void main() {
    long curTime = System.currentTimeMillis();
    totalNQueens(13);
    System.out.print((System.currentTimeMillis() - curTime));
  }

  public int totalNQueens(int n) {
    List<List<Integer>> perms = new ArrayList<>();
    permutate(n, perms, new ArrayList<Integer>(), new ArrayList<Integer>(),
        new ArrayList<Integer>());
    return perms.size();
  }

  // enumerate permutation with pruning
  public void permutate(int n, List<List<Integer>> perms, List<Integer> cur, List<Integer> diagonal, List<Integer> rDiagonal) {
    if (cur.size() == n) {
      perms.add(new ArrayList<>(cur));
      return;
    }
    int[] diffs = new int[2];
    for (int i = 0; i < n; i++) {
      if (!cur.contains(i)) {
        diffs[0] = i - cur.size();
        diffs[1] = i + cur.size();
        if (!diagonal.contains(diffs[0]) && !rDiagonal.contains(diffs[1])) {
          cur.add(i);
          diagonal.add(diffs[0]);
          rDiagonal.add(diffs[1]);
          permutate(n, perms, cur, diagonal, rDiagonal);
          diagonal.remove(cur.size() - 1);
          rDiagonal.remove(cur.size() - 1);
          cur.remove(cur.size() - 1);
        }
      }
    }
  }
}
