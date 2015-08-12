package com.shuoma.alg;

import static com.shuoma.annotation.Tag.Algorithm.TopDown;
import static com.shuoma.annotation.Tag.DataStructure.StringT;
import static com.shuoma.annotation.Tag.Reference.LeetCode;

import com.shuoma.annotation.Tag;

import java.util.ArrayList;
import java.util.List;

@Tag(algs = TopDown, dss = StringT, references = LeetCode)
public class PascalTriangle {
  //second pass
  public List<List<Integer>> generate(int numRows) {

    List<List<Integer>> ret = new ArrayList<>();
    if (numRows <= 0) {
      return ret;
    }
    List<Integer> prev = new ArrayList<>();
    prev.add(1);
    ret.add(prev);

    for (int i = 1; i < numRows; i++) {
      List<Integer> newLayer = new ArrayList<>();
      newLayer.add(1);
      for (int j = 0; j < prev.size() - 1; j++) {
        newLayer.add(prev.get(j) + prev.get(j + 1));
      }
      newLayer.add(1);
      ret.add(newLayer);
      prev = newLayer;
    }
    return ret;

  }

  //first pass
  public ArrayList<ArrayList<Integer>> generateFirstPass(int numRows) {
    ArrayList<ArrayList<Integer>> res = new ArrayList<ArrayList<Integer>>();
    if (numRows < 1)
      return res;
    ArrayList<Integer> lastRow = new ArrayList<Integer>();
    lastRow.add(1);
    res.add(lastRow);
    for (int row = 1; row < numRows; row++) {
      ArrayList<Integer> curRow = new ArrayList<Integer>();
      for (int i = 0; i < lastRow.size(); i++) {
        if (i == 0)
          curRow.add(lastRow.get(i));
        if (i == lastRow.size() - 1)
          curRow.add(lastRow.get(i));
        if (i < lastRow.size() - 1)
          curRow.add(lastRow.get(i) + lastRow.get(i + 1));
      }
      res.add(new ArrayList<Integer>(curRow));
      lastRow = curRow;
    }
    return res;
  }
}
