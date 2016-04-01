package com.sma.alg;

import com.sma.util.RandomUtil;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class MultipleChoicesAnsweringStrategy {

  public static void main(String[] args) {
    MultipleChoicesAnsweringStrategy ins = new MultipleChoicesAnsweringStrategy(4, 10000);
    int cycleWins = 0, draws = 0, times = 100000;
    for (int i = 0; i < times; i++) {
      int[] groundTruthAns = ins.genRandomGroundTruthAnswers();
      //System.out.println("dis: " + ins.getAnswerDistribution(groundTruthAns));

      double cycleRate =
          ins.getCorrectPercentage(ins.genAnswersByFillingCyclingChoices(), groundTruthAns);
      //System.out.println("cycle: " + cycleRate);

      double singleAvgRate = 0, singleMaxRate = 0;
      for (int j = 0; j < ins.k; j++) {
        double rate =
            ins.getCorrectPercentage(groundTruthAns, ins.genAnswersByFillingSingleChoice(j));
        singleAvgRate += rate;
        singleMaxRate = Math.max(rate, singleMaxRate);
      }
      singleAvgRate /= ins.k;
      //System.out.println("single: " + singleAvgRate + " " + singleMaxRate);

      if (cycleRate > singleAvgRate) {
        cycleWins++;
      } else {
        if (cycleRate == singleAvgRate) {
          draws++;
        }
      }
    }
    System.out.println(
        "cycle wins : " + cycleWins + " draws: " + draws + " single wins: " + (times - draws
            - cycleWins));
  }

  int k; // number of choices per question
  int n; // number of questions

  public MultipleChoicesAnsweringStrategy(int k, int n) {
    this.k = k;
    this.n = n;
  }

  int[] genAnswersByFillingCyclingChoices() {
    int[] ans = new int[n];
    int choice = 0;
    for (int i = 0; i < n; i++) {
      ans[i] = choice++ % k;
    }
    return ans;
  }

  int[] genAnswersByFillingSingleChoice(int i) {
    int[] ans = new int[n];
    Arrays.fill(ans, i);
    return ans;
  }

  int[] genRandomGroundTruthAnswers() {
    int[] ans = new int[n];
    for (int i = 0; i < n; i++) {
      ans[i] = RandomUtil.r.nextInt(k);
    }
    return ans;
  }

  double getCorrectPercentage(int[] groundTruthAns, int[] ans) {
    int n = groundTruthAns.length;
    double sum = 0;
    for (int i = 0; i < n; i++) {
      if (groundTruthAns[i] == ans[i]) {
        sum++;
      }
    }
    return sum / n;
  }

  Map<Integer, Integer> getAnswerDistribution(int[] ans) {
    Map<Integer, Integer> distribution = new HashMap<>();
    for (int number : ans) {
      if (!distribution.containsKey(number)) {
        distribution.put(number, 0);
      }
      distribution.put(number, distribution.get(number) + 1);
    }
    return distribution;
  }
}
