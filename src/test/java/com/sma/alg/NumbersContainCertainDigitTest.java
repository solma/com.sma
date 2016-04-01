package com.sma.alg;

import com.sma.util.RandomUtil;
import junit.framework.TestCase;
import org.junit.Test;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class NumbersContainCertainDigitTest extends TestCase {

  @Test
  public void testCount() throws Exception {
    NumbersContainCertainDigit ins = new NumbersContainCertainDigit();

    //System.out.println(ins.countNumbers(156, '3') + " " + countNumberDummy(156, '3').size());

    for (int i = 0; i < 100; i++) {
      int n = RandomUtil.r.nextInt(100000);
      char k = '3';
      int[] appearanceCnt = new int[2];
      appearanceCnt[0] = countAppearanceDummy(n, k);
      appearanceCnt[1] = ins.countAppearances(n, k);
      if (appearanceCnt[0] != appearanceCnt[1]) {
        System.out.println("appearance cnt: " + n + " " + k + " " + Arrays.toString(appearanceCnt));
      }

      int[] numberCnt = new int[2];
      numberCnt[0] = countNumberDummy(n, k).size();
      numberCnt[1] = ins.countNumbers(n, k);
      if (numberCnt[0] != numberCnt[1]) {
        System.out.println("number cnt: " + n + " " + k + " " + Arrays.toString(numberCnt));
      }
    }

  }

  int countAppearanceDummy(int n, char k) {
    int cnt = 0;
    for (int i = 1; i <= n; i++) {
      char[] nry = String.valueOf(i).toCharArray();
      for (char c : nry) {
        if (c == k) {
          cnt++;
        }
      }
    }
    return cnt;
  }

  List<Integer> countNumberDummy(int n, char k) {
    List<Integer> numbers = new LinkedList<>();
    for (int i = 1; i <= n; i++) {
      if (!String.valueOf(i).contains(String.valueOf(k))) {
        continue;
      }
      numbers.add(i);
    }
    return numbers;
  }
}
