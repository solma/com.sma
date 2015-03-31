package com.shuoma;

import static com.shuoma.annotation.Tag.DataStructure.Array;
import static com.shuoma.annotation.Tag.Difficulty.D2;
import static com.shuoma.annotation.Tag.Source.LeetCode;
import static com.shuoma.annotation.Tag.Trick.InplaceSwap;

import com.shuoma.annotation.Tag;

@Tag(dl = D2, dss = Array, source = LeetCode, tricks = InplaceSwap)
public class FirstMissingPositive {
  public static void main(String[] args) {
    new FirstMissingPositive().firstMissingPositive(new int[] {2, 1});
  }

  //pass 2
  public int firstMissingPositive(int[] A) {
    //idea: using the array it self as a hash table
    int n = A.length;
    if (n == 0)
      return 1;
    for (int i = 0; i < n; ) {
      if (A[i] != i + 1 && A[i] > 0 && A[i] <= n && A[A[i] - 1] != A[i]) {
        swap(A, i, A[i] - 1);
      } else
        i++;
    }

    for (int i = 0; i < n; i++) {
      if (A[i] != i + 1) {
        return i + 1;
      }
    }
    return n + 1;
  }

  public void swap(int[] A, int i, int j) {
    int tmp = A[j];
    A[j] = A[i];
    A[i] = tmp;
  }


  //pass 1
  // public int firstMissingPositive(int[] A) {
  // int n=A.length;
  // int i = 0;
  // while (i < n)
  // {
  // if (A[i] != (i+1) && A[i] >= 1 && A[i] <= n && A[A[i]-1] != A[i]){
  // int tmp=A[i];
  // A[i]=A[A[i]-1];
  // A[tmp-1]=tmp;
  // }
  // else
  // i++;
  // //System.out.println(i+" "+A[i]);
  // }

  // for (i = 0; i < n; i++)
  // if (A[i] != i+1 )  return i+1;
  // return n+1;
  // }
}
