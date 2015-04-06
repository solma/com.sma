package com.shuoma.alg;

import static com.shuoma.annotation.Tag.Algorithm.DynamicProgramming;
import static com.shuoma.annotation.Tag.DataStructure.Array;
import static com.shuoma.annotation.Tag.Difficulty.D3;
import static com.shuoma.annotation.Tag.Source.JulyEdu;

import com.shuoma.annotation.Tag;

import java.util.Arrays;

/**
 * Given a list of candidates (M males and F females), each of which has
 * an ability value and a cost value, a budget B, a number X <= M and a number Y <= F,
 * hire X males and Y female within the budget and maximizes the total ability
 */

@Tag(algs = DynamicProgramming, dl = D3, dss = Array, source = JulyEdu)
public class HireWho {

  int hireWhoDp(final int[] ability, final int[] cost, final boolean[] male, int nM, int nF, int budget) {
    int n = ability.length;
    //int[] hired = new int[n];
    int[][][][] dp = new int[n + 1][budget + 1][nM + 1][nF + 1];
    for (int i = 1; i <= n; i++) {
      for (int b = 0; b <= budget; b++) {
        for (int m = 0; m <= nM; m++) {
          for (int f = 0; f <= nF; f++) {
            dp[i][b][m][f] = dp[i - 1][b][m][f]; // important inheritance
            if (b >= cost[i - 1]) {
              if (male[i - 1]) {
                if(m > 0) {
                  dp[i][b][m][f] = Math.max(dp[i][b][m][f], dp[i - 1][b - cost[i - 1]][m - 1][f] + ability[i - 1]);
                }
              } else {
                if (!male[i - 1]){
                  if (f > 0) {
                    dp[i][b][m][f] = Math.max(dp[i][b][m][f], dp[i - 1][b - cost[i - 1]][m][f - 1] + ability[i - 1]);
                  }
                }
              }
            }
          }
        }
      }
    }

    return dp[n][budget][nM][nF];
    //return hired;
  }

  int hireWhoRecursion(final int[] ability, final int[] cost, final boolean[] male, int nM, int nF, int budget) {
    int[] maxAbility = new int[] {0};
    boolean[] hired = new boolean[ability.length];
    boolean[] isHire = new boolean[ability.length];
    recursion(ability, cost, male, nM, nF, budget, maxAbility, isHire, 0, hired);
    System.out.println(Arrays.toString(isHire));
    return maxAbility[0];
  }

  private void recursion(int[] ability, int[] cost, boolean[] male, int nM, int nF, int budget, int[] maxAbility, boolean[] isHire, int sum, boolean[] hired) {
    if (budget < 0 || nM < 0 || nF < 0) {
      return;
    }
    if (nM == 0 && nF == 0 && maxAbility[0] < sum) {
      System.arraycopy(hired, 0, isHire, 0, hired.length);
      // isHire = Arrays.copyOf(isHire, isHire.length); // not working?
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
}
