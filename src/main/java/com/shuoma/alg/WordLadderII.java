package com.shuoma.alg;

import static com.shuoma.annotation.Tag.Algorithm.Backtracking;
import static com.shuoma.annotation.Tag.Algorithm.Recursion;
import static com.shuoma.annotation.Tag.DataStructure.StringT;
import static com.shuoma.annotation.Tag.Difficulty.D3;
import static com.shuoma.annotation.Tag.Reference.LeetCode;

import com.shuoma.annotation.Tag;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Set;

@Tag(algs = {Backtracking, Recursion}, dl = D3, dss = StringT, references = LeetCode)
public class WordLadderII {
  public static void main(String[] args) {
    new WordLadderII().main();
  }

  public void main() {
    HashSet<String> dict = new HashSet<>();
    String[] words = {};
    for (int i = 0; i < words.length; i++)
      dict.add(words[i]);

    // String start= "hit", end="cog";
    String start = "cet", end = "ism";

    //ladderLength findAllPaths
    for (List<String> path : findAllPaths(start, end, dict))
      System.out.println(path);
  }

  List<List<String>> findAllPaths(String start, String end, Set<String> dict) {
    PriorityQueue<WordNode> queue = new PriorityQueue<>();
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
          if (!dict.contains(neighborWord))  continue;

          if (visited.containsKey(neighborWord)) {
            WordNode nw = visited.get(neighborWord);
            if (nw.dis < top.dis + 1) {
              continue;
            }
            if (nw.dis > top.dis + 1) {
              nw.prev.clear();
            }
            // update the existing node
            nw.dis = top.dis + 1;
            nw.addPrev(top);
            queue.remove(nw);
            queue.offer(nw);
            continue;
          }
          // not visited before
          WordNode nw = new WordNode(neighborWord, top.dis + 1);
          nw.addPrev(top);
          queue.offer(nw);
          visited.put(nw.word, nw);
        }
      }
    }

    System.out.println(visited.size());

    // there is no end;
    if (!visited.containsKey(end)) {
      return new LinkedList<>();
    }
    return buildPath(visited.get(end));
  }

  List<List<String>> buildPath(WordNode cur) {
    List<List<String>> paths = new LinkedList<>();
    if (cur == null) {
      paths.add(new LinkedList<String>());
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
}
