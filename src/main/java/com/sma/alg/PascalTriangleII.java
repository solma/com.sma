/*
 Given an integer rowIndex, return the rowIndexth row of the Pascal's triangle.
 Notice that the row index starts from 0.
*/
package com.sma.alg;

import com.sma.annotation.Tag;

import java.util.ArrayList;
import java.util.List;

import static com.sma.annotation.Tag.Algorithm.TopDown;
import static com.sma.annotation.Tag.DataStructure.StringT;
import static com.sma.annotation.Tag.Reference.LeetCode;

@Tag(algs = TopDown, dss = StringT, references = LeetCode)
public class PascalTriangleII {
  public static void main(String[] args) {
    new PascalTriangleII().getRow(30);
  }

  public long comb(long m, long n) {
    if (n == 0 || n == m)
      return 1;
    long product = 1;
    if (n > m - n) {
      for (long i = n + 1; i <= m; i++)
        product *= i;
      for (long i = 2; i <= m - n; i++)
        product /= i;
    } else {
      for (long i = m - n + 1; i <= m; i++)
        product *= i;
      for (long i = 2; i <= n; i++)
        product /= i;
    }
    System.out.println(m + " " + n + " " + product);
    return product;
  }

  //O(K) space
  public List<Integer> getRow(int rowIndex) {
    List<Integer> res = new ArrayList<>();
    for (int i = 0; i <= rowIndex; i++) {
      res.add((int) comb((long) rowIndex, (long) i));
    }
    return res;
  }
}
