package com.shuoma.alg;

/**
 * given a set of {x: N}, where x is a character and N is the number x should be used. put all
 * characters in one line such that for any x, no two occurrences of x are next to each other.
 */
import static com.shuoma.annotation.Tag.Algorithm.Greedy;
import static com.shuoma.annotation.Tag.DataStructure.String;
import static com.shuoma.annotation.Tag.Reference.Interview;

import com.shuoma.annotation.Tag;

import java.util.PriorityQueue;

@Tag(algs = Greedy, dss = {String, Tag.DataStructure.PriorityQueueT}, references = Interview)
public class SeperateSameLetters {
  public static void main(String[] args) {
    new SeperateSameLetters().main("abc", "145");
  }

  // intervals guarantee that a solution exists
  public void main(String characters, String numbers) {
    char[] symbols = characters.toCharArray();
    char[] nums = numbers.toCharArray();

    // initilize priority queue
    PriorityQueue<Counter> pq = new PriorityQueue<>();
    for (int i = 0; i < symbols.length; i++) {
      Counter nc = new Counter(symbols[i], nums[i]);
      pq.add(nc);
      System.out.println(nc);
    }

    // initialize tabu list
    StringBuilder sb = new StringBuilder();
    char lastUsed = ' ';

    //ArrayList<Counter> collectors = new ArrayList<>();

    while (pq.size() > 0) {
      System.out.println(pq + " ");

//      while (pq.size() > 0 && pq.peek().key == lastUsed) {
//        collectors.add(pq.poll());
//      }

      //System.out.println("collectors:" + collectors + " " + sb.toString());
      Counter nxtAvail = pq.poll();
      sb.append(nxtAvail.key);
      lastUsed = nxtAvail.key;
      nxtAvail.num--;
      if (nxtAvail.num > 0) pq.offer(nxtAvail);
//      for (Counter c : collectors)
//        pq.offer(c);
//      collectors.clear();
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
