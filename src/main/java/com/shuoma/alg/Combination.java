package com.shuoma.alg;

import static com.shuoma.annotation.Tag.Algorithm.Recursion;

import com.shuoma.annotation.Tag;

import java.util.ArrayList;
import java.util.List;

@Tag(algs = Recursion, dss = Tag.DataStructure.String)
public class Combination {
  public static void main(String[] args) {
     String s1="1233";
     allCombinationsI(s1, new ArrayList<String>());
     System.out.println(allCombinations(s1));
     System.out.println(combinationsOfSizeN(s1, 2));
  }

  public static List<String> allCombinations(String input) {
    return allCombinations(input, new StringBuilder(), 0);
  }

  static List<String> allCombinations(String input, StringBuilder com, int sIdx) {
    List<String> ret = new ArrayList<>();
    for (int i = sIdx; i < input.length(); i++) {
      com.append(input.charAt(i));
      ret.add(com.toString());
      for (String s : allCombinations(input, com, i + 1))
        ret.add(s);
      com.deleteCharAt(com.length() - 1);
    }
    return ret;
  }

  public static List<String> combinationsOfSizeN(String input, int size) {
    return combinationsOfSizeN(input, size, new StringBuilder(), 0);
  }

  static List<String> combinationsOfSizeN(String input, int n, StringBuilder sb, int startIdx) {
    List<String> ret = new ArrayList<>();
    char[] array = input.toCharArray();
    int len = array.length;
    if (sb.length() == n) {
      ret.add(sb.toString());
      return ret;
    }
    for (int i = startIdx; i < len; i++) {
      sb.append(array[i]);
      ret.addAll(combinationsOfSizeN(input, n, sb, i + 1));
      sb.deleteCharAt(sb.length() - 1);
    }
    return ret;
  }

  /** By iteration. */
  static void allCombinationsI(String input, ArrayList<String> res) {
    if (input != null && input.length() > 0) {
      char[] array = input.toCharArray();
      String[] copy = new String[1];
      copy[0] = String.valueOf(array[array.length - 1]);
      res.add(copy[0]);
      for (int i = array.length - 2; i >= 0; i--) {
        for (int j = 0; j < copy.length; j++)
          res.add(String.valueOf(array[i]) + copy[j]);
        res.add(String.valueOf(array[i]));
        copy = res.toArray(new String[1]);
      }
      for (int i = 0; i < res.size(); i++)
        System.out.println((i + 1) + ". " + res.get(i));
      System.out.println();
    }
  }

  static List<String> myInuitiveAlgrithm(String input) {
    List<String> res = new ArrayList<>();
    if (input != null && input.length() > 0) {
      char[] array = input.toCharArray();
      String[] copy = new String[1];
      copy[0] = String.valueOf(array[0]);
      for (int i = 1; i < array.length; i++) {
        for (int j = 0; j < copy.length; j++) {
          for (int idx = 0; idx <= i; idx++)
            res.add(copy[j].substring(0, idx) + String.valueOf(array[i])
                + copy[j].substring(idx, i));
          res.remove(copy[j]);
        }
        copy = res.toArray(new String[1]);
      }
    }
    return res;
  }
}
