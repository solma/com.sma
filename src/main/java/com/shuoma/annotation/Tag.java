package com.shuoma.annotation;

import static java.lang.annotation.RetentionPolicy.SOURCE;
import static java.lang.annotation.ElementType.TYPE;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

@Target(TYPE)
@Retention(SOURCE)
public @interface Tag {

  Algorithm alg() default Algorithm.NA;
  DataStructure ds() default DataStructure.NA;
  Source source() default Source.NA;
  Trick trick() default Trick.NA;


  public enum Algorithm {
    BinarySearch,
    BitOperation,
    DivideConquer,
    DynamicProgramming,
    Greedy,
    Recursion,
    Streaming,
    NA,
  }

  public enum DataStructure {
    BinarySearchTree,
    HashTable,
    LinkedList,
    PriorityQueue,
    Queue,
    Stack,
    Trie,
    NA,
  }

  public enum Source {
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
