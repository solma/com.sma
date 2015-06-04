package com.shuoma.lang.generic.Interface;

public class SampleMapper implements GenericMapper<String, Integer>{
  public Integer map(String input) {
    if (input == null) return 0;
    return input.length();
  }
}
