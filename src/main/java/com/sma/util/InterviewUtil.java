package com.sma.util;


import com.sma.alg.HouseRobber;

import java.util.Arrays;
import java.util.LinkedList;

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

  int CandidateImpl(int[] arr) {
    LinkedList<Integer[]> subarrays = new LinkedList<>();
    for (int i : arr) {
      subarrays.add({i});

    }

    return 0;
  }

  LinkedList<Integer[]> createSubarrays(int current_idx, int[] array) {
    int current_item = array[current_idx];
    if (current_idx + 1 > array.length) {
      return {current_item};
    } else {
      for (int i = current_idx + 2; i)
    }
  }
}
