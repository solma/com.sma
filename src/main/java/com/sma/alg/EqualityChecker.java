package com.sma.alg;

import static com.sma.annotation.Tag.DataStructure.UnionFind;
import static com.sma.annotation.Tag.Reference.Interview;

import com.sma.annotation.Tag;

import java.util.HashMap;
import java.util.Map;

/**
 Given a list of equality relations such as A = B, and a list of inequality relations
 such as B ~ C (~ means not equal), check if these two lists contradict each other.
 */
@Tag(dss = UnionFind, references = Interview)
public class EqualityChecker {

  boolean isValid(String[] equality, String[] inequality) {
    Map<Character, Character> parent = new HashMap<>();
    Map<Character, Integer> setSize = new HashMap<>();

    // iterate equality relations and build unions
    for (String r : equality) {
      String[] vars = r.replaceAll("\\s+", "").split("=");
      char x = vars[0].charAt(0), y = vars[1].charAt(0);
      if (!parent.containsKey(x)) {
        parent.put(x, x);
        setSize.put(x, 1);
      }
      if (!parent.containsKey(y)) {
        parent.put(y, y);
        setSize.put(y, 1);
      }
      char xParent = findParent(x, parent), yParent = findParent(y, parent);
      if (xParent == yParent) { continue; }

      if (setSize.get(xParent) >= setSize.get(yParent)) {
        setSize.put(xParent, setSize.get(xParent) + setSize.get(yParent));
        parent.put(yParent, xParent);
      } else {
        setSize.put(yParent, setSize.get(xParent) + setSize.get(yParent));
        parent.put(xParent, yParent);
      }
    }
    System.out.println(parent);
    System.out.println(setSize);

    // iterate inequality relations and search for contradictions
    for (String r: inequality) {
      String[] vars = r.replaceAll("\\s+", "").split("~");
      char x = vars[0].charAt(0), y = vars[1].charAt(0);
      if (findParent(x, parent) == findParent(y, parent)) { return false; }
    }
    return true;
  }

  char findParent(char c, Map<Character, Character> parent) {
    if (!parent.containsKey(c)) { return c; }// for inequality case

    while(parent.get(c) != c) { c = parent.get(c); }
    return c;
  }
}
