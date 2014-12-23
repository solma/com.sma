package com.shuoma.alg.number;

import java.util.Arrays;

/*
 * http://www.mitbbs.com/article_t/JobHunting/32719993.html
 *
 * һ��0��1��ɵ����飬�ı�����һ����0��1����1��0����ʹ�øı�������������0 ����1�ĳ�����󣬷��������󳤶ȡ�Ҫ��O(N)��
 * ����[1 0 1]���ı�0������3 [1 1 0 1 0 0]���ı��м��0����1������4
 */

public class LongestContinuousSubarrayWithChange {

  public static void main(String[] args) {
    System.out.println(maxConsecutive(new int[] {1, 1, 0, 1, 0, 0}));
  }

  static int maxConsecutive(int[] ary) {
    int[] changed = {0, 0};
    int[] noChange = {0, 0};
    int maxL = 0;

    for (int x : ary) {
      maxL = Math.max(maxL, Math.max(++changed[x], ++noChange[x]));
      changed[1 - x] = 1 + noChange[1 - x];
      noChange[1 - x] = 0;
      System.out.println(maxL + " " + Arrays.toString(changed) + " " + Arrays.toString(noChange));
    }

    return maxL;
  }
}
