package com.sma.alg;

import static com.sma.annotation.Tag.DataStructure.StackT;

import com.sma.annotation.Tag;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

/**
 * Given a sequence of push operations, determine if a sequence of pop operations is possible.
 * E.g.
 * pushes: 2 9 3 7 8
 * pops:
 * 3 9 7 8 2   yes
 * 7 2 9 3 8   no
 *
 */
@Tag(dss = StackT)
public class ValidStackPushAndPopSequence {

  boolean isPossible(final int[] pushes, final int[] pops) {
    Stack<Integer> stack = new Stack<>();
    int pushIdx = 0;
    for (int pop : pops) {
      if (!stack.isEmpty() && stack.peek() == pop) {
        stack.pop();
      } else {
        if (pushIdx == pushes.length) {
          return false;
        }

        do {
          stack.push(pushes[pushIdx++]);
        } while(pushIdx < pushes.length && stack.peek() != pop);
        if (stack.peek() == pop) {
          stack.pop();
        } else {
          return false;
        }
      }
    }
    return true;
  }

  boolean isPossibleWithoutStack(final int[] pushes, final int[] pops) {
    int n = pushes.length;
    Map<Integer, Integer> revertedIdx = new HashMap<>();
    for (int i = 0; i < n; i++) {
      revertedIdx.put(pushes[i], i);
    }

    for (int i = 0; i < n - 2; i++) {
      if (pops[i + 2] > pops[i + 1] && pops[i + 2] < pops[i]) {
        return false;
      }
    }
    return true;
  }
}
