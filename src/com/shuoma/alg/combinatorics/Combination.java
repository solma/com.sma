package com.shuoma.alg.combinatorics;

import java.util.ArrayList;

public class Combination {
  public static void main(String[] args) {
    new Combination().main();
  }

  void main() {
    // String s1="123";
    // allCombinationsByIteration(s1, new ArrayList<String>());
    // //myInuitiveAlgrithm(s1, new ArrayList<String>());


    // ArrayList<String> res;
    // res=new ArrayList<String>();
    // //allPermutationsByRecursion(s1, res, new StringBuilder());
    // for (int i = 0; i < res.size(); i++)
    // System.out.println((i + 1) + ". " + res.get(i));
    // System.out.println();

    // res.clear();
    // //allCombinationsByRecursion(s1, res, new StringBuilder(), 0);
    // for (int i = 0; i < res.size(); i++)
    // System.out.println((i + 1) + ". " + res.get(i));
    // System.out.println();
    System.out.println(myInuitiveAlgrithm("235"));
    // System.out.println( combinationsByRecursion("235", new StringBuilder(), 0) );
    // System.out.println( permutationsByRecursion("235", new StringBuilder()) );
    // System.out.println( firstKPermutationsByRecursion("235", new StringBuilder(), 3) );
  }

  void CombinationsByRecursion(String input, int n, ArrayList<String> res, StringBuilder sb, int startIdx) {
    char[] array = input.toCharArray();
    int len = array.length;
    if (sb.length() == n + 1) return;
    for (int i = startIdx; i < len; i++) {
      sb.append(array[i]);
      System.out.println(startIdx + " " + i + " " + len + " " + sb.toString());
      if (sb.length() == n) res.add(sb.toString());
      CombinationsByRecursion(input, n, res, sb, i + 1);
      sb.deleteCharAt(sb.length() - 1);
    }
  }


  ArrayList<String> combinationsByRecursion(String input, StringBuilder com, int sIdx) {
    ArrayList<String> ret = new ArrayList<String>();
    for (int i = sIdx; i < input.length(); i++) {
      com.append(input.charAt(i));
      ret.add(com.toString());
      for (String s : combinationsByRecursion(input, com, i + 1))
        ret.add(s);
      com.deleteCharAt(com.length() - 1);
    }
    return ret;
  }

  void allCombinationsByIteration(String input, ArrayList<String> res) {
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

  ArrayList<String> myInuitiveAlgrithm(String input) {
    ArrayList<String> res = new ArrayList<String>();
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

  /*
   * ArrayList<String> myInuitiveAlgrithm(String input){ //generate grey code of permutations
   * ArrayList<String> res=new ArrayList<String>();
   *
   * return res; }
   */
}
