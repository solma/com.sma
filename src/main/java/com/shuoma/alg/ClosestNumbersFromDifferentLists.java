package com.shuoma.alg;

import static com.shuoma.annotation.Tag.DataStructure.Array;
import static com.shuoma.annotation.Tag.DataStructure.PriorityQueue;
import static com.shuoma.annotation.Tag.Trick.TwoOrMorePointers;
import static com.shuoma.ds.misc.MinMaxPriorityQueue.Element;

import com.shuoma.annotation.Tag;
import com.shuoma.ds.misc.MinMaxPriorityQueue;

import java.util.Arrays;

/**
 * Given n sorted list, pick n numbers one number from each list
 * such that the difference between the largest and smallest number is minimized.
 */
@Tag(dss = {Array, PriorityQueue}, tricks = TwoOrMorePointers)
public class ClosestNumbersFromDifferentLists {
  public static void main(String[] args) {
    // sorted lists
    int[][] lists = { {8, 9, 10}, {1, 2, 3}, {5, 6, 7, 11}};
    kWayMerging(lists);
  }

  public static void kWayMerging(int[][] lists) {
    int n = lists.length;
    int[] pos = new int[n];

    MinMaxPriorityQueue pq = new MinMaxPriorityQueue();
    int sum = 0;
    for (int i = 0; i < n; i++) {
      pq.add(new Element(i, lists[i][0]));
      sum += lists[i].length;
    }

    int[] merged = new int[sum];
    int cnt = 0;

    int[] minWindow = new int[2];
    minWindow[1] = Integer.MAX_VALUE;
    boolean oobArray = false;

    while (cnt < merged.length) {
      Element max = pq.max.peek();
      Element min = pq.poll(); // idx of list with smallest number
      System.out.println(cnt + " : " + max + " " + min);
      if (!oobArray && max.value - min.value < minWindow[1] - minWindow[0]) {
        minWindow[0] = min.value;
        minWindow[1] = max.value;
      }

      int i = min.key;
      merged[cnt++] = lists[i][pos[i]];
      pos[i]++;
      if (pos[i] < lists[i].length) {
        pq.add(new Element(i, lists[i][pos[i]]));
      } else {
        oobArray = true;
      }
    }
    System.out.println(Arrays.toString(merged));
    System.out.println(Arrays.toString(minWindow));
  }
}
