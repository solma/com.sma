package com.shuoma;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RepeatedDNASequences {

  public static void main(String[] args) {
    String s = "AAAAACCCCCAAAAACCCCCAAAAAGGGTTT";
    System.out.println(new RepeatedDNASequences().findRepeatedDnaSequencesBest(s));
  }

  // https://oj.leetcode.com/discuss/24478/i-did-it-in-10-lines-of-c
  List<String> findRepeatedDnaSequencesBest(String s) {
    Map<Integer, Integer> m = new HashMap<>();
    List<String> result = new ArrayList<>();
    int t = 0, i = 0, n = s.length();
    if (n < 10) return result;

    int codeBitLength = 3;
    while (i < 9) {
      t = t << codeBitLength | encode(s.charAt(i++), codeBitLength);
    }

    while (i < n) {
      t = t << codeBitLength & 0xFFFFF | encode(s.charAt(i++), codeBitLength);

      if (!m.containsKey(t)) {
        m.put(t, 0);
      }
      int cnt = m.get(t);
      if (cnt == 1) {
        result.add(s.substring(i - 10, i));
      }
      m.put(t, cnt + 1);
    }
    return result;
  }

  int encode(char DNA, int codeBitLength) {
    switch (codeBitLength) {
      case 2:
        switch (DNA) {
          case 'A':
            return 0;
          case 'C':
            return 1;
          case 'G':
            return 2;
          default:
            return 3;
        }
      case 3:
        return DNA & 7;
      default:
        return -1;
    }
  }
}
