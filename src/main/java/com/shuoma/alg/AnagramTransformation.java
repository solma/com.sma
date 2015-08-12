package com.shuoma.alg;

import static com.shuoma.annotation.Tag.Algorithm.BreadthFirstSearch;
import static com.shuoma.annotation.Tag.DataStructure.StringT;
import static com.shuoma.annotation.Tag.Reference.Interview;

import com.shuoma.annotation.Tag;
import com.shuoma.util.ArrayUtil;
import com.shuoma.util.CollectionsUtil;

import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

/**
 Given string S and P, they have same characters but different order, You can perform following two
 operations on S, 1. swap two consecutive characters, 2. swap first and last characters.
 Write code to find the min operation needed to change S into P.
 */
@Tag(algs = BreadthFirstSearch, dss = StringT, references = Interview)
public class AnagramTransformation {

  public static void main(String[] args) {
    AnagramTransformation ins = new AnagramTransformation();
    for (List<String> transformation : ins.transform("NEILA", "ALIEN")) {
      System.out.println(transformation);
    }
  }

  List<List<String>> transform(String s, String p) {
    if (s.equals(p)) return new LinkedList<>();

    Map<String, Collection<String>> predecessors = new HashMap<>();
    CollectionsUtil.upsert(predecessors, s, null, new HashSet<String>());

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

        dis.put(next, dis.get(cur) + 1);
        bfs.offer(next);
        CollectionsUtil.upsert(predecessors, next, cur, new HashSet<String>());
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
