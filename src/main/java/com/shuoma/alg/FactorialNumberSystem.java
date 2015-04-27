package com.shuoma.alg;

import static com.shuoma.annotation.Tag.Algorithm.Arithmetic;
import static com.shuoma.annotation.Tag.DataStructure.String;

import com.shuoma.annotation.Tag;

import java.util.LinkedList;
import java.util.List;

@Tag(algs = Arithmetic, dss = String)
public class FactorialNumberSystem {

  public static void main(String[] args) {
    FactorialNumberSystem ins = new FactorialNumberSystem("abcdef");
    ins.computerFactorials();
    System.out.println(ins.toPermutation(6));
  }

  public FactorialNumberSystem(String s) {
    alphabet = s;
  }

  String toPermutation(int ith) {
    int n = alphabet.length() - 1;
    StringBuilder sb = new StringBuilder();
    while (n >= 0) {
      int factorial = factorials.get(n);
      sb.append(factorial == 0 ? ith : ith / factorial);
      if (factorial > 0) {
        ith %= factorial;
      }
      n--;
    }
    return sb.toString();
  }

  private void computerFactorials() {
    factorials.add(0);
    int product = 1;
    for (int i = 1; i < alphabet.length(); i++) {
      factorials.add(product);
      product *= i + 1;
    }
  }

  private String alphabet;
  private List<Integer> factorials = new LinkedList<>();
}
