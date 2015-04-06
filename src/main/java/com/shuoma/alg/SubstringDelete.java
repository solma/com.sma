package com.shuoma.alg;

/**
 * Delete all occurrences of "ac" and "b" from a string
 *
 * @requirement: O(n) time
 * @author solma
 *
 */
import static com.shuoma.annotation.Tag.DataStructure.Array;

import com.shuoma.annotation.Tag;

import java.util.Arrays;

@Tag(dss = Array)
public class SubstringDelete {
  public static void main(String[] args) {
    new SubstringDelete().main("acbabcaccb");
  }

  public void main(String s) {
    char[] arr = s.toCharArray();
    int j = 0, state = 1;

    for (int i = 0; i < arr.length; i++) {
      if (state == 1 && arr[i] != 'a' && arr[i] != 'b') {
        arr[j++] = arr[i];
      }
      if (state == 2 && arr[i] != 'c') {
        arr[j++] = 'a';
        if (arr[i] != 'a' && arr[i] != 'b') arr[j++] = arr[i];
      }
      state = arr[i] == 'a' ? 2 : 1;
      System.out.println("i=" + i + " j=" + j + " " + Arrays.toString(arr));
    }
    if (state == 2) arr[j++] = 'a';
    System.out.println(new String(arr).substring(0, j));
  }
}
