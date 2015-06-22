package com.shuoma.alg;

import static com.shuoma.annotation.Tag.Algorithm.Recursion;
import static com.shuoma.annotation.Tag.DataStructure.String;
import static com.shuoma.annotation.Tag.Reference.LeetCode;

import com.shuoma.annotation.Tag;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Tag(algs = Recursion, dss = String, references = LeetCode)
public class GenerateParenthesis {
  public static void main(String[] args) {
    new GenerateParenthesis().main();
  }

  public void main() {
    List<String> ret = generateParenthesis(0);
    System.out.println(ret.size());
    for (String s : ret) {
      System.out.println(s);
    }
  }

  //recursion
  public List<String> generateParenthesis(int n) {
    if (n == 0) {
      return new ArrayList<>();
    }
    return generateRecursion("", 0, 0, n);
  }

  public List<String> generateRecursion(String cur, int open, int close, int n) {
    List<String> ret = new ArrayList<>();
    if (close > open || open > n) {
      return ret;
    }
    if (open == n && close == n) {
      ret.add(cur);
    }
    ret.addAll(generateRecursion(cur + "(", open + 1, close, n));
    ret.addAll(generateRecursion(cur + ")", open, close + 1, n));
    return ret;
  }


  //second pass
  public List<String> generateParenthesis1(int n) {
    Set<String> sets = new HashSet<>();
    if (n == 0) {
      List<String> ret = new ArrayList<>();
      ret.add("");
      return ret;
    } else {
      for (String set : generateParenthesis(n - 1)) {
        int len = set.length();

        for (int i = 0; i <= len; i++) {
          StringBuilder setCpy1 = new StringBuilder(set);
          setCpy1.insert(i, '(');
          for (int j = i + 1; j <= len + 1; j++) {
            StringBuilder setCpy2 = new StringBuilder(setCpy1);
            setCpy2.insert(j, ')');
            sets.add(setCpy2.toString());
          }
        }
      }
      List<String> ret = new ArrayList<>();
      ret.addAll(sets);
      //System.out.println(n+" : "+ret.size());
      return ret;
    }

  }


  // first pass
  // public ArrayList<String> generateParenthesis(int n) {
  // ArrayList<String> allParenthesis=new ArrayList<String>();
  // generateParenthesis(allParenthesis, 2*n, new StringBuilder());
  // return allParenthesis;
  // }

  public boolean isValid(StringBuilder s) {
    int diff = 0;
    for (int i = 0; i < s.length(); i++) {
      if (s.charAt(i) == '(')
        diff += 1;
      else
        diff -= 1;
      if (diff < 0)
        return false;
    }
    return true;
  }

  public boolean isMatched(StringBuilder s) {
    int diff = 0;
    for (int i = 0; i < s.length(); i++) {
      if (s.charAt(i) == '(')
        diff += 1;
      else
        diff -= 1;
    }
    if (diff == 0)
      return true;
    else
      return false;
  }

  public void generateParenthesis(ArrayList<String> allParenthesis, int n, StringBuilder s) {
    if (!isValid(s))
      return; //compartibility check

    if (s.length() == n) {
      if (isMatched(s))
        allParenthesis.add(new String(s));
      return;
    }
    char[] ps = new char[] {'(', ')'};
    for (int i = 0; i < ps.length; i++) {
      s.append(ps[i]);
      generateParenthesis(allParenthesis, n, s);
      s.deleteCharAt(s.length() - 1);
    }
  }
}
