package com.shuoma.alg;

import static com.shuoma.annotation.Tag.Algorithm.Recursion;
import static com.shuoma.annotation.Tag.Reference.LeetCode;

import com.shuoma.annotation.Tag;

import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

@Tag(algs = Recursion, references = LeetCode)
public class DifferentWaysToAddParentheses {

  public static void main(String[] args) {
    DifferentWaysToAddParentheses ins = new DifferentWaysToAddParentheses();
    String expr = "2* 3- 4 *5";
    expr = expr.replaceAll("\\s+", "").trim();
    System.out.println(ins.diffWaysToCompute(expr));
  }

  List<Integer> diffWaysToCompute(String input) {
    Map<String, List<Integer>> mem = new HashMap<>();
    List<Integer> res = diffWaysToCompute(input, 0, input.length() - 1, mem);
    Collections.sort(res);
    return res;
  }

  List<Integer> diffWaysToCompute(String input, int sIdx, int eIdx, Map<String, List<Integer>> mem) {
    List<Integer> ret = new LinkedList<>();
    String key = sIdx + "-" + eIdx;
    if (mem.containsKey(key)) {
      return mem.get(key);
    }
    try {
      int num = Integer.parseInt(input.substring(sIdx, eIdx + 1));
      ret.add(num);
    } catch(NumberFormatException e) {
      for (int i = sIdx; i <= eIdx; i++) {
        char c = input.charAt(i);
        if (isOperator(c)) {
          List<Integer> operand1 = diffWaysToCompute(input, sIdx, i - 1, mem);
          List<Integer> operand2 = diffWaysToCompute(input, i + 1, eIdx, mem);
          switch(c) {
            case '+':
              for (int op1: operand1) {
                for (int op2: operand2) {
                  ret.add(op1 + op2);
                }
              }
              break;
            case '-':
              for (int op1: operand1) {
                for (int op2: operand2) {
                  ret.add(op1 - op2);
                }
              }
              break;
            case '*':
              for (int op1: operand1) {
                for (int op2: operand2) {
                  ret.add(op1 * op2);
                }
              }
              break;
          }
        }
      }
    }
    mem.put(key, ret);
    return ret;
  }

  boolean isOperator(char c) {
    return c == '+' || c == '-' || c == '*';
  }
}
