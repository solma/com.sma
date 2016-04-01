package com.sma.alg;

import static com.sma.annotation.Tag.Algorithm.Backtracking;
import static com.sma.annotation.Tag.DataStructure.StringT;

import com.sma.annotation.Tag;

import java.util.Arrays;

@Tag(algs = Backtracking, dss = StringT)
public class StringMatching {
  public static void main(String[] args) {
    new StringMatching().main();
  }

  void main() {
    // TODO AhoCorasick algorithm

    System.out.println(Arrays.toString(next("ababc", "kmp")));

    // match('abbba', 'bba', 'bf')
    String mode = "kmp";
    // int idx=match("ababcababababcabab", "baba", "kmp");
    // /System.out.println(idx);
    String s;
    // s="participate in parachute";
    s = "abab";
    System.out.println(s + " 's next=" + Arrays.toString(next(s, mode)));
    s = "ababcababababcabab";
    System.out.println(s + " 's next=" + Arrays.toString(next(s, mode)));

  }

  // calculate the next array in KMP/sunday algorithm
  public int[] next(String t, String method) {
    int[] next = null;
    switch (method) {
      case "kmp":
        next = new int[t.length()];
        int i = 0,
            j = -1;
        next[i] = j;
        while (i < t.length() - 1) {
          System.out.print("i=" + i + " " + t.charAt(i) + " j=" + j + " ");
          if (j >= 0) { System.out.print(t.charAt(j)); }
          System.out.println(" " + t.substring(0, i + 1) + " " + Arrays.toString(
              Arrays.copyOfRange(next, 0, i + 1)));

          if (j == -1 || t.charAt(i) == t.charAt(j)) { next[++i] = ++j; }
          else { j = next[j]; }
        }
        break;
      case "sunday":
        next = new int[26];
        for (i = 0; i < next.length; i++) { next[i] = t.length(); }
        for (i = 0; i < t.length(); i++) { next[t.charAt(i) - 'a'] = t.length() - i; }
      default:
        break;
    }

    return next;
  }

  public int match(String s, String t, String method) {
    // preprocessing before match
    int[] next = new int[t.length()];
    if (method.equals("kmp") || method.equals("sunday")) { next = next(t, method); }

    int i, j;
    i = j = 0;
    while (i + j < s.length()) {
      if (s.charAt(i + j) == t.charAt(j)) {
        if (j == t.length() - 1) {
          System.out.println(s.substring(0, i) + " " + s.substring(i, i + t.length()) + " " + s
              .substring(i + t.length()));
          return i;
        }
        j += 1;
      } else {
        if (method.equals("kmp")) { // kmp
          i += j - next[j];
          j = Math.max(next[j], 0);
        } else {
          if (method.equals("bf")) {// brutal force
            i += 1; // when t does not have a "pattern", i can increase more aggressively, i.e.
            // max(1,j)
            j = 0;
          } else { // sunday
            i += next[s.charAt(Math.min(i + t.length(), s.length() - 1)) - 'a'];
            j = 0;
          }
        }
      }
    }
    System.out.println(s + " failed to match " + t);
    return -1;
  }
}
