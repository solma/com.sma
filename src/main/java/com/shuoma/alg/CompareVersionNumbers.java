package com.shuoma.alg;

import static com.shuoma.annotation.Tag.DataStructure.StringT;
import static com.shuoma.annotation.Tag.Reference.LeetCode;

import com.shuoma.annotation.Tag;

@Tag(dss = StringT, references = LeetCode)
/**
 Compare two version numbers version1 and version2.
 If version1 > version2 return 1, if version1 < version2 return -1, otherwise return 0.

 You may assume that the version strings are non-empty and contain only digits and the . character.
 The . character does not represent a decimal point and is used to separate number sequences.
 For instance, 2.5 is not "two and a half" or "half way to version three", it is the fifth
 second-level revision of the second first-level revision.

 Here is an example of version numbers ordering:
 0.1 < 1.1 < 1.2 < 13.37
 */
public class CompareVersionNumbers {

  public int compareVersion(String version1, String version2) {
    String[] vLvl1 = version1.split("\\.");
    String[] vLvl2 = version2.split("\\.");

    for (int i = 0; i < Math.max(vLvl1.length, vLvl2.length); i++) {
      if (i >= vLvl1.length || i >= vLvl2.length) {
        //special case such as 1.0.0 vs 1
        if ((i >= vLvl1.length && Integer.parseInt(vLvl2[i]) == 0)
            || (i >= vLvl2.length && Integer.parseInt(vLvl1[i]) == 0))
          continue;
        return i >= vLvl1.length ? -1 : 1;
      }
      int diff = Integer.parseInt(vLvl1[i]) - Integer.parseInt(vLvl2[i]);
      if (diff != 0)
        return diff > 0 ? 1 : -1;
    }
    return 0;
  }
}
