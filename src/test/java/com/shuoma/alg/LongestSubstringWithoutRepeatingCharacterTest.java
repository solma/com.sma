package com.shuoma.alg;

import com.shuoma.alg.LongestSubstringWithoutRepeatingCharacter;
import junit.framework.TestCase;
import org.junit.Test;

public class LongestSubstringWithoutRepeatingCharacterTest extends TestCase {

  @Test
  public void testLengthOfLongestSubstring() throws Exception {
    LongestSubstringWithoutRepeatingCharacter ins = new LongestSubstringWithoutRepeatingCharacter();
    assertEquals(12, ins.lengthOfLongestSubstring(
        "wlrbbmqbhcdarzowkkyhiddqscdxrjmowfrxsjybldbefsarcbynecdyggxxpklorellnmpapqfwkhopkmco"));
  }
}
