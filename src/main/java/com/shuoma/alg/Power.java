package com.shuoma.alg;

public class Power {
  public static void main(String[] args) {
    new Power().main();
  }

  public void main() {
    //System.out.println(Arrays.toString(toBinary(65535)));
    System.out.println(pow(1.039, 30));
  }

  public double pow(double x, int n) {
    double ret = 1.0;
    boolean nIsNeg = false;
    if (n < 0) {
      nIsNeg = true;
      n = 0 - n;
    }

    while (n > 0) {
      ret *= (n & 1) == 1 ? x : 1;
      x *= x;
      n >>= 1;
    }

    return nIsNeg ? 1 / ret : ret;
  }
}
