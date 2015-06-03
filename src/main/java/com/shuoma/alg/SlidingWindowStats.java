package com.shuoma.alg;

import static com.shuoma.annotation.Tag.Algorithm.SlidingWindow;
import static com.shuoma.annotation.Tag.DataStructure.Queue;

import com.shuoma.annotation.Tag;

import java.util.ArrayDeque;
import java.util.Deque;

/** Given a stream, compute the stats, e.g. average, min of a sliding window. */
@Tag(algs = SlidingWindow, dss = Queue)
public class SlidingWindowStats {

  /** Window size. */
  int K;
  /** Array that simulates a stream. */
  int[] stream;

  /**
   * @param k      Window size.
   * @param stream Array that simulates a stream.
   */
  public SlidingWindowStats(int k, int[] stream) {
    this.K = k;
    this.stream = stream;
  }

  /** Get the average of the sliding window. */
  double[] average() {
    double sum = 0;
    double[] avg = new double[stream.length - K + 1];
    for (int i = 0; i < stream.length; i++) {
      sum += stream[i];
      if (i >= K) {
        sum -= stream[i - K];
      }

      // compute avg
      if (i >= K - 1) {
        avg[i - K + 1] = (int) (sum / K * 100) / 100.0;
      }
    }
    return avg;
  }

  /** Get the max of the sliding window. */
  int[] max() {
    int[] max = new int[stream.length - K + 1];
    Deque<Integer> queue = new ArrayDeque<>();

    for (int i = 0; i < stream.length; i++) {
      if (i >= K) {
        max[i - K] = stream[queue.peekFirst()];
        while (!queue.isEmpty() && queue.peekFirst() <= i - K)
          queue.pollFirst();
      }
      while (!queue.isEmpty() && stream[i] >= stream[queue.peekLast()])
        queue.pollLast();
      queue.add(i);
    }
    max[stream.length - K] = stream[queue.peekFirst()];
    return max;
  }
}
