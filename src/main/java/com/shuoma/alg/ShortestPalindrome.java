package com.shuoma.alg;

import static com.shuoma.annotation.Tag.DataStructure.Palindrome;
import static com.shuoma.annotation.Tag.DataStructure.StringT;
import static com.shuoma.annotation.Tag.Reference.LeetCode;

import com.shuoma.annotation.Tag;

@Tag(dss = {Palindrome, StringT}, references = LeetCode)
public class ShortestPalindrome {

  public String shortestPalindrome(String s) {
    int n = s.length();
    if(n <= 1) return s;
    int center = (n - 1) / 2;
    String res = "";
    for(int i = center; i >= 0; i--) {
      // center is two chars
      if(s.charAt(i) == s.charAt(i + 1)) {
        if((res = expandToBuildPalindrome(s, i, i + 1)) != null)
          return res;
      }
      // center is one char
      if((res = expandToBuildPalindrome(s, i, i)) != null) return res;
    }
    return res;
  }
  //aabaac
  private String expandToBuildPalindrome(String s, int l, int r) {
    int expansionDis = 1;
    for(; l - expansionDis >= 0; expansionDis++) {
      if(s.charAt(l - expansionDis) != s.charAt(r + expansionDis) ) break;
    }

    if(l - expansionDis >= 0) return null;
    StringBuilder sb = new StringBuilder(s.substring(r + expansionDis));
    sb.reverse();
    return sb + s;
  }
}
