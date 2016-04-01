package com.sma.alg;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class ExpressionEvalerTest {

  @Test public void testEval1() throws Exception {
    ExpressionEvaler ins = new ExpressionEvaler();
    assertEquals(6, ins.eval("( * 1 ( + 1 2 3 ) )"));
    assertEquals(34, ins.eval("( * ( + 1 1 ) 17 )"));
    assertEquals(51, ins.eval("( * 17 ( + 1 ( + 1 1 ) ) )"));
  }
}
