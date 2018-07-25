package com.sma.util;


import com.sma.alg.HouseRobber;

import java.util.Arrays;

public class InterviewUtil {
  public static void main(String args[]) {
    new InterviewUtil().main();
  }

  void main() {
    com.sma.alg.HouseRobber ins = new HouseRobber();
    for (int i = 0; i < 1; i++) {
      //int[] arr = genRandomArray(5, 5, true, true);
      int[] arr = {-1, -2, 100};
      int ground_truth = ins.rob(arr);
      int candidate  = CandidateImpl(arr);
      if (ground_truth != candidate) {
        System.out.println(Arrays.toString(arr));
        System.out.println(ground_truth + " " +candidate);
      }
    }
  }

  int CandidateImpl(int[] num) {
    return 0;
  }
}
