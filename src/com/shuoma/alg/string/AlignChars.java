package com.shuoma.alg.string;

/**
 * given a set of {x: N}, where x is a character and N is the number x should be used. put all
 * characters in one line such that for any x, no two occurrences of x are next to each other.
 */
import java.util.ArrayList;
import java.util.PriorityQueue;

public class AlignChars {
  public static void main(String[] args) {
    assert args.length == 2;
    new AlignChars().main(args[0], args[1]);
  }

  public void main(String characters, String numbers) {
    char[] syms = characters.toCharArray();
    char[] nums = numbers.toCharArray();

    // initilize priority queue
    PriorityQueue<Counter> pq = new PriorityQueue<Counter>();
    for (int i = 0; i < syms.length; i++) {
      Counter nc = new Counter(syms[i], nums[i]);
      pq.add(nc);
      System.out.println(nc);
    }

    // initialize tabu list
    StringBuilder sb = new StringBuilder();
    char lastUsed = ' ';

    ArrayList<Counter> collectors = new ArrayList<Counter>();

    while (pq.size() > 0) {
      System.out.print(pq + " ");
      while (pq.size() > 0 && pq.peek().key == lastUsed) {
        collectors.add(pq.poll());
      }
      System.out.println(" " + collectors + " " + sb.toString());
      Counter nxtAvail = pq.poll();
      sb.append(nxtAvail.key);
      lastUsed = nxtAvail.key;
      nxtAvail.num--;
      if (nxtAvail.num > 0) pq.add(nxtAvail);
      for (Counter c : collectors)
        pq.add(c);
      collectors.clear();
    }

    System.out.println(sb.toString());
  }

  class Counter implements Comparable<Counter> {
    char key;
    int num;

    public Counter(char key, char num) {
      this.key = key;
      this.num = num - '0';
    }

    @Override
    public int compareTo(Counter other) {
      return other.num - num;
    }

    @Override
    public String toString() {
      return key + ":" + num;
    }
  }
}
