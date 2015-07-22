package com.shuoma.alg;

import static com.shuoma.annotation.Tag.DataStructure.Hash;
import static com.shuoma.annotation.Tag.DataStructure.String;
import static com.shuoma.ds.graph.tree.TrieNode.buildTree;

import com.shuoma.annotation.Tag;
import com.shuoma.ds.graph.tree.TrieNode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

@Tag(dss = {Hash, String, Tag.DataStructure.PriorityQueue})
public class TopKWords {

  public static void main(String[] args) {
    new TopKWords().main();
  }

  public void main() {
    String[] words =
        new String[] {"dog", "be", "cut", "car", "cat", "be", "cut", "be", "cut", "car", "cat",
            "be"};
    int K = 3;

    countByHashMap(words, K);
    countByTrie(words, K);
  }

  public void countByTrie(String[] words, int k) {
    TrieNode root = buildTree(words);

    List<WordCnt> all = new ArrayList<>();
    // Traverse the tries
    for (TrieNode node : levelTraversal(root)) {
      all.add(new WordCnt(node.path, node.cnt));
    }

    topK(all, k);
  }

  public void countByHashMap(String[] words, int k) {
    // count using hashmap
    Map<String, Integer> wordCnt = new HashMap<>();
    for (String w : words) {
      if (!wordCnt.containsKey(w)) wordCnt.put(w, 0);
      wordCnt.put(w, wordCnt.get(w) + 1);
    }

    List<WordCnt> all = new ArrayList<>();
    for (String w : wordCnt.keySet()) {
      all.add(new WordCnt(w, wordCnt.get(w)));
    }

    topK(all, k);
  }

  public List<WordCnt> topK(List<WordCnt> all, int k) {
    PriorityQueue<WordCnt> pq = new PriorityQueue<>();
    for (WordCnt w : all) {
      pq.add(w);
      if (pq.size() == k + 1) pq.poll();
    }

    List<WordCnt> topK = new ArrayList<>();
    while (!pq.isEmpty()) {
      WordCnt wc = pq.poll();
      topK.add(wc);
    }
    System.out.println(topK);
    return topK;
  }

  List<TrieNode> levelTraversal(TrieNode root) {
    List<TrieNode> allNodes = new ArrayList<>();
    List<TrieNode> curLvl = new ArrayList<>(), nxtLvl;
    curLvl.add(root);
    while (!curLvl.isEmpty()) {
      nxtLvl = new ArrayList<>();
      while (!curLvl.isEmpty()) {
        TrieNode first = curLvl.remove(0);
        allNodes.add(first);
        for (TrieNode child : first.children.values()) {
          nxtLvl.add(child);
        }
      }
      curLvl = nxtLvl;
    }
    return allNodes;
  }


  class WordCnt implements Comparable<WordCnt> {
    String key;
    int cnt;

    public WordCnt(String w, int cnt) {
      key = w;
      this.cnt = cnt;
    }

    @Override
    public int compareTo(WordCnt other) {
      return cnt - other.cnt;
    }

    @Override
    public String toString() {
      return key + ":" + cnt;
    }
  }
}
