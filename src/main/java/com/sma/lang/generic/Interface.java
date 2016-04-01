package com.sma.lang.generic;

public class Interface {
  public static void main(String[] args) {
    InstantiatedMapper im = new InstantiatedMapper();
    System.out.println(im.map("test"));

    GenericMapper<Integer, Long> gm = new GenericMapper<>();
    System.out.println(gm.map(10));

    GenericMapper gm1 = new GenericMapper<>();
    System.out.println(gm1.map("t"));
  }
}


interface Mapper<T, R> {
  public R map(T input);
}

class GenericMapper<T, R> implements Mapper<T, R> {
  public R map(T input) {
    return (R) input;
  }
}

class InstantiatedMapper implements Mapper<String, Integer> {
  public Integer map(String input) {
    if (input == null) return 0;
    return input.length();
  }
}


