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
  Reference[] references() default Reference.NA;
  Trick[] tricks() default Trick.NA;

  enum Algorithm {
    Arithmetic,
    ArithmeticSum,
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
    Searching,
    SlidingWindow,
    Sorting,
    TopDown,
    NA,
  }

  enum DataStructure {
    Array,
    BinarySearchTree,
    BinaryTree,
    Calculator,
    DirectedGraph,
    Hash,
    Interval,
    LinkedList,
    MatrixGraph,
    MinimumSpanningTree,
    MonotonicSequence,
    Palindrome,
    PriorityQueue,
    Queue,
    SegmentTree,
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

  enum Reference {
    CrackingTheCodeInterview,
    CSDN,
    ElementsOfProgrammingInterviews,
    GoogleCodeChallengeFoobar,
    Interview,
    JulyEdu,
    LeetCode,
    LintCode,
    StackOverview,
    Topcoder,
    WeChat,
    NA,
  }

  enum Trick {
    AccumulativeSum,
    ForwardAndBackwardScan,
    FromTwoEndsToMiddle,
    InplaceSwap,
    MultipleSourceBFS,
    TwoOrMorePointers,
    NA,
  }
}
