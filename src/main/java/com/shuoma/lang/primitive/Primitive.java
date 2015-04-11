package com.shuoma.lang.primitive;

public class Primitive {
  public static void main(String[] args) {
    new Primitive().main();
  }

  void main() {
    booleanXor();
  }

  void booleanXor() {
    boolean isOme = true;
    System.out.println(isOme ^ true);

    boolean[] all = { false, true };
    for (boolean a : all) {
      for (boolean b: all) {
        boolean c = a ^ b;
        System.out.println(a + " ^ " + b + " = " + c);
      }
    }
  }
}
