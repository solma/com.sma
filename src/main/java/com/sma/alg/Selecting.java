package com.sma.alg;

import static com.sma.annotation.Tag.Algorithm.Searching;
import static com.sma.annotation.Tag.DataStructure.Array;
import static com.sma.util.ArrayUtil.partition;

import com.sma.annotation.Tag;
import com.sma.ds.graph.tree.TournamentTree;
import com.sma.ds.misc.MaxHeap;

/** Select Kth smallest number in an array. */
@Tag(algs = Searching, dss = Array)
public class Selecting {

  public static void main(String[] args) {
    int[] arr = {3, 101, 123, 1, 2, 5, 13, 67, 10};

    int[] tournamentTree = new int[(arr.length << 1) - 1];
    System.arraycopy(arr, 0, tournamentTree, tournamentTree.length - arr.length, arr.length);
    MaxHeap mh = new MaxHeap(tournamentTree);
    System.out.println(mh.removeTop());
    System.out.println(mh.top());
  }

  // O(N+logN-2)
  public int selectKthLargest(int[] arr, int K) {
    if (arr == null || arr.length == 0) return -1;
    TournamentTree tt = new TournamentTree(arr);
    if (K == 1) return tt.nodes[0];
    return selectKthLargest(tt.losersOfRoot(), K - 1);
  }

  void quickSelect(int[] a, int K) {
    quickSelect(a, 0, a.length - 1, K);
  }

  void quickSelect(int[] a, int l, int r, int K) {
    if (l > r) { return; }
    int cut = partition(a, l, r, l + ((r - l) >> 1));
    // System.out.println(cut);
    int offset = cut - l;
    if (offset == K - 1) { return; }
    if (offset > K - 1) quickSelect(a, l, cut - 1, K);
    else quickSelect(a, cut + 1, r, K - (offset + 1));
  }
}
