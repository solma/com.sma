package com.shuoma.alg;

import static com.google.common.base.Strings.isNullOrEmpty;
import static com.shuoma.annotation.Tag.Algorithm.Recursion;

import com.shuoma.annotation.Tag;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

@Tag(algs = Recursion, dss = Tag.DataStructure.String)
public class Combination {
  public static void main(String[] args) {
    String s1 = "123";
    allCombinationsIteration(s1, new ArrayList<String>());
    System.out.println(allCombinationsRecursionNOrderBottomUp(s1));
    System.out.println(combinationsOfSizeNRecursionNOrderTopDown(s1, 2));
  }

  /** F(n) = F(n-1) + F(n-2) + ... + F(1) */
  public static List<String> allCombinationsRecursionNOrderBottomUp(String input) {
    return allCombinationsRecursionNOrderBottomUp(input, new StringBuilder(), 0);
  }

  static List<String> allCombinationsRecursionNOrderBottomUp(String input, StringBuilder com, int sIdx) {
    List<String> ret = new ArrayList<>();
    for (int i = sIdx; i < input.length(); i++) {
      com.append(input.charAt(i));
      ret.add(com.toString());
      for (String s : allCombinationsRecursionNOrderBottomUp(input, com, i + 1))
        ret.add(s);
      com.deleteCharAt(com.length() - 1);
    }
    return ret;
  }

  /** F(n) = 2 * F(n-1) * */
  public static List<List<String>> allCombinationsRecursionFirstOrderBottomUp(List<String> input) {
    return allCombinationsRecursionFirstOrderBottomUp(input, 0);
  }

  private static List<List<String>> allCombinationsRecursionFirstOrderBottomUp(List<String> input, int idx) {
    List<List<String>> ret = new LinkedList<>();
    if (idx == input.size()) {
      ret.add(new LinkedList<String>());
      return ret;
    }
    for (List<String> comb : allCombinationsRecursionFirstOrderBottomUp(input, idx + 1)) {
      ret.add(comb);
      List<String> cpy = new ArrayList<>(comb);
      cpy.add(input.get(idx));
      ret.add(cpy);
    }
    return ret;
  }

  /** By iteration. */
  public static void allCombinationsIteration(String input, ArrayList<String> res) {
    if (isNullOrEmpty(input)) {
     return;
    }
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

  /** F(n) = F(n-1) + F(n-2) + ... + F(1) */
  public static List<String> combinationsOfSizeNRecursionNOrderTopDown(String input, int size) {
    return combinationsOfSizeNRecursionNOrderTopDown(input, size, new StringBuilder(), 0);
  }

  private static List<String> combinationsOfSizeNRecursionNOrderTopDown(String input, int n, StringBuilder sb, int startIdx) {
    List<String> ret = new ArrayList<>();
    char[] array = input.toCharArray();
    int len = array.length;
    if (sb.length() == n) {
      ret.add(sb.toString());
      return ret;
    }
    for (int i = startIdx; i < len; i++) {
      sb.append(array[i]);
      ret.addAll(combinationsOfSizeNRecursionNOrderTopDown(input, n, sb, i + 1));
      sb.deleteCharAt(sb.length() - 1);
    }
    return ret;
  }
}
