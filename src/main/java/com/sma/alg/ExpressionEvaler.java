package com.sma.alg;

import static com.sma.annotation.Tag.DataStructure.Calculator;
import static com.sma.annotation.Tag.DataStructure.StackT;
import static com.sma.annotation.Tag.Reference.Interview;

import com.sma.annotation.Tag;

import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

/**
 * Expression Eval. An expression is defined as expr ::= int | ‘(‘ op expr… ‘)’ and op ::= ‘+’ | ‘*’;
 * for example: "( * 1 ( + 1 2 3 ) )” => 6 and “( * ( + 1 1 ) 17 )” => 34.
 * Write code to eval the expression.
 */
@Tag(dss = {Calculator, StackT}, references = Interview)
public class ExpressionEvaler {
  public int eval(String expression) {
    String[] tokens = expression.split("\\s");
    Stack<String> operandStck = new Stack();
    Stack<String> operatorStck = new Stack<>();
    for (int i = 0; i < tokens.length; i++) {
      String token = tokens[i];
      if (token.equals("("))
        operandStck.push(token);
      else if (token.equals("*") || tokens[i].equals("+"))
        operatorStck.push(token);
      else if (tokens[i].equals(")")) {
        List<String> operands = new LinkedList<>();
        while (!operandStck.peek().equals("("))
          operands.add(operandStck.pop());
        operandStck.pop();

        operandStck.push(String.valueOf(eval(operatorStck.pop(), operands)));
      } else
        operandStck.push(tokens[i]);
    }
    return Integer.parseInt(operandStck.peek());
  }

  int eval(String op, List<String> numbers) {
    if (op.equals("*")) {
      int product = 1;
      for (int i = 0; i < numbers.size(); i++)
        product *= Integer.parseInt(numbers.get(i));
      return product;
    } else {
      int sum = 0;
      for (int i = 0; i < numbers.size(); i++)
        sum += Integer.parseInt(numbers.get(i));
      return sum;
    }
  }
}
