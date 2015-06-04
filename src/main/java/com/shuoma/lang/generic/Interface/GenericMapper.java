package com.shuoma.lang.generic.Interface;

public interface GenericMapper<T, R> {
  public R map(T input);
}
