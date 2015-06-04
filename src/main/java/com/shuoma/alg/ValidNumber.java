package com.shuoma.alg;

import static com.shuoma.annotation.Tag.Algorithm.RegularExpression;
import static com.shuoma.annotation.Tag.DataStructure.String;
import static com.shuoma.annotation.Tag.Reference.LeetCode;

import com.shuoma.annotation.Tag;

@Tag(algs = RegularExpression, dss = String, reference = LeetCode)
public class ValidNumber {
  public static void main(String[] args) {
    new ValidNumber().main();
  }

  public void main() {
    String s = " 0.1 ";
    System.out.println(isNumber(s));
  }

  public boolean isNumber(String s) {
    //my first try:        return s.matches("[+-]*\\d*.?\\d*(e[+-])?\\d+");
    return s.matches("^\\s*[+-]?(\\d+|\\d*\\.\\d+|\\d+\\.\\d*)([eE][+-]?\\d+)?\\s*$");
  }
}
