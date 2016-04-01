package com.sma.alg;

import static com.google.common.base.Strings.isNullOrEmpty;
import static com.sma.annotation.Tag.DataStructure.StringT;
import static com.sma.annotation.Tag.Reference.Interview;

import com.sma.annotation.Tag;

/**
 Implement a encoding system as following: Abckkkkkkkkkkk55p => Abc11xk55p.
 Encoding rules: encoded them as: [n]x[c], where n>=3 is the repetition count and c is the actual character,
 X is the special character.
 Decoder side: Any time above pattern is detected, it will output n number of c. If x is the last character,
 output x.
 */
@Tag(dss = StringT, references = Interview)
public class StringAbbreviation {

  public static void main(String[] args) {
    StringAbbreviation ins = new StringAbbreviation();
    System.out.println(ins.decode(ins.encode("Abckkkkkkk55pp")));
  }

  static String REP = "x";

  String encode(String s) {
    if (isNullOrEmpty(s)) return "";
    char[] ary = s.toCharArray();

    StringBuilder abbr = new StringBuilder();
    int cnt = 0;
    int i;
    for (i = 0; i < ary.length; i++) {
      if (i == 0 || ary[i] != ary[i - 1]) {
        if (cnt > 0) {
          if (cnt >= 3) abbr.append(cnt + REP + ary[i - 1]);
          else {
            abbr.append(cnt == 1 ? ary[i - 1] : (ary[i - 1] + "" + ary[i - 1]));
          }
        }
        cnt = 1;
        continue;
      }
      if (i > 0 && ary[i] == ary[i - 1]) {
        cnt++;
        continue;
      }
    }
    if (cnt >= 3) abbr.append(cnt + REP + ary[i - 1]);
    else {
      abbr.append(cnt == 1 ? ary[i - 1] : (ary[i - 1] + "" + ary[i - 1]));
    }
    return abbr.toString();
  }

  String decode(String s) {
    if (isNullOrEmpty(s)) return "";
    char[] ary = s.toCharArray();

    StringBuilder ret = new StringBuilder();
    int digitStart = -1;
    for (int i = 0; i < ary.length; ) {
      if (Character.isDigit(ary[i])) {
        if (digitStart == -1) digitStart = i;
        i++;
        continue;
      }

      if (ary[i] == REP.charAt(0) && i < ary.length && digitStart != -1) {
        int cnt = Integer.parseInt(s.substring(digitStart, i));
        for (int j = 0; j < cnt; j++) ret.append(ary[i + 1]);
        digitStart = -1;
        i += 2;
      } else {
        if (digitStart != -1) {
          ret.append(s.substring(digitStart, i + 1));
          digitStart = -1;
        } else {
          ret.append(ary[i]);
        }
        i++;
      }
    }
    return ret.toString();
  }
}
