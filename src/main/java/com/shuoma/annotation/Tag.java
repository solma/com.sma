package com.shuoma.annotation;

import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.SOURCE;

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
    Concurrent,
    DepthFirstSearch,
    DivideConquer,
    DynamicProgramming,
    Geometry,
    Greedy,
    RandomT,
    Recursion,
    RegularExpression,
    RejectingMethod,
    Searching,
    SlidingWindow,
    Streaming,
    Sorting,
    TopDown,
    TopologicalSorting,
    NA,
  }

  enum DataStructure {
    Array,
    BTree,
    BinarySearchTree,
    BinaryTree,
    Calculator,
    DirectedGraph,
    Hash,
    IntervalT,
    IteratorT,
    LinkedListT,
    MatrixGraph,
    MinimumSpanningTree,
    MonotonicSequence,
    Palindrome,
    PriorityQueueT,
    QueueT,
    SegmentTree,
    StackT,
    StringT,
    Subarray,
    Substring,
    ThreadT,
    TournamentTree,
    Trie,
    UndirectedGraph,
    UnionFind,
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
    BFSWithMultipleSource,
    BFSWithHigherDimension,
    CheckAtEveryIndex,
    ForwardAndBackwardScan,
    FromTwoEndsToMiddle,
    InplaceSwap,
    PseudoHead,
    TwoOrMorePointers,
    NA,
  }
}
