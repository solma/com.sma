package com.shuoma.alg.number;

import com.shuoma.ds.misc.MaxHeap;
import com.shuoma.util.ArrayUtil;
import com.shuoma.util.RandomUtil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Stack;

public class Sorting {

  public static void main(String[] args) {
    testSortAlgorithms();
  }

  public enum SortingAlg {
    BUBBLESORT("BubbleSort"),
    COUNTSORT("CountSort"),
    HEAPSORT("HeapSort"),
    INSERTIONSORT("InsertionSort"),
    MERGESORT("MergeSort"),
    PATIENCESORT("PatienceSort"),
    QUICKSORT("QuickSort"),
    RADIXSORT("RadixSort");

    String name;

    SortingAlg(String name) {
      this.name = name;
    }

    @Override
    public String toString() {
      return name;
    }
  }

  static void testSortAlgorithms() {
    SortingAlg[] algs = {SortingAlg.RADIXSORT};
    // algs=SortingAlg.values();
    for (SortingAlg alg : algs) {
      for (int i = 0; i < 10000; i++) {
        int[] a = RandomUtil.genRandomArrayWithMinSize(100);
        int[] cpy = Arrays.copyOf(a, a.length);
        switch (alg) {
          case BUBBLESORT:
            bubbleSort(cpy);
            break;
          case COUNTSORT:
            cpy = countSort(cpy);
            break;
          case HEAPSORT:
            heapSort(cpy);
            break;
          case INSERTIONSORT:
            cpy = insertionSort(cpy);
            break;
          case MERGESORT:
            cpy = mergeSort(cpy);
            break;
          case PATIENCESORT:
            cpy = patienceSort(cpy);
            break;
          case QUICKSORT:
            quickSort(cpy);
            break;
          case RADIXSORT:
            cpy = radixSort(cpy);
            break;
          default:
            break;
        }
        int[] sortBase = Arrays.copyOf(a, a.length);
        Arrays.sort(sortBase);
        if (!ArrayUtil.equals(sortBase, cpy)) {
          System.out.println(alg);
          System.out.println(Arrays.toString(a));
          System.out.println(Arrays.toString(cpy));
          System.out.println();
        }
      }
    }
  }

  public static int[] bubbleSort(int[] a) {
    // in place
    int tmp;
    for (int i = 0; i < a.length; i++)
      for (int j = i + 1; j < a.length; j++)
        if (a[j] < a[i]) {
          ArrayUtil.swap(a, i, j);
        }
    return a;
  }

  public static int[] countSort(int[] a) {
    // add RandomUtil.MAX_RANDOM_VALUE to offset negative numbers
    for (int i = 0; i < a.length; i++) a[i] += RandomUtil.MAX_RANDOM_VALUE;
    // not in place
    int[] c = new int[RandomUtil.MAX_RANDOM_VALUE * 2 + 1];
    Arrays.fill(c, 0);
    for (int i = 0; i < a.length; i++) c[a[i]]++;
    for (int i = 1; i < c.length; i++) c[i] += c[i - 1];
    int[] b = new int[a.length];
    for (int i = 0; i < a.length; i++) {
      b[c[a[i]] - 1] = a[i] - RandomUtil.MAX_RANDOM_VALUE;
      c[a[i]]--;
    }
    return b;
  }

  public static void heapSort(int[] a) {
    int n = a.length;
    if (n < 2) return;

    MaxHeap heap = new MaxHeap(a);
    // heapsort
    for (int i = 0; i < n - 1; i++) {
      ArrayUtil.swap(a, 0, n - 1 - i);
      heap.heapify(0, n - 1 - i);
    }
  }

  public static int[] insertionSort(int[] a) {
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

  public static int[] mergeSort(int[] a) {
    return mergeSort(a, 0, a.length - 1);
  }

  public static int[] mergeSort(int[] a, int low, int high) {
    if (low > high) return null;
    if (low == high) return new int[]{a[low]};
    int mid = low + (high - low) / 2;
    return merge(mergeSort(a, low, mid), mergeSort(a, mid + 1, high));
  }

  static int[] merge(int[] a, int[] b) {
    if (a == null) return b;
    if (b == null) return a;
    int[] r = new int[a.length + b.length];
    int ri = 0, ai = 0, bi = 0;
    while (ai < a.length && bi < b.length && ri < r.length) {
      if (a[ai] <= b[bi]) r[ri++] = a[ai++];
      else r[ri++] = b[bi++];
    }
    if (ai == a.length) {
      for (int i = ri; i < r.length; i++)
        r[ri++] = b[bi++];
    } else {
      for (int i = ri; i < r.length; i++)
        r[ri++] = a[ai++];
    }
    return r;
  }

  public static int[] patienceSort(int[] a) {
    List<Pile<Integer>> piles = new ArrayList<>();
    for (int ele : a) {
      Pile<Integer> newPile = new Pile();
      newPile.push(ele);
      int insertionIdx = Collections.binarySearch(piles, newPile);
      if (insertionIdx < 0) insertionIdx = ~insertionIdx;
      if (insertionIdx != piles.size()) piles.get(insertionIdx).push(ele);
      else piles.add(newPile);
    }

    //System.out.println(piles);
    // merge
    PriorityQueue<Pile<Integer>> pq = new PriorityQueue<>(piles);
    for (int i = 0; i < a.length; i++) {
      // poll minPile then offer so the minPile will update
      Pile<Integer> minPile = pq.poll();
      a[i] = minPile.pop();
      if (!minPile.isEmpty()) pq.offer(minPile);
    }
    return a;
  }

  public static void quickSort(int[] a) {
    quickSort(a, 0, a.length - 1);
  }

  static void quickSort(int[] a, int low, int high) {
    // in place
    if (low >= high) return; // tricky line
    int cut = partition(a, low, high);
    quickSort(a, low, cut - 1);
    quickSort(a, cut + 1, high);
  }

  public static int partition(int[] a, int low, int high) {
    int med = low + (high - low) / 2;
    int pivot = a[med];

    int smallerIdx = low, largerIdx = high + 1;
    for (int i = smallerIdx; i < largerIdx;) {
      if (a[i] < pivot) ArrayUtil.swap(a, i++, smallerIdx++);
      else {
        if (a[i] == pivot) i++;
        else ArrayUtil.swap(a, i, --largerIdx);
      }
    }
    return largerIdx - 1;
  }

  public static int[] radixSort(int[] a) {
    if (a.length == 0) return null;
    List<Integer>[] lists = new LinkedList[3];
    for(int i = 0; i<lists.length; i++) lists[i] = new LinkedList<>();
    for (int i : a){
      int idx;
      if (i == 0) idx = 1;
      else
        if (i > 0) idx = 0;
        else idx = 2;
      lists[idx].add(i);
    }
    int radix = 10;
    int[] res = null;
    res = ArrayUtil.concat(res,
        ArrayUtil.negateSign(
            ArrayUtil.reverse(radixSort(
                ArrayUtil.negateSign(ArrayUtil.integerListToIntArray(lists[2])), radix))));
    res = ArrayUtil.concat(res, ArrayUtil.integerListToIntArray(lists[1]));
    res = ArrayUtil.concat(res, radixSort(ArrayUtil.integerListToIntArray(lists[0]), radix));
    return res;
  }

  public static int[] radixSort(int[] a, int radix) {
    LinkedList<Integer>[] counter = new LinkedList[radix];
    for (int i = 0; i < counter.length; i++) counter[i] = new LinkedList<>();
    int mod = 10;
    int dev = 1;
    int maxDigitSymbols = getDigitLength((int) ArrayUtil.max(ArrayUtil.intToDoubleArray(a)), radix);
    for (int i = 0; i < maxDigitSymbols; i++, dev *= 10, mod *= 10) {
        for(int j = 0; j < a.length; j++) {
            int bucket = (a[j] % mod) / dev;
            counter[bucket].add(a[j]);
        }
        //System.out.println(Arrays.toString(counter));

        int pos = 0;
        for(int j = 0; j < counter.length; j++) {
            Integer value = null;
            while ((value = counter[j].poll()) != null) {
                a[pos++] = value;
            }
        }
    }
    return a;
  }

  static int getDigitLength(int n, int radix) {
    int cnt = 0;
    while (n > 0) {
      cnt++;
      n /= radix;
    }
    return cnt;
  }


  public static class Pile<Integer> extends Stack<Integer> implements Comparable <Pile<Integer>> {
    @Override
    public int compareTo(Pile<Integer> o) {
      return ((int) peek()) - ((int) o.peek());
    }
  }
}
