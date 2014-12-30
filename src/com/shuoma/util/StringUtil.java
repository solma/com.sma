package com.shuoma.util;

public class StringUtil {
  /*
   * @start: index of the starting field (inclusive)
   * @end: index of the ending field (exclusive)
   */
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
}
