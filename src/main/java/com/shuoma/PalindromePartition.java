package com.shuoma;

import static com.shuoma.annotation.Tag.Algorithm.Recursion;
import static com.shuoma.annotation.Tag.DataStructure.String;
import static com.shuoma.annotation.Tag.Source.LeetCode;

import com.shuoma.annotation.Tag;

import java.util.ArrayList;
import java.util.List;

@Tag(algs = Recursion, dss = String, source = LeetCode)
public class PalindromePartition {
  public static void main(String[] args) {
    new PalindromePartition().main();
  }

  public void main() {
    String s = "acabaacab";
    for (List<String> par : partition(s))
      System.out.println(par);
  }

  public boolean isPalindrome(String s, int sIdx, int eIdx) {
    int i = sIdx, j = eIdx;
    while (i < j) {
      if (s.charAt(i) != s.charAt(j))
        return false;
      i++;
      j--;
    }
    return true;
  }

  public List<List<String>> partition(String s) {
    List<List<String>> allPartitions = new ArrayList<>();
    partition(s, allPartitions, new ArrayList<String>(), 0);
    return allPartitions;
  }

  public void partition(String s, List<List<String>> allPartitions, List<String> curPartition, int l) {
    if (l == s.length()) {
      allPartitions.add(new ArrayList<>(curPartition));
      return;
    }
    for (int i = l; i < s.length(); i++) {
      if (isPalindrome(s, l, i)) {
        curPartition.add(s.substring(l, i + 1));
        //System.out.println(l+" "+i+"  "+curPartition);
        partition(s, allPartitions, curPartition, i + 1);
        curPartition.remove(curPartition.size() - 1);
      }
    }
  }
}
