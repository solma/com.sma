package com.shuoma.alg;

import static com.shuoma.annotation.Tag.Algorithm.Backtracking;
import static com.shuoma.annotation.Tag.Algorithm.DynamicProgramming;

import com.shuoma.annotation.Tag;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
// An ATM can only dispense values of $1, $5, $20, and $50. Write code to find out the number of unique ways that a $ amount
// of X can be tendered, ($1,$5) is distinct from ($5, $1).
 */
@Tag(algs = {Backtracking, DynamicProgramming})
public class ChangeCoin {
  public static void main(String[] args) {
    int n = 100;

    //Map<Integer, List<List<Integer>>> memory = new HashMap<>();
    //change(n, new int[] {1, 5, 20, 50}, memory);

    Map<Integer, Long> memory = new HashMap<>();
    changeCnt(n, new int[] {1, 5, 20, 50}, memory);

    //System.out.println(memory.get(n).size());
    System.out.println(memory.get(n));
  }

  static long changeCnt(int n, int[] denominations, Map<Integer, Long> memory) {
    long cnt = 0;
    if (memory.containsKey(n)) {
      return memory.get(n);
    }
    if (n == 0) {
      return 1;
    }
    if (n < 0) {
      return 0;
    }

    for (int val : denominations) {
      cnt += changeCnt(n - val, denominations, memory);
    }
    memory.put(n, cnt);
    return cnt;
  }

  static List<List<Integer>> change(int n, int[] denominations,
      Map<Integer, List<List<Integer>>> memory) {
    List<List<Integer>> ret = new LinkedList<>();
    if (memory.containsKey(n)) {
      return memory.get(n);
    }
    if (n == 0) {
      ret.add(new LinkedList<Integer>());
      return ret;
    }
    if (n < 0) {
      return null;
    }

    for (int val : denominations) {
      List<List<Integer>> nxt = change(n - val, denominations, memory);
      if (nxt == null) {
        continue;
      }
      for (List<Integer> seq : nxt) {
        List<Integer> seqCpy = new LinkedList<>(seq);
        seqCpy.add(val);
        ret.add(seqCpy);
      }
    }
    memory.put(n, ret);
    return ret;
  }
}
