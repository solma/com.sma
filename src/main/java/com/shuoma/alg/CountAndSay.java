package com.shuoma.alg;

import static com.shuoma.annotation.Tag.Source.LeetCode;

import com.shuoma.annotation.Tag;

@Tag(dss = Tag.DataStructure.String, source = LeetCode)
public class CountAndSay {
  public static void main(String[] args) {
    new CountAndSay().main();
  }

  public void main() {
    System.out.println(countAndSay(6));
  }


  public String countAndSay(int n) {
    String ret = "1", next;
    for (int i = 1; i < n; i++) {
      char cur = ret.charAt(0);
      int cnt = 1;
      next = "";
      for (int j = 1; j < ret.length(); j++) {
        if (cur == ret.charAt(j)) {
          cnt++;
        } else {
          next += String.valueOf(cnt) + String.valueOf(cur);
          cur = ret.charAt(j);
          cnt = 1;
        }
      }
      next += String.valueOf(cnt) + String.valueOf(cur);
      ret = next;
    }
    return ret;
  }
}
