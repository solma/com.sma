package com.shuoma.alg;

import static com.shuoma.annotation.Tag.DataStructure.Array;
import static com.shuoma.annotation.Tag.DataStructure.PriorityQueueT;
import static com.shuoma.annotation.Tag.Trick.TwoOrMorePointers;

import com.shuoma.annotation.Tag;

import java.util.PriorityQueue;

@Tag(dss = {Array, PriorityQueueT}, tricks = TwoOrMorePointers)
public class KLargestNumbersFromLists {

  public Integer[] kLargestNumbersFromLists(int[][] lists, int k) {
    PriorityQueue<Integer> pq = new PriorityQueue<>();

    for (int[] list: lists) {
      for (int e: list) {
        pq.add(e);
        if (pq.size() == k + 1) {
          pq.poll();
        }
      }
    }
    //System.out.println(pq);
    return pq.toArray(new Integer[0]);
  }

  /** This implementation assumes that all lists are sorted. */
  public Integer[] kLargestNumbersFromLists1(int[][] lists, int k) {
    int n = lists.length;
    int[] pos = new int[n];
    PriorityQueue<Integer> pq = new PriorityQueue<>();

    int sum = 0;
    for (int[] list: lists) {
      sum += list.length;
    }

    int cnt = 0;
    while (cnt < sum) {
      for (int i = 0; i < n; i++) {
        if (pos[i] >= lists[i].length) {
          continue;
        }
        if (pq.isEmpty() || lists[i][pos[i]] > pq.peek()) {
          pq.add(lists[i][pos[i]]);
          if (pq.size() == k + 1) {
            pq.poll();
          }
        }
        pos[i]++;
        cnt++;
      }
    }
    //System.out.println(pq);
    return pq.toArray(new Integer[0]);
  }
}
