package com.sma.alg;

import static com.sma.annotation.Tag.Algorithm.Arithmetic;
import static com.sma.annotation.Tag.DataStructure.Array;
import static com.sma.annotation.Tag.Reference.LeetCode;

import com.sma.annotation.Tag;

@Tag(algs = Arithmetic, dss = Array, references = LeetCode)
public class ZigZagConversion {

  //second pass
  public String convert(String s, int nRows) {
    if (s == null || s.length() == 0 || nRows == 1)
      return s;

    char[][] print = new char[nRows][];
    for (int i = 0; i < nRows; i++) {
      print[i] = new char[2 * (s.length() / (2 * nRows - 2) + 1)];
    }

    int row, col;
    for (int i = 0; i < s.length(); i++) {
      col = 2 * (i / (2 * nRows - 2)); //determine the block
      if (i % (2 * nRows - 2) < nRows) {//determine if the 1st column or 2nd column of the block
        row = i % (2 * nRows - 2);
      } else {
        col += 1;
        row = (nRows - 2) - (i % (2 * nRows - 2) - nRows);
      }
      //System.out.println(row+" "+col);
      print[row][col] = s.charAt(i);

    }

    String ret = "";
    for (int i = 0; i < print.length; i++) {
      for (int j = 0; j < print[i].length; j++) {
        if (print[i][j] > 0)
          ret = ret + print[i][j];
      }
    }
    return ret;
  }


  //first pass
  // public String convert(String s, int nRows) {
  // if (nRows == 1) return s;

  // StringBuffer buf = new StringBuffer();
  // int diff = nRows + nRows - 2;
  // for (int i = 0; i < nRows; i++) {
  // int index = i;
  // while (index < s.length()) {
  // buf.append(s.charAt(index));
  // index += diff;
  // if (i != 0 && i != nRows - 1 && index - i - i < s.length()) { //add zag column
  // buf.append(s.charAt(index - i - i));
  // }
  // }
  // }

  // return buf.toString();

  // }
}
