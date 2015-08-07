package com.shuoma.alg;

import static com.shuoma.util.RandomUtil.genRandomBinaryByteArray;
import static com.shuoma.util.RandomUtil.r;

import org.junit.Test;

import java.util.Arrays;

public class OneByteOrTwoBytesCharacterTest {

  @Test public void testLastCharacterIsOneByte() throws Exception {
    OneByteOrTwoBytesCharacter ins = new OneByteOrTwoBytesCharacter();
    for (int times = 0; times < 10; times++) {
      int len = r.nextInt(10) + 3;
      byte[] ary = genRandomBinaryByteArray(len);
      if(dummyLastCharacterIsOneByte(ary) != ins.lastCharacterIsOneByte(ary)) {
        System.out.println(Arrays.toString(ary));
      }
    }
  }

  boolean dummyLastCharacterIsOneByte(byte[] bytes) {
    int n = bytes.length;
    for (int i = 0; i < n; ) {
      int firstBit = (bytes[i] >>> 7) & 1;
      if (i == n - 1 && firstBit == 0) return true;
      i += firstBit == 1 ? 2 : 1;
    }
    return false;
  }
}
