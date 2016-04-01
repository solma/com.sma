package com.sma.alg;

import static com.sma.annotation.Tag.DataStructure.MonotonicSequence;
import static com.sma.annotation.Tag.DataStructure.StackT;
import static com.sma.annotation.Tag.Difficulty.D3;
import static com.sma.annotation.Tag.Reference.LeetCode;

import com.sma.annotation.Tag;

import java.util.Stack;

@Tag(dl = D3, dss = {MonotonicSequence, StackT}, references = LeetCode)
public class LargestRectangleInHistogram {

  public static int largestRectangleArea(int[] height) {
    int n = height.length;
    Stack<Integer> hStc = new Stack<>();
    Stack<Integer> wStc = new Stack<>();
    int maxArea = 0;
    for (int i = 0; i < n; i++) {
      if (hStc.isEmpty() || hStc.peek() <= height[i]) {
        hStc.push(height[i]);
        wStc.push(i);
      } else {
        int lastWidth = 0;
        while (!hStc.isEmpty() && hStc.peek() > height[i]) {
          lastWidth = wStc.pop();
          maxArea = Math.max(maxArea, hStc.pop() * (i - lastWidth));
        }
        hStc.push(height[i]);
        wStc.push(lastWidth);
      }
    }
    while (!hStc.isEmpty()) {
      maxArea = Math.max(maxArea, hStc.pop() * (n - wStc.pop()));
    }
    return maxArea;
  }
}
