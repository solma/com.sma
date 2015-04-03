package com.shuoma.alg;

import static com.shuoma.annotation.Tag.DataStructure.Array;
import static com.shuoma.annotation.Tag.Difficulty.D2;
import static com.shuoma.annotation.Tag.Source.LeetCode;
import static com.shuoma.annotation.Tag.Trick.InplaceSwap;

import com.shuoma.annotation.Tag;

@Tag(dl = D2, dss = Array, source = LeetCode, tricks = InplaceSwap)
public class RotateImage {
  //get the relation straight first on the paper
  public void rotate(int[][] matrix) {
    int n = matrix.length;
    if (n < 1)
      return;
    int m = n;
    int maxLayer = (m + 1) / 2;
    int tmp, offset;
    for (int layer = 0; layer < maxLayer; layer++) {
      for (int j = layer;
           j < m - 1 - layer; j++) { //note the range of j(only includes one end point of an edge)
        offset = j - layer;
        tmp = matrix[layer][j];
        matrix[layer][j] = matrix[n - 1 - layer - offset][layer];
        matrix[n - 1 - layer - offset][layer] = matrix[n - 1 - layer][m - 1 - layer - offset];
        matrix[n - 1 - layer][m - 1 - layer - offset] = matrix[layer + offset][m - 1 - layer];
        matrix[layer + offset][m - 1 - layer] = tmp;
      }
    }
  }
}
