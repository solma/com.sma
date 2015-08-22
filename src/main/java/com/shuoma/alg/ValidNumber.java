package com.shuoma.alg;

import static com.shuoma.annotation.Tag.Algorithm.RegularExpression;
import static com.shuoma.annotation.Tag.DataStructure.StringT;
import static com.shuoma.annotation.Tag.Reference.LeetCode;

import com.shuoma.annotation.Tag;

@Tag(algs = RegularExpression, dss = StringT, references = LeetCode)
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
    s = s.trim();
    return s.matches("^[+-]?(\\d+|\\d*\\.\\d+|\\d+\\.\\d*)([eE][+-]?\\d+)?$");
  }
}
