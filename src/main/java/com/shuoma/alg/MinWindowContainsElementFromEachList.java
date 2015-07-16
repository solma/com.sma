package com.shuoma.alg;

import static com.shuoma.annotation.Tag.DataStructure.Array;
import static com.shuoma.annotation.Tag.DataStructure.PriorityQueue;
import static com.shuoma.annotation.Tag.Trick.TwoOrMorePointers;
import static com.shuoma.ds.misc.MinMaxPriorityQueue.Element;

import com.shuoma.annotation.Tag;
import com.shuoma.ds.misc.MinMaxPriorityQueue;

import java.util.Arrays;

@Tag(dss = {Array, PriorityQueue}, tricks = TwoOrMorePointers)
public class MinWindowContainsElementFromEachList {
  public static void main(String[] args) {
    // sorted lists
    int[][] lists = {{3, 5, 8, 10}, {2, 4, 9}, {6, 7, 11}};
    minWindowContainsElementFromEachList(lists);
  }

  public static void minWindowContainsElementFromEachList(int[][] lists) {
    int n = lists.length;
    int[] pos = new int[n];

    MinMaxPriorityQueue pq = new MinMaxPriorityQueue();
    int sum = 0;
    for (int i = 0; i < n; i++) {
      pq.add(new MinMaxPriorityQueue.Element(i, lists[i][0]));
      sum += lists[i].length;
    }

    int[] res = new int[sum];
    int cnt = 0;

    // the min window that contains at least one element from each list
    int[] minWindow = new int[2];
    minWindow[1] = Integer.MAX_VALUE;
    boolean oobArray = false;

    while (cnt < res.length) {
      Element max = pq.max.peek();
      Element min = pq.poll();
      System.out.println(cnt + " : " + max + " " + min);
      if (!oobArray && max.value - min.value < minWindow[1] - minWindow[0]) {
        minWindow[0] = min.value;
        minWindow[1] = max.value;
      }

      int i = min.idxOfOriginList;
      res[cnt++] = lists[i][pos[i]];
      pos[i]++;

      if (pos[i] < lists[i].length) {
        pq.add(new Element(i, lists[i][pos[i]]));
      } else {
        oobArray = true;
      }
    }
    System.out.println(Arrays.toString(res));
    System.out.println(Arrays.toString(minWindow));
  }
}
