package com.shuoma.alg.number;
import java.util.Arrays;
import java.util.Collections;
import java.util.PriorityQueue;

/**
 * Given n sorted list, pick n numbers one number from each list
 * such that the difference between the largest and smallest number is minimized.
 */
public class CloestNumbersFromDifferentLists {
  public static void main(String[] args) {
    // sorted lists
    int[][] lists = { {3, 5, 8, 10}, {2, 4, 9}, {5, 6, 7, 11}};
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
}

class Element implements Comparable<Element> {
  int key;
  int value;

  public Element(int key, int value) {
    this.key = key;
    this.value = value;
  }

  @Override
  public int compareTo(Element other) {
    return value - other.value;
  }

  @Override
  public String toString(){
    return "" + value;
  }
}


class MinMaxPriorityQueue {
  PriorityQueue<Element> max;
  PriorityQueue<Element> min;

  public MinMaxPriorityQueue() {
    max = new PriorityQueue<>(10, Collections.reverseOrder());
    min = new PriorityQueue<>();
  }

  public void add(Element e) {
    min.add(e);
    max.add(e);
  }

  // poll using Min
  public Element poll() {
    Element e = min.poll();
    max.remove(e);
    return e;
  }
}
