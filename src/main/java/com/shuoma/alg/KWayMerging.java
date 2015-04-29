package com.shuoma.alg;

import static com.shuoma.annotation.Tag.DataStructure.Array;
import static com.shuoma.annotation.Tag.DataStructure.PriorityQueue;
import static com.shuoma.annotation.Tag.Trick.TwoOrMorePointers;
import static com.shuoma.ds.misc.MinMaxPriorityQueue.Element;

import com.shuoma.annotation.Tag;
import com.shuoma.ds.misc.MinMaxPriorityQueue;

import java.util.Arrays;
import java.util.PriorityQueue;

/**
 * Given n arrays with size K, produce K largest numbers among all arrays. Consider this is the
 * reduce step of picking K largest numbers from N numbers where N is huge. The map step is to split
 * N into ceil(N/K) arrays and produce top K for each array.
 */
@Tag(dss = {Array, PriorityQueue}, tricks = TwoOrMorePointers)
public class KWayMerging {
  public static void main(String[] args) {
    //kLargestNumbersFromSizeKLists();
    kWayMerging();
  }

  public static void kWayMerging() {
    // sorted lists
    int[][] lists = {{3, 5, 8, 10}, {2, 4, 9}, {1, 6, 7, 11}};

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

    int[] minWindow = new int[2];
    minWindow[1] = Integer.MAX_VALUE;
    boolean oobArray = false;

    while (cnt < res.length) {
      Element max = pq.max.peek();
      Element min = pq.poll(); // idx of list with smallest number
      System.out.println(cnt + " : " + max + " " + min);
      if (!oobArray && max.value - min.value < minWindow[1] - minWindow[0]) {
        minWindow[0] = min.value;
        minWindow[1] = max.value;
      }

      int i = min.key;
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

  public static void kLargestNumbersFromSizeKLists() {
    int[][] lists = {{3, 5, 8}, {9, 2, 4}, {7, 6, 1}};
    int n = lists.length;
    int k = lists[0].length;
    int[] pos = new int[n];
    PriorityQueue<Integer> pq = new PriorityQueue<>();

    int cnt = 0;
    while (cnt < n * k) {
      for (int i = 0; i < n; i++) {
        if (pos[i] < k) {
          if (pq.size() == 0 || lists[i][pos[i]] > pq.peek()) {
            if (pq.size() == k) {
              pq.poll();
            }
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
