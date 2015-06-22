package com.shuoma.alg;

// reference: crack the interview
// kth number in the form of 3^i*5^j*7^k
import static com.shuoma.annotation.Tag.DataStructure.Stack;
import static com.shuoma.annotation.Tag.Reference.CrackingTheCodeInterview;

import com.shuoma.annotation.Tag;

import java.util.ArrayList;

@Tag(dss = Stack, references = CrackingTheCodeInterview)
public class KthMagicNumber {

  public static void main(String[] args) {
    new KthMagicNumber().kthNumber(20);
  }

  // get kth number
  void kthNumber(int n) {
    if (n < 1) return;
    System.out.println(1);
    ArrayList<Integer> q3 = new ArrayList<>();
    ArrayList<Integer> q5 = new ArrayList<>();
    ArrayList<Integer> q7 = new ArrayList<>();
    q3.add(3);
    q5.add(5);
    q7.add(7);
    for (; n > 1; n--) {
      int min = Math.min(Math.min(q3.get(0), q5.get(0)), q7.get(0));
      // int min=Math.min(q3.get(0), q5.get(0));
      System.out.println(min);
      if (min != q7.get(0)) {
        if (min != q5.get(0)) {
          q3.remove(0);
          q3.add(min * 3);
        } else
          q5.remove(0);
        q5.add(min * 5);
      } else
        q7.remove(0);
      q7.add(min * 7);
    }
  }
}
