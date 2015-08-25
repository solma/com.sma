package com.shuoma.alg;


import static com.shuoma.annotation.Tag.Algorithm.SlidingWindow;
import static com.shuoma.annotation.Tag.DataStructure.Hash;
import static com.shuoma.annotation.Tag.DataStructure.Substring;
import static com.shuoma.annotation.Tag.Difficulty.D3;
import static com.shuoma.annotation.Tag.Reference.Interview;

import com.shuoma.annotation.Tag;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * The extension of Minimum Window Substring with order constraints, the difference is how to shrink.*

 * tracking the appears of i-th char in pattern in index[i] as a list, when found all the chars, try to
 * do shrink from the end to begin, shrink the index to higher one if its value smaller than the index
 * of next char in pattern sequence. For the example, UAXXBAUB,
 * when parse to UAXXB, it will got following index, 'A': 1; 'B': 4, found a substring,
 * when parse to UAXXBA, index is: 'A': 1,5; 'B':4, but A cannot shrink to 5 because it need be smaller than B's index.
 * when parse to UAXXBAUB, index is 'A':1,5; 'B':4,7, B first shrink to 7, then A shrink to 5, find a shorter substring.
 */
@Tag(algs = SlidingWindow, dl = D3, dss = {Hash, Substring}, references = Interview)
public class MinimumWindowSubstringWithOrder {

  public static void main(String[] args) {
    MinimumWindowSubstringWithOrder finder = new MinimumWindowSubstringWithOrder();
    System.out.println(finder.minSubstring("UAXXBAB", "AB")); //AUB
    System.out.println(finder.minSubstring("UAXSSXSXAXXAUB", "XXA")); //XSXA
  }

  public String minSubstring(String str, String pattern) {
    if (str == null || str.length() == 0 || pattern == null) { return str; }
    int pl = pattern.length();
    if (pl == 0 || pl > str.length()) { return ""; }

    String minSubstring = "";
    HashMap<Character, List<Integer>> indexes = new HashMap<>();
    for (int i = 0; i < pl; i++) {
      indexes.put(pattern.charAt(i), new ArrayList<Integer>());
    }

    int[] currentIndex = new int[pl];
    int idx = 0;
    for (int i = 0; i < str.length(); i++) {
      if (!indexes.containsKey(str.charAt(i))) { continue; }

      indexes.get(str.charAt(i)).add(i);
      if (idx < pl && str.charAt(i) == pattern.charAt(idx)) { //haven't found all the chars
        currentIndex[idx] = i;
        idx++;
      }

      //found all the chars, and start to shrink
      if (idx == pl) {
        //shrink from the end to begin, shrink the index to higher if its value smaller than the index of all chars in its right to keep the order.
        for (int j = pl - 1; j >= 0; j--) {
          List<Integer> pos = indexes.get(pattern.charAt(j));
          for (int p = pos.size() - 1; p >= 0 && pos.get(p) > currentIndex[j]; p--) {
            //check if the shrink index is smaller than next char to keep the order
            if (j == pl - 1 || pos.get(p) < currentIndex[j + 1]) {
              currentIndex[j] = pos.get(p);   //do shrink
              break;
            }
          }
        }
        int end = currentIndex[pl - 1];
        int begin = currentIndex[0];
        if (minSubstring.length() == 0 || end - begin + 1 < minSubstring.length())
          minSubstring = str.substring(begin, end + 1);
      }
    }
    return minSubstring;
  }
}
