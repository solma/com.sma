package com.shuoma.alg;

import com.shuoma.alg.SearchInRotatedArray;
import junit.framework.TestCase;
import org.junit.Test;

public class SearchInRotatedArrayTest extends TestCase {

  @Test
  public void testSearch() throws Exception {
    int[] A = new int[] {2, 2, 2, 2, 3, 2, 2, 2, 2, 2};
    SearchInRotatedArray ins = new SearchInRotatedArray();
    assertEquals(ins.search(A, 3), 4);
  }
}
