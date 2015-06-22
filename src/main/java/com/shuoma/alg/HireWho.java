package com.shuoma.alg;

import static com.shuoma.annotation.Tag.Algorithm.DynamicProgramming;
import static com.shuoma.annotation.Tag.DataStructure.Array;
import static com.shuoma.annotation.Tag.Difficulty.D3;
import static com.shuoma.annotation.Tag.Reference.JulyEdu;

import com.google.common.collect.ImmutableList;

import com.shuoma.annotation.Tag;
import org.apache.commons.lang3.tuple.MutablePair;
import org.apache.commons.lang3.tuple.MutableTriple;
import org.apache.commons.lang3.tuple.Pair;
import org.apache.commons.lang3.tuple.Triple;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * Given a list of candidates (M males and F females), each of which has
 * an ability value and a cost value, a budget B, a number X <= M and a number Y <= F,
 * hire X males and Y female within the budget and maximizes the total ability
 */

@Tag(algs = DynamicProgramming, dl = D3, dss = Array, references = JulyEdu)
public class HireWho {

  List<List<Integer>> hireWhoDp(final int[] ability, final int[] cost, final boolean[] male, int nM, int nF, int budget) {
    int n = ability.length;
    Map<Triple<Integer, Integer, Integer>, Pair<Triple<Integer, Integer, Integer>, Integer>> backtracking =
        new HashMap<>();
    int[][][] dp = new int[budget + 1][nM + 1][nF + 1];
    for (int i = 1; i <= n; i++) {
      for (int b = budget; b >= 0; b--) {
        for (int m = nM; m >= 0; m--) {
          for (int f = nF; f >= 0; f--) {
            if (b >= cost[i - 1]) {
              if (male[i - 1] && m > 0 && dp[b - cost[i - 1]][m - 1][f] + ability[i - 1] > dp[b][m][f]) {
                dp[b][m][f] = dp[b - cost[i - 1]][m - 1][f] + ability[i - 1];
                backtracking.put(new MutableTriple<>(b, m, f),
                    new MutablePair<Triple<Integer, Integer, Integer>, Integer>(
                        new MutableTriple<>(b - cost[i - 1], m - 1, f), i - 1));
                continue;
              }
              if (!male[i - 1] && f > 0 && dp[b - cost[i - 1]][m][f - 1] + ability[i - 1] > dp[b][m][f]) {
                dp[b][m][f] = dp[b - cost[i - 1]][m][f - 1] + ability[i - 1];
                backtracking.put(new MutableTriple<>(b, m, f),
                    new MutablePair<Triple<Integer, Integer, Integer>, Integer>(
                        new MutableTriple<>(b - cost[i - 1], m, f - 1), i - 1));
              }
            }
          }
        }
      }
    }

    return buildResult(dp[budget][nM][nF], getHiredCandidates(backtracking, new MutableTriple<>(budget, nM, nF)));
  }

  private List<Integer> getHiredCandidates(Map<Triple<Integer, Integer, Integer>,
      Pair<Triple<Integer, Integer, Integer>, Integer>> backtracking, Triple<Integer, Integer, Integer> cur) {
    List<Integer> ret = new LinkedList<>();
    if (cur.getLeft() == 0) { //budget==0
      return ret;
    }
    ret.addAll(getHiredCandidates(backtracking, backtracking.get(cur).getLeft()));
    ret.add(backtracking.get(cur).getRight());
    return ret;
  }

  List<List<Integer>> hireWhoRecursion(final int[] ability, final int[] cost, final boolean[] male, int nM, int nF, int budget) {
    int[] maxAbility = new int[] {0};
    boolean[] hire = new boolean[ability.length];
    boolean[] isHire = new boolean[ability.length];
    recursion(ability, cost, male, nM, nF, budget, maxAbility, isHire, 0, hire);
    List<Integer> hiredCandidates = new ArrayList<>(nM + nF);
    for (int i = 0; i < isHire.length; i++) {
      if (isHire[i]) {
        hiredCandidates.add(i);
      }
    }

    return buildResult(maxAbility[0], hiredCandidates);
  }

  private void recursion(int[] ability, int[] cost, boolean[] male, int nM, int nF, int budget, int[] maxAbility, boolean[] isHire, int sum, boolean[] hired) {
    if (budget < 0 || nM < 0 || nF < 0) {
      return;
    }
    if (nM == 0 && nF == 0 && maxAbility[0] < sum) {
      System.arraycopy(hired, 0, isHire, 0, hired.length);
      //System.out.println(sum + " " + maxAbility[0] + " " + Arrays.toString(isHire));
      maxAbility[0] = sum;
    }
    for (int i = 0; i < ability.length; i++) {
      if (!hired[i]) {
        hired[i] = true;
        if (male[i]) {
          recursion(ability, cost, male, nM - 1, nF, budget - cost[i], maxAbility, isHire,
              sum + ability[i], hired);
        } else {
          recursion(ability, cost, male, nM, nF - 1, budget - cost[i], maxAbility, isHire,
              sum + ability[i], hired);
        }
        hired[i] = false;
      }
    }
  }

  private List<List<Integer>> buildResult(int maxAbility, List<Integer> hiredPersons) {
    List<List<Integer>> res = new ArrayList<>(2);
    res.add(ImmutableList.of(maxAbility));
    res.add(hiredPersons);
    return res;
  }
}
