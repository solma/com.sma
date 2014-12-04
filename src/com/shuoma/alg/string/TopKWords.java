package com.shuoma.alg.string;

import com.shuoma.ds.tree.Trie;
import com.shuoma.ds.tree.TrieNode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.PriorityQueue;

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
    Trie trie = new Trie(words);

    ArrayList<WordCnt> all = new ArrayList<WordCnt>();
    // Traverse the tries
    for (TrieNode node : trie.levelTravesal()) {
      all.add(new WordCnt(node.path, node.cnt));
    }

    topK(all, k);
  }

  public void countByHashMap(String[] words, int k) {
    // count using hashmap
    HashMap<String, Integer> wordCnt = new HashMap<String, Integer>();
    for (String w : words) {
      if (!wordCnt.containsKey(w)) wordCnt.put(w, 0);
      wordCnt.put(w, wordCnt.get(w) + 1);
    }

    ArrayList<WordCnt> all = new ArrayList<WordCnt>();
    for (String w : wordCnt.keySet()) {
      all.add(new WordCnt(w, wordCnt.get(w)));
    }

    topK(all, k);
  }

  public ArrayList<WordCnt> topK(ArrayList<WordCnt> all, int k) {
    PriorityQueue<WordCnt> pq = new PriorityQueue<WordCnt>();
    for (WordCnt w : all) {
      pq.add(w);
      if (pq.size() == k + 1) pq.poll();
    }

    ArrayList<WordCnt> topK = new ArrayList<WordCnt>();
    while (!pq.isEmpty()) {
      WordCnt wc = pq.poll();
      topK.add(wc);
    }
    System.out.println(topK);
    return topK;
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
