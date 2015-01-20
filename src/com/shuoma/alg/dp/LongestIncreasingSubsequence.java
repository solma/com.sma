package com.shuoma.alg.dp;

// http://blog.csdn.net/liangbopirates/article/details/9421399
import com.shuoma.util.ArrayUtil;
import com.shuoma.util.RandomUtil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

public class LongestIncreasingSubsequence {
  public static void main(String[] args) {
    new LongestIncreasingSubsequence().main();
  }

  public void main() {
    for (int i = 0; i < 5; i++) {
      int[] num = RandomUtil.genRandomArray(10, 100, false, false);
      // {77, 97, 30, 63, 89, 75, 99, 34, 43, 80, 46, 37, 64, 31, 23, 12}
      // num=new int[]{77, 97, 30, 63, 89, 75, 99, 34, 43, 80, 46, 37, 64, 31, 23, 12};
      // System.out.println("array=" + Arrays.toString(num));
      int[] a = queueBasedLengthOnly(num);
      int[] b = dp(num);
      int[] c = dpWithBinarySearch(num);
      int[] d = patienceSortingMethod(num);
      if (!ArrayUtil.equals(b, d)) {
        System.out.println("array = " + Arrays.toString(num));
        System.out.println("b = " + Arrays.toString(b));
        System.out.println("c = " + Arrays.toString(c));
        System.out.println("d = " + Arrays.toString(d));
      }
      if (a == null || b == null || a.length != b.length || a.length != c.length) {
        System.out.println("array = " + Arrays.toString(num));
        System.out.println(a.length + "  " + Arrays.toString(a));
        System.out.println(b.length + "  " + Arrays.toString(b));
        System.out.println(c.length + " " + Arrays.toString(c));
      }
    }
  }

  int[] patienceSortingMethod(int[] num) {
    List<Node> pileTops = new LinkedList<>();
    for (int i : num) {
      Node n = new Node(i);
      int insertionIdx = Collections.binarySearch(pileTops, n);
      if (insertionIdx < 0) insertionIdx = ~insertionIdx;
      if (insertionIdx > 0) n.prev = pileTops.get(insertionIdx - 1);
      if (insertionIdx < pileTops.size())
        pileTops.set(insertionIdx, n);
      else
        pileTops.add(n);
    }

    List<Integer> res = new LinkedList<>();
    for (Node cur = pileTops.get(pileTops.size() - 1); cur != null; cur = cur.prev) {
      res.add(0, cur.val);
    }
    return ArrayUtil.integerListToIntArray(res);
  }

  class Node implements Comparable<Node> {
    int val;
    Node prev;

    public Node(int value) {
      this.val = value;
    }

    @Override
    public int compareTo(Node o) {
      return val - o.val;
    }
  }


  // this can only be used to get the length but not the actual sequence
  int[] queueBasedLengthOnly(int[] num) {
    int n = num.length;
    if (n == 0) return null;
    ArrayList<Integer> queue = new ArrayList<Integer>();
    for (int i = 0; i < n; i++) {
      if (queue.isEmpty() || queue.get(queue.size() - 1) < num[i])
        queue.add(num[i]);
      else {
        int L = -1, R = queue.size();
        // replace the first one larger or equal than num[i]
        while (L + 1 != R) {
          int M = L + (R - L) / 2;
          if (queue.get(M) < num[i])
            L = M;
          else
            R = M;
        }
        // replace Rth element;
        queue.remove(R);
        queue.add(R, num[i]);
      }
    }
    int[] ret = new int[queue.size()];
    for (int i = 0; i < ret.length; i++)
      ret[i] = queue.get(i);
    return ret;
  }

  int[] dpWithBinarySearch(int[] nums) {

    HashMap<Integer, LIS> lisOfAllLength = new HashMap<Integer, LIS>();
    LIS zeroLIS = new LIS(0, 0);
    zeroLIS.add(new ArrayList<Integer>());
    lisOfAllLength.put(0, zeroLIS);

    int maxLis = 0;
    for (int i = 0; i < nums.length; i++) {
      int l = -1, r = maxLis + 1;
      while (l + 1 != r) {
        int m = l + (r - l) / 2;
        if (lisOfAllLength.get(m).min >= nums[i])
          r = m;
        else
          l = m;
      }

      if (l >= 0 && lisOfAllLength.get(l).min < nums[i]) {
        int curLen = l + 1;
        if (maxLis < curLen) {
          maxLis = curLen;
          lisOfAllLength.put(curLen, new LIS(curLen, nums[i]));
        }
        LIS curLIS = lisOfAllLength.get(curLen);
        for (ArrayList<Integer> path : lisOfAllLength.get(l).paths) {
          if (path.size() == 0 || path.get(path.size() - 1) < nums[i]) {
            ArrayList<Integer> seq = new ArrayList<Integer>(path);
            seq.add(nums[i]);
            curLIS.add(seq);
          }
        }
        curLIS.min = nums[i];
      }
      // System.out.println("i=" + i + " l=" + l + ", maxLis=" + maxLis);
      for (Integer len : lisOfAllLength.keySet()) {
        // System.out.println("len=" + len + " :" + "  min=" + lisOfAllLength.get(len).min +
        // "   lis=" + lisOfAllLength.get(len).paths);
      }
    }

    int[] ret = new int[maxLis];
    for (int i = 1; i <= maxLis; i++) {
      ret[i - 1] = lisOfAllLength.get(i).min;
    }

    return ret;
  }

  class LIS {
    int len;
    int min;
    ArrayList<ArrayList<Integer>> paths;

    public LIS(int length, int minEndValue) {
      this.len = length;
      this.min = minEndValue;
      paths = new ArrayList<ArrayList<Integer>>();
    }

    public void add(ArrayList<Integer> path) {
      paths.add(path);
    }
  }

  int[] dp(int[] values) {
    int max = 0;
    int[] optimal = new int[values.length];
    int[] seq = new int[values.length];

    for(int i = 0; i < optimal.length; i ++) {
        optimal[i] = 1;
        seq[i] = i;
    }

    for(int i = 1; i < optimal.length; i ++) {
        for(int j = 0; j < i; j ++ ) {
            if(values[j] < values[i])
                if(optimal[j] + 1 > optimal[i]) {
                    optimal[i] = optimal[j] + 1;
                    seq[i] = j;
                    if(optimal[i] > optimal[max]) max = i;
                }
        }
    }

    //backtrace the result
    int[] result = new int[optimal[max]];
    int current = max;
    for(int i = result.length - 1; i >= 0; i-- ){
        result[i] = values[current];
        current = seq[current];
    }
    return result;
  }
}
