package com.shuoma.alg;

import static com.shuoma.annotation.Tag.Algorithm.Geometry;
import static com.shuoma.annotation.Tag.Reference.LeetCode;

import com.shuoma.annotation.Tag;

@Tag(algs = Geometry, reference = LeetCode)
public class RectangleArea {

  public static void main(String[] args) {
    RectangleArea ins = new RectangleArea();
    System.out.println(ins.computeArea(-2, -2, 2, 2, -3, -3, 3, -1));
  }

  // C>A, D>B, G>E, H>F
  public int computeArea(int A, int B, int C, int D, int E, int F, int G, int H) {
    System.out.println(overlapArea(A, B, C, D, E, F, G, H));
    return (C - A) * (D - B) + (G - E) * (H - F) - overlapArea(A, B, C, D, E, F, G, H);
  }

  int overlapArea(int A, int B, int C, int D, int E, int F, int G, int H) {
    // contain case
    if (A >= E && B >= F && C <= G && D <= H) {
      return (C - A) * (D - B);
    }
    if (E >= A && F >= B && G <= C && H <= D) {
      return (G - E) * (H - F);
    }

    // overlap case
    if (A <= G && B <= H && C >= E && D >= F) {
      return (Math.min(C, G) - Math.max(A, E)) * (Math.min(D, H) - Math.max(B, F));
    }
    return 0;
  }
}
