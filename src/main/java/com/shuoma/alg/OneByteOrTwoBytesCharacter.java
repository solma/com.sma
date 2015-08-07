package com.shuoma.alg;

import static com.shuoma.annotation.Tag.Algorithm.Backtracking;
import static com.shuoma.annotation.Tag.DataStructure.Array;
import static com.shuoma.annotation.Tag.Reference.Interview;

import com.shuoma.annotation.Tag;

/**
 * Given a byte array, which is an encoding of characters. Here is the rule:
 * a. If the first bit of a byte is 0, that byte stands for a one-byte character;
 * b. If the first bit of a byte is 1, that byte and its following byte together stand for a two-byte character;
 * Now implement a function to decide if the last character is a one-byte character or a two-byte character.
 * Constraint: You must scan the byte array from the end to the start. Otherwise it will be very trivial.
 */
@Tag(algs = Backtracking, dss = Array, references = Interview)
public class OneByteOrTwoBytesCharacter {

  // assume that bytes are encoded correctly
  boolean lastCharacterIsOneByte(byte[] bytes) {
    int n = bytes.length;
    if (((bytes[n - 1] >>> 7) & 1) > 0) return false; // if first bit of last byte is 1

    boolean[] isOneByte = new boolean[n];
    isOneByte[n - 1] = true;
    for (int i = n - 2; i >= 0; i--) {
      int firstBit = (bytes[i] >>> 7) & 1;
      if (firstBit == 0) {
        isOneByte[i] = isOneByte[i + 1];
      } else {
        if (i + 2 >= n) return false;
        isOneByte[i] = isOneByte[i + 2];
      }
    }
    return isOneByte[0];
  }
}
