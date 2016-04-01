package com.sma.alg;

import static com.sma.annotation.Tag.DataStructure.StringT;
import static com.sma.annotation.Tag.Difficulty.D2;
import static com.sma.annotation.Tag.Reference.LeetCode;

import com.sma.annotation.Tag;

import java.util.ArrayList;
import java.util.List;

@Tag(dl = D2, dss = StringT, references = LeetCode)
/**
 Given an array of words and a length L, format the text such that each line has exactly
 L characters and is fully (left and right) justified. You should pack your words in
 a greedy approach; that is, pack as many words as you can in each line.
 Pad extra spaces ' ' when necessary so that each line has exactly L characters.
 Extra spaces between words should be distributed as evenly as possible. If the number of spaces
 on a line do not divide evenly between words, the empty slots on the left will be assigned more
 spaces than the slots on the right.
 For the last line of text, it should be left justified and no extra space is inserted between words.

 For example,
 words: ["This", "is", "an", "example", "of", "text", "justification."]
 L: 16.

 Return the formatted lines as:
 [
 "This    is    an",
 "example  of text",
 "justification.  "
 ]
 */
public class TextJustification {

  public static void main(String[] args) {
    new TextJustification().main();
  }

  public void main() {
    // String[] words=new String[]{"This", "is", "an","example", "of",
    // "text", "justification."};
    String[] words = {"a", "b", "c", "d", "e"};
    int L = 3; // 16
    for (String line : fullJustify(words, L)) { System.out.println(line); }
  }

  public List<String> fullJustify(String[] words, int L) {
    List<String> ret = new ArrayList<>();
    if (words == null || words.length == 0) { return ret; }

    int totalLenOfWordsInLine = 0, idxOfFirstWordInLine = 0;
    for (int i = 0; i < words.length; i++) {
      // words[i] cannot fit in this line
      if (totalLenOfWordsInLine + words[i].length() + (i - idxOfFirstWordInLine) > L) {
        int btwWordsSpaceNum = 0;
        int endOfLineSpaceNum = 0;

        int numOfWordsInLine = i - idxOfFirstWordInLine;
        if (numOfWordsInLine - 1 > 0) {
          btwWordsSpaceNum = (L - totalLenOfWordsInLine) / (numOfWordsInLine - 1);
          endOfLineSpaceNum = (L - totalLenOfWordsInLine) % (numOfWordsInLine - 1);
        }
        StringBuilder line = new StringBuilder();

        for (int j = idxOfFirstWordInLine; j < i; j++) {
          line.append(words[j]);
          if (j < i - 1) {
            for (int k = 0; k < btwWordsSpaceNum; k++) { line.append(" "); }
            if (endOfLineSpaceNum > 0) { line.append(" "); }
            endOfLineSpaceNum--;
          }
        }

        // padding spaces to the end of the line
        for (int j = line.length(); j < L; j++) { line.append(" "); }
        ret.add(line.toString());

        totalLenOfWordsInLine = 0;
        idxOfFirstWordInLine = i;
      }
      totalLenOfWordsInLine += words[i].length();
    }

    //process last line
    StringBuilder lastLine = new StringBuilder();
    for (int i = idxOfFirstWordInLine; i < words.length; i++) {
      lastLine.append(words[i]);
      if (lastLine.length() < L) { lastLine.append(" "); }
    }
    for (int i = lastLine.length(); i < L; i++) { lastLine.append(" "); }
    ret.add(lastLine.toString());

    return ret;
  }
}
