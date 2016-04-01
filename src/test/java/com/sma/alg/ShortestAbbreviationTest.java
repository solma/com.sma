package com.sma.alg;

import com.google.common.collect.ImmutableSet;
import junit.framework.TestCase;
import org.junit.Test;

import java.util.Set;

public class ShortestAbbreviationTest extends TestCase {

  @Test
  public void testShortestAbbreviation() throws Exception {
    ShortestAbbreviation ins = new ShortestAbbreviation();
    Set[] dicts = {
        ImmutableSet.of("plain", "amber", "blade", "spain", "loped", "hallo", "aplan", "aqewe"),
        ImmutableSet.of("plain", "amber", "blade", "spain", "loped", "hallo", "aplan", "aqewe", "awlle")
    };
    String[] queries = {
        "apple",
        "apple"
    };
    String[] answers = {
        "3le",
        "app2"
    };
    assertEquals(dicts.length, queries.length);
    for (int i = 0; i < dicts.length; i++) {
      assertEquals(answers[i], ins.shortestAbbreviation(dicts[i], queries[i]));
    }
  }
}
