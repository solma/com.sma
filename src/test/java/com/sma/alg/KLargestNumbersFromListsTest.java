package com.sma.alg;

import static com.sma.util.RandomUtil.r;

import com.sma.util.ArrayUtil;
import com.sma.util.RandomUtil;
import org.junit.Test;

import java.util.Arrays;

public class KLargestNumbersFromListsTest {

  @Test
  public void test() {
    KLargestNumbersFromLists ins = new KLargestNumbersFromLists();
    for (int i = 0; i < 10; i++) {
      int noOfLists = r.nextInt(6) + 2;
      int[][] lists = new int[noOfLists][];
      for (int j = 0; j < noOfLists; j++) {
        lists[j] = RandomUtil.genRandomArrayWithMinSize(3, 20);
        Arrays.sort(lists[j]);
      }
      Integer[][] res = new Integer[2][];
      int k = 5;
      res[0] = ins.kLargestNumbersFromLists(lists, k);
      res[1] = ins.kLargestNumbersFromLists1(lists, k);
      Arrays.sort(res[0]);
      Arrays.sort(res[1]);
      if(!Arrays.equals(res[0], res[1])) {
        ArrayUtil.print(lists);
        System.out.println(Arrays.toString(res[0]));
        System.out.println(Arrays.toString(res[1]));
        System.out.println();
      }
    }
  }
}
