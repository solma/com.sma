package com.sma.alg;

import static com.sma.annotation.Tag.Algorithm.Recursion;
import static com.sma.annotation.Tag.Difficulty.D3;
import static com.sma.annotation.Tag.Reference.LeetCode;

import com.sma.annotation.Tag;

import java.util.*;

/**
 Given a collection of candidate numbers (C) and a target number (T),
 find all unique combinations in C where the candidate numbers sums to T.
 Each number in C may only be used once in the combination.

 Note:
 All numbers (including target) will be positive integers.
 The solution set must not contain duplicate combinations.
 For example, given candidate set [10, 1, 2, 7, 6, 1, 5] and target 8,
 A solution set is:
 [
 [1, 7],
 [1, 2, 5],
 [2, 6],
 [1, 1, 6]
 ]
 */
//each element can only be used once
@Tag(algs = Recursion, dl = D3, references = LeetCode)
public class CombinationSumII {
  public static void main(String[] args) {
    new CombinationSumII().main();
  }

  public void main() {
    int[] candidates = {10, 1, 2, 7, 6, 1, 5};
    int target = 8;
    for (List<Integer> com : dpCombinationSum2(candidates, target)) {
      System.out.println(com);
    }
  }

  // second pass
  //dp+dfs
  public List<List<Integer>> dpCombinationSum2(int[] candidates, int target) {
    //Arrays.sort(candidates);
    int n = candidates.length;
    List<List<Integer>> ret = new ArrayList<>();
    if (n == 0) { return ret; }

    List<List<Integer>> dp = new ArrayList<>();
    for (int i = 0; i < target + 1; i++) { dp.add(new ArrayList<Integer>()); }

    for (int j = 1; j <= target; j++) {
      for (int i = 0; i < n; i++) {
        int prev = j - candidates[i];
        if (prev == 0 || (prev > 0 && dp.get(prev).size() > 0)) {
          dp.get(j).add(i); //index of the prev num in the array
        }
      }
    }

    dfs(ret, candidates, dp, target, new ArrayList<Integer>());
    return ret;
  }

  public void dfs(List<List<Integer>> ret, int[] candidates,
                  List<List<Integer>> dp, int curNode, List<Integer> idx) {
    if (curNode == 0) {
      List<Integer> sorted = new ArrayList<>();
      for (Integer index : idx) { sorted.add(candidates[index]); }
      Collections.sort(sorted);
      if (!ret.contains(sorted)) { ret.add(sorted); }
      return;
    }

    for (Integer next : dp.get(curNode)) {
      if (idx.contains(next)) { continue; }
      idx.add(next);
      dfs(ret, candidates, dp, curNode - candidates[next], idx);
      idx.remove(idx.size() - 1);
    }
  }

  //first pass
  //recursive
  public List<List<Integer>> combinationSum2(int[] candidates, int target) {
    Arrays.sort(candidates);
    HashSet<List<Integer>> res = new HashSet<>();
    //res.add(new ArrayList<Integer>());

    List<List<Integer>> ret = new ArrayList<>();

    for (List<Integer> temp : combinationClosestSum(candidates, target, 0,
        new ArrayList<Integer>(), new int[] {target})) {
      ret.add(temp);
    }
    return ret;
  }

  public Set<List<Integer>> combinationSum(int[] candidates, int target, int i, Set<List<Integer>> prev) {
    Set<List<Integer>> res = new HashSet<>();
    if (target == 0) {
      for (List<Integer> temp : prev) {
        res.add(new ArrayList<>(temp));
      }
      return res;
    }
    for (int j = i; j < candidates.length; j++) {
      if (candidates[j] > target) { break; }
      for (List<Integer> temp : prev) { temp.add(candidates[j]); }
      Set<List<Integer>> next = combinationSum(candidates, target - candidates[j], j + 1, prev);
      if (next.size() > 0) { res.addAll(next); }
      for (List<Integer> temp : prev) { temp.remove(temp.size() - 1); }
    }
    return res;
  }

  //dp only return false/true
  public boolean dpCombinationSum(int[] candidates, int target) {
    Arrays.sort(candidates);
    int n = candidates.length;
    if (n == 0) { return false; }
    boolean[] dp = new boolean[target + 1];
    dp[0] = true;
    for (int i = 0; i < n; i++) {
      if (candidates[i] > target) { break; }
      for (int j = target; j >= 0; j--) {
        if (candidates[i] <= j) { dp[j] = dp[j - candidates[i]]; }
      }
    }
    System.out.println(dp[target]);
    return dp[target];
  }

  //recursive
  public Set<List<Integer>> combinationClosestSum(int[] candidates, int target, int i, ArrayList<Integer> curSubset, int[] closestSoFar) {
    Set<List<Integer>> ret = new HashSet<>();
    int diff = Math.abs(target);

    if (diff <= closestSoFar[0]) {
      if (diff < closestSoFar[0]) {
        closestSoFar[0] = diff;
      }
      ret.add(new ArrayList<>(curSubset));
      //          System.out.println(closestSoFar[0]+" "+subsets);
    }
    int oldValue;
    for (int j = i; j < candidates.length; j++) {
      //if(target>closestSoFar[0]&&candidates[j]>=0) break;
      curSubset.add(candidates[j]);

      oldValue = closestSoFar[0];
      //use candidate[j]
      Set<List<Integer>> nxt =
          combinationClosestSum(candidates, target - candidates[j], j + 1, curSubset, closestSoFar);
      if (closestSoFar[0] < oldValue) {
        ret.clear(); // clear the results in the current set
      }
      ret.addAll(nxt);

      curSubset.remove(curSubset.size() - 1);
      //System.out.println(closestSoFar[0]+" "+curSubset+" j="+j+" target="+target  );
      oldValue = closestSoFar[0];
      nxt = combinationClosestSum(candidates, target, j + 1, curSubset,
          closestSoFar); //do not use candidate[j]
      if (closestSoFar[0] < oldValue) {
        ret.clear(); // clear the results in the current set
      }
      ret.addAll(nxt);
    }
    return ret;
  }
}
