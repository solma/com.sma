package com.shuoma.alg;

import static com.shuoma.annotation.Tag.Algorithm.BitManipulation;
import static com.shuoma.annotation.Tag.DataStructure.Hash;
import static com.shuoma.annotation.Tag.Reference.JulyEdu;

import com.google.common.collect.ImmutableSet;

import com.shuoma.annotation.Tag;

import java.util.Set;

/**
 * Given a dictionary and a String S in the dictionary, find the longest string in the dictionary
 * that does not have any letter in S.
 * Requirement: O(len(S))
 */
@Tag(algs = BitManipulation, dss = Hash, references = JulyEdu)
public class LongestStringWithNoCommonLetter {

  public static void main(String[] args) {
    Set<String> set = ImmutableSet.of("abc", "de", "gfgh", "aaeeqesd", "xyz", "xxyyzz", "xyzwpqp");
    LongestStringWithNoCommonLetter ins = new LongestStringWithNoCommonLetter(set);
    ins.preprocess();
    System.out.println(ins.longestStringWithNoCommonLetter("abc"));
  }

  Set<String> dictionary;
  String[] hashDp = new String[1 << 26];

  public LongestStringWithNoCommonLetter(Set<String> dictionary) {
    this.dictionary = dictionary;
  }

  void preprocess() {
    for (String s : dictionary) {
      int hash = hash(s);
      if (hashDp[hash] == null || hashDp[hash].length() < s.length()) {
        hashDp[hash] = s;
      }
    }

   for (int i = 1; i < hashDp.length; i++) {
      // neighbors, i.e. one bit from 1 -> 0
      for (int j = 0; j < 26; j++) {
        if (((i >>> j) & 1) == 0) continue; // skip 0 bit
        int k = i & (~(1 << j)); // set jth bit to 0
        if (hashDp[i] == null || (hashDp[k] != null && hashDp[i].length() < hashDp[k].length())) {
          hashDp[i] = hashDp[k];
        }
      }
    }
  }

  int hash(String s) {
    int hash = 0;
    s = s.toLowerCase();
    for (char c: s.toCharArray()) {
      hash |= 1 << (c - 'a');
    }
    return hash;
  }

  public String longestStringWithNoCommonLetter(String s) {
    return hashDp[~hash(s) & ((1 << 26) - 1)];
  }
}
