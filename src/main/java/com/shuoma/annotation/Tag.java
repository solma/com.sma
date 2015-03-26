package com.shuoma.annotation;

import static java.lang.annotation.RetentionPolicy.SOURCE;
import static java.lang.annotation.ElementType.TYPE;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

@Target(TYPE)
@Retention(SOURCE)
public @interface Tag {

  Algorithm[] algs() default Algorithm.NA;
  DataStructure[] dss() default DataStructure.NA;
  Difficulty dl() default Difficulty.D1;
  Source source() default Source.NA;
  Trick[] tricks() default Trick.NA;

  public enum Algorithm {
    BinarySearch,
    BitOperation,
    BreadthFirstSearch,
    DepthFirstSearch,
    DivideConquer,
    DynamicProgramming,
    Greedy,
    Recursion,
    Sorting,
    Streaming,
    NA,
  }

  public enum DataStructure {
    BinarySearchTree,
    BinaryTree,
    DirectedGraph,
    HashTable,
    LinkedList,
    MatrixGraph,
    PriorityQueue,
    Queue,
    Stack,
    String,
    Subarray,
    Trie,
    UndirectedGraph,
    NA,
  }

  /** Larger number indicates more difficulty  */
  public enum Difficulty {
    D1,
    D2,
    D3,
  }

  public enum Source {
    CrackingTheCodeInterview,
    ElementsOfProgrammingInterviews,
    LeetCode,
    NA,
  }

  public enum Trick {
    TwoPointer,
    ForwardAndBackwardScan,
    NA,
  }
}
