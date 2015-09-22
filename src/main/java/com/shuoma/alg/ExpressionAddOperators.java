package com.shuoma.alg;

import static com.shuoma.annotation.Tag.Algorithm.DepthFirstSearch;
import static com.shuoma.annotation.Tag.Algorithm.Recursion;
import static com.shuoma.annotation.Tag.DataStructure.StringT;
import static com.shuoma.annotation.Tag.Reference.LeetCode;

import com.shuoma.annotation.Tag;

import java.util.LinkedList;
import java.util.List;

/**
 Given a string that contains only digits 0-9 and a target value,
 return all possibilities to add binary operators (not unary) +, -, or * between the digits
 so they evaluate to the target value.

 Examples:
 "123", 6 -> ["1+2+3", "1*2*3"]
 "232", 8 -> ["2*3+2", "2+3*2"]
 "105", 5 -> ["1*0+5","10-5"]
 "00", 0 -> ["0+0", "0-0", "0*0"]
 "3456237490", 9191 -> []
 */
@Tag(algs = {DepthFirstSearch, Recursion}, dss = StringT, references = LeetCode)
public class ExpressionAddOperators {

  public static void main(String[] args) {
    ExpressionAddOperators ins = new ExpressionAddOperators();
    System.out.println(ins.addOperators("232", 8));
    System.out.println(ins.addOperators("123", 6));
    System.out.println(ins.addOperators("00", 0));
    System.out.println(ins.addOperators("000", 0));
  }

  List<String> addOperators(String num, int target) {
    List<String> res = new LinkedList<>();
    addOperatorsDFS(num, target, 0, "", res);
    return res;
  }

  void addOperatorsDFS(String num, long target, long diff, String curExpr, List<String> res) {
    if (num.length() == 0 && target == 0) {
      res.add(curExpr);
    }
    for (int i = 1; i <= num.length(); i++) {
      String operand = num.substring(0, i);
      if (operand.length() > 1 && operand.charAt(0) == '0') { return; }
      String next = num.substring(i);

      long val = Long.parseLong(operand);
      if (curExpr.isEmpty()) {
        addOperatorsDFS(next, target - val, val, operand, res);
      } else {
        addOperatorsDFS(next, target - val, val, curExpr + "+" + operand, res);
        addOperatorsDFS(next, target + val, -val, curExpr + "-" + operand, res);
        /**
         * e.g given expr=2+3*2, target=8,
         * right before multiply by 2，target = 3, diff = 3,
         * now if when multiply by 2，new-diff should be diff*val, i.e. 3*2=6，
         * and to calculate new target, we need to revert the last diff, i.e. +3，and add the new
         * diff，i.e. (3+3)-6=0，i.e. the target of 2+3*2 is 0.
         */
        long bundle = diff * val;
        addOperatorsDFS(next, target + diff - bundle, bundle, curExpr + "*" + operand, res);
      }
    }
  }
}
