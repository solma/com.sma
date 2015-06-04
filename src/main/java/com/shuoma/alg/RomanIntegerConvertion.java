package com.shuoma.alg;

import static com.shuoma.annotation.Tag.DataStructure.String;
import static com.shuoma.annotation.Tag.Difficulty.D2;
import static com.shuoma.annotation.Tag.Reference.LeetCode;

import com.shuoma.annotation.Tag;

@Tag(dl = D2, dss = String, reference = LeetCode)
public class RomanIntegerConvertion {
  public static void main(String[] args) {
    new RomanIntegerConvertion().main();
  }

  public void main() {
    System.out.println(romanToInt("MCMLIV"));
    System.out.println(intToRoman(1954));
  }

  public int romanToInt(String s) {
    if (s == null)
      return 0;

    int sum = 0, i = 0;
    while (i < s.length()) {
      switch (s.charAt(i)) {
        case 'M':
          i += 1;
          sum += 1000;
          break;
        case 'D':
          i += 1;
          sum += 500;
          break;
        case 'L':
          i += 1;
          sum += 50;
          break;
        case 'V':
          i += 1;
          sum += 5;
          break;
        case 'C':
          if (i + 1 < s.length()) {
            switch (s.charAt(i + 1)) {
              case 'D':
                sum += 400;
                i += 2;
                break;
              case 'M':
                sum += 900;
                i += 2;
                break;
              default:
                sum += 100;
                i += 1;
                break;
            }
          } else {
            sum += 100;
            i += 1;
          }
          break;
        case 'X':
          if (i + 1 < s.length()) {
            switch (s.charAt(i + 1)) {
              case 'L':
                sum += 40;
                i += 2;
                break;
              case 'C':
                sum += 90;
                i += 2;
                break;
              default:
                sum += 10;
                i += 1;
                break;
            }
          } else {
            sum += 10;
            i += 1;
          }
          break;
        case 'I':
          if (i + 1 < s.length()) {
            switch (s.charAt(i + 1)) {
              case 'V':
                sum += 4;
                i += 2;
                break;
              case 'X':
                sum += 9;
                i += 2;
                break;
              default:
                sum += 1;
                i += 1;
                break;
            }
          } else {
            sum += 1;
            i += 1;
          }
          break;
        default:
          break;
      }

    }
    return sum;

  }


  public String intToRoman(int num) {
    int[] numerals = new int[] {1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1};

    StringBuilder sb = new StringBuilder();
    String roman = "";
    for (int i = 0; i < numerals.length; i++) {
      while (num >= numerals[i]) {
        num -= numerals[i];

        switch (numerals[i]) {
          case 1000:
            roman = "M";
            break;
          case 900:
            roman = "CM";
            break;
          case 500:
            roman = "D";
            break;
          case 400:
            roman = "CD";
            break;
          case 100:
            roman = "C";
            break;
          case 90:
            roman = "XC";
            break;
          case 50:
            roman = "L";
            break;
          case 40:
            roman = "XL";
            break;
          case 10:
            roman = "X";
            break;
          case 9:
            roman = "IX";
            break;
          case 5:
            roman = "V";
            break;
          case 4:
            roman = "IV";
            break;
          case 1:
            roman = "I";
            break;
        }
        sb.append(roman);

      }
    }


    return sb.toString();
  }
}
