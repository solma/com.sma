package com.shuoma.alg;

import static com.shuoma.annotation.Tag.Algorithm.BreadthFirstSearch;
import static com.shuoma.annotation.Tag.DataStructure.Array;
import static com.shuoma.annotation.Tag.Reference.Interview;

import com.shuoma.annotation.Tag;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

@Tag(algs = BreadthFirstSearch, dss = Array, references = Interview)
public class JumpRiver {

  public static void main(String[] args) {
    JumpRiver ins = new JumpRiver();
    int[] river = {1,1,1,0,1,1,0,0};
    System.out.println(ins.jump(river));
  }

  int jump(int[] river) {
    int n = river.length;
    if (n == 0) return -1;
    if (n <= 2) return 1;
    // List<Integer>:  0:Idx 1:StepCnt 2:spd
    Queue<List<Integer>> q = new LinkedList<>();
    q.offer(Arrays.asList(0, 0, 1));
    q.offer(Arrays.asList(0, 0, 2));
    int spdRange = 2;
    while (!q.isEmpty()) {
      List<Integer> cur = q.poll();
      for (int spdOffset = 0; spdOffset < spdRange; spdOffset++) {
        int spd = cur.get(2) + spdOffset;
        int pos = cur.get(0) + spd;
        List<Integer> next = Arrays.asList(pos, cur.get(1) + 1, spd);
        if (next.get(0) >= n) {
          return next.get(1);
        } else {
          q.offer(next);
        }
      }
    }
    return -1;
  }
}
