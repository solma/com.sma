package com.shuoma.alg;

import static com.shuoma.annotation.Tag.DataStructure.Array;
import static com.shuoma.annotation.Tag.Reference.Interview;
import static com.shuoma.annotation.Tag.Trick.TwoOrMorePointers;

import com.shuoma.annotation.Tag;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

@Tag(dss = Array, references = Interview, tricks = {TwoOrMorePointers})
/**
 * Given a sorted array, find two elements i and j, num[i] - num[j] = target.
 * Space:O(1), Time: O(N)
 */
public class TwoDifference {

  List<Integer> twoDifference(int[] num, int target) {
    int n = num.length;
    List<Integer> ret = new LinkedList<>();
    for (int first = 0, second = 0; first < n && second < n; ) {
      int diff = num[second] - num[first];
      if (diff == target) {
        return Arrays.asList(num[second], num[first]);
      } else {
        if (diff < target) second++;
        else first++;
      }
    }
    return ret;
  }
}
