package com.sma.alg;

import com.sma.util.ArrayUtil;
import com.sma.util.RandomUtil;
import junit.framework.TestCase;
import org.junit.Test;

import java.util.Arrays;

public class SingularElementInArrayTest extends TestCase {

  @Test
  public void testSingularElement() throws Exception {
    SingularElementInArray ins = new SingularElementInArray();
    for (int i = 0; i < 100; i++) {
      int singular = RandomUtil.r.nextInt(10);
      int repeatedTimes = 3;
      int[] singularArray = generateArrayWithSingularElement(repeatedTimes, singular);
      int outputSingular = ins.singularElement(singularArray, repeatedTimes);
      if (outputSingular != singular) {
        System.out.println("true singular: " + singular);
        System.out.println("detected element: " + outputSingular);
        System.out.println(Arrays.toString(singularArray));
      }
    }
  }

  int[] generateArrayWithSingularElement(int repeatedTimes, int singular) {
    int n = 10;
    int[] naturalArray = ArrayUtil.getNaturalArray(n);
    ArrayUtil.swap(naturalArray, singular, 0);
    naturalArray = RandomUtil.shuffle(naturalArray, 1, n - 1);

    int[] result = new int[(n - 1) * repeatedTimes + 1];
    for (int i = 0; i < repeatedTimes; i++) {
      System.arraycopy(naturalArray, 1, result, (n - 1) * i, n - 1);
    }
    result[result.length - 1] = naturalArray[0];
    return RandomUtil.shuffle(result);
  }
}
