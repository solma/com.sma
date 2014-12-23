package com.shuoma.alg.array;

import com.shuoma.ds.misc.MaxHeap;
import com.shuoma.helper.RandomArrayUtil;

import java.util.Arrays;

public class Sorting {

  public static void main(String[] args) {
    new Sorting().main();
  }

  // TODO main method
  void main() {
    // System.out.println(r.nextInt(100)+r.nextFloat());
    int min = (1 << 31) + -1;
    // System.out.println(Arrays.binarySearch(a, 1) );
    // System.out.println(binarySearch(a, 1));
    testSortAlgorithms();
  }

  public enum SortingAlg {
    QUICKSORT, COUNTSORT, INSERTIONSORT, BUBBLESORT, HEAPSORT;
  }

  void testSortAlgorithms() {
    SortingAlg[] algs = {SortingAlg.HEAPSORT};
    // algs=SortingAlg.values();
    for (SortingAlg alg : algs) {
      for (int i = 0; i < 10000; i++) {
        int[] a = RandomArrayUtil.genRandomArrayWithMinSize(10);
        int[] cpy = Arrays.copyOf(a, a.length);
        switch (alg) {
          case QUICKSORT:
            quickSort(cpy, 0, cpy.length - 1);
            break;
          case COUNTSORT:
            cpy = countSort(cpy);
            break;
          case INSERTIONSORT:
            cpy = insertionSort(cpy);
            break;
          case BUBBLESORT:
            bubbleSort(cpy);
            break;
          case HEAPSORT:
            heapSort(cpy);
            break;
          default:
            break;
        }
        Arrays.sort(a);
        if (!isSame(a, cpy)) {
          System.out.println("cpy array=" + Arrays.toString(cpy));
        }
      }
    }
  }

  boolean isSame(int[] a, int[] cpy) {
    if (a.length != cpy.length) return false;
    for (int i = 0; i < a.length; i++) {
      if (a[i] != cpy[i]) return false;
    }
    return true;
  }


  void bubbleSort(int[] a) {
    // in place
    int tmp;
    for (int i = 0; i < a.length; i++)
      for (int j = i + 1; j < a.length; j++)
        if (a[j] < a[i]) {
          RandomArrayUtil.swap(a, i, j);
        }
  }

  int[] insertionSort(int[] a) {
    // not in place
    int[] b = new int[a.length];
    Arrays.fill(b, Integer.MAX_VALUE);
    int i, j, k;
    for (i = 0; i < a.length; i++) {
      for (j = 0; j < b.length && b[j] < a[i];)
        j++;
      for (k = i - 1; k >= j; k--)
        b[k + 1] = b[k]; // move the elements backward
      b[j] = a[i];
      // System.out.println(Arrays.toString(b));
    }
    return b;
  }

  int[] countSort(int[] a) {
    int MAX_VALUE = 1000;
    // not in place
    int[] b = new int[a.length];
    int[] c = new int[MAX_VALUE * 2 + 1];
    Arrays.fill(c, 0);
    int i;
    for (i = 0; i < a.length; i++)
      c[a[i] + MAX_VALUE]++;
    for (i = 1; i < c.length; i++)
      c[i] += c[i - 1];
    for (i = 0; i < a.length; i++) {
      b[c[a[i] + MAX_VALUE] - 1] = a[i];
      c[a[i] + MAX_VALUE]--;
    }
    return b;
  }

  void quickSort(int[] a, int low, int high) {
    // in place
    if (low >= high) return; // tricky line
    int cut = partition(a, low, high);
    quickSort(a, low, cut - 1);
    quickSort(a, cut + 1, high);
  }

  int partition(int[] a, int low, int high) {
    int med = low + (high - low) / 2;
    int pivot = a[med];

    int smallerIdx = low, largerIdx = high + 1;
    for (int i = smallerIdx; i < largerIdx;) {
      if (a[i] < pivot)
        RandomArrayUtil.swap(a, i++, smallerIdx++);
      else {
        if (a[i] == pivot)
          i++;
        else
          RandomArrayUtil.swap(a, i, --largerIdx);
      }
    }
    return largerIdx - 1;
  }

  void heapSort(int[] a) {
    int n = a.length;
    if (n < 2) return;

    MaxHeap heap = new MaxHeap(a);
    // heapsort
    for (int i = 0; i < n - 1; i++) {
      RandomArrayUtil.swap(a, 0, n - 1 - i);
      heap.heapify(0, n - 1 - i);
    }
  }

  void testSelectionAlgorithms() {
    int sortAlgorithmChoice;
    for (int code = 1; code <= 1; code++) {
      sortAlgorithmChoice = code;
      for (int i = 0; i < 100; i++) {
        int[] a = RandomArrayUtil.genRandomArrayWithMinSize(10);
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
    int cut = partition(a, l, r);
    // System.out.println(cut);
    if (cut == K - 1) return;
    if (cut > K - 1)
      quickSelect(a, l, cut - 1, K);
    else if (cut < K - 1) quickSelect(a, cut + 1, r, K - cut - 1);
  }
}
