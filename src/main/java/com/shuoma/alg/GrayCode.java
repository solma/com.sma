package com.shuoma.alg;

import static com.shuoma.annotation.Tag.Algorithm.Recursion;
import static com.shuoma.annotation.Tag.DataStructure.String;
import static com.shuoma.annotation.Tag.Source.LeetCode;

import com.shuoma.annotation.Tag;

import java.util.ArrayList;
import java.util.List;

@Tag(algs = Recursion, dss = String, source = LeetCode)
public class GrayCode {
  public static void main(String[] args) {
    GrayCode ins = new GrayCode();
    for (int i : ins.grayCode(3))
      System.out.println(i);
  }

  public List<Integer> grayCode(int n) {
    List<Integer> codes;
    if (n <= 2) {
      codes = new ArrayList<>();
      if (n >= 0)
        codes.add(0);
      if (n >= 1)
        codes.add(1);
      if (n == 2) {
        codes.add(3);
        codes.add(2);
      }
      return codes;
    }

    codes = grayCode(n - 1);
    int i;
    for (i = codes.size() - 1; i >= 0; i--) {
      codes.add(codes.get(i) + (int) Math.pow(2, n - 1));
    }
    return codes;
  }
}
