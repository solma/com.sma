package com.sma.lang.generic;

// http://docs.oracle.com/javase/tutorial/java/generics/bridgeMethods.html
public class MethodOverride {
  public static void main(String[] args) {
    Y y = new Y(10);
    X yAsX = y;
    yAsX.setData("s");
    Integer i = y.data;
  }
}

class X<T> {

  T data;

  X(T data) {
    this.data = data;
  }

  void setData(T t) {
    data = t;
    System.out.println("X: " + data);
  }
}

class Y extends X<Integer> {

  Y(Integer i) {
    super(i);
  }

  void setData(Integer i) {
    super.setData(i);
    System.out.println("Y: " + data);
  }
}
