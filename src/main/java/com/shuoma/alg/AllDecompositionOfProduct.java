package com.shuoma.alg;

import static com.shuoma.annotation.Tag.Algorithm.Arithmetic;
import static com.shuoma.annotation.Tag.Algorithm.Backtracking;
import static com.shuoma.annotation.Tag.Algorithm.Recursion;

import com.shuoma.annotation.Tag;
import com.shuoma.util.CollectionsUtil;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * All possible combinations of a product.
 */
@Tag(algs = {Arithmetic, Backtracking, Recursion})
public class AllDecompositionOfProduct {

  public static void main(String[] args) {
    AllDecompositionOfProduct ins = new AllDecompositionOfProduct();
    System.out.println(ins.bottomUpRecursionWithMemory(108));
  }

  public Set<Map<Integer, Integer>> bottomUpRecursionWithMemory(int n){
    Map<Integer, Set<Map<Integer, Integer>>> decompositionsMem = new HashMap<>();
    bottomUpRecursionWithMemory(n, decompositionsMem);
    return decompositionsMem.get(n);
  }

  void bottomUpRecursionWithMemory(int cur, Map<Integer, Set<Map<Integer, Integer>>> decompositionsMem) {
    if (decompositionsMem.containsKey(cur)) { return; }

    Set<Map<Integer, Integer>> setForN = new HashSet<>();
    decompositionsMem.put(cur, setForN);

    for (int next = cur / 2; next >= 2; next--) {
      if (cur % next != 0) continue;
      bottomUpRecursionWithMemory(next, decompositionsMem);

      setForN.add(getPair(cur, next)); // since recursion does not return n*1
      for (Map<Integer, Integer> decomposition : decompositionsMem.get(next)) {
        Map<Integer, Integer> cpy = new HashMap<>(decomposition);
        CollectionsUtil.increaseMapCounter(cpy, cur / next, 1);
        setForN.add(cpy);
      }
    }
    System.out.println(cur + " : " + setForN);
  }

  Map<Integer, Integer> getPair(int n, int factor) {
    Map<Integer, Integer> pair = new HashMap<>();
    if (factor != n / factor) {
      pair.put(factor, 1);
      pair.put(n / factor, 1);
    } else {
      pair.put(factor, 2);
    }
    return pair;
  }
}
