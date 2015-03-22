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
    DivideConquer,
    DynamicProgramming,
    Greedy,
    Recursion,
    Streaming,
    NA,
  }

  public enum DataStructure {
    BinarySearchTree,
    LinkedList,
    PriorityQueue,
    Queue,
    Stack,
    NA,
  }

  public enum Source {
    Leetcode,
    NA,
  }

  public enum Trick {
    TwoPointer,
    ForwardAndBackwardScan,
    NA,
  }
}
