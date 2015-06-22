package com.shuoma.alg;

import static com.shuoma.annotation.Tag.DataStructure.Array;
import static com.shuoma.annotation.Tag.Reference.LeetCode;

import com.shuoma.annotation.Tag;

@Tag(dss = Array, references = LeetCode)
public class ValidSudoku {
  public boolean isValidSudoku(char[][] board) {
    boolean[][] row = new boolean[9][9];//if num n is on row i then row[i][num]=true
    boolean[][] col = new boolean[9][9];
    boolean[][] block = new boolean[9][9];

    for (int i = 0; i < 9; i++) {
      for (int j = 0; j < 9; j++) {
        if (board[i][j] == '.')
          continue;
        int n = board[i][j] - '1';
        if (row[i][n] || col[j][n] || block[i / 3 * 3 + j / 3][n])
          return false;
        row[i][n] = col[j][n] = block[i / 3 * 3 + j / 3][n] = true;
      }
    }
    return true;
  }
}
