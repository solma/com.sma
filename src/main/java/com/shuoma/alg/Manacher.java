package com.shuoma.alg;

import static com.shuoma.annotation.Tag.Algorithm.Greedy;
import static com.shuoma.annotation.Tag.DataStructure.Substring;
import static com.shuoma.annotation.Tag.Difficulty.D3;
import static com.shuoma.annotation.Tag.Reference.Interview;

import com.shuoma.annotation.Tag;

import java.util.Arrays;

// reference: http://articles.leetcode.com/2011/11/longest-palindromic-substring-part-ii.html
@Tag(algs = Greedy, dl = D3, dss = Substring, references = Interview)
public class Manacher {

  // test client
  public static void main(String[] args) {
    String s = "aaadawadaq";
    Manacher manacher = new Manacher(s);
    System.out.println(manacher.longestPalindromicSubstring());
    for (int i = 0; i < 2 * s.length(); i++) {
      System.out.println(i + ": " + manacher.p[i] + " " + manacher.longestPalindromicSubstring(i));
    }
  }

  private int[] p;  // p[i] = length of longest palindromic substring of t, centered at i
  private String s;  // original string
  private char[] t;  // transformed string

  public Manacher(String s) {
    this.s = s;
    preprocess();
    p = new int[t.length];

    int center = 0, right = 0;
    for (int i = 1; i < t.length - 1; i++) {
      int mirror = 2 * center - i;

      if (right > i) {
        p[i] = Math.min(right - i, p[mirror]);
      }

      // attempt to expand palindrome centered at i
      while (t[i + (1 + p[i])] == t[i - (1 + p[i])]) {
        p[i]++;
      }

      // if palindrome centered at i expands past right,
      // adjust center based on expanded palindrome.
      if (i + p[i] > right) {
        center = i;
        right = i + p[i];
      }
      System.out.println("i: " + i + " p[i]: " + p[i] + " center: " + center + " right: " + right);
    }
    System.out.println(Arrays.toString(p));
  }

  // Transform s into t.
  // For example, if s = "abba", then t = "$#a#b#b#a#@"
  // the # are interleaved to avoid even/odd-length palindromes uniformly
  // $ and @ are prepended and appended to each end to avoid bounds checking
  private void preprocess() {
    int sl = s.length();
    t = new char[sl * 2 + 3];
    Arrays.fill(t, '#');
    t[0] = '$';
    t[t.length - 1] = '@';
    for (int i = 0; i < sl; i ++) {
      t[2 * i + 2] = s.charAt(i);
    }
    System.out.println(t);
  }

  // longest palindromic substring
  public String longestPalindromicSubstring() {
    int length = 0;   // length of longest palindromic substring
    int center = 0;   // center of longest palindromic substring
    for (int i = 1; i < p.length - 1; i++) {
      if (p[i] > length) {
        length = p[i];
        center = i;
      }
    }
    return s.substring((center - 1 - length) / 2, (center - 1 + length) / 2);
  }

  // longest palindromic substring centered at index i/2
  public String longestPalindromicSubstring(int i) {
    int length = p[i + 2];
    int center = i + 2;
    return s.substring((center - 1 - length) / 2, (center - 1 + length) / 2);
  }
}
