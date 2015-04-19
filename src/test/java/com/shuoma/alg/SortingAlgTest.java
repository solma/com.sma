package com.shuoma.alg;

import static com.shuoma.alg.Sorting.SortingAlg;

import com.shuoma.util.RandomUtil;
import junit.framework.TestCase;
import org.junit.Test;

import java.util.Arrays;

public class SortingAlgTest extends TestCase {

  @Test
  public void testSortAlgorithms() {
    Sorting ins = new Sorting();

    SortingAlg[] algs = {SortingAlg.RADIX_SORT};
    // algs=SortingAlg.values();
    for (SortingAlg alg : algs) {
      for (int i = 0; i < 10000; i++) {
        int[] a = RandomUtil.genRandomArrayWithMinSize(100);
        int[] cpy = Arrays.copyOf(a, a.length);
        switch (alg) {
          case BUBBLE_SORT:
            ins.bubbleSort(cpy);
            break;
          case COUNT_SORT:
            cpy = ins.countSort(cpy);
            break;
          case HEAP_SORT:
            ins.heapSort(cpy);
            break;
          case INSERTION_SORT:
            cpy = ins.insertionSort(cpy);
            break;
          case MERGE_SORT:
            cpy = ins.mergeSort(cpy);
            break;
          case PATIENCE_SORT:
            cpy = ins.patienceSort(cpy);
            break;
          case QUICK_SORT:
            ins.quickSort(cpy);
            break;
          case RADIX_SORT:
            cpy = ins.radixSort(cpy);
            break;
          case STACK_SORT:
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
