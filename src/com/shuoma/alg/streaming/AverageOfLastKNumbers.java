package com.shuoma.alg.streaming;

import com.shuoma.util.RandomUtil;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/** Compute the average of last K numbers in a stream. */
public class AverageOfLastKNumbers {
  int K;
  int[] stream; // array that simulates a stream

  public AverageOfLastKNumbers(int k, int[] stream) {
    this.K = k;
    this.stream = stream;
  }

  /** Get the average of last K numbers at timestamp cur. */
  public double averageOfLastKNumbers(int cur, Set<Integer> outputTimestamps) {
    int sum = 0;
    double avg = 0;
    for (int i = 0; i <= cur; i++) {
      sum += stream[i];
      if (i >= K) sum -= stream[i - K];

      // compute avg
      if (outputTimestamps.contains(i)) {
        avg = sum * 1.0 / Math.min(K, i + 1);
        System.out.println(i + " " + Arrays.toString(lastKNumbers(i)) + " "
            + String.format("%.2f", avg));
      }
    }
    return avg;
  }

  public int[] lastKNumbers(int idx) {
    return Arrays.copyOfRange(stream, Math.max(0, idx - K + 1), idx + 1);
  }

  public static void main(String[] args) {
    int length = 1000;
    int K = 3;
    int[] ary = RandomUtil.genRandomArray(length, 10, true, false);
    AverageOfLastKNumbers ins = new AverageOfLastKNumbers(K, ary);

    int[] idxes = RandomUtil.genRandomArray(10, length, false, false);
    Arrays.sort(idxes);
    Set<Integer> timestamps = new HashSet<>();
    for (int timestamp : idxes)
      timestamps.add(timestamp);

    ins.averageOfLastKNumbers(idxes[idxes.length - 1], timestamps);
  }
}
