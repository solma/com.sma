package com.sma.alg;

import static com.sma.annotation.Tag.Algorithm.Arithmetic;
import static com.sma.annotation.Tag.Algorithm.Backtracking;
import static com.sma.annotation.Tag.Algorithm.Recursion;

import com.sma.annotation.Tag;
import com.sma.util.CollectionsUtil;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;


/**
 * All possible combinations of a sum.
 */
@Tag(algs = {Arithmetic, Backtracking, Recursion})
public class AllDecompositionsOfSum {

  public Set<Map<Integer, Integer>> bottomUpRecursionWithMemory(int n){
    Map<Integer, Set<Map<Integer, Integer>>> decompositionsMem = new HashMap<>();
    bottomUpRecursionWithMemory(n, decompositionsMem);
    return decompositionsMem.get(n);
  }

  void bottomUpRecursionWithMemory(int n, Map<Integer, Set<Map<Integer, Integer>>> allDecomposes) {
    if (allDecomposes.containsKey(n)) { return; }

    Set<Map<Integer, Integer>> setForN = new HashSet<>();
    allDecomposes.put(n, setForN);

    if (n == 0) {
      setForN.add(new HashMap<Integer, Integer>());
      return;
    }

    bottomUpRecursionWithMemory(n - 1, allDecomposes);
    for (int i = 0; i <= n - 1; i++) {
      for (Map<Integer, Integer> decomposition : allDecomposes.get(i)) {
        Map<Integer, Integer> cpy = new HashMap<>(decomposition);
        CollectionsUtil.increaseMapCounter(cpy, n - i, 1);
        setForN.add(cpy);
      }
    }
  }

  public Set<Map<Integer, Integer>> topDownRecursion(int n) {
    Map<Integer, Set<Map<Integer, Integer>>> allDecomposes = new HashMap<>();
    topDownRecursion(n, 1, new HashMap<Integer, Integer>(), allDecomposes);
    //CollectionsUtil.printMap(allDecomposes);
    return allDecomposes.get(n);
  }

  Set<Map<Integer, Integer>> topDownRecursion(int whole, int part, Map<Integer, Integer> cur,
      Map<Integer, Set<Map<Integer, Integer>>> allDecomposes) {

    if (allDecomposes.containsKey(whole)) {
      //return allDecomposes.get(whole);
    }

    Set<Map<Integer, Integer>> ret = new HashSet<>();
    if (whole == 0) {
      // cannot collect allDecomposes
      // ret is no covariant of whole
      // cur contains numbers larger than whole
      ret.add(new HashMap<>(cur));
      return ret;
    }

    for (; part <= whole; part++) {
      CollectionsUtil.increaseMapCounter(cur, part, 1);
      ret.addAll(topDownRecursion(whole - part, part, cur, allDecomposes));
      CollectionsUtil.increaseMapCounter(cur, part, -1);
    }

    allDecomposes.put(whole, ret);
    return ret;
  }
}
