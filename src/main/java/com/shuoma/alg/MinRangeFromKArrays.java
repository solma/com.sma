package com.shuoma.alg;

import static com.shuoma.annotation.Tag.Trick.TwoOrMorePointers;

import com.shuoma.annotation.Tag;

import java.util.Arrays;
import java.util.PriorityQueue;

/**
 * Given K arrays find the smallest interval that contains at least one number from each of the K
 * arrays
 */

@Tag(dss = Tag.DataStructure.PriorityQueueT, tricks = TwoOrMorePointers)
public class MinRangeFromKArrays {
  public static void main(String[] args) {
    new MinRangeFromKArrays().minRange();
  }

  public int[] minRange() {
    int[][] arrays = { {4, 10, 15, 24, 26}, {0, 9, 12, 20}, {5, 18, 22, 30}};

    int n = arrays.length, totLen = 0;
    int[] ptrs = new int[n];
    PriorityQueue<Element> heap = new PriorityQueue<>();
    int max = Integer.MIN_VALUE;
    for (int i = 0; i < n; i++) {
      heap.add(new Element(arrays[i][0], i));
      max = Math.max(max, arrays[i][0]);
      ptrs[i] = 1;
      totLen += arrays[i].length;
    }

    int[] ret = new int[2];
    ret[0] = heap.peek().value;
    ret[1] = max;

    int cnt = 0;
    while (cnt < totLen) {
      Element minE = heap.poll();
      int arr = minE.from;
      if (ptrs[arr] >= arrays[arr].length) {
        break;
      }
      int nxt = arrays[arr][ptrs[arr]++];
      heap.add(new Element(nxt, arr));
      max = Math.max(max, nxt);
      if (ret[1] - ret[0] > max - heap.peek().value) {
        ret[1] = max;
        ret[0] = heap.peek().value;
      }
      cnt++;
    }
    System.out.println(Arrays.toString(ret));
    return ret;
  }

  public class Element implements Comparable<Element> {
    int value;
    int from;

    public Element(int value, int from) {
      this.value = value;
      this.from = from;
    }

    @Override
    public int compareTo(Element other) {
      return value - other.value;
    }
  }
}
