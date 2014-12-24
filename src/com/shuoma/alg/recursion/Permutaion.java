package com.shuoma.alg.recursion;

import com.shuoma.util.ArrayUtil;

import java.util.ArrayList;

public class Permutaion {

  public static void main(String[] args) {
    String curPermutation = "dcba";
    System.out.println(nextPermutation(curPermutation));
    System.out.println(prevPermutation(curPermutation));
  }

  public static ArrayList<String> allPermutations(String input) {
    return allPermutations(input, new StringBuilder());
  }

  static ArrayList<String> allPermutations(String input, StringBuilder perm) {
    ArrayList<String> ret = new ArrayList<String>();
    for (int i = 0; i < input.length(); i++) {
      if (perm.toString().contains(input.substring(i, i + 1))) continue;
      perm.append(input.charAt(i));
      if (perm.length() == input.length()) ret.add(perm.toString());
      for (String s : allPermutations(input, perm))
        ret.add(s);
      perm.deleteCharAt(perm.length() - 1);
    }
    return ret;
  }

  public static ArrayList<String> firstKPermutationsByRecursion(String input, int K) {
    return firstKPermutationsByRecursion(input, new StringBuilder(), K);
  }

  static ArrayList<String> firstKPermutationsByRecursion(String input, StringBuilder perm, int K) {
    // firt K permutatins in alphabetical order
    ArrayList<String> ret = new ArrayList<String>();
    for (int i = 0; i < input.length(); i++) {
      if (perm.toString().contains(input.substring(i, i + 1))) continue;
      perm.append(input.charAt(i));
      if (perm.length() == input.length()) ret.add(perm.toString());
      for (String s : firstKPermutationsByRecursion(input, perm, K)) {
        if (ret.size() >= K) break;
        ret.add(s);
      }
      perm.deleteCharAt(perm.length() - 1);
    }
    return ret;
  }

  public static String nextPermutation(String curPermutation) {
    char[] cur = curPermutation.toCharArray();

    // the first increasing pair from backward
    int i;
    for (i = cur.length - 1; i > 0; i--) {
      if (cur[i - 1] < cur[i]) break;
    }
    if (i == 0) {
      ArrayUtil.reverse(cur);
      return new String(cur);
    }
    i -= 1;
    // the first one larger than cur[i] from backward
    int j;
    for (j = cur.length - 1; j > i; j--) {
      if (cur[j] > cur[i]) break;
    }
    ArrayUtil.swap(cur, i, j);
    // reverse the sequence after i
    i += 1;
    while (i < j) {
      ArrayUtil.swap(cur, i++, j--);
    }
    return new String(cur);
  }

  public static String prevPermutation(String curPermutation) {
    char[] cur = curPermutation.toCharArray();

    // the first decreasing pair from backward
    int i;
    for (i = cur.length - 1; i > 0; i--) {
      if (cur[i - 1] > cur[i]) break;
    }
    if (i == 0) {
      ArrayUtil.reverse(cur);
      return new String(cur);
    }
    i -= 1;
    // the first one smaller than cur[i] from backward
    int j;
    for (j = cur.length - 1; j > i; j--) {
      if (cur[j] < cur[i]) break;
    }
    ArrayUtil.swap(cur, i, j);
    // reverse the sequence after i
    i += 1;
    while (i < j) {
      ArrayUtil.swap(cur, i++, j--);
    }
    return new String(cur);
  }
}
