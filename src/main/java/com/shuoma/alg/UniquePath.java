package com.shuoma.alg;

import static com.shuoma.annotation.Tag.Algorithm.Arithmetic;
import static com.shuoma.annotation.Tag.Reference.LeetCode;

import com.shuoma.annotation.Tag;

@Tag(algs = Arithmetic, references = LeetCode)
public class UniquePath {
  public static void main(String[] args) {
    UniquePath ins = new UniquePath();
    System.out.println(ins.uniquePaths(10, 10));
  }


  public int uniquePaths(int m, int n) {
    int total = m + n - 2;
    //C(total, m-1) or C(total, n-1)
    long ret = 1;
    if (m >= n) {
      for (int i = m; i <= total; i++)
        ret *= i;
      for (int i = 2; i <= n - 1; i++)
        ret /= i;
    } else {
      for (int i = n; i <= total; i++)
        ret *= i;
      for (int i = 2; i <= m - 1; i++)
        ret /= i;
    }
    return (int) ret;
  }
}
