package com.shuoma.alg;

// source: http://www.careercup.com/question?id=5687257423937536

/**
 * Car parking problem. An array given represents actual order of cars need to be parked. Like for
 * example order is 4,6,5,1,7,3,2,empty. If cars are parked in some order like empty,1,2,3,7,6,4,2.
 * Some person needs to get them into correct order, list out all instructions to the person to get
 * in correct order with least number of swaps.
 */
import static com.shuoma.annotation.Tag.Algorithm.BreadthFirstSearch;
import static com.shuoma.annotation.Tag.DataStructure.Array;
import static com.shuoma.annotation.Tag.Difficulty.D3;

import com.shuoma.annotation.Tag;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

@Tag(algs = BreadthFirstSearch, dl = D3, dss = Array)
public class CarParking {
  public static void main(String[] args) {
    new CarParking().main();
  }

  public void main() {
    int[] start = new int[] {1, 0, 10, 2, 3, 5};
    int[] end = new int[] {5, 3, 2, 10, 0, 1};
    System.out.println("\n Path=" + reorder(start, end));
  }

  public int getEmptyIdx(int[] pos) {
    int emptyIdx = 0;
    for (int i = 0; i < pos.length; i++) {
      if (pos[i] == 0) {
        emptyIdx = i;
        break;
      }
    }
    return emptyIdx;
  }

  public List<Integer> reorder(int[] pos, int[] targetPos) {
    List<Integer> path = new ArrayList<>();
    if (pos.length != targetPos.length)
      return path;
    StateNode start = new StateNode(pos, getEmptyIdx(pos));
    StateNode end = new StateNode(targetPos, getEmptyIdx(targetPos));
    if (start.equals(end))
      return path;

    LinkedList<StateNode> bfs = new LinkedList<>();
    bfs.add(start);

    Set<StateNode> visited = new HashSet<>();
    visited.add(start);


    StateNode cur = null;
    boolean find = false;
    while (!bfs.isEmpty()) {
      cur = bfs.remove();
      System.out.println(Arrays.toString(cur.pos));

      for (StateNode neighbor : cur.getNeighbors()) {
        if (!visited.contains(neighbor)) {
          bfs.add(neighbor);
          visited.add(neighbor);
          if (neighbor.equals(end)) {
            find = true;
            cur = neighbor;// set the cur to the goal state
            break;
          }
          System.out.print("\t" + Arrays.toString(neighbor.pos));
        }
      }
      if (find)
        break;
      System.out.println();
    }

    while (!cur.equals(start)) {
      path.add(0, cur.prev.pos[cur.emptyIdx]);
      cur = cur.prev;
    }
    return path;
  }
}


class StateNode {
  public enum STATUS {
    UNVISITED, VISITED, EXPLORED
  }


  public int[] pos;
  public int emptyIdx;
  // public STATUS visitStatus;
  public StateNode prev;


  public StateNode(int[] pos, int emptyIdx) {
    this.pos = pos;
    this.emptyIdx = emptyIdx;
    // this.visitStatus=STATUS.UNVISITED;
  }

  public StateNode(int[] pos, int emptyIdx, StateNode prev) {
    this(pos, emptyIdx);
    this.prev = prev;
  }

  public StateNode[] getNeighbors() {
    StateNode[] neighbors = new StateNode[pos.length - 1];
    int[] pos;
    int beforeEmptyIdx = 0;
    int newEmptyIdx;
    int cnt = 0;
    for (int i = 0; i < this.pos.length; i++) {
      pos = Arrays.copyOf(this.pos, this.pos.length);
      if (i == this.emptyIdx)
        continue;
      swap(pos, i, this.emptyIdx);
      neighbors[cnt++] = new StateNode(pos, i, this);
    }
    return neighbors;
  }

  void swap(int[] pos, int i, int j) {
    if (i == j)
      return;
    int tmp = pos[i];
    pos[i] = pos[j];
    pos[j] = tmp;
  }

  @Override public boolean equals(Object other) {
    try {
      for (int i = 0; i < pos.length; i++) {
        if (pos[i] != ((StateNode) other).pos[i])
          return false;
      }
      return true;
    } catch (Exception ex) {
      return false;
    }
  }

  @Override public int hashCode() {
    int sum = 0;
    for (int i = 0; i < pos.length; i++) {
      sum += i * pos[i];
    }
    return sum;
  }
}
