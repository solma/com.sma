package com.sma.lang.primitive;

public class Boolean {
  public static void main(String[] args) {
    Boolean ins = new Boolean();
    ins.booleanXor();
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
