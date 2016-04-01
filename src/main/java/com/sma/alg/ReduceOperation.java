package com.sma.alg;

import static com.sma.annotation.Tag.Algorithm.Recursion;
import static com.sma.annotation.Tag.DataStructure.StringT;
import static com.sma.annotation.Tag.Trick.AccumulativeSum;

import com.sma.annotation.Tag;

import java.util.HashMap;
import java.util.Map;

/**
 * Given a string consisting of a, b and c, if two consecutive letters are different they can be
 * replaced by the third letter, e.g. ab -> c. Return the shortest string that can be
 * obtained via this operation.
 */
@Tag(algs = Recursion, dss = StringT, tricks = AccumulativeSum)
public class ReduceOperation {
  public static void main(String[] args) {
    String input = "cbcabacbbbabc"; //abcba
    String res = minLength(input);

    while (!res.equals(input)) {
      System.out.println(res);
      res = path.get(res);
    }
    System.out.println(input);
  }

  static Map<String, String> atoms = new HashMap<>();
  static {
    atoms.put("ab", "c");
    atoms.put("ba", "c");
    atoms.put("ac", "b");
    atoms.put("ca", "b");
    atoms.put("bc", "a");
    atoms.put("cb", "a");
  }

  static Map<String, String> path = new HashMap<>();

  static String minLength(String s) {
    // edge case
    if (atoms.containsKey(s)){
      path.put(atoms.get(s), s);
      return atoms.get(s);
    }

    String res = s;
    for (int i = 0; i < s.length() - 1; i++) {
      if (s.charAt(i) != s.charAt(i + 1)) {
        String reduced = s.substring(0, i) + minLength(s.substring(i, i + 2)) + s.substring(i + 2);
        path.put(reduced, s);
        String recur = minLength(reduced);
        if (recur.length() < res.length()) {
          res = recur;
        }
      }
    }
    //System.out.println(path);
    return res;
  }
}
