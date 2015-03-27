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

  enum Algorithm {
    Arithmetic,
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

  enum DataStructure {
    Array,
    BinarySearchTree,
    BinaryTree,
    DirectedGraph,
    HashTable,
    Interval,
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
  enum Difficulty {
    D1,
    D2,
    D3,
  }

  enum Source {
    CrackingTheCodeInterview,
    ElementsOfProgrammingInterviews,
    LeetCode,
    NA,
  }

  enum Trick {
    ForwardAndBackwardScan,
    FromTwoEndsToMiddle,
    InplaceHash,
    TwoPointer,
    NA,
  }
}
