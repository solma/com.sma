package com.sma.alg;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import com.sma.util.ArrayUtil;
import com.sma.util.RandomUtil;
import org.junit.Test;

public class ValidStackPushAndPopSequenceTest {

  @Test
  public void testIsPossible() throws Exception {
    ValidStackPushAndPopSequence ins = new ValidStackPushAndPopSequence();
    int[] pushes = {2, 9, 3, 7, 8};
    assertTrue(ins.isPossible(pushes, new int[] {3, 9, 7, 8, 2}));
    assertFalse(ins.isPossible(pushes, new int[] {7, 2, 9, 3, 8}));

    int[] arr = ArrayUtil.getNaturalArray(20);
    for (int i = 0; i < 1000; i++) {
      int[] shuffle = RandomUtil.shuffle(arr);
      boolean[] isPossible = {ins.isPossible(arr, shuffle), ins.isPossibleWithoutStack(arr, shuffle)};
      assertEquals(isPossible[0], isPossible[1]);
//      if(isPossible[0] ^ isPossible[1]) {
//        System.out.println(Arrays.toString(arr));
//        System.out.println(Arrays.toString(shuffle));
//        break;
//      }
    }
  }
}
