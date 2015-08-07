package com.shuoma.alg;

import static com.shuoma.annotation.Tag.DataStructure.StackT;
import static com.shuoma.annotation.Tag.Reference.LeetCode;

import com.shuoma.annotation.Tag;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

@Tag(dss = StackT, references = LeetCode)
public class MinStack {
  public static class MinEle {
    int val;
    int cnt;

    public MinEle(int val) {
      this.val = val;
      this.cnt = 1;
    }

    public void incr() {
      cnt++;
    }

    public void decr() {
      cnt--;
    }

    public int getCnt() {
      return cnt;
    }

    public int getVal() {
      return val;
    }
  }


  Stack<Integer> stack;
  List<MinEle> mins;

  public MinStack() {
    stack = new Stack<>();
    mins = new ArrayList<>();
  }

  public void push(int x) {
    stack.push(x);
    if (mins.isEmpty() || mins.get(0).getVal() > x) {
      mins.add(0, new MinEle(x));
    } else {
      mins.get(0).incr();
    }
  }

  public void pop() {
    if (stack.isEmpty())
      return;
    stack.pop();
    mins.get(0).decr();
    if (mins.get(0).getCnt() == 0)
      mins.remove(0);
  }

  public int top() {
    if (stack.isEmpty())
      return -1;
    return stack.peek();
  }

  public int getMin() {
    if (stack.isEmpty())
      return -1;
    return mins.get(0).getVal();
  }

  public static void main(String[] args) {
    MinStack ms = new MinStack();
    ms.push(3);
    ms.push(1);
    ms.push(5);
    System.out.println(ms.getMin());
    ms.pop();
    System.out.println(ms.getMin());
    ms.pop();
    System.out.println(ms.getMin());
    ms.pop();
  }
}
