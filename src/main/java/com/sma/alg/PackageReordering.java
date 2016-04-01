package com.sma.alg;

import static com.sma.annotation.Tag.Algorithm.SlidingWindow;
import static com.sma.annotation.Tag.DataStructure.Hash;
import static com.sma.annotation.Tag.Reference.JulyEdu;

import com.sma.annotation.Tag;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

//http://ask.julyedu.com/question/308

/*
 In distributed systems, RPC request packages often arrive out of order.
 Write an algorithm to output packages in order when a new package arrives.
    For example, assuming the package number starts at 1，
    if packages arrive in the order of (1, 2, 5, 8, 10, 4, 3, 6, 9, 7),
    the output should be:
    1
    2
    3, 4, 5
    6
    7, 8, 9, 10
 since, when 3 arrives, 4 and 5 has already been arrived. Therefore (3, 4, 5)
 should output in one line.
*/

@Tag(algs = SlidingWindow, dss = Hash, references = JulyEdu)
public class PackageReordering {

  List<List<Integer>> reorder(int[] packages) {
    List<List<Integer>> ordered = new LinkedList<>();
    int nextExpected = 1;
    Set<Integer> cached = new HashSet<>();
    for (int p : packages) {
      if (p == nextExpected) {
        List<Integer> seq = new LinkedList<>();
        seq.add(p);
        p += 1;
        while (cached.contains(p)) {
          seq.add(p);
          cached.remove(p);
          p++;
        }
        ordered.add(seq);
        nextExpected = p;
      } else {
        cached.add(p);
      }
    }
    return ordered;
  }
}
