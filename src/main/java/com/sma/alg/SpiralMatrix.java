package com.sma.alg;

import static com.sma.annotation.Tag.DataStructure.Array;
import static com.sma.annotation.Tag.Reference.LeetCode;

import com.sma.annotation.Tag;

import java.util.ArrayList;

@Tag(dss = Array, references = LeetCode)
public class SpiralMatrix {
  public static void main(String[] args) {
    new SpiralMatrix().main();
  }

  public void main() {
    int[][] A =
        new int[][] {{1, 2, 3, 4, 5}, {6, 7, 8, 9, 10}, {11, 12, 13, 14, 15}, {16, 17, 18, 19, 20},
            {21, 22, 23, 24, 25}};
    System.out.println(spiralOrder(A));
  }

  //second pass
  public ArrayList<Integer> spiralOrder(int[][] matrix) {
    ArrayList<Integer> ret = new ArrayList<Integer>();
    int n = matrix.length;
    if (n == 0)
      return ret;
    int m = matrix[0].length;
    if (m == 0)
      return ret;

    boolean[][] visit = new boolean[n][m];
    int[][] dir = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};

    int dirChange;
    int i = 0, j = 0, dirIdx = 0;
    while (true) {
      visit[i][j] = true;
      ret.add(matrix[i][j]);

      for (dirChange = 0; dirChange < dir.length; dirChange++) {
        int newDirIdx = (dirIdx + dirChange) % dir.length;
        if (validMove(i + dir[newDirIdx][0], j + dir[newDirIdx][1], n, m, visit)) {
          i += dir[newDirIdx][0];
          j += dir[newDirIdx][1];
          dirIdx = newDirIdx;//change the direction
          break;
        }
      }
      if (dirChange == dir.length)
        break;
    }
    return ret;

  }

  boolean validMove(int i, int j, int n, int m, boolean[][] visit) {
    if (i < 0 || i >= n)
      return false;
    if (j < 0 || j >= m)
      return false;
    if (visit[i][j])
      return false;
    return true;
  }

  //first pass
  // public ArrayList<Integer> spiralOrder(int[][] matrix) {
  // ArrayList<Integer> ret=new ArrayList<Integer>();
  // int nRow=matrix.length;
  // if(nRow==0) return ret;
  // int nCol=matrix[0].length;
  // int [][] visitFlag=new int[nRow][nCol];
  // int i, j;
  // for(i=0;i<nRow;i++)
  // for(j=0;j<nCol;j++)
  // visitFlag[i][j]=0;
  // i=0;
  // j=0;
  // int[][] dir=new int[][]{{0,1}, {1,0}, {0,-1}, {-1, 0}};
  // int dirIdx=0;
  // int dirChange=0;
  // while(visitFlag[i][j]==0||dirChange==1){
  // visitFlag[i][j]=1;
  // if(dirChange==0) ret.add(matrix[i][j]);
  // if( ((j>0&&dirIdx==2)|| (j<nCol-1&&dirIdx==0)||(i<nRow-1&&dirIdx==1)||(i>0&&dirIdx==3))&&visitFlag[i+dir[dirIdx][0]][j+dir[dirIdx][1]]==0){
  // dirChange=0;
  // i+=dir[dirIdx][0];
  // j+=dir[dirIdx][1];
  // }else{
  // dirIdx=(dirIdx+1)%4;
  // dirChange+=1;
  // }
  // }

  // return ret;
  // }
}
