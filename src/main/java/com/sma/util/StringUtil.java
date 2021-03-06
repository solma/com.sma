package com.sma.util;

public class StringUtil {

  public static String join(String[] fields, int start, int end, String connectingDelimeter){
      try{
          StringBuilder sb=new StringBuilder();
          for(int i=start;i<end;i++){
              if(i!=start) sb.append(connectingDelimeter);
              sb.append(fields[i]);
          }
          return sb.toString();
      }catch(Exception ex){
          ex.printStackTrace();
          return null;
      }
  }

  /**
   * @param s: string
   * @param x: (leading or trailing) character to be deleted
   * @param isLeading:
   */
  public static String removePaddingCharacters(String s, char x, boolean isLeading) {
    if (s == null) { return s; }
    int i;
    if (isLeading) {
      for (i = 0; i < s.length(); i++) {
        if (s.charAt(i) != x) {
          break;
        }
      }
      return s.substring(i);
    } else {
      for (i = s.length() - 1; i >= 0; i--) {
        if (s.charAt(i) != x) {
          break;
        }
      }
      return s.substring(0, i + 1);
    }
  }

  public static char[][] toCharMatrix(String[] strs) {
    assert(strs.length > 0);
    char[][] matrix = new char[strs.length][];
    for (int i = 0; i < matrix.length; i++) {
      matrix[i] = strs[i].toCharArray();
    }
    return matrix;
  }

  /** To padded binary string. */
  public static String toPaddedBinString(long n, int wordLength) {
    String rawBin = "";
    switch (wordLength) {
      case 32:
        rawBin = Integer.toBinaryString((int) n);
        break;
      case 64:
        rawBin = Long.toBinaryString(n);
        break;
      default:
        break;
    }
    StringBuilder bin = new StringBuilder(rawBin);
    for (int i = 0; i < wordLength - rawBin.length(); i++) {
      bin.insert(0, '0');
    }
    return bin.toString();
  }

  public static String toPaddedBinString(long n) {
    return toPaddedBinString(n, 64);
  }
}
