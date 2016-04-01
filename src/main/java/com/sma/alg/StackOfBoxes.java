package com.sma.alg;

import static com.sma.annotation.Tag.Algorithm.DynamicProgramming;
import static com.sma.annotation.Tag.DataStructure.Array;
import static com.sma.annotation.Tag.Reference.CrackingTheCodeInterview;
import static com.sma.util.ArrayUtil.comparatorIntArray;

import com.sma.annotation.Tag;

import java.util.Arrays;

/**
 You have a stack of n boxes, with widths wi, height hi and depth di. The boxes can't be rotated and
 can only be stacked on top of one another if each box in the stack is strictly larger than the box
 above it in width and depth. Implement a method to build the tallest stack possible,
 where the height of a stack is the sum of the heights of each box.
 */
@Tag(algs = {DynamicProgramming}, dss = {Array}, references = CrackingTheCodeInterview)
public class StackOfBoxes {

  public static void main(String[] args) {
    StackOfBoxes ins = new StackOfBoxes();
    int[][] boxes = new int[][]{
        {1, 1, 1},
        {7, 10, 7},
        {10, 7, 10},
        {5, 5, 5}
    };
    System.out.println(ins.maxHeight(boxes));
  }

  public int maxHeight(int[][] boxes) {
    int n = boxes.length;
    if (n == 0) return 0;
    int max = 0;
    for (int i = 0; i < 2; i++) {
      max = Math.max(max, maxHeightOneDimension(boxes, i));
    }
    return max;
  }

  int maxHeightOneDimension(int[][] boxes, int idx) {
    Arrays.sort(boxes, comparatorIntArray(idx));
    int n = boxes.length;
    int[] max = new int[n];
    int maxHeight = max[0] = boxes[0][2];
    for (int i = 1; i < n; i++) {
      for (int j = i - 1; j >= 0; j--) {
        if (boxes[j][1 - idx] < boxes[i][1 - idx]) {
          max[i] = Math.max(max[i], max[j] + boxes[i][2]);
        }
      }
      maxHeight = Math.max(max[i], maxHeight);
    }
    return maxHeight;
  }
}
