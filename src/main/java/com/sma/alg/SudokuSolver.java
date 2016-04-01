package com.sma.alg;
import java.util.*;
public class SudokuSolver{
    public static void main(String[] args){

    }

    public void solveSudoku(char[][] board) {
         ArrayList<Integer> toBeFilled=new ArrayList<Integer>();
         for(int i=0;i<9;i++){
            for(int j=0;j<9;j++){
                if(board[i][j]=='.') toBeFilled.add(9*i+j);
            }
        }
        dfs(toBeFilled,0,toBeFilled.size(),board);
    }

    public boolean dfs(ArrayList<Integer> toBeFilled, int cur, int len, char[][]board){
        if(cur==len) return true;
        int idx=toBeFilled.get(cur);

        int i=idx/9, j=idx%9;
        for(int fill=1;fill<=9;fill++){
            if(isValid(fill, i, j, board)){
                board[i][j]=(char)('0'+fill);
                if(dfs(toBeFilled, cur+1, len, board)) return true;
                board[i][j]='.';
            }
        }
        return false;
    }

    public boolean isValid(int fill, int row, int col, char[][] board){
        for(int i=0;i<9;i++){
            if(board[row][i]-'0'==fill) return false;
            if(board[i][col]-'0'==fill) return false;
            if(board[row/3*3+i/3][col/3*3+i%3]-'0'==fill) return false;
        }
        return true;
    }
}
