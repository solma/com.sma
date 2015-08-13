package com.shuoma.alg;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;

public class TokenizeStringBasedOnDictionaryTest {

  @Test public void testTokenize() throws Exception {
    TokenizeStringBasedOnDictionary ins = new TokenizeStringBasedOnDictionary();
    String[][] dicts = new String[][]{
        {"abc", "def", "ghi", "ab", "cde", "f", "gi"},
        {"abc", "a", "bc"}
    };
    String[] queries = {
        "abcdef",
        "abc"
    };
    List[] answers = new List[] {
        Arrays.asList("ab cde f", "abc def"),
        Arrays.asList("a bc", "abc")
    };
    for (int i = 0; i < dicts.length; i++) {
      assertEquals(answers[i], ins.tokenize(dicts[i], queries[i]));
    }
  }
}
