package com.shuoma.alg;

import static com.shuoma.annotation.Tag.Algorithm.RandomT;
import static com.shuoma.annotation.Tag.Algorithm.RejectingMethod;
import static com.shuoma.util.RandomUtil.r;

import com.shuoma.annotation.Tag;

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
