package com.shuoma.thirdpartylibrary.appengine.mapreduce;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;

public class ObjectToByteBuffer {

  private static final String NEWLINE_CHARACTER = "\n";

  String f1;
  String f2;

  public ByteBuffer toBytes() {
    ByteArrayOutputStream out = new ByteArrayOutputStream();
    try {
      out.write(NEWLINE_CHARACTER.getBytes());
    } catch (IOException e) {
      //throw new RuntimeException("Error in serializing to bigquery json " + jsonObject, e);
    }
    return ByteBuffer.wrap(out.toByteArray());
  }
}
