package com.sma.alg;

import static com.sma.annotation.Tag.Algorithm.RandomT;
import static com.sma.annotation.Tag.Algorithm.RejectingMethod;
import static com.sma.annotation.Tag.Reference.Interview;

import com.sma.annotation.Tag;

import java.util.Random;

/**
 * Given random() that can return 0 or 1 uniformly, implement random_new()
 * that can return 0 with 90% and 1 with 10%.
 */
@Tag(algs = {RandomT, RejectingMethod}, references = Interview)
public class GenerateProbabilityUsingBooleanRandom {

  int Prob91() {
    Random r = new Random();
    StringBuilder sb;
    int gen;
    do {
      sb = new StringBuilder();
      for (int i = 0; i < 4; i++) { sb.append(r.nextInt(2)); }
      gen = Integer.parseInt(sb.toString(), 2);
    } while (gen >= 10);
    //System.out.println(sb.toString());
    return gen == 9 ? 1 : 0;
  }
}
