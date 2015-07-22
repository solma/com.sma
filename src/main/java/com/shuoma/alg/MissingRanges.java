package com.shuoma.alg;

import static com.shuoma.annotation.Tag.DataStructure.Array;
import static com.shuoma.annotation.Tag.DataStructure.Hash;
import static com.shuoma.annotation.Tag.Difficulty.D2;
import static com.shuoma.annotation.Tag.Reference.LeetCode;

import com.shuoma.annotation.Tag;

import java.util.LinkedList;
import java.util.List;

//Given a sorted integer array where the range of elements are [0, 99] inclusive, return its missing ranges.
//For example, given [0, 1, 3, 50, 75], return [“2”, “4->49”, “51->74”, “76->99”]

@Tag(dl = D2, dss = {Array, Hash}, references = LeetCode)
public class MissingRanges {
  public static void main(String[] args) {
    System.out.println(missingRanges(new int[] {0, 1, 3, 50, 75}, new int[] {0, 99}));
  }

  public static List<List<Integer>> missingRanges(int[] array, int[] boundary) {
    int n = array.length;
    List<List<Integer>> res = new LinkedList<>();
    if (n == 0)
      return res;
    int prev = array[0];
    List<Integer> range;
    if (boundary[0] < prev) {
      range = new LinkedList<>();
      range.add(boundary[0]);
      range.add(prev - 1);
      res.add(range);
    }
    for (int i = 1; i < n; i++) {
      if (array[i] - prev >= 2) {
        range = new LinkedList<>();
        range.add(prev + 1);
        range.add(array[i] - 1);
        res.add(range);
      }
      prev = array[i];
    }
    if (array[n - 1] < boundary[1]) {
      range = new LinkedList<>();
      range.add(array[n - 1] + 1);
      range.add(boundary[1]);
      res.add(range);
    }
    return res;
  }
}
