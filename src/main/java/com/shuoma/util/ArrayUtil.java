package com.shuoma.util;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class ArrayUtil {
  public static double calculateCosineSimilarity(List<Double> list1, List<Double> list2) {
    if (list1.size() != list2.size()) {
      System.err.println("Two lists must have the same dimensionality.");
      return 0;
    }
    double dividend = 0, divisor1 = 0, divisor2 = 0;

    for (int i = 0; i < list1.size(); i++) {
      dividend += list1.get(i) * list2.get(i);
      divisor1 += Math.pow(list1.get(i), 2);
      divisor2 += Math.pow(list2.get(i), 2);
    }
    // System.out.println(dividend+" "+divisor1+" "+divisor2);
    return dividend / (Math.sqrt(divisor1) * Math.sqrt(divisor2));
  }

  public static double[] calculateHistogram(List<Double> values, double[] normalizedInterval, int noOfBins) {
    double[] doubles = new double[values.size()];
    for (int i = 0; i < values.size(); i++)
      doubles[i] = values.get(i);
    return calculateHistogram(doubles, normalizedInterval, noOfBins);
  }

  public static double[] calculateHistogram(double[] values, double[] normalizedInterval, int noOfBins) {
    if (values.length < 1)
      return null;
    if (noOfBins < 2)
      return new double[] {1};

    double min = normalizedInterval[0], max = normalizedInterval[1];
    double[] probs = new double[noOfBins];
    for (int i = 0; i < values.length; i++) {
      probs[(int) ((values[i] - min) * noOfBins / (max - min))] += 1;
    }
    for (int i = 0; i < probs.length; i++) {
      probs[i] /= values.length;
    }
    return probs;
  }

  public static double calculateMean(List<Double> list) {
    if (list == null || list.size() == 0)
      return Double.MAX_VALUE;
    double sum = 0;
    for (double num : list)
      sum += num;
    return sum / list.size();
  }

  public static double calculatePearsonCorrelation(List<Double> list1, List<Double> list2) {
    if (list1.size() != list2.size()) {
      System.err.println("Two lists must have the same dimensionality.");
      return 0;
    }
    double mean1 = calculateMean(list1);
    double mean2 = calculateMean(list2);

    double std1 = Math.sqrt(calculateVariance(list1, mean1));
    double std2 = Math.sqrt(calculateVariance(list2, mean2));

    double dividend = 0;
    for (int i = 0; i < list1.size(); i++) {
      dividend += (list1.get(i) - mean1) * (list2.get(i) - mean2);
    }
    dividend /= list1.size() - 1;

    // System.out.println(mean1+" "+std1+" "+mean2+" "+std2+" "+dividend);
    return dividend / (std1 * std2);
  }

  public static double calculatePDFOfNormalDistribution(double mean, double std, double value) {
    double prob = Math.pow(Math.E, -Math.pow(value - mean, 2) / 2 / Math.pow(std, 2)) / (
        Math.sqrt(Math.PI * 2) * std);
    if (prob > 1)
      System.out.println(mean + " " + std + " " + value);
    return prob;
  }

  public static double calculateVariance(List<Double> list, double mean) {
    if (mean == Double.MAX_VALUE)
      return mean;
    if (list.size() == 1)
      return 0;
    double sum = 0;
    for (double num : list) {
      sum += Math.pow(num - mean, 2);
    }
    return sum / (list.size() - 1);
  }

  public static int[] concat(int[] a, int[] b) {
    if (a == null)
      return b;
    if (b == null)
      return a;
    int[] c = new int[a.length + b.length];
    System.arraycopy(a, 0, c, 0, a.length);
    System.arraycopy(b, 0, c, a.length, b.length);
    return c;
  }

  public static Comparator<double[]> comparatorDoubleArray(final int k) {
    return new Comparator<double[]>() {
      @Override public int compare(double[] a1, double[] a2) {
        double diff = a1[k] - a2[k];
        if (diff == 0.0) return 0;
        return diff > 0 ? 1: -1;
      }
    };
  }

  public static Comparator<int[]> comparatorIntArray(final int k) {
    return new Comparator<int[]>() {
      @Override public int compare(int[] a1, int[] a2) {
        double diff = a1[k] - a2[k];
        if (diff == 0.0) return 0;
        return diff > 0 ? 1: -1;
      }
    };
  }

  public static double[] doubleListToDoubleArray(List<Double> values) {
    if (values == null)
      return null;
    double[] ret = new double[values.size()];
    for (int i = 0; i < ret.length; i++)
      ret[i] = values.get(i);
    return ret;
  }

  public static float[] doubleToFloatArray(double[] arr) {
    if (arr == null)
      return null;
    int n = arr.length;
    float[] ret = new float[n];
    for (int i = 0; i < n; i++) {
      ret[i] = (float) arr[i];
    }
    return ret;
  }

  public static double[] floatToDoubleArray(float[] arr) {
    if (arr == null)
      return null;
    int n = arr.length;
    double[] ret = new double[n];
    for (int i = 0; i < n; i++) {
      ret[i] = arr[i];
    }
    return ret;
  }

  public static int[] getNaturalArray(int size) {
    int[] res = new int[size];
    for (int i = 0; i < size; i++)
      res[i] = i;
    return res;
  }

  public static boolean inBoundary(int[][] matrix, int row, int col) {
    int m = matrix.length;
    int n = matrix[0].length;
    if (row >= m || row < 0 || col >=n || col < 0) {
      return false;
    }
    return true;
  }

  public static double[] intToDoubleArray(int[] arr) {
    if (arr == null)
      return null;
    int n = arr.length;
    double[] ret = new double[n];
    for (int i = 0; i < n; i++) {
      ret[i] = arr[i];
    }
    return ret;
  }

  public static int[] integerListToIntArray(List<Integer> values) {
    if (values == null)
      return null;
    int[] ret = new int[values.size()];
    for (int i = 0; i < ret.length; i++)
      ret[i] = values.get(i);
    return ret;
  }

  public static double[] maxAndMin(double[] values) {
    if (values.length == 0)
      return null;
    double max = values[0], min = values[0];
    for (int i = 1; i < values.length; i++) {
      max = Math.max(max, values[i]);
      min = Math.min(min, values[i]);
    }
    return new double[] {min, max};
  }

  public static double max(double[] values) {
    if (values.length == 0)
      return Integer.MIN_VALUE;
    double max = values[0];
    for (int i = 1; i < values.length; i++) {
      max = Math.max(max, values[i]);
    }
    return max;
  }

  public static double min(double[] values) {
    if (values.length == 0)
      return Integer.MIN_VALUE;
    double min = values[0];
    for (int i = 1; i < values.length; i++) {
      min = Math.min(min, values[i]);
    }
    return min;
  }

  /**
   * Given array a and an index, partial sort a such that all numbers
   * smaller than a[pivotIdx] is on the left and all larger numbers on right.
   * return the index of a[pivotIdx]
   */
  public static int partition(int[] a, int l, int r, int pivotIdx) {
    int pivot = a[pivotIdx];
    for (int i = l; i <= r; ) {
      if (a[i] == pivot) {
        i++;
        continue;
      }
      if (a[i] < pivot) {
        swap(a, i++, l++);
        continue;
      }
      swap(a, i, r--);
    }
    System.out.println(Arrays.toString(a) + " " + pivot + " " + (r - l + 1));
    return r;
  }

  public static void print(int[][] board) {
    print(board, 0, board.length - 1, 0, board[0].length - 1, 5);
  }

  public static void print(int[][] board, int sRow, int eRow, int sCol, int eCol, int printWidth) {
    assert  (sRow >= 0 && sRow <= board.length && eRow >= 0 && eRow <= board.length && sCol >= 0
        && sCol <= board[0].length && eCol >= 0 && eCol <= board[0].length);
    for (int i = sRow; i <= eRow; i++) {
      for (int j = sCol; j <= Math.min(eCol, board[i].length - 1); j++) {
        System.out.print(String.format("%" + printWidth + "d", board[i][j]));
      }
      System.out.println();
    }
    System.out.println();
  }

  public static int[] negateSign(int[] a) {
    for (int i = 0; i < a.length; i++) {
      a[i] *= -1;
    }
    return a;
  }

  public static char[] reverse(char[] array) {
    return reverse(array, 0, array.length - 1);
  }

  public static char[] reverse(char[] array, int l, int r) {
    while (l < r) {
      swap(array, l++, r--);
    }
    return array;
  }

  public static int[] reverse(int[] array) {
    return reverse(array, 0, array.length - 1);
  }

  public static int[] reverse(int[] array, int l, int r) {
    while (l < r) {
      swap(array, l++, r--);
    }
    return array;
  }

  public static int sum(int[] arr) {
    int sum = 0;
    for (int n : arr) {
      sum += n;
    }
    return sum;
  }

  public static void swap(char[] array, int i, int j) {
    if (i == j)
      return;
    char swap;
    swap = array[i];
    array[i] = array[j];
    array[j] = swap;
  }

  public static void swap(int[] a, int i, int j) {
    int n = a.length;
    if (i == j || i < 0 || j < 0 || i >= n || j >= n)
      return;
    a[i] = a[i] ^ a[j];
    a[j] = a[i] ^ a[j];
    a[i] = a[i] ^ a[j];
  }

  public static int[] toValue(int[] arr, int[] indexs) {
    int[] values = new int[indexs.length];
    for (int i = 0; i < indexs.length; i++) {
      values[i] = arr[indexs[i]];
    }
    return  values;
  }
}
