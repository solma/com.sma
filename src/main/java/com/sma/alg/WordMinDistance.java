package com.sma.alg;

import static com.sma.annotation.Tag.Algorithm.Sorting;
import static com.sma.annotation.Tag.DataStructure.Array;
import static com.sma.annotation.Tag.Reference.Interview;

import com.sma.annotation.Tag;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Given a sens of words in array, find the min distance of two given words.
 * For example: the sens is: {“the”, “quick”, “brown”, “fox”, “quick”},
 * distance(“fox”,”the”) == 3 and distance(“quick”, “fox”) == 1
 */
@Tag(algs = Sorting, dss = Array, references = Interview)
public class WordMinDistance {
  Map<String, List<Integer>> index = new HashMap<>();

  public WordMinDistance(String[] sens) {
    for (int i = 0; i < sens.length; i++) {
      if (index.containsKey(sens[i])) {
        index.get(sens[i]).add(i);
      } else {
        List<Integer> offset = new ArrayList<>();
        offset.add(i);
        index.put(sens[i], offset);
      }
    }
  }

  public int distance(String w1, String w2) {
    List<Integer> offset1 = index.get(w1);
    List<Integer> offset2 = index.get(w2);
    if (offset1 == null || offset2 == null)
      return Integer.MAX_VALUE;
    int minDistance = Integer.MAX_VALUE;
    int idx1 = 0;
    int idx2 = 0;
    while (idx1 < offset1.size() && idx2 < offset2.size()) {
      minDistance = Math.min(minDistance, Math.abs(offset1.get(idx1) - offset2.get(idx2)));
      if (offset1.get(idx1) < offset2.get(idx2))
        idx1++;
      else
        idx2++;
    }
    return minDistance;
  }

  public static void main(String[] args) {
    String[] sens = new String[] {"the", "quick", "brown", "fox", "quick"};
    WordMinDistance dict = new WordMinDistance(sens);
    System.out.println(dict.distance("fox", "the")); //3
    System.out.println(dict.distance("quick", "fox")); //1
  }
}
