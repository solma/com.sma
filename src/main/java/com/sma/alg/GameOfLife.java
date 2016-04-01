package com.sma.alg;

import com.sma.annotation.Tag;

import static com.sma.annotation.Tag.Algorithm.Recursion;
import static com.sma.annotation.Tag.DataStructure.Hash;
import static com.sma.annotation.Tag.DataStructure.StringT;
import static com.sma.annotation.Tag.Reference.LeetCode;
import static com.sma.util.ArrayUtil.print;

/**
 According to the Wikipedia's article: "The Game of Life, also known simply as Life, is a cellular automaton
 devised by the British mathematician John Horton Conway in 1970."
 Given a board with m by n cells, each cell has an initial state live (1) or dead (0). Each cell interacts with its
 eight neighbors (horizontal, vertical, diagonal) using the following four rules (taken from the above Wikipedia article):

 Any live cell with fewer than two live neighbors dies, as if caused by under-population.
 Any live cell with two or three live neighbors lives on to the next generation.
 Any live cell with more than three live neighbors dies, as if by over-population.
 Any dead cell with exactly three live neighbors becomes a live cell, as if by reproduction.

 Write a function to compute the next state (after one update) of the board given its current state.

 Follow up:
 Could you solve it in-place? Remember that the board needs to be updated at the same time: You cannot update some cells
 first and then use their updated values to update other cells.
 In this question, we represent the board using a 2D array. In principle, the board is infinite, which would cause
 problems when the active area encroaches the border of the array. How would you address these problems?
 */
@Tag(algs = {Recursion}, dss = {Hash, StringT}, references = {LeetCode})
public class GameOfLife {

  public static void main(String[] args) {
    GameOfLife ins = new GameOfLife();
    int[][] board = {{1}};
    ins.gameOfLife(board);
    print(board);
  }

  static int LIVE = 1;

  void gameOfLife(int[][] board) {
    int m = board.length;
    int n = board[0].length;
    for (int i = 0; i < m; i++) {
      for (int j = 0; j < n; j++) {
        board[i][j] |= isLive(board, i, j, m, n) ? 2 : 0;
      }
    }

    for (int i = 0; i < m; i++) {
      for (int j = 0; j < n; j++) {
        board[i][j] >>= 1;
      }
    }
  }

  boolean isLive(int[][] board, int row, int col, int m, int n) {
    int liveCnt = 0;
    for (int i = -1; i <= 1; i++) {
      for (int j = -1; j <= 1; j++) {
        if (i == 0 && j == 0) {
          continue;
        }
        int nRow = row + i, nCol = col + j;
        if (!outOfBound(nRow, nCol, m, n) && (board[nRow][nCol] & 1) == LIVE) {
          liveCnt++;
        }
      }
    }
    return liveCnt == 3 || (liveCnt == 2 && (board[row][col] & 1) == LIVE);
  }

  boolean outOfBound(int i, int j, int m, int n) {
    return i < 0 || j < 0 || i >= m || j >= n;
  }
}
