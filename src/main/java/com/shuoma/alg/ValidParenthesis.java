package com.shuoma.alg;

import static com.shuoma.annotation.Tag.DataStructure.StackT;
import static com.shuoma.annotation.Tag.DataStructure.String;
import static com.shuoma.annotation.Tag.Reference.LeetCode;

import com.shuoma.annotation.Tag;

import java.util.Stack;

@Tag(dss = {StackT, String}, references = LeetCode)
public class ValidParenthesis {
  public boolean isValid(String s) {
    if (s == null)
      return true;
    Stack<Character> stack = new Stack<>();
    char c;
    for (int i = 0; i < s.length(); i++) {
      c = s.charAt(i);
      if (c == '(' || c == '{' || c == '[')
        stack.push(c);
      else {
        if (!stack.isEmpty() && match(stack.peek(), c))
          stack.pop(); //catch: stack is empty
        else
          return false;
      }
    }
    if (stack.isEmpty())
      return true;  //catch stack is empty
    else
      return false;
  }

  public boolean match(char stack, char input) {
    if ((stack == '{' && input == '}') || (stack == '(' && input == ')') || (stack == '['
        && input == ']'))
      return true;
    else
      return false;
  }
}
