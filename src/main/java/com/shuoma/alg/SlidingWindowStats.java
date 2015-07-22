package com.shuoma.alg;

import static com.shuoma.annotation.Tag.Algorithm.SlidingWindow;
import static com.shuoma.annotation.Tag.DataStructure.Queue;
import static com.shuoma.annotation.Tag.Reference.LeetCode;

import com.shuoma.annotation.Tag;

import java.util.Deque;
import java.util.LinkedList;

/** Given a stream, compute the stats, e.g. average, min of a sliding window. */
@Tag(algs = SlidingWindow, dss = Queue, references = LeetCode)
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
  int[] maxSlidingWindow(int[] nums, int k) {
    if (nums == null || k == 0) return new int[0];
    int n = nums.length;
    if (n < k) return new int[0];

    int[] ret = new int[n - k + 1];
    // decreasing queue
    Deque<Integer> queue = new LinkedList<>();
    for (int i = 0; i < n; i++) {
      // remove expired elements
      while(!queue.isEmpty() && queue.peek() < i - k + 1) queue.poll();
      // remove all smaller numbers and append the current element
      while (!queue.isEmpty() && nums[queue.getLast()] < nums[i]) queue.removeLast();
      queue.add(i);
      if (i >= k - 1) {
        ret[i - k + 1] = nums[queue.peek()];
      }
    }
    return ret;
  }
}
