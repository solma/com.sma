package com.shuoma.lang.primitive;

import static com.shuoma.annotation.Tag.Reference.StackOverview;

import com.shuoma.annotation.Tag;

@Tag(references = StackOverview)
//http://stackoverflow.com/questions/4266756/can-we-make-unsigned-byte-in-java
// primitives are signed in Java
public class Byte {
  public static void main(String[] args) {
    // not compilable since hex or binary literals are int by default
    // byte b = 0xA1;
    // byte b = 0b10000001;

    System.out.println("-1 is " + (byte)-1 + " in byte");
    System.out.println("255 is " + (byte)255 + " in byte");

    // unsign a byte
    int ub = ((byte) -1 & 0xFF);
    System.out.println(ub);
  }
}
