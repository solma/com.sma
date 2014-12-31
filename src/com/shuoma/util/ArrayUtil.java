package com.shuoma.util;

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
    if (values.length < 1) return null;
    if (noOfBins < 2) return new double[] {1};

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
    if (list == null || list.size() == 0) return Double.MAX_VALUE;
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
    double prob =
        Math.pow(Math.E, -Math.pow(value - mean, 2) / 2 / Math.pow(std, 2))
            / (Math.sqrt(Math.PI * 2) * std);
    if (prob > 1) System.out.println(mean + " " + std + " " + value);
    return prob;
  }

  public static double calculateVariance(List<Double> list, double mean) {
    if (mean == Double.MAX_VALUE) return mean;
    if (list.size() == 1) return 0;
    double sum = 0;
    for (double num : list) {
      sum += Math.pow(num - mean, 2);
    }
    return sum / (list.size() - 1);
  }

  public static int[] concat(int[] a, int[] b) {
    if (a == null) return b;
    if (b == null) return a;
    int[] c = new int[a.length + b.length];
    System.arraycopy(a, 0, c, 0, a.length);
    System.arraycopy(b, 0, c, a.length, b.length);
    return c;
  }

  public static double[] doubleListToDoubleArray(List<Double> values) {
    if (values == null) return null;
    double[] ret = new double[values.size()];
    for (int i = 0; i < ret.length; i++)
      ret[i] = values.get(i);
    return ret;
  }

  public static float[] doubleToFloatArray(double[] arr) {
    if (arr == null) return null;
    int n = arr.length;
    float[] ret = new float[n];
    for (int i = 0; i < n; i++) {
      ret[i] = (float) arr[i];
    }
    return ret;
  }

  public static boolean equals(int[] a, int[] b) {
    if (a == null && b == null) return true;
    if (a == null || b == null) return false;
    int la = a.length, lb = b.length;
    if (la != lb) return false;
    for (int i = 0; i < la; i++) {
      if (a[i] != b[i]) return false;
    }
    return true;
  }

  public static double[] floatToDoubleArray(float[] arr) {
    if (arr == null) return null;
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

  public static double[] intToDoubleArray(int[] arr) {
    if (arr == null) return null;
    int n = arr.length;
    double[] ret = new double[n];
    for (int i = 0; i < n; i++) {
      ret[i] = arr[i];
    }
    return ret;
  }

  public static int[] integerListToIntArray(List<Integer> values) {
    if (values == null) return null;
    int[] ret = new int[values.size()];
    for (int i = 0; i < ret.length; i++)
      ret[i] = values.get(i);
    return ret;
  }

  public static double[] maxAndMin(double[] values) {
    if (values.length == 0) return null;
    double max = values[0], min = values[0];
    for (int i = 1; i < values.length; i++) {
      max = Math.max(max, values[i]);
      min = Math.min(min, values[i]);
    }
    return new double[] {min, max};
  }

  public static double max(double[] values) {
    if (values.length == 0) return Integer.MIN_VALUE;
    double max = values[0];
    for (int i = 1; i < values.length; i++) {
      max = Math.max(max, values[i]);
    }
    return max;
  }

  public static double min(double[] values) {
    if (values.length == 0) return Integer.MIN_VALUE;
    double min = values[0];
    for (int i = 1; i < values.length; i++) {
      min = Math.min(min, values[i]);
    }
    return min;
  }

  public static int[] negateSign(int[] a) {
    for (int i = 0; i < a.length; i++) {
      a[i] *= -1;
    }
    return a;
  }

  public static void reverse(char[] array) {
    int l = 0, r = array.length - 1;
    while (l < r) {
      swap(array, l++, r--);
    }
  }

  public static int[] reverse(int[] array) {
    int l = 0, r = array.length - 1;
    while (l < r) {
      swap(array, l++, r--);
    }
    return array;
  }

  public static void swap(char[] array, int i, int j) {
    if (i == j) return;
    char swap;
    swap = array[i];
    array[i] = array[j];
    array[j] = swap;
  }

  public static void swap(int[] a, int i, int j) {
    int n = a.length;
    if (i == j || i < 0 || j < 0 || i >= n || j >= n) return;
    int tmp = a[i];
    a[i] = a[j];
    a[j] = tmp;
  }
}
