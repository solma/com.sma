package com.sma.alg;

import com.sma.annotation.Tag;

import java.util.*;

import static com.sma.annotation.Tag.Algorithm.Backtracking;
import static com.sma.annotation.Tag.Algorithm.Recursion;
import static com.sma.annotation.Tag.DataStructure.StringT;
import static com.sma.annotation.Tag.Difficulty.D3;
import static com.sma.annotation.Tag.Reference.LeetCode;

@Tag(algs = {Backtracking, Recursion}, dl = D3, dss = StringT, references = LeetCode)
public class WordLadderII {
  public static void main(String[] args) {
    new WordLadderII().main();
  }

  public void main() {
    HashSet<String> dict = new HashSet<>();
    String[] words = {"hit", "hot", "dot", "dog", "lot", "log", "cog"};
    for (int i = 0; i < words.length; i++) {
      dict.add(words[i]);
    }

    String start = "hit", end = "cog";
    //String start = "cet", end = "ism";

    List<List<String>> paths = findAllPaths(start, end, dict);
    System.out.println("no of paths: " + paths.size());
    for (List<String> path : paths) {
      System.out.println(path);
    }
  }

  List<List<String>> findAllPaths(String start, String end, Set<String> dict) {
    Queue<WordNode> queue = new LinkedList<>();
    Map<String, WordNode> visited = new HashMap<>();
    WordNode startNode = new WordNode(start, 0);
    startNode.addPrev(null);
    queue.offer(startNode);
    visited.put(start, startNode);
    while (!queue.isEmpty()) {
      WordNode top = queue.poll();
      String topWord = top.word;
      //System.out.println(topWord);
      if (topWord.equals(end)) {
        break;
      }
      for (int i = 0; i < topWord.length(); i++) {
        for (int j = 0; j < 26; j++) {
          char replacementChar = (char) ('a' + j);
          if (replacementChar == topWord.charAt(i)) continue;
          String neighborWord = getNeighborWord(topWord, i, replacementChar);
          if (!dict.contains(neighborWord)) continue;

          if (!visited.containsKey(neighborWord)) {
            WordNode nw = new WordNode(neighborWord, top.dis + 1);
            nw.addPrev(top);
            queue.offer(nw);
            visited.put(nw.word, nw);
            continue;
          }

          // visited before
          WordNode visitedNeighbor = visited.get(neighborWord);
          if (visitedNeighbor.dis < top.dis + 1) {
            continue;
          }

          // visitedNeighbor.dis = top.dis + 1
          visitedNeighbor.addPrev(top);
          // impossible visitedNeighbor.dis > top.dis + 1
        }
      }
    }

    //System.out.println("visited size: " + visited.size());

    // there is no end;
    if (!visited.containsKey(end)) {
      return new LinkedList<>();
    }
    return buildPath(visited.get(end));
  }

  List<List<String>> buildPath(WordNode cur) {
    List<List<String>> paths = new LinkedList<>();
    if (cur == null) {
      paths.add(new LinkedList<>());
      return paths;
    }
    for (WordNode prev : cur.prev) {
      for (List<String> path : buildPath(prev)) {
        path.add(cur.word);
        paths.add(path);
      }
    }
    return paths;
  }

  String getNeighborWord(String cur, int replaceIdx, char replaceChar) {
    StringBuilder neighborWordBuilder = new StringBuilder(cur);
    neighborWordBuilder.setCharAt(replaceIdx, replaceChar);
    return neighborWordBuilder.toString();
  }
}


class WordNode implements Comparable<WordNode> {
  int dis;
  String word;
  List<WordNode> prev;

  public WordNode(String word, int dis) {
    this.dis = dis;
    this.word = word;
    prev = new LinkedList<>();
  }

  public void addPrev(WordNode prevWord) {
    prev.add(prevWord);
  }

  @Override public int compareTo(WordNode other) {
    return dis - other.dis;
  }

  @Override
  public String toString() {
    return word + ":" + dis;
  }
}
