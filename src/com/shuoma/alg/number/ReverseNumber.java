package com.shuoma.alg.number;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Find all numbers with length N that have the following property:
 * It remains unchanged after rotating 180 degree followed by reversing
 * e.g. 96196 ---rotating 180 degree --> 69169 --reversing --> 96196。
 */
public class ReverseNumber {
  public static void main(String[] args) {
    List<Integer> res = new ArrayList<>(findAll(5));
    Collections.sort(res);
    System.out.println(res + "\n" + res.size());
  }

  public static Set<Integer> findAll(int N) {
    return findAll(N, new int[]{1, 8, 6, 9}, "", "", "");
  }

  public static Set<Integer> findAll(int N, int[] options, String prefix, String middle, String suffix) {
    Set<Integer> res = new HashSet<>();
    String number = prefix + middle + suffix;
    if (number.length() >= N) {
      if (number.length() == N) res.add(Integer.parseInt(number));
      return res;
    }
    for (int i : options) {
      switch(i) {
        case 1:
        case 8:
          res.addAll(findAll(N, options, i + "" + prefix, middle, suffix + "" + i));
          if (middle.equals("")) res.addAll(findAll(N, options, prefix, middle + "" + i, suffix));
          break;
        case 6:
        case 9:
          res.addAll(findAll(N, options, i+ "" + prefix, middle, suffix + "" + (15 - i)));
          break;
      }
    }
    return res;
  }
}
