package com.shuoma.alg;

import static com.shuoma.annotation.Tag.DataStructure.Hash;
import static com.shuoma.annotation.Tag.DataStructure.Substring;
import static com.shuoma.annotation.Tag.Difficulty.D2;
import static com.shuoma.annotation.Tag.Reference.LeetCode;
import static com.shuoma.annotation.Tag.Trick.CheckAtEveryIndex;

import com.shuoma.annotation.Tag;

import java.util.Arrays;

@Tag(dl = D2, dss = {Hash, Substring}, references = LeetCode, tricks = CheckAtEveryIndex)
public class LongestSubstringWithoutRepeatingCharacter {

  public int lengthOfLongestSubstring(String s) {
    int n = s.length();
    int[] lastPos = new int[256];
    Arrays.fill(lastPos, -1);

    int maxLen = 0, curLen = 0;
    for (int i = 0; i < n; i++) {
      char letter = s.charAt(i);
      if (lastPos[letter] != -1 && i - lastPos[letter] <= curLen) {
        curLen = i - lastPos[letter];
      } else {
        curLen += 1;
      }
      lastPos[letter] = i;
      maxLen = maxLen >= curLen ? maxLen : curLen;
      //System.out.println(letter+" "+curLen+" "+maxLen);
    }
    return maxLen;
  }
}
