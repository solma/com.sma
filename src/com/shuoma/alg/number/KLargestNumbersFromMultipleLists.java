package com.shuoma.alg.number;

import java.util.PriorityQueue;

public class KLargestNumbersFromMultipleLists {
  public static void main(String[] args) {
    int[][] lists = {{3, 5, 8, 19}, {9, 2, 4}, {7, 6, 1}};
    kLargestNumbers(lists, 5);
  }

  public static void kLargestNumbers(int[][] lists, int k) {
    int n = lists.length;
    int[] pos = new int[n];
    PriorityQueue<Integer> pq = new PriorityQueue<>();

    int totalCnt = 0;
    for(int[] list : lists) totalCnt += list.length;

    int cnt = 0;
    while (cnt < totalCnt) {
      for (int i = 0; i < n; i++) {
        if (pos[i] < lists[i].length) {
          if (pq.size() == 0 || lists[i][pos[i]] > pq.peek()) {
            if (pq.size() == k) pq.poll();
            pq.add(lists[i][pos[i]]);
          }
          pos[i]++;
          cnt++;
        }
      }
    }
    System.out.println(pq);
  }
}
