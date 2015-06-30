package com.shuoma.lang.primitive;

public class MyString {
  public static void main(String[] args) {
    new MyString().main();
  }

  public void main() {
    containNullOrEmpty();
  }

  void containNullOrEmpty() {
    String s = "s";
    System.out.println(s.contains("") + " " + s.contains(null));
  }
}
