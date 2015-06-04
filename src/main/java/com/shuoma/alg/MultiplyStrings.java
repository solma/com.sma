package com.shuoma.alg;

import static com.shuoma.annotation.Tag.DataStructure.Array;
import static com.shuoma.annotation.Tag.DataStructure.String;
import static com.shuoma.annotation.Tag.Difficulty.D2;
import static com.shuoma.annotation.Tag.Reference.LeetCode;

import com.shuoma.annotation.Tag;

@Tag(dl = D2, dss = {Array, String}, reference = LeetCode)
public class MultiplyStrings {
  public static void main(String[] args) {
    new MultiplyStrings().main();
  }

  public void main() {
    System.out.println(multiply("0", "0"));
    System.out.println(multiply1("23891201012", "1238123178"));
  }

  //second pass
  public String multiply(String num1, String num2) {
    int n1 = num1.length();
    int n2 = num2.length();
    int i, ii, jj, carry;
    int[] product = new int[n1 + n2];
    for (i = 0; i < n1; i++) {
      carry = 0;
      ii = n1 - 1 - i;
      int j;
      for (j = 0; j < n2; j++) {
        jj = n2 - 1 - j;
        product[i + j] += (num1.charAt(ii) - '0') * (num2.charAt(jj) - '0') + carry;
        carry = product[i + j] / 10;
        product[i + j] %= 10;
      }
      product[i + j] += carry;
    }
    //System.out.println(Arrays.toString(product));
    StringBuilder ret = new StringBuilder();
    for (i = product.length - 1; i > 0 && product[i] == 0; i--)
      ; //keep the last 0
    for (; i >= 0; i--)
      ret.append(product[i]);
    return ret.toString();
  }

  //first pass
  public String multiply1(String num1, String num2) {
    int n = num1.length(), m = num2.length();
    int[] num = new int[n + m];
    for (int i = 0; i < n; i++) {
      int carry = 0;
      int a = num1.charAt(n - 1 - i) - '0';
      for (int j = 0; j < m; j++) {
        int b = num2.charAt(m - 1 - j) - '0';
        num[i + j] += carry + a * b;
        carry = num[i + j] / 10;
        num[i + j] %= 10;
      }
      num[i + m] += carry;
    }

    int i = num.length - 1;
    while (i > 0 && num[i] == 0)
      i--; //tricks: remove leading 0's
    StringBuilder sb = new StringBuilder();
    while (i >= 0) {
      sb.append((char) ('0' + num[i--]));
    }
    return sb.toString();

  }
}
