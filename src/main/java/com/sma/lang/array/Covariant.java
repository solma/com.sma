package com.sma.lang.array;

/**
 * Show covariance of Java arrays.
 * See Sec. 19.3 <<Programming in Scala>>
 */
public class Covariant {

  public static void main(String[] args) {
    try {
      String[] sArr = {"abc"};
      Object[] oArr = sArr; // covariance allow you to assign String[] to Object[]
      oArr[0] = new Integer(7);
      String s = sArr[0];
    } catch (ArrayStoreException e) {
      System.out.println("Java arrays are covariant.");
      System.out.println("The above block throws an ArrayStoreException");
      e.printStackTrace();
    }
  }
}
