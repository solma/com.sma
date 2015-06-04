package com.shuoma.alg;

import static com.shuoma.annotation.Tag.Algorithm.Recursion;
import static com.shuoma.annotation.Tag.Reference.LeetCode;

import com.shuoma.annotation.Tag;

import java.util.ArrayList;

@Tag(algs = Recursion, reference = LeetCode)
public class NQueens {
  public static void main(String[] args) {
    new NQueens().main(Integer.parseInt(args[0]));
  }

  public void main(int n) {
    long curTime = System.currentTimeMillis();
    // for(String[] sol:solveNQueens(n)){
    // for(String row: sol){
    // System.out.println(row);
    // }
    // System.out.println();
    // }
    System.out.println(solveNQueens(n).size());
    //System.out.print((System.currentTimeMillis()-curTime));
  }


  //second pass
  public ArrayList<String[]> solveNQueens(int n) {
    return solveNQueens(n, new ArrayList<Integer>(), new ArrayList<Integer>(),
        new ArrayList<Integer>());
  }

  public ArrayList<String[]> solveNQueens(int n, ArrayList<Integer> cols, ArrayList<Integer> diags, ArrayList<Integer> counterDiags) {
    ArrayList<String[]> rets = new ArrayList<String[]>();
    if (cols.size() == n) {
      String[] sol = new String[n];
      for (int i = 0; i < n; i++) {
        sol[i] = "";
        for (int j = 0; j < n; j++) {
          if (j == cols.get(i))
            sol[i] = sol[i] + "Q";
          else
            sol[i] = sol[i] + ".";
        }
      }
      rets.add(sol);
      return rets;
    }
    for (int col = 0; col < n; col++) {
      if (cols.contains(col) || diags.contains(col - cols.size()) || counterDiags
          .contains(col + cols.size()))
        continue;
      cols.add(col);
      diags.add(col - cols.size() + 1);
      counterDiags.add(col + cols.size() - 1);
      rets.addAll(solveNQueens(n, cols, diags, counterDiags));
      cols.remove(cols.size() - 1);
      diags.remove(diags.size() - 1);
      counterDiags.remove(counterDiags.size() - 1);
    }

    return rets;
  }

  //first pass
  // public ArrayList<String[]> solveNQueens(int n) {
  // int[] col=new int[n];
  // int[][] diagonals=new int[2][2*n-1];
  // ArrayList<String[]> sols=new ArrayList<String[]>();
  // solveNQueens(n, sols, new ArrayList<Integer>(),  col, diagonals);
  // for(String[] sol: sols){
  // for(int i=0;i<sol.length;i++)
  // System.out.print(sol[i]);
  // System.out.println();
  // }
  // System.out.println(sols.size());
  // return sols;
  // }

  // public void solveNQueens(int n, ArrayList<String[]> sols, ArrayList<Integer> sol, int[] col, int[][] diagonals){
  // int num=sol.size();
  // if(num==n){
  // String[] board=new String[n];
  // for(int i=0;i<n;i++){
  // board[i]="";
  // for(int j=0;j<n;j++){
  // if(j==sol.get(i)) board[i]+="Q";
  // else board[i]+=".";
  // }
  // }
  // System.out.println();
  // sols.add(board);
  // return;
  // }
  // int diag[]=new int[2];
  // for(int i=0;i<n;i++){
  // diag[0]=i-num+n-1;
  // diag[1]=i+num;
  // System.out.println(i+" "+diag[0]+" "+diag[1]);
  // if(col[i]==0&&diagonals[0][diag[0]]==0&&diagonals[1][diag[1]]==0){
  // col[i]=diagonals[0][diag[0]]=diagonals[1][diag[1]]=1;
  // sol.add(i);
  // solveNQueens(n, sols, sol, col, diagonals);
  // col[i]=diagonals[0][diag[0]]=diagonals[1][diag[1]]=0;
  // sol.remove(sol.size()-1);
  // }
  // }
  // }

}
