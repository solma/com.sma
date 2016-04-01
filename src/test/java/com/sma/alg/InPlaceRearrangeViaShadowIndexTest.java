package com.sma.alg;

import com.sma.util.RandomUtil;
import org.junit.Test;

import java.util.Arrays;

public class InPlaceRearrangeViaShadowIndexTest {

  @Test public void testInplaceSwap() throws Exception {
    InPlaceRearrangeViaShadowIndex ins = new InPlaceRearrangeViaShadowIndex();
    for(int times = 0; times < 1; times++) {
      int size = RandomUtil.r.nextInt(5) + 5;
      //int[] arr = RandomUtil.genRandomArray(size, 10, false, false);
      //int[] index = ArrayUtil.getNaturalArray(size);
      //RandomUtil.shuffle(index);
      int[] arr = {0, 1, 2, 3, 4, 5, 6, 7};

      //{0, 2, 4, 6, 1, 3, 5, 7}
      int[] index = {1, 3, 5, 4, 6, 0, 7, 2};

      int[][] cpy = new int[3][];
      for(int i = 0; i < cpy.length; i++)
        cpy[i] = Arrays.copyOf(arr, arr.length);

      ins.rearrange1(Arrays.copyOf(index, index.length), cpy[0]);
      ins.rearrange2(Arrays.copyOf(index, index.length), cpy[1]);
      ins.rearrange(Arrays.copyOf(index, index.length), cpy[2]);
      if (! (Arrays.equals(cpy[0], cpy[1]) && Arrays.equals(cpy[0], cpy[2]))) {
        System.out.println(" array: " + Arrays.toString(arr));
        System.out.println(" index: " + Arrays.toString(index));
        System.out.println(" cpy[0]: " + Arrays.toString(cpy[0]));
        System.out.println(" cpy[1]: " + Arrays.toString(cpy[1]));
        System.out.println(" cpy[2]: " + Arrays.toString(cpy[2]));
      }
    }
  }
}
