package com.sma.alg;

import junit.framework.TestCase;
import org.junit.Test;

public class RomanIntegerConversionTest extends TestCase {

  RomanIntegerConversion ins = new RomanIntegerConversion();

  @Test
  public void testRomanToInt() throws Exception {
    assertEquals(1954, ins.romanToInt("MCMLIV"));
  }

  @Test
  public void testIntToRoman() throws Exception {
    assertEquals("MCMLIV", ins.intToRoman(1954));
  }
}
