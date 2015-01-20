package com.shuoma.alg.dp;


public class MatrixMultiplication {
  public static void main(String[] args) {
    new MatrixMultiplication().main();
  }

  public void main() {
    int[] p = {10, 30, 20, 10, 5, 25, 15};
    matrixChainOrder(p);

    StringBuilder expr = new StringBuilder();
    for (int i = 0; i < p.length - 1; i++) {
      if (i > 0) expr.append("*");
      expr.append(i + 1);
    }
    printOptimalParenthesizations(partition, 0, partition.length - 1, expr.toString());
    System.out.println(expr);

    System.out.println(count(p));
  }

  protected int[][] cost;
  protected int[][] partition;

  public void matrixChainOrder(int[] p) {
    int n = p.length - 1; // number of matrices
    cost = new int[n][n];
    partition = new int[n][n];

    for (int i = 0; i < n; i++) {
      cost[i] = new int[n];
      partition[i] = new int[n];
    }

    for (int len = 2; len <= n; len++) {
      for (int i = 0; i < n - len + 1; i++) {
        int j = i + len - 1;
        cost[i][j] = Integer.MAX_VALUE;
        for (int k = i; k <= j - 1; k++) {
          int q = cost[i][k] + cost[k + 1][j] + p[i] * p[k + 1] * p[j + 1];
          if (q < cost[i][j]) {
            cost[i][j] = q;
            partition[i][j] = k;
          }
        }
      }
    }
  }

  void printOptimalParenthesizations(int[][] partition, int i, int j, String expr) {
    if (i != j) {
      int split = partition[i][j];

      StringBuilder cpy = new StringBuilder();
      for (int idx = 0; idx < expr.length(); idx++) {
        char c = expr.charAt(idx);
        cpy.append(c);
        if (c == (split + 1 + '0') || c == (j + 1 + '0')) cpy.append(')');
        if (c == (split + 1 + '1') || c == (i + 1 + '0')) cpy.insert(cpy.length() - 1, '(');
      }
      expr = cpy.toString();
      System.out.println("split=" + split + " expr=" + expr);

      printOptimalParenthesizations(partition, i, split, expr);
      printOptimalParenthesizations(partition, split + 1, j, expr);
    }
  }




  static class Result {
    int cost;
    String combination;
    @Override
    public String toString() {
      return combination;
    }
  }

  public static Result count(int[] dim) {
    int N = dim.length;
    int[][] opt = new int[N][N];
    int[][] sol = new int[N][N];

    for (int i = 1; i < N; i++)
      opt[i][i] = 0;

    for (int l = 2; l < N; l++) { // l is the length of matrix chain
      for (int i = 1; i < N - l + 1; i++) { // i is the start of the chain
        int j = i + l - 1; // j is the end of the chain
        opt[i][j] = Integer.MAX_VALUE;
        for (int k = i; k < j; k++) {
          int ten = opt[i][k] + opt[k + 1][j] + dim[i - 1] * dim[k] * dim[j];
          if (ten < opt[i][j]) {
            opt[i][j] = ten;
            sol[i][j] = k;
          }
        }
      }
    }

    Result result = new Result();
    result.cost = opt[1][N - 1];
    result.combination = buildCombination(N, sol);
    return result;
  }

  private static String buildCombination(int N, int[][] sol) {
    StringBuilder builder = new StringBuilder();
    for (int i = 1; i < N; i++) {
      builder.append((char) ('A' + (i - 1)));
    }
    return addBracket(builder.toString(), 1, N - 1, sol[1][N - 1], sol);
  }

  private static String addBracket(String str, int i, int j, int k, int[][] sol) {
    if (str.length() <= 2) return str;
    String a = str.substring(i - 1, k);
    String b = str.substring(k, j);
    StringBuilder builder = new StringBuilder();
    builder.append("(");
    builder.append(addBracket(a, i, k, sol[i][k], sol));
    builder.append(")(");
    builder.append(addBracket(b, 1, j - k, sol[k][j], sol));
    builder.append(")");
    return builder.toString();
  }
}
