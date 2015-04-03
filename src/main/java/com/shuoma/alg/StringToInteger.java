package com.shuoma.alg;

import static com.shuoma.annotation.Tag.DataStructure.String;
import static com.shuoma.annotation.Tag.Source.LeetCode;

import com.shuoma.annotation.Tag;

@Tag(dss = String, source = LeetCode)
public class StringToInteger {
  public static void main(String[] args) {
    new StringToInteger().main();
  }

  public void main() {
    String s = " +21a";
    System.out.println(atoi(s));
    System.out.println(s.replaceAll("\\s+", ""));
    //System.out.println(s.lastIndexOf("\\d"));
  }

  public int atoi(String str) {
    if (str == null || str.length() == 0)
      return 0;
    //str=str.replaceAll("\\s+", "");
    str = str.trim();                 //catch, strip leading and trailing spaces
    int sign = 1;
    if (str.charAt(0) == '-' || str.charAt(0) == '+') {  //catch
      if (str.charAt(0) == '-')
        sign = -1;
      str = str.substring(1);
    }

    int i = 0;
    int sLen = str.length();
    while (i < sLen && str.charAt(i) >= '0' && str.charAt(i) <= '9') { i++; }
    str = str.substring(0, i);

    i = 0;
    int c;
    double ret = 0;
    sLen = str.length();
    while (i < sLen) {
      c = str.charAt(i) - '0';
      if (c < 0 || c > 9)
        return 0;
      ret += c * Math.pow(10, sLen - i - 1);
      i++;
    }
    ret *= sign;
    if (ret > Integer.MAX_VALUE)
      ret = Integer.MAX_VALUE; //catch
    if (ret < Integer.MIN_VALUE)
      ret = Integer.MIN_VALUE;
    return (int) ret;
  }
}

