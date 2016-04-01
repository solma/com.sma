package com.sma.alg;

import junit.framework.TestCase;
import org.junit.Test;


public class ZigZagConversionTest extends TestCase {

  @Test
  public void testConvert() throws Exception {
    ZigZagConversion ins = new ZigZagConversion();
    assertEquals("AEBDFCG", ins.convert("ABCDEFG", 3));
  }
}
