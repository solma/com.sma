package com.shuoma.alg.linear;

import com.shuoma.ds.misc.MaxHeap;
import com.shuoma.ds.tree.TournamentTree;

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

}
