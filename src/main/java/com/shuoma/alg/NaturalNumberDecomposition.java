package com.shuoma.alg;

import static com.shuoma.annotation.Tag.Algorithm.Backtracking;
import static com.shuoma.annotation.Tag.Algorithm.Recursion;

import com.shuoma.annotation.Tag;
import com.shuoma.util.CollectionsUtil;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

@Tag(algs = {Backtracking, Recursion})
public class NaturalNumberDecomposition {

  public Set<Map<Integer, Integer>> bottomUpRecursion(int n){
    Map<Integer, Set<Map<Integer, Integer>>> allDecomposes = new HashMap<>();
    bottomUpRecursion(n, allDecomposes);
    return allDecomposes.get(n);
  }

  void bottomUpRecursion(int n, Map<Integer, Set<Map<Integer, Integer>>> allDecomposes) {
    Set<Map<Integer, Integer>> setForN = new HashSet<>();

    if (n > 0) {
      bottomUpRecursion(n - 1, allDecomposes);
      for (int i = 0; i <= n - 1; i++) {
        for (Map<Integer, Integer> decomposition : allDecomposes.get(i)) {
          Map<Integer, Integer> cpy = new HashMap<>(decomposition);
          CollectionsUtil.increaseMapCounter(cpy, n - i, 1);
          setForN.add(cpy);
        }
      }
    } else { //n==0
      setForN.add(new HashMap<Integer, Integer>());
    }
    allDecomposes.put(n, setForN);
  }

  public Set<Map<Integer, Integer>> topDownRecursion(int n) {
    return topDownRecursion(n, 1, new HashMap<Integer, Integer>(), 0);
  }

  Set<Map<Integer, Integer>> topDownRecursion(int target, int s, Map<Integer, Integer> cur, int lvl) {
    Set<Map<Integer, Integer>> ret = new HashSet<>();

    if (target == 0) {
      ret.add(new HashMap<>(cur));
      return ret;
    }

    for (; s <= target; s++) {
      CollectionsUtil.increaseMapCounter(cur, s, 1);
      ret.addAll(topDownRecursion(target - s, s, cur, lvl + 1));
      CollectionsUtil.increaseMapCounter(cur, s, -1);
    }
    return ret;
  }
}
