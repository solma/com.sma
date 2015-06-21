package com.shuoma.alg;

import static com.shuoma.annotation.Tag.DataStructure.Queue;
import static com.shuoma.annotation.Tag.DataStructure.Stack;
import static com.shuoma.annotation.Tag.Reference.LeetCode;

import com.shuoma.annotation.Tag;

import java.util.LinkedList;
import java.util.Queue;

@Tag(dss = {Stack, Queue}, reference = LeetCode)
public class ImplementStackUseQueue {
  public static void main(String[] args) {
    MyStack ms = new MyStack();
    ms.push(1);
    ms.push(2);
    ms.pop();
    System.out.println(ms.top());
    ms.pop();
  }
}

// Opt 1. expensive push
// Opt 2. expensive pop TLE
class MyStack {
  Queue<Integer> in = new LinkedList<>();
  Queue<Integer> out = new LinkedList<>();

  // Push element x onto stack.
  public void push(int x) {
    in.offer(x);
    while (!out.isEmpty()) {
      in.offer(out.poll());
    }
    swap();
  }

  // Removes the element on top of the stack.
  public void pop() {
    out.poll();
  }

  // Get the top element.
  public int top() {
    if (empty()) throw new IllegalStateException("stack is empty.");
    return out.peek();
  }

  // Return whether the stack is empty.
  public boolean empty() {
    return in.isEmpty() && out.isEmpty();
  }

  void swap() {
    Queue<Integer> q = in;
    in = out;
    out = q;
  }
}
