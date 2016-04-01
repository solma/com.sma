package com.sma.alg;

import static com.sma.annotation.Tag.DataStructure.LinkedListT;
import static com.sma.annotation.Tag.Trick.TwoOrMorePointers;

import com.sma.annotation.Tag;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

@Tag(dss = {LinkedListT}, tricks = TwoOrMorePointers)
public class CommonElementsOfSortedLists {

  public static void main(String[] args) {
    new CommonElementsOfSortedLists().main();
  }

  Random rand = new Random();

  public void main() {
    for (int test = 1; test <= 100; test++) {
      int nOfList = rand.nextInt(3) + 2;
      List<List<Integer>> lists = new ArrayList<>();
      for (int i = 0; i < nOfList; i++) {
        List<Integer> li = new ArrayList<>();
        int sizeOfList = rand.nextInt(10) + 3;
        for (int j = 0; j < sizeOfList; j++) { li.add(rand.nextInt(20)); }
        li.add(5);
        Collections.sort(li);
        lists.add(li);
      }

      System.out.println("Case " + test);
      for (List<Integer> li : lists) { System.out.println(li); }
      // find the intersection of all lists
      System.out.println("Intersection set=" + intersect(lists) + "\n");
    }
  }

  public List<Integer> intersect(List<List<Integer>> lists) {
    List<Integer> ret = new ArrayList<>();

    int[] pos = new int[lists.size()];
    while (true) {
      // read one ele from the head of each list
      int max = Integer.MIN_VALUE;
      for (int i = 0; i < pos.length; i++) {
        List<Integer> li = lists.get(i);
        if (pos[i] == li.size()) return ret;
        max = Math.max(max, li.get(pos[i]));
      }

      boolean common = true;
      for (int i = 0; i < pos.length; i++) {
        List<Integer> li = lists.get(i);
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
