package com.shuoma.alg;

import static com.shuoma.annotation.Tag.Algorithm.Sorting;
import static com.shuoma.annotation.Tag.DataStructure.LinkedList;
import static com.shuoma.annotation.Tag.DataStructure.PriorityQueue;
import static com.shuoma.annotation.Tag.Trick.TwoOrMorePointers;

import com.shuoma.annotation.Tag;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

// http://www.cs.princeton.edu/courses/archive/spring13/cos423/lectures/LongestIncreasingSubsequence.pdf
@Tag(algs = Sorting, dss = {LinkedList, PriorityQueue}, tricks = TwoOrMorePointers)
public class PatienceLIS {
  public static <E extends Comparable<? super E>> List<E> lis(List<E> n) {
    List<Node<E>> pileTops = new ArrayList<>();
    // sort into piles
    for (E x : n) {
      Node<E> node = new Node<>(x);
      int i = Collections.binarySearch(pileTops, node);
      if (i < 0)
        i = ~i; // get the insertion point
      if (i > 0)
        node.prev = pileTops.get(i - 1);
      if (i != pileTops.size())
        pileTops.set(i, node);
      else
        pileTops.add(node);
      System.out.println("x:" + node + " i:" + i + " piles:" + pileTops);
    }

    // extract LIS from nodeMap
    List<E> result = new ArrayList<>();
    for (Node<E> node = pileTops.size() == 0 ? null : pileTops.get(pileTops.size() - 1);
         node != null; node = node.prev)
      result.add(0, node.value);
    return result;
  }

  private static class Node<E extends Comparable<? super E>> implements Comparable<Node<E>> {
    public E value;
    public Node<E> prev;

    Node(E value) {
      this.value = value;
    }

    @Override public int compareTo(Node<E> y) {
      return value.compareTo(y.value);
    }

    @Override public String toString() {
      return value.toString() + ((prev == null) ? "" : (" -> " + prev.toString()));
    }
  }

  public static void main(String[] args) {
    List<Integer> d = Arrays.asList(6, 3, 5, 10, 11, 2, 9, 14, 13, 7, 4, 8, 12);
    System.out.printf("an L.I.S. of %s is %s\n", d, lis(d));
    d = Arrays.asList(0, 8, 4, 12, 2, 10, 6, 14, 1, 9, 5, 13, 3, 11, 7, 15);
    System.out.printf("an L.I.S. of %s is %s\n", d, lis(d));
  }
}
