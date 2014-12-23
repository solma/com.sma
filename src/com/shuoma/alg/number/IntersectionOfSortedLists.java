package com.shuoma.alg.number;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class IntersectionOfSortedLists {

  public static void main(String[] args) {
    new IntersectionOfSortedLists().main();
  }

  Random rand = new Random();

  public void main() {
    for (int test = 1; test <= 100; test++) {

      int nOfList = rand.nextInt(3) + 2;

      ArrayList<ArrayList<Integer>> lists = new ArrayList<ArrayList<Integer>>();
      for (int i = 0; i < nOfList; i++) {
        ArrayList<Integer> li = new ArrayList<>();
        int sizeOfList = rand.nextInt(10) + 3;
        for (int j = 0; j < sizeOfList; j++)
          li.add(rand.nextInt(20));
        li.add(5);
        Collections.sort(li);

        lists.add(li);
      }

      System.out.println("Case " + test);
      for (ArrayList<Integer> li : lists) {
        System.out.println(li);
      }
      // find the intersection of all lists
      System.out.println("Intersection set=" + intersect(lists) + "\n");
    }
  }

  public ArrayList<Integer> intersect(ArrayList<ArrayList<Integer>> lists) {
    ArrayList<Integer> ret = new ArrayList<Integer>();

    int[] pos = new int[lists.size()];
    while (true) {
      // read one ele from the head of each list
      int max = Integer.MIN_VALUE;
      for (int i = 0; i < pos.length; i++) {
        ArrayList<Integer> li = lists.get(i);
        if (pos[i] == li.size()) return ret;
        max = Math.max(max, li.get(pos[i]));
      }

      boolean common = true;
      for (int i = 0; i < pos.length; i++) {
        ArrayList<Integer> li = lists.get(i);
        if (li.get(pos[i]) < max) {
          pos[i]++;
          common = false;
        }
      }

      if (common) {// find a common ele
        ret.add(lists.get(0).get(pos[0]));
        for (int i = 0; i < pos.length; i++)
          pos[i]++;
      }

    }


  }

}
