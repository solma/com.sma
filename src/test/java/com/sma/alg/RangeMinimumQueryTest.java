package com.sma.alg;

import static com.sma.alg.RangeMinimumQuery.Algorithm;

import com.sma.ds.misc.Interval;
import com.sma.util.RandomUtil;
import org.junit.Test;

import java.util.Arrays;
import java.util.Random;

public class RangeMinimumQueryTest {

  @Test
  public void testGetMinimum() throws Exception {
    RangeMinimumQuery rmq = new RangeMinimumQuery();

    for (int time = 0; time < 10; time++) {
      int[] rdmArray = RandomUtil.genRandomArrayWithMinSize(10);
      Random rand = new Random();
      int noOfQueries = rand.nextInt(10) + 10;
      Interval[] queries = new Interval[noOfQueries];


      for (int i = 0; i < noOfQueries; i++) {
        int start = rand.nextInt(rdmArray.length);
        queries[i] = new Interval(start, start + rand.nextInt(rdmArray.length - start));
      }

      int[][] res = new int[3][];
      Algorithm[] algs = Algorithm.values();
      for (int i = 0; i < algs.length; i++) {
        res[i] = rmq.getMinimum(queries, rdmArray, algs[i]);
      }

      if (!Arrays.equals(res[0], res[1]) || !Arrays.equals(res[0], res[2])) {
        System.out.println("Arrays=" + Arrays.toString(rdmArray));
        System.out.println("Ranges=" + Arrays.toString(queries));
        for (int i = 0; i < algs.length; i++) {
          System.out.println(algs[i] + " : " + Arrays.toString(res[i]));
        }
      }
    }
  }
}
