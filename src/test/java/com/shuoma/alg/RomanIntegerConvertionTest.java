package com.shuoma.alg;

import com.shuoma.alg.RomanIntegerConvertion;
import junit.framework.TestCase;
import org.junit.Test;

public class RomanIntegerConvertionTest extends TestCase {

  RomanIntegerConvertion ins = new RomanIntegerConvertion();

  @Test
  public void testRomanToInt() throws Exception {
    assertEquals(1954, ins.romanToInt("MCMLIV"));
  }

  @Test
  public void testIntToRoman() throws Exception {
    assertEquals("MCMLIV", ins.intToRoman(1954));
  }
}
