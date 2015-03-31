package com.shuoma;

import static com.shuoma.annotation.Tag.DataStructure.Array;
import static com.shuoma.annotation.Tag.Source.LeetCode;

import com.shuoma.annotation.Tag;

import java.util.Arrays;

@Tag(dss = Array, source = LeetCode)
public class PrintSpiralSquare {
  public static void main(String[] args) {
    new PrintSpiralSquare().main();
  }

  void main() {
    int n = 7, i = 0, j = -1, count = 1;
    int[][] bits = new int[n][n];
    for (int k = 0; k < bits.length; k++)
      Arrays.fill(bits[k], 0);
    while (count <= n * n) {
      System.out.println(i + " " + j);
      // go right
      j++;
      while (!outBound(i, j, n) && bits[i][j] == 0) {
        bits[i][j] = count;
        j++;
        count++;
      }
      j--;
      System.out.println(i + " " + j);
      //  go down
      i++;
      while (!outBound(i, j, n) && bits[i][j] == 0) {
        bits[i][j] = count;
        i++;
        count++;
      }
      i--;
      System.out.println(i + " " + j);
      // go left
      j--;
      while (!outBound(i, j, n) && bits[i][j] == 0) {
        bits[i][j] = count;
        j--;
        count++;
      }
      j++;
      System.out.println(i + " " + j);
      // go up
      i--;
      while (!outBound(i, j, n) && bits[i][j] == 0) {
        bits[i][j] = count;
        i--;
        count++;
      }
      i++;
    }

    for (i = 0; i < bits.length; i++) {
      for (j = 0; j < bits.length; j++)
        System.out.print(String.format("%-4d", bits[i][j]));
      System.out.println();
    }
  }

  Boolean outBound(int i, int j, int n) {
    if (i < 0 || i >= n)
      return true;
    if (j < 0 || j >= n)
      return true;
    return false;
  }
}
