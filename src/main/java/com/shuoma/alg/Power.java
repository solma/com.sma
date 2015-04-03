package com.shuoma.alg;

public class Power {
  public static void main(String[] args) {
    new Power().main();
  }

  public void main() {
    //System.out.println(Arrays.toString(toBinary(65535)));
    System.out.println(pow(1.039, 30));
  }

  public double powOn(double x, int n) {
    double ret = 1;
    int m = Math.abs(n);
    for (int i = 0; i < m; i++) {
      ret *= x;
    }
    if (n < 0) {
      ret = 1 / ret;
    }
    return ret;
  }

  //second pass
  public double pow(double x, int n) {
    double ret = 1.0;
    boolean nIsNeg = false;
    if (n < 0) {
      nIsNeg = true;
      n *= -1;
    }
    while (n > 0) {
      ret *= (n & 1) == 1 ? x : 1;
      x *= x;
      n >>= 1;
    }
    if (nIsNeg)
      return 1 / ret;
    else
      return ret;
  }

  public double powFirstPass(double x, int n) {
    // Start typing your Java solution below
    // DO NOT write main() function
    return powViaBinaryDecomposition(x, n);
  }

  //first pass
  public double powViaBinaryDecomposition(double x, int n) {
    //int[] bin=new int[32];
    int i;
    double ret = 1;
    int m = Math.abs(n);
    //i=bin.length-1;
    while (m > 0) {
      //bin[i]=n%2;
      if ((m & 1) == 1)
        ret *= x;
      m >>= 1;
      x *= x;
      //i--;
    }
    if (n < 0)
      ret = 1 / ret;
    return ret;
    //return Arrays.copyOfRange(bin, i+1, bin.length);
  }

}
