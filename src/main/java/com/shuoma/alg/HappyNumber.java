package com.shuoma.alg;

import static com.shuoma.annotation.Tag.Algorithm.ArithmeticSum;
import static com.shuoma.annotation.Tag.DataStructure.HashTable;
import static com.shuoma.annotation.Tag.Reference.LeetCode;

import com.shuoma.annotation.Tag;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

@Tag(algs = ArithmeticSum, dss = HashTable, references = LeetCode)
public class HappyNumber {

  public static void main(String[] args) {
    HappyNumber ins = new HappyNumber();
    //System.out.println(ins.allHappyNumbersSmallerThan(100));
    System.out.println(ins.isHappy(49));
  }

  Map<Integer, Boolean> isHappy = new HashMap<>();
  Map<Integer, Integer> cache = new HashMap<>();

  List<Integer> allHappyNumbersSmallerThan(int n) {
    List<Integer> allHappyNumbers = new LinkedList<>();
    for (int i = 1; i < n; i++) {
      if (isHappy(i)) {
        allHappyNumbers.add(i);
      }
    }
    return allHappyNumbers;
  }

  boolean isHappy(int n) {
    if (n <= 0) return false;
    int cur = n;
    List<Integer> prevs = new LinkedList<>();

    while (cur != 1) {
      if (isHappy.containsKey(cur)) {
        isHappy.put(n, isHappy.get(cur));
        return isHappy.get(n);
      }

      if (prevs.contains(cur)) {
        return false;
      }
      prevs.add(cur);
      if (cache.containsKey(cur)) {
        cur = cache.get(cur);
      } else {
        int key = cur;
        cur = next(cur);
        cache.put(key, cur);
      }
    }

    System.out.println(prevs);
    return true;
  }

  int next(int n) {
    int sum = 0;
    while (n > 0) {
      sum += Math.pow(n % 10, 2);
      n /= 10;
    }
    return sum;
  }
}
