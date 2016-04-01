package com.sma.alg;

import com.sma.annotation.Tag;

import java.util.*;

import static com.sma.annotation.Tag.Algorithm.SlidingWindow;
import static com.sma.annotation.Tag.Algorithm.Streaming;
import static com.sma.annotation.Tag.DataStructure.PriorityQueueT;
import static com.sma.annotation.Tag.DataStructure.QueueT;
import static com.sma.annotation.Tag.Reference.Interview;
import static com.sma.annotation.Tag.Reference.LeetCode;
import static com.sma.util.CollectionsUtil.increaseMapCounter;

/** Given a stream, compute the stats, e.g. average, min of a sliding window. */
@Tag(algs = {SlidingWindow, Streaming}, dss = {QueueT, PriorityQueueT}, references = {LeetCode, Interview})
public class StreamingStats {

  /** Window size. */
  int K;
  /** Array that simulates a stream. */
  int[] stream;

  /**
   * @param k      Window size.
   * @param stream Array that simulates a stream.
   */
  public StreamingStats(int k, int[] stream) {
    this.K = k;
    this.stream = stream;
  }

  /** Get the average of the sliding window. */
  double[] getAverage() {
    double sum = 0;
    double[] avg = new double[stream.length - K + 1];
    List<Integer> window = new LinkedList<>();
    for (int i = 0; i < stream.length; i++) {
      sum += stream[i];
      window.add(stream[i]);
      if (i >= K) { sum -= window.remove(0); }

      // compute avg
      if (i >= K - 1) { avg[i - K + 1] = sum / K; }
    }
    return avg;
  }

  /** Get the max of the sliding window. */
  int[] getMax(int[] nums, int k) {
    if (nums == null || k == 0) return new int[0];
    int n = nums.length;
    if (n < k) return new int[0];

    int[] ret = new int[n - k + 1];
    // decreasing queue
    List<Integer> window = new LinkedList<>();
    for (int i = 0; i < n; i++) {
      // remove expired elements
      while(!window.isEmpty() && window.get(0) < i - k + 1) window.remove(0);
      // remove all smaller numbers and append the current element
      while (!window.isEmpty() && nums[window.get(window.size() - 1)] < nums[i]) {
        window.remove(window.size() - 1);
      }
      window.add(i);
      if (i >= k - 1) {
        ret[i - k + 1] = nums[window.get(0)];
      }
    }
    return ret;
  }

  /** Get the max of the sliding window. */
  int[] getMaxWithTreeMap(int[] nums, int k) {
    if (nums == null || k == 0) return new int[0];
    int n = nums.length;
    if (n < k) return new int[0];

    int[] ret = new int[n - k + 1];
    // use tree map key:value in nums, value:counter
    TreeMap<Integer, Integer> treeMap = new TreeMap<>();
    for (int i = 0; i < n; i++) {
      // insert new key
      increaseMapCounter(treeMap, nums[i], 1);
      if (i >= k - 1) {
        if (i >= k) { //remove expired key
          increaseMapCounter(treeMap, nums[i - k], -1);
        }
        ret[i - k + 1] = treeMap.lastKey();
      }
    }
    return ret;
  }

  /**
   * Get median of the stream (compute median one time when a new element arrives)
   * @param nums
   * @return
   */
  int[] getMedian(int[] nums) {
    int n = nums.length;
    assert (n >= 2);
    PriorityQueue<Integer> maxHeap = new PriorityQueue<>(20, Collections.reverseOrder());
    PriorityQueue<Integer> minHeap = new PriorityQueue<>();
    maxHeap.add(nums[0]);

    int[] medians = new int[n - 1];
    for (int i = 1; i < n; i++) {
      if (nums[i] < maxHeap.peek()) maxHeap.offer(nums[i]);
      else minHeap.offer(nums[i]);

      if (minHeap.size() < maxHeap.size() - 1) {
        minHeap.offer(maxHeap.poll());
      }
      if(maxHeap.size() < minHeap.size() - 1) {
        maxHeap.offer(minHeap.poll());
      }
      // get the median
      medians[i - 1] = minHeap.size() == maxHeap.size() ? (maxHeap.peek() + minHeap.peek()) / 2
          : (maxHeap.size() > minHeap.size() ? maxHeap.peek() : minHeap.peek());
    }
    return medians;
  }
}
