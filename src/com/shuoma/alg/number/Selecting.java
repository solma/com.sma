package com.shuoma.alg.number;

import com.shuoma.ds.misc.MaxHeap;
import com.shuoma.ds.tree.TournamentTree;
import com.shuoma.util.RandomUtil;

import java.util.Arrays;

public class Selecting {

  public static void main(String[] args) {
    int[] arr = {3, 101, 123, 1, 2, 5, 13, 67, 10};
    System.out.println(selectKthLargest(arr, 5));


    int[] tournamentTree = new int[(arr.length << 1) - 1];
    System.arraycopy(arr, 0, tournamentTree, tournamentTree.length - arr.length, arr.length);
    MaxHeap mh = new MaxHeap(tournamentTree);
    System.out.println(mh.removeTop());
    System.out.println(mh.top());
  }


  /**
   *
   * @param arr
   * @return second smallest out of an arr in O(N+logN-2)
   */
  public static int selectKthLargest(int[] arr, int K) {
    if (arr == null || arr.length == 0) return -1;
    TournamentTree tt = new TournamentTree(arr);
    if (K == 1) return tt.nodes[0];
    return selectKthLargest(tt.losersOfRoot(), K - 1);
  }

  void testSelectionAlgorithms() {
    int sortAlgorithmChoice;
    for (int code = 1; code <= 1; code++) {
      sortAlgorithmChoice = code;
      for (int i = 0; i < 100; i++) {
        int[] a = RandomUtil.genRandomArrayWithMinSize(10);
        int K;
        for (int j = 1; j <= a.length; j++) {
          K = j;
          int[] cpy = Arrays.copyOf(a, a.length);
          switch (sortAlgorithmChoice) {
            case 1:
              quickSelect(cpy, 0, cpy.length - 1, K);
              break;
          }
          Arrays.sort(a);
          if (a[K - 1] != cpy[K - 1])
            System.out.println(Arrays.toString(cpy) + " " + K + " " + a[K - 1]);
        }
      }
    }
  }

  void quickSelect(int[] a, int l, int r, int K) {
    // this is actually partial sorting
    if (l >= r) return;
    int cut = Sorting.partition(a, l, r);
    // System.out.println(cut);
    if (cut == K - 1) return;
    if (cut > K - 1)
      quickSelect(a, l, cut - 1, K);
    else if (cut < K - 1) quickSelect(a, cut + 1, r, K - cut - 1);
  }
}
