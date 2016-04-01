package com.sma.alg;

import static com.sma.annotation.Tag.Algorithm.Arithmetic;
import static com.sma.annotation.Tag.DataStructure.QueueT;
import static com.sma.annotation.Tag.Reference.CrackingTheCodeInterview;
import static com.sma.annotation.Tag.Reference.LeetCode;

import com.sma.annotation.Tag;

import java.util.ArrayList;
import java.util.List;

/***
 * Write a program to find the n-th ugly number.
 Ugly numbers are positive numbers whose prime factors only include 2, 3, 5.
 For example, 1, 2, 3, 4, 5, 6, 8, 9, 10, 12 is the sequence of the first 10 ugly numbers.
 Note that 1 is typically treated as an ugly number.
 */
@Tag(algs = Arithmetic, dss = QueueT, references = {CrackingTheCodeInterview, LeetCode})
public class UglyNumberII {

  public static void main(String[] args) {
    System.out.println(new UglyNumberII().nthUglyNumber(1600));
  }

  public int nthUglyNumber(int n) {
    if (n == 1) { return 1; }
    int[] uglies = {2, 3, 5};
    List<Long> q1 = new ArrayList<>();
    List<Long> q2 = new ArrayList<>();
    List<Long> q3 = new ArrayList<>();
    q1.add((long) uglies[0]);
    q2.add((long) uglies[1]);
    q3.add((long) uglies[2]);
    long min = 0;
    for (; n > 1; n--) {
      min = Math.min(Math.min(q1.get(0), q2.get(0)), q3.get(0));
      if (min != q3.get(0)) {
        if (min != q2.get(0)) {
          q1.remove(0);
          q1.add(min * uglies[0]);
        } else { q2.remove(0); }
        q2.add(min * uglies[1]);
      } else { q3.remove(0); }
      q3.add(min * uglies[2]);
    }
    return (int) min;
  }
}
