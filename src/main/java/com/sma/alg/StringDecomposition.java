package com.sma.alg;

// reference: Elements of Programming Interviews
import static com.sma.annotation.Tag.Algorithm.Backtracking;
import static com.sma.annotation.Tag.Algorithm.Recursion;
import static com.sma.annotation.Tag.DataStructure.StringT;

import com.sma.annotation.Tag;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Tag(algs = {Backtracking, Recursion}, dss = StringT)
public class StringDecomposition {
  public static void main(String[] args) {
    new StringDecomposition().main();
  }

  public void main() {
    String[] dict = new String[] {"ab", "cd", "ef", "abc", "d", "ea", "b", "e"};
    List<List<String>> decompositions = decompose(dict, "eabcd");
    for (List<String> decomp : decompositions) {
      System.out.println(decomp);
    }
  }

  public List<List<String>> decompose(String[] dict, String s) {
    Set<String> words = new HashSet<>();
    for (String w : dict)
      words.add(w);

    // the length of the last string of the decomposition at position k
    List<List<Integer>> lenOfLastWord = new ArrayList<>();
    int sLen = s.length();
    for (int i = 0; i < sLen; i++) {
      lenOfLastWord.add(new ArrayList<Integer>());
    }

    for (int i = 0; i < sLen; i++) {
      for (int wLen = i + 1; wLen >= 1; wLen--) {
        if (words.contains(s.substring(i + 1 - wLen, i + 1))
            && (wLen == i + 1 || lenOfLastWord.get(i - wLen).size() > 0))
          lenOfLastWord.get(i).add(wLen);
      }
    }
    return buildDecompositions(s, lenOfLastWord, new ArrayList<String>(), sLen - 1);
  }

  public List<List<String>> buildDecompositions(String s, List<List<Integer>> lenOfLastWord,
      List<String> curDecomp, int idx) {
    List<List<String>> ret = new ArrayList<>();
    if (idx < 0) {
      ret.add(new ArrayList<>(curDecomp));
      return ret;
    }
    for (Integer wLen : lenOfLastWord.get(idx)) {
      String lastWord = s.substring(idx + 1 - wLen, idx + 1);
      curDecomp.add(0, lastWord);
      ret.addAll(buildDecompositions(s, lenOfLastWord, curDecomp, idx - wLen));
      curDecomp.remove(0);
    }
    return ret;
  }

}
