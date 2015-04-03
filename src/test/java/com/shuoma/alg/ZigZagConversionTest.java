package com.shuoma.alg;

import com.shuoma.alg.ZigZagConversion;
import junit.framework.TestCase;
import org.junit.Test;


public class ZigZagConversionTest extends TestCase {

  @Test
  public void testConvert() throws Exception {
    ZigZagConversion ins = new ZigZagConversion();
    assertEquals("AEBDFCG", ins.convert("ABCDEFG", 3));
  }
}
