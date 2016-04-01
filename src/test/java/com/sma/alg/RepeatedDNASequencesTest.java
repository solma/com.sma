package com.sma.alg;

import static org.junit.Assert.assertArrayEquals;

import junit.framework.TestCase;
import org.junit.Test;

public class RepeatedDNASequencesTest extends TestCase {

  @Test
  public void testFindRepeatedDnaSequencesBest() throws Exception {
    String s = "AAAAACCCCCAAAAACCCCCAAAAAGGGTTT";
    RepeatedDNASequences ins = new RepeatedDNASequences();
    assertArrayEquals(ins.findRepeatedDnaSequencesBest(s).toArray(new String[0]),
        new String[] {"AAAAACCCCC", "AAAACCCCCA", "AAACCCCCAA", "AACCCCCAAA",
            "ACCCCCAAAA", "CCCCCAAAAA"});
  }
}
