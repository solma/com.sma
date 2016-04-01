package com.sma.alg;

import static com.sma.alg.Searching.SearchingAlgorithm;
import static com.sma.alg.Searching.binarySearchFirstAppear;
import static com.sma.alg.Searching.binarySearchFirstAppear1;
import static com.sma.alg.Searching.binarySearchLastAppear;
import static com.sma.alg.Searching.findMinElementInRotatedArrayBinary;
import static com.sma.alg.Searching.findMinElementInRotatedArrayLinear;
import static com.sma.alg.Searching.linearSearchFirstAppear;
import static com.sma.alg.Searching.linearSearchLastAppear;
import static com.sma.alg.Searching.searchInARotatedArray;

import com.sma.util.RandomUtil;
import org.junit.Test;

import java.util.Arrays;


public class SearchingTest {

  @Test public void testSearchAlgorithms() throws Exception {
    SearchingAlgorithm[] algs = SearchingAlgorithm.values();
    for (SearchingAlgorithm sortAlgorithmChoice : algs) {
      for (int i = 0; i < 200; i++) {
        // generate array
        int[] a;
        switch (sortAlgorithmChoice) {
          case BIN_ROTATED:
            a = RandomUtil.generateRandomRotateArray();
            break;
          default:
            a = RandomUtil.genRandomArrayWithMinSize(10);
            Arrays.sort(a);
            break;
        }
        int[] cpy = Arrays.copyOf(a, a.length);

        // generate a search key
        int rand = RandomUtil.r.nextInt((int) (a.length * 1.2));
        int key;
        if (rand >= 0 && rand < cpy.length)
          key = cpy[rand];// select a key out of the array
        else
          key = rand; // some rand number

        // search for the key
        int idx = -1;
        switch (sortAlgorithmChoice) {
          case BIN_FIRST_SORTED:
            idx = binarySearchFirstAppear(cpy, key);
            if (idx != linearSearchFirstAppear(cpy, key))
              System.out.println(Arrays.toString(cpy) + " " + key + " " + idx);
            break;
          case BIN_FIRST_SORTED_1:
            idx = binarySearchFirstAppear1(cpy, key);
            if (idx != linearSearchFirstAppear(cpy, key))
              System.out.println(Arrays.toString(cpy) + " " + key + " " + idx);
            break;
          case BIN_LAST_SORTED:
            idx = binarySearchLastAppear(cpy, key);
            if (idx != linearSearchLastAppear(cpy, key))
              System.out.println(Arrays.toString(cpy) + " " + key + " " + idx);
            break;
          case BIN_ROTATED:
            idx = searchInARotatedArray(cpy, key);
            if (idx != linearSearchLastAppear(cpy, key) && idx != linearSearchFirstAppear(cpy, key))
              System.out.println(Arrays.toString(cpy) + " " + key + " " + idx);
            break;
          default:
            break;
        }
      }
    }
  }

  @Test
  public void testRotateArray() {
    int[] rotatedArray;
    for (int i = 0; i < 10000; i++) {
      rotatedArray = RandomUtil.generateRandomRotateArray();
      // rotatedArray=new int[]{951, 983, -669, -647, -517, -290, 99, 99, 122, 357, 731};
      int idx = findMinElementInRotatedArrayBinary(rotatedArray);
      int idxGT = findMinElementInRotatedArrayLinear(rotatedArray);
      if (idx != idxGT) {
        System.out.println(Arrays.toString(rotatedArray) + " idx=" + idx + "  idxGT=" + idxGT);
      }
    }
  }
}
