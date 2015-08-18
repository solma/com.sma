package com.shuoma.alg;

import static com.shuoma.annotation.Tag.DataStructure.Array;
import static com.shuoma.annotation.Tag.Difficulty.D2;
import static com.shuoma.annotation.Tag.Reference.LeetCode;

import com.shuoma.annotation.Tag;

@Tag(dl = D2, dss = Array, references = LeetCode)
public class NextPermutation {

  //pass 2
  public void nextPermutation(int[] num) {
    int n = num.length;
    if (n == 0 || n == 1) { return; }

    int i;
    //scan backward find the first i such that num[i]<num[i+1]
    for (i = n - 2; i >= 0; i--) {
      if (num[i] < num[i + 1])
        break;
    }
    //this is last permutation
    if (i == -1) {
      reverse(num, 0, n - 1);
      return;
    }

    int j;
    //scan backward find the first j such that num[j]>num[i]
    for (j = n - 1; j > i; j--) {
      if (num[j] > num[i])
        break;
    }
    //swap num[j] and num[i]
    swap(num, i, j);

    //reverse the subarray of num starting index i+1
    reverse(num, i + 1, n - 1);
  }

  public void swap(int[] num, int i, int j) {
    if (i == j)
      return;
    else {
      int tmp = num[i];
      num[i] = num[j];
      num[j] = tmp;
    }
  }

  public void reverse(int[] num, int start, int end) {
    int i = start, j = end;
    while (i < j) {
      swap(num, i++, j--);
    }
  }


  //pass1

  // private void swap(int[] num, int a, int b) {
  // int temp = num[a];
  // num[a] = num[b];
  // num[b] = temp;
  // }

  // /* O(n) -- reverse the array, given a range */
  // private void reverse(int[] num, int l, int r) {
  // while (l < r) {
  // swap(num, l++, r--);
  // }
  // }

  // /* O(n) -- find the next permutation */
  // public void nextPermutation(int[] num) {
  // // find descending part from right to left
  // int cur = num.length - 1;
  // while (cur>0 && num[cur-1] >= num[cur]) cur--;
  // if (cur > 0) {
  // cur--;
  // int right = num.length-1;
  // while (num[right] <= num[cur])  right--;
  // //System.out.println(cur+" "+right);
  // swap(num,cur, right);
  // reverse(num, cur+1, num.length-1);
  // }else{// the last permutation, so the next is the first in the alphabetical order
  // Arrays.sort(num);
  // }

  // }
}
