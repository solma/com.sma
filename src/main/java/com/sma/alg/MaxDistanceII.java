package com.sma.alg;

import static com.sma.annotation.Tag.DataStructure.MonotonicSequence;
import static com.sma.annotation.Tag.Reference.Interview;
import static com.sma.annotation.Tag.Trick.TwoOrMorePointers;

import com.sma.annotation.Tag;

import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

/**
 * Given an int array A[], define distance as j-i if A[i] < A[j] and 0 otherwise.
 * Find max distance in A[]. Requirement O(n) time
 */

@Tag(dss = {MonotonicSequence}, references = Interview, tricks = TwoOrMorePointers
)
public class MaxDistanceII {

  List<Integer> maxDistance(int[] nums){
    int n = nums.length;
    if (n < 2) return new LinkedList<>();
    Deque<Integer> decrSeq = new LinkedList<>();
    decrSeq.offer(0);
    for (int i = 1; i < n; i++) {
      if (nums[i] < nums[decrSeq.peekLast()]) {
        decrSeq.add(i);
      }
    }

    List<Integer> ret = Arrays.asList(0, -1, -1);
    for (int back = n - 1; back >=0 && !decrSeq.isEmpty(); ) {
      int front = decrSeq.peekLast();
      if (nums[front] >= nums[back]) {
        back--;
        continue;
      }

      if (ret.get(0) < back - front) {
        ret = Arrays.asList(back - front, front, back);
      }
      decrSeq.pollLast();
    }
    return ret;
  }
}
