package com.shuoma.alg;

import static com.shuoma.annotation.Tag.DataStructure.QueueT;
import static com.shuoma.annotation.Tag.DataStructure.StackT;
import static com.shuoma.annotation.Tag.Reference.LeetCode;

import com.shuoma.annotation.Tag;

import java.util.EmptyStackException;
import java.util.LinkedList;
import java.util.Queue;

@Tag(dss = {StackT, QueueT}, references = LeetCode)
public class ImplementStackUseQueue {
  public static void main(String[] args) {
    MyStack2 ms = new MyStack2();
    ms.push(1);
    ms.push(2);
    //ms.pop();
    System.out.println(ms.top());
    ms.pop();
  }
}

// Opt 1. expensive push (in queue is always empty after push)
// Opt 2. expensive pop/peek (out queue is always empty after pop)
class MyStack1 {
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
    if (empty()) throw new EmptyStackException();
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

class MyStack2 {
  Queue<Integer> in = new LinkedList<>();
  Queue<Integer> out = new LinkedList<>();

  // Push element x onto stack.
  public void push(int x) {
    in.offer(x);
  }

  // Removes the element on top of the stack.
  public void pop() {
    while (in.size() > 1) {
      out.offer(in.poll());
    }
    in.poll();
    swap();
  }

  // Get the top element.
  public int top() {
    if (empty()) throw new EmptyStackException();
    while (in.size() > 1) {
      out.offer(in.poll());
    }
    int top = in.peek();
    out.offer(top);
    swap();
    return top;
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
