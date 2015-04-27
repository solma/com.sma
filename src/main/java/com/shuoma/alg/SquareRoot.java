package com.shuoma.alg;

import static com.shuoma.annotation.Tag.Algorithm.Geometry;
import static com.shuoma.annotation.Tag.Source.LeetCode;
import static com.shuoma.annotation.Tag.Algorithm.Arithmetic;

import com.shuoma.annotation.Tag;

@Tag(algs = {Arithmetic, Geometry}, source = LeetCode)
public class SquareRoot {
  public static void main(String[] args) {
    SquareRoot ins = new SquareRoot();
    int c = 150;
    //System.out.println(ins.newtonMethod(Integer.parseInt(args[0])));
    System.out.println(ins.quakeIIISolution(c));
    System.out.println(ins.newtonMethod(c));
  }

  // not work yet
  // https://en.wikipedia.org/wiki/Fast_inverse_square_root
  public double quakeIIISolution(long c) {
    long i;
    float x2, y;
    final float threehalfs = 1.5F;

    x2 = c * 0.5F;
    y = c;
    i = (long) y;                       // evil floating point bit level hacking
    i = 0x5f3759df - (i >> 1);               // what the fuck?
    y = i;
    y = y * (threehalfs - (x2 * y * y));   // 1st iteration
    //      y  = y * ( threehalfs - ( x2 * y * y ) );   // 2nd iteration, this can be removed

    return y;
  }

  //second pass
  public double newtonMethod(int c) {
    if (c < 0)
      return -1;
    if (c == 0)
      return 0;
    double x = c / 2.0;
    while (Math.abs(x * x - c) > 0.0000000001) {
      x = (x * x + c) / (x * 2);
    }
    return x;
  }

  //first pass
  public int sqrt(int c) {
    double seed = c, delta = 0.00001, fx = seed * seed - c;
    //Newton's Method
    while (fx > delta) {
      seed = seed - fx / 2 / seed;
      fx = seed * seed - c;
    }
    return (int) seed;
  }
}
