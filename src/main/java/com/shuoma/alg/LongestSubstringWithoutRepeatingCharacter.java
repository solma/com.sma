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

  // abba
  public int lengthOfLongestSubstring(String s) {
    int maxLen = 0, start = 0;
    int[] indexes = new int[256];
    Arrays.fill(indexes, -1);
    for(int i = 0; i < s.length(); i++){
      if(indexes[s.charAt(i)] == -1 || indexes[s.charAt(i)] < start){
        maxLen = Math.max(maxLen, i - start + 1);
      } else {//find a duplication
        start = indexes[s.charAt(i)] + 1;
      }
      indexes[s.charAt(i)] = i;
    }
    return maxLen;
  }

  public int lengthOfLongestSubstring1(String s) {
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
      maxLen = Math.max(maxLen, curLen);
      //System.out.println(letter+" "+curLen+" "+maxLen);
    }
    return maxLen;
  }
}
