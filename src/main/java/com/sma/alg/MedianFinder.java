package com.sma.alg;

import com.sma.annotation.Tag;

import java.util.Collections;
import java.util.PriorityQueue;

import static com.sma.annotation.Tag.Algorithm.Streaming;
import static com.sma.annotation.Tag.DataStructure.PriorityQueueT;
import static com.sma.annotation.Tag.Reference.LeetCode;

/**
 * Median is the middle value in an ordered integer list. If the size of the list is even, there is no middle value.
 * So the median is the mean of the two middle value.
 Examples:
 [2,3,4] , the median is 3
 [2,3], the median is (2 + 3) / 2 = 2.5
 */
@Tag(algs = {Streaming}, dss = {PriorityQueueT}, references = {LeetCode})
public class MedianFinder {

  PriorityQueue<Double> maxHeap = new PriorityQueue<>(1000, Collections.reverseOrder());
  PriorityQueue<Double> minHeap = new PriorityQueue<>();

  // Adds a number into the data structure.
  public void addNum(int num) {
    if (maxHeap.isEmpty()) {
      maxHeap.offer(num + .0);
      return;
    }

    if (num < maxHeap.peek()) maxHeap.offer(num + .0);
    else minHeap.offer(num + .0);

    if (minHeap.size() < maxHeap.size() - 1) {
      minHeap.offer(maxHeap.poll());
    }
    if(maxHeap.size() < minHeap.size() - 1) {
      maxHeap.offer(minHeap.poll());
    }
  }

  // Returns the median of current data stream
  public double findMedian() {
    assert (!minHeap.isEmpty() || !maxHeap.isEmpty());
    return minHeap.size() == maxHeap.size() ? (maxHeap.peek() + minHeap.peek()) / 2
        : (maxHeap.size() > minHeap.size() ? maxHeap.peek() : minHeap.peek());
  }
}
