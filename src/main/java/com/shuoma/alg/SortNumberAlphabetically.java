package com.shuoma.alg;

import static com.shuoma.annotation.Tag.Algorithm.Arithmetic;
import static com.shuoma.annotation.Tag.Algorithm.Recursion;
import static com.shuoma.annotation.Tag.Reference.Interview;

import com.shuoma.annotation.Tag;

import java.util.LinkedList;
import java.util.List;

/**
 * Given number N, print numbers 1 ~ N alphabetically
 * Requirement O(n) time
 * For example, given 11
 * print 1 10 11 2 3 4 5 6 7 8 9
 */
@Tag(algs = {Arithmetic, Recursion}, references = Interview)
public class SortNumberAlphabetically {

  public static void main(String[] args) {
    SortNumberAlphabetically ins = new SortNumberAlphabetically();
    System.out.println(ins.sort(32));
  }

  public List<Integer> sort(int N) {
    return sort(N, 1);
  }

  List<Integer> sort(int N, int k) {
    List<Integer> ret = new LinkedList<>();
    if (k > N) { return ret; }
    for (int i = (k == 1 ? 1 : 0); i < 10 && k <= N; i++) {
      ret.add(k);
      ret.addAll(sort(N, k * 10));
      k++;
    }
    return ret;
  }
}
