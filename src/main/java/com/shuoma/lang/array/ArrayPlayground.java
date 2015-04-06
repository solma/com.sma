package com.shuoma.lang.array;

import java.util.Arrays;

public class ArrayPlayground {
  public static void main(String[] args) {
    new ArrayPlayground().main();
  }

  public void main() {
    int[] arr = {3, 1, 2};
    arr[0]++;
    arr[1]--;
    //swap(arr, 0, arr[2]);
    System.out.println(Arrays.toString(Arrays.copyOf(arr, 5)));
    System.out.println(Arrays.toString(arr) + " ");
  }
}
