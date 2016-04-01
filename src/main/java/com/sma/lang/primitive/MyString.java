package com.sma.lang.primitive;

public class MyString {
  public static void main(String[] args) {
    new MyString().main();
  }

  public void main() {
    containNullOrEmpty();
  }

  void containNullOrEmpty() {
    String s = "s";
    //System.out.println(s.contains("") + " " + s.contains(null));
    System.out.println("0+0-1".replaceAll("[+-0]",""));
  }
}
