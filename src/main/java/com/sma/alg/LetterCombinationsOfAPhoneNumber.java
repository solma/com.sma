package com.sma.alg;

import java.util.*;

public class LetterCombinationsOfAPhoneNumber {
  public static void main(String[] args) {
    new LetterCombinationsOfAPhoneNumber().main();
  }

  public void main() {
    for (String s : letterCombinations("345")) {
      System.out.println(s);
    }
  }

  public ArrayList<String> letterCombinations(String digits) {
    HashMap<Character, String> keyboard = new HashMap<>();
    keyboard.put('2', "abc");
    keyboard.put('3', "def");
    keyboard.put('4', "ghi");
    keyboard.put('5', "jkl");
    keyboard.put('6', "mno");
    keyboard.put('7', "pqrs");
    keyboard.put('8', "tuv");
    keyboard.put('9', "wxyz");
    ArrayList<String> res = new ArrayList<>();
    res.add("");
    ArrayList<String> shadow = new ArrayList<>();
    String letters;
    for (int i = 0; i < digits.length(); i++) {
      letters = keyboard.get(digits.charAt(i));
      for (int j = 0; j < res.size(); j++) {
        for (int z = 0; z < letters.length(); z++) {
          shadow.add(res.get(j) + String.valueOf(letters.charAt(z)));
        }
      }
      res = shadow;
      shadow = new ArrayList<>();
    }
    return res;
  }
}
