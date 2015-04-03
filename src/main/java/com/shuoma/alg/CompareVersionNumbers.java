package com.shuoma.alg;

import static com.shuoma.annotation.Tag.DataStructure.String;
import static com.shuoma.annotation.Tag.Source.LeetCode;

import com.shuoma.annotation.Tag;

@Tag(dss = String, source = LeetCode)
public class CompareVersionNumbers {

  public int compareVersion(String version1, String version2) {
    String[] vLvl1 = version1.split("\\.");
    String[] vLvl2 = version2.split("\\.");

    for (int i = 0; i < Math.max(vLvl1.length, vLvl2.length); i++) {
      if (i >= vLvl1.length || i >= vLvl2.length) {
        if ((i >= vLvl1.length && Integer.parseInt(vLvl2[i]) == 0) || (i >= vLvl2.length
            && Integer.parseInt(vLvl1[i]) == 0))  //special case such as 1.0.0 vs 1
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
