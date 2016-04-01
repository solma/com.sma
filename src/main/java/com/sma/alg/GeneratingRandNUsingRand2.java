package com.sma.alg;

import static com.sma.annotation.Tag.Algorithm.RandomT;
import static com.sma.annotation.Tag.Algorithm.RejectingMethod;
import static com.sma.util.RandomUtil.r;

import com.sma.annotation.Tag;

@Tag(algs = {RandomT, RejectingMethod})
public class GeneratingRandNUsingRand2 {

  int rand2() {
    return r.nextInt(2);
  }

  int rand3() {
    int ret;
    do {
      ret = (rand2() << 1) + rand2();
    } while (ret == 3);
    return ret;
  }

  int rand4() {
    return rand2() + (rand2() == 0 ? 0 : 2);
  }

  int randN(int N) {
    int ret = 0;
    for (int i = 0; i < 31; i++) {
      ret = (ret << 1) + rand2();
    }
    return (int) ((ret + .0) / Integer.MAX_VALUE * N);
  }
}
