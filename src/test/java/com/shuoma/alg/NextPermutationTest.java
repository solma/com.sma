package com.shuoma.alg;

import com.shuoma.alg.NextPermutation;
import junit.framework.TestCase;
import org.junit.Test;

import java.util.Arrays;

public class NextPermutationTest extends TestCase {

  @Test
  public void testNextPermutation() throws Exception {
    NextPermutation ins = new NextPermutation();
    int[] num = new int[] {3, 2, 1};
    ins.nextPermutation(num);
    assertTrue(Arrays.equals(new int[]{1, 2, 3}, num));
  }
}
