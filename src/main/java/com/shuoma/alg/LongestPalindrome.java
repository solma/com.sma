package com.shuoma.alg;

import static com.shuoma.annotation.Tag.Algorithm.Greedy;
import static com.shuoma.annotation.Tag.DataStructure.String;
import static com.shuoma.annotation.Tag.Difficulty.D3;
import static com.shuoma.annotation.Tag.Reference.Interview;

import com.google.common.collect.ImmutableSet;

import com.shuoma.annotation.Tag;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

/**
 * Given a dictionary, find longest palindrome built by the combination of words
 * (without duplication).
 */
// TODO: algorithm is incorrect
@Tag(algs = Greedy, dl = D3, dss = String, references = Interview)
public class LongestPalindrome {
  public static void main(String[] args) {
    Set<String> dict = ImmutableSet.of("cbca", "xxyy", "yyxx", "hhijk", "kji", "acee", "fee", "ca");
    LongestPalindrome sol = new LongestPalindrome();
    System.out.println(sol.getLongestPalindrome(dict));
  }

  String getLongestPalindrome(Set<String> dictData) {
    // we need to process the words from longest to shortest based on length.
    // e.g. for “hhijk” and “kji”, if “kji” is processed first then
    // it will be deleted and “hhijk” won’t find a match.
    TreeSet<String> dict = new TreeSet<>(new Comparator<String>() {
      @Override public int compare(String o1, String o2) {
        if (o2.length() != o1.length())
          return o2.length() - o1.length();
        return o2.compareTo(o1);
      }
    });
    dict.addAll(dictData);
    System.out.println(dict);

    Map<String, String> palindromes = new HashMap<>();  // left word -> right word
    String centerPalindrome = "";

    while (!dict.isEmpty()) {
      String word = dict.iterator().next();
      String reversedWord = new StringBuilder(word).reverse().toString();
      // direct reversing match is the longest match for current word
      if (dict.contains(reversedWord) && !reversedWord.equals(word)) {
        palindromes.put(word, reversedWord);
        dict.remove(word);
        dict.remove(reversedWord);
      } else {

        //     try to search palindrome solution with part of the word. E.g. "dcb" is a match for "abcd".
        //     it's possible the word is like "aabcdd", so we need to two scans from each side to find
        //     possible palindrome solution. for the partly matched palindrome pair solution,
        //     it can only be placed in the center of final string.

        String matched = null;
        // scan from left to right
        for (int i = 0; i < word.length() - 1; i++) {
          if (i == 0 || word.charAt(i) == word.charAt(i - 1)) {
            String subWord = word.substring(i + 1);
            String reversedSubWord = new StringBuilder(subWord).reverse().toString();
            if (dict.contains(reversedSubWord)) {
              int length = word.length() + reversedWord.length();
              if (centerPalindrome.length() < length) {
                centerPalindrome = reversedSubWord + word;
                matched = reversedSubWord;
              }
              break; // following solutions must be shorter than current one.
            }
          }
        }

        // scan from right to left
        for (int i = word.length() - 1; i > 0; i--) {
          if (i == word.length() - 1 || word.charAt(i) == word.charAt(i + 1)) {
            String subWord = word.substring(0, i);
            String reversedSubWord = new StringBuilder(subWord).reverse().toString();
            if (dict.contains(reversedSubWord)) {
              int length = word.length() + reversedSubWord.length();
              if (centerPalindrome.length() < length) {
                centerPalindrome = word + reversedSubWord;
                matched = reversedSubWord;
              }
              break;
            }
          }
        }

        if (matched == null && isPalindrome(word) && centerPalindrome.length() < word.length()) {
          // check whether the word itself is a palindrome
          // this is the shortest possible palindrome substring with current word included
          centerPalindrome = word;
        }

        if (matched != null)
          dict.remove(matched);
        dict.remove(word);
      }
    }

    StringBuilder builder = new StringBuilder(centerPalindrome);
    System.out.println(centerPalindrome);
    System.out.println(palindromes);
    for (Map.Entry<String, String> entry : palindromes.entrySet()) {
      builder.insert(0, entry.getKey());
      builder.append(entry.getValue());
    }

    return builder.toString();
  }

  boolean isPalindrome(String s) {
    for (int i = 0, j = s.length() - 1; i < j; i++, j--) {
      if (s.charAt(i) != s.charAt(j))
        return false;
    }
    return true;
  }
}
