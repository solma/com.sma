package com.shuoma.alg;

import static com.shuoma.alg.RangeMinimumQuery.Solution;
import com.shuoma.ds.misc.Interval;
import com.shuoma.util.RandomUtil;
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

      System.out.println("Arrays=" + Arrays.toString(rdmArray));
      System.out.println("Ranges=" + Arrays.toString(queries));
      for (Solution alg : Solution.values()) {
        System.out.println(alg + " : " + Arrays.toString(rmq.getMinimum(queries, rdmArray, alg)));
      }
    }
  }
}
