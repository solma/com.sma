package com.shuoma.alg;
import static com.shuoma.annotation.Tag.Reference.LeetCode;

import com.shuoma.annotation.Tag;

import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

@Tag(dss = Tag.DataStructure.LinkedList, reference = LeetCode)
public class LargestNumber {
  public static void main(String[] args) {
    System.out.println(largestNumber(new int[]{0, 0}));
  }

  public static String largestNumber(int[] num) {
    List<Integer> li = new LinkedList<>();
    for(int n : num) li.add(n);
    Comparator<Integer> comp = new Comparator<Integer>() {
      @Override
      public int compare(Integer o1, Integer o2) {
        String s1 = String.valueOf(o1), s2 = String.valueOf(o2);
        if (s1.contains(s2) || s2.contains(s1)) {
          return (s1 + s2).compareTo(s2 + s1);
        }
        return s1.compareTo(s2);
      }
    };
    Collections.sort(li, Collections.reverseOrder(comp));
    StringBuilder sb = new StringBuilder();
    for(int n : li) {
      sb.append(String.valueOf(n));
    }
    try {
      return String.valueOf(Long.parseLong(sb.toString()));
    } catch (Exception ex) {
      return sb.toString();
    }
  }
}
