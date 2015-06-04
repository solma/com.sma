package com.shuoma.alg;

import static com.shuoma.annotation.Tag.Algorithm.TopDown;
import static com.shuoma.annotation.Tag.DataStructure.String;
import static com.shuoma.annotation.Tag.Reference.LeetCode;

import com.shuoma.annotation.Tag;

import java.util.*;

@Tag(algs = TopDown, dss = String, reference = LeetCode)
public class PascalTriangleII {
  public static void main(String[] args) {
    new PascalTriangleII().getRow(30);
  }

  // public ArrayList<Integer> getRow(int rowIndex) {
  // ArrayList<Integer> res=new ArrayList<Integer>();
  // numRows+=1;
  // ArrayList<Integer> lastRow=new ArrayList<Integer>();
  // lastRow.add(1);
  // if(numRows==1) return lastRow;
  // ArrayList<Integer> curRow=null;
  // for(int row=1;row<numRows;row++){
  // curRow=new ArrayList<Integer>();
  // for(int i=0;i<lastRow.size();i++){
  // if(i==0) curRow.add(lastRow.get(i));
  // if(i==lastRow.size()-1) curRow.add(lastRow.get(i));
  // if(i<lastRow.size()-1) curRow.add(lastRow.get(i)+lastRow.get(i+1));
  // }
  // lastRow=curRow;
  // }
  // res=curRow;
  // return res;
  // }

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
