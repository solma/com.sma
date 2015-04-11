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
    Backtracking,
    BinarySearch,
    BitManipulation,
    BreadthFirstSearch,
    BottomUp,
    DepthFirstSearch,
    DivideConquer,
    DynamicProgramming,
    Geometry,
    Greedy,
    Recursion,
    RegularExpression,
    Sorting,
    Streaming,
    TopDown,
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
    TournamentTree,
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
    CSDN,
    ElementsOfProgrammingInterviews,
    Interview,
    JulyEdu,
    LeetCode,
    Topcoder,
    NA,
  }

  enum Trick {
    AccumulativeSum,
    ForwardAndBackwardScan,
    FromTwoEndsToMiddle,
    InplaceSwap,
    MultipleSourceBFS,
    TwoPointer,
    NA,
  }
}
