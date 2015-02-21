package com.shuoma.alg.graph;

import com.shuoma.util.ArrayUtil;
import com.shuoma.util.CollectionsUtil;

import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

/**
 Given string S and P, they have same characters but different order, You can perform following two
 operations on S, 1. swap two consecutive characters, 2. swap first and last characters.
 Write code to find the min operation needed to change S into P.
 */
public class StringWithSameLengthTransformation {

  public static void main(String[] args) {
    StringWithSameLengthTransformation ins = new StringWithSameLengthTransformation();
    for (List<String> transformation : ins.transform("ASAP", "APAS")) {
      System.out.println(transformation);
    }
  }

  List<List<String>> transform(String s, String p) {
    if (s.equals(p)) return new LinkedList<>();

    Map<String, Collection<String>> predecessors = new HashMap<>();

    Set<String> dummyHead = new HashSet<>();
    dummyHead.add(null);
    predecessors.put(s, dummyHead);

    Map<String, Integer> dis = new HashMap<>();
    dis.put(s, 0);

    int n = s.length();
    Queue<String> bfs = new LinkedList<>();
    bfs.offer(s);

    while (!bfs.isEmpty()) {
      String cur = bfs.poll();
      if (cur.equals(p)) {
        return buildPath(cur, predecessors);
      }
      for (int i = 0; i < n; i++) {
        char[] curArray = cur.toCharArray();
        ArrayUtil.swap(curArray, i % n, (i + 1) % n); // swap
        String next = new String(curArray);

        if (dis.containsKey(next) && dis.get(next) < dis.get(cur) + 1) {
          continue;
        }

        if (!dis.containsKey(next) || dis.get(next) > dis.get(cur) + 1) {
          predecessors.put(next, new HashSet<String>());
          dis.put(next, dis.get(cur) + 1);
        }
        CollectionsUtil.upsert(predecessors, next, cur, new HashSet<String>());
        bfs.offer(next);
      }
    }
    return new LinkedList<>();
  }

  List<List<String>> buildPath(String cur, Map<String, Collection<String>> path) {
    List<List<String>> allPaths = new LinkedList<>();
    if (cur == null) {
      allPaths.add(new LinkedList<String>());
    } else {
      for (String prevString : path.get(cur)) {
        for (List<String> prevPath : buildPath(prevString, path)) {
          prevPath.add(cur);
          allPaths.add(prevPath);
        }
      }
    }
    return allPaths;
  }
}
