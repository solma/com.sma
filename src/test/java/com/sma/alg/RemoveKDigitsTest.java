package com.sma.alg;

import com.sma.util.RandomUtil;
import junit.framework.TestCase;
import org.junit.Test;

import static com.sma.util.StringUtil.removePaddingCharacters;

public class RemoveKDigitsTest extends TestCase {

  @Test
  public void testRemove() throws Exception {
    String[] res = new String[2];
    RemoveKDigits ins = new RemoveKDigits();
    for (int i = 0; i < 10000; i++) {
      int n = 123 + RandomUtil.r.nextInt(100000), k = 1 + RandomUtil.r.nextInt(3);
      String s = n + "";
      res[0] = removeDummy(s, k);
      res[1] = ins.remove(s, k);
      assertEquals(res[0], res[1]);
    }
  }

  String removeDummy(String s, int k) {
    if (k >= s.length()) return "0";
    String min = s;
    for (String com : Combination.combinationsOfSizeNRecursionNOrderTopDown(s, s.length() - k)) {
      min = com.compareTo(min) < 0 ? com : min;
    }

    if (min.length() == 0 || min.equals("0")) { return "0"; }
    else { return removePaddingCharacters(min, '0', true); }
  }
}
