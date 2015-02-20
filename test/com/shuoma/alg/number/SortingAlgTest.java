package com.shuoma.alg.number;

import static com.shuoma.alg.number.Sorting.SortingAlg;
import com.shuoma.util.RandomUtil;
import junit.framework.TestCase;
import org.junit.Test;

import java.util.Arrays;

public class SortingAlgTest extends TestCase {

  @Test
  public void testSortAlgorithms() {
    Sorting ins = new Sorting();

    SortingAlg[] algs = {SortingAlg.STACKSORT};
    // algs=SortingAlg.values();
    for (SortingAlg alg : algs) {
      for (int i = 0; i < 10000; i++) {
        int[] a = RandomUtil.genRandomArrayWithMinSize(100);
        int[] cpy = Arrays.copyOf(a, a.length);
        switch (alg) {
          case BUBBLESORT:
            ins.bubbleSort(cpy);
            break;
          case COUNTSORT:
            cpy = ins.countSort(cpy);
            break;
          case HEAPSORT:
            ins.heapSort(cpy);
            break;
          case INSERTIONSORT:
            cpy = ins.insertionSort(cpy);
            break;
          case MERGESORT:
            cpy = ins.mergeSort(cpy);
            break;
          case PATIENCESORT:
            cpy = ins.patienceSort(cpy);
            break;
          case QUICKSORT:
            ins.quickSort(cpy);
            break;
          case RADIXSORT:
            cpy = ins.radixSort(cpy);
            break;
          case STACKSORT:
            cpy = ins.stackSort(cpy);
            break;
          default:
            break;
        }
        int[] sortBase = Arrays.copyOf(a, a.length);
        Arrays.sort(sortBase);
        if (!Arrays.equals(sortBase, cpy)) {
          System.out.println(alg);
          System.out.println(Arrays.toString(a));
          System.out.println(Arrays.toString(cpy));
          System.out.println();
        }
      }
    }
  }

}
