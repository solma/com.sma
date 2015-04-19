package com.shuoma.alg;

import static com.shuoma.annotation.Tag.Algorithm.Sorting;
import static com.shuoma.annotation.Tag.DataStructure.Array;

import com.shuoma.annotation.Tag;
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

@Tag(algs = Sorting, dss = Array)
public class Sorting {

  public static enum SortingAlg {
    BUBBLE_SORT,
    COUNT_SORT,
    HEAP_SORT,
    INSERTION_SORT,
    MERGE_SORT,
    PATIENCE_SORT,
    QUICK_SORT,
    RADIX_SORT,
    STACK_SORT,
    ;
  }

  int[] bubbleSort(int[] a) {
    // in place
    int tmp;
    for (int i = 0; i < a.length; i++)
      for (int j = i + 1; j < a.length; j++)
        if (a[j] < a[i]) {
          ArrayUtil.swap(a, i, j);
        }
    return a;
  }

  int[] countSort(int[] a) {
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

  void heapSort(int[] a) {
    int n = a.length;
    if (n < 2) return;

    MaxHeap heap = new MaxHeap(a);
    // heapsort
    for (int i = 0; i < n - 1; i++) {
      ArrayUtil.swap(a, 0, n - 1 - i);
      heap.heapify(0, n - 1 - i);
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

  int[] mergeSort(int[] a) {
    return mergeSort(a, 0, a.length - 1);
  }

  int[] mergeSort(int[] a, int low, int high) {
    if (low > high) return null;
    if (low == high) return new int[]{a[low]};
    int mid = low + (high - low) / 2;
    return merge(mergeSort(a, low, mid), mergeSort(a, mid + 1, high));
  }

  int[] merge(int[] a, int[] b) {
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

  int[] patienceSort(int[] a) {
    List<Pile> piles = new ArrayList<>();
    for (int ele : a) {
      Pile newPile = new Pile();
      newPile.push(ele);
      int insertionIdx = Collections.binarySearch(piles, newPile);
      if (insertionIdx < 0) insertionIdx = ~insertionIdx;
      if (insertionIdx != piles.size()) piles.get(insertionIdx).push(ele);
      else piles.add(newPile);
    }

    //System.out.println(piles);
    // merge
    PriorityQueue<Pile> pq = new PriorityQueue<>(piles);
    for (int i = 0; i < a.length; i++) {
      // poll minPile then offer so the minPile will update
      Pile minPile = pq.poll();
      a[i] = minPile.pop();
      if (!minPile.isEmpty()) pq.offer(minPile);
    }
    return a;
  }

  void quickSort(int[] a) {
    quickSort(a, 0, a.length - 1);
  }

  void quickSort(int[] a, int low, int high) {
    // in place
    if (low >= high) return; // tricky line
    int cut = ArrayUtil.partition(a, low, high, a[low + (high - low) / 2]);
    quickSort(a, low, cut - 1);
    quickSort(a, cut + 1, high);
  }

  int[] radixSort(int[] a) {
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

  int[] radixSort(int[] a, int radix) {
    LinkedList<Integer>[] counter = new LinkedList[radix];
    for (int i = 0; i < counter.length; i++) counter[i] = new LinkedList<>();
    int dev = 1;
    int maxDigitSymbols = getDigitLength((int) ArrayUtil.max(ArrayUtil.intToDoubleArray(a)), radix);
    for (int i = 0; i < maxDigitSymbols; i++, dev *= radix) {
        for(int j = 0; j < a.length; j++) {
            int bucket = (a[j] / dev) % radix;
            counter[bucket].add(a[j]);
        }
        //System.out.println(Arrays.toString(counter));

        int pos = 0;
        for(int j = 0; j < counter.length; j++) {
            Integer value;
            while ((value = counter[j].poll()) != null) {
                a[pos++] = value;
            }
        }
    }
    return a;
  }

  int getDigitLength(int n, int radix) {
    int cnt = 0;
    while (n > 0) {
      cnt++;
      n /= radix;
    }
    return cnt;
  }

  /** Sorting using three stacks only. */
  int[] stackSort(int[] a) {
    int n = a.length;

    List<Stack<Integer>> threeStacks = new ArrayList<>(3);
    for (int i = 0; i < 3; i++) threeStacks.add(new Stack<Integer>());

    for (int ai : a) threeStacks.get(0).push(ai);
    stackSort(threeStacks, n, 0, 2);

    for (int i = 0; i < n; i++) {
      a[i] = threeStacks.get(2).pop();
    }
    return a;
  }

  void stackSort(List<Stack<Integer>> threeStacks, int n, int from, int to) {
    if (n == 0) return;
    // move n-1 numbers from S0 to S2.
    stackSort(threeStacks, n - 1, 0, 2);
    // insert nth number to S2
    stackSortInsertion(threeStacks, 1, 0, 2);
  }

  void stackSortInsertion(List<Stack<Integer>> threeStacks, int bridge, int from, int to) {
    Stack<Integer> toStack = threeStacks.get(to);
    Stack<Integer> bridgeStack = threeStacks.get(bridge);
    int number = threeStacks.get(from).pop();
    while (!toStack.isEmpty() && toStack.peek() < number) {
      bridgeStack.push(toStack.pop());
    }
    toStack.push(number);
    while (!bridgeStack.empty()) {
      toStack.push(bridgeStack.pop());
    }
  }

  static class Pile extends Stack<Integer> implements Comparable<Pile> {
    @Override
    public int compareTo(Pile o) {
      return peek() - o.peek();
    }
  }
}

