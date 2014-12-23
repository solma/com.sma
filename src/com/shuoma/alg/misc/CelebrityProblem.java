package com.shuoma.alg.misc;

import java.util.Stack;

/**
 * In a party of N people, only one person is known to everyone. Such a person may be present in the
 * party, if yes, (s)he doesn't know anyone in the party. We can only ask questions like ��does A
 * know B? ��. Find the stranger (celebrity) in minimum number of questions.
 *
 * http://blog.csdn.net/fightforyourdream/article/details/17410993
 * http://www.careercup.com/question?id=13167666 http://www.geeksforgeeks.org/the-celebrity-problem/
 */

public class CelebrityProblem {

  static int N = 8;
  static int size = 4;
  static int matrix[][] = { {0, 0, 1, 1}, {1, 0, 1, 0}, {0, 0, 0, 0}, {0, 1, 1, 0}};

  public static boolean firstKnowsSecond(int a, int b) {
    return matrix[a][b] != 0;
  }

  public static int celebrity(boolean[][] F) {
    // Start checking the relation from F[0][1].
    int i = 0, j = 1;
    while (j < F.length) {
      if (F[i][j]) {
        i = j++; // all candidates j' < j are not celebrity candidates.
      } else { // F[i][j] == false.
        ++j; // i is still a celebrity candidate but j is not.
      }
    }
    return i;
  }

  public static int celebrityUsingStack(int size) {
    Stack<Integer> stack = new Stack<Integer>();
    int i;
    int C; // Celebrity


    i = 0;
    while (i < size) {
      stack.push(i);
      i++;
    }

    int A = stack.pop();
    int B = stack.pop();

    while (!stack.empty()) {
      if (firstKnowsSecond(A, B)) {
        A = stack.pop();
      } else {
        B = stack.pop();
      }
    }

    if (firstKnowsSecond(A, B))
      C = B;
    else
      C = A;

    if (sanityTest(C) == -1) {
      System.out.println("Sanity Test Failed!");
    } else {
      System.out.println("Test ok!");
    }
    return C;
  }

  public static int sanityTest(int C) {
    int i = 0;
    Stack<Integer> stack = new Stack<Integer>();
    while (i < size) {
      if (i != C) {
        stack.push(i);
      }
      i++;
    }
    while (!stack.isEmpty()) {
      i = stack.pop();
      if (firstKnowsSecond(C, i)) {
        return -1;
      }
      if (!firstKnowsSecond(i, C)) {
        return -1;
      }
    }
    return C;
  }

  public static void main(String[] args) {
    int id = celebrityUsingStack(size);
    System.out.println(id);
  }



}
