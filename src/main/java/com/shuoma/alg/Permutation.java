package com.shuoma.alg;

import static com.google.common.base.Strings.isNullOrEmpty;
import static com.shuoma.annotation.Tag.Algorithm.Recursion;
import static com.shuoma.annotation.Tag.Reference.LeetCode;

import com.shuoma.annotation.Tag;
import com.shuoma.util.ArrayUtil;
import com.shuoma.util.MathUtil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Tag(algs = Recursion, references = LeetCode)
public class Permutation {

  public static void main(String[] args) {
    // String curPermutation = "dcba";
    // System.out.println(nextPermutation(curPermutation));
    // System.out.println(prevPermutation(curPermutation));
    //System.out.println(iThPermutation("abcd", 10));
    System.out.println(nearestPermutation("1234", 1784));
  }

  public static List<String> allPermutations(String input) {
    return allPermutations(input, new StringBuilder());
  }

  static List<String> allPermutations(String input, StringBuilder perm) {
    List<String> ret = new ArrayList<>();
    for (int i = 0; i < input.length(); i++) {
      if (perm.toString().contains(input.substring(i, i + 1))) continue;
      perm.append(input.charAt(i));
      if (perm.length() == input.length()) ret.add(perm.toString());
      for (String s : allPermutations(input, perm))
        ret.add(s);
      perm.deleteCharAt(perm.length() - 1);
    }
    return ret;
  }

  public static List<String> firstKPermutationsByRecursion(String input, int K) {
    return firstKPermutationsByRecursion(input, new StringBuilder(), K);
  }

  static List<String> firstKPermutationsByRecursion(String input, StringBuilder perm, int K) {
    // first K permutations in alphabetical order
    ArrayList<String> ret = new ArrayList<>();
    for (int i = 0; i < input.length(); i++) {
      if (perm.toString().contains(input.substring(i, i + 1))) continue;
      perm.append(input.charAt(i));
      if (perm.length() == input.length()) ret.add(perm.toString());
      for (String s : firstKPermutationsByRecursion(input, perm, K)) {
        if (ret.size() >= K) { break; }
        ret.add(s);
      }
      perm.deleteCharAt(perm.length() - 1);
    }
    return ret;
  }

  // ith factorial numbering system (0-based counting)
  public static String iThPermutation(String input, long ith) {
    int n = input.length();
    Map<Integer, Character> map = new HashMap<>();
    for (int i = 0; i < n; i++) {
      map.put(i + 1, input.charAt(i));
    }

    int[] inversion = new int[n];
    for (int divisor = 1; ith > 0; divisor++) {
      inversion[divisor - 1] = (int) ith % divisor;
      ith /= divisor;
    }
    //System.out.println("inversion = " + Arrays.toString(inversion));
    int[] res = recover(ArrayUtil.reverse(inversion));
    //System.out.println("res = " + Arrays.toString(res));

    StringBuilder sb = new StringBuilder();
    for (int i : res) sb.append(map.get(i));
    return sb.toString();
  }

  // find the permutation of intervals that is closes to target
  public static long nearestPermutation(String input, int target) {
    char[] nums = input.toCharArray();
    Arrays.sort(nums);

    int smallest = Integer.parseInt(new String(nums));
    if (target <= smallest) return smallest;
    int largest = Integer.parseInt(new String(ArrayUtil.reverse(nums)));
    if (target >= largest) return largest;

    // between smallest and largest, binary search
    long l = 0, r = MathUtil.factorial(nums.length) - 1;
    long curPermutation, closestPermutation = smallest, diff = target;
    while (l <= r) {
      long m = l + ((r - l) >> 1);
      curPermutation = Integer.parseInt(iThPermutation(input, m));
//      System.out.println("l:" + l + " m:" + m + " r:" + r);
//      System.out.println(" permutation:" + curPermutation);
      if (curPermutation == target) {
        return curPermutation;
      } else {
        if (curPermutation < target) l = m + 1;
        else r = m - 1;
        long curDiff = Math.abs(curPermutation - target);
        if (curDiff < diff) {
          diff = curDiff;
          closestPermutation = curPermutation;
        }
      }
      if (m == l && m == r) break;
    }
    return closestPermutation;
  }

  public static String nextPermutation(String curPermutation) {
    char[] cur = curPermutation.toCharArray();

    // the first increasing pair from backward
    int i;
    for (i = cur.length - 1; i > 0; i--) {
      if (cur[i - 1] < cur[i]) break;
    }
    if (i == 0) {
      ArrayUtil.reverse(cur);
      return new String(cur);
    }
    i -= 1;
    // the first one larger than cur[i] from backward
    int j;
    for (j = cur.length - 1; j > i; j--) {
      if (cur[j] > cur[i]) break;
    }
    ArrayUtil.swap(cur, i, j);
    // reverse the sequence after i
    i += 1;
    while (i < j) {
      ArrayUtil.swap(cur, i++, j--);
    }
    return new String(cur);
  }

  public static String prevPermutation(String curPermutation) {
    char[] cur = curPermutation.toCharArray();

    // the first decreasing pair from backward
    int i;
    for (i = cur.length - 1; i > 0; i--) {
      if (cur[i - 1] > cur[i]) break;
    }
    if (i == 0) {
      ArrayUtil.reverse(cur);
      return new String(cur);
    }
    i -= 1;
    // the first one smaller than cur[i] from backward
    int j;
    for (j = cur.length - 1; j > i; j--) {
      if (cur[j] < cur[i]) break;
    }
    ArrayUtil.swap(cur, i, j);
    // reverse the sequence after i
    i += 1;
    while (i < j) {
      ArrayUtil.swap(cur, i++, j--);
    }
    return new String(cur);
  }


  // codes for restore inversion to array
  public static class BSTNode {
    int value;
    int size;
    BSTNode left, right;

    public BSTNode(int value) {
      this.value = value;
      this.size = 1;
    }
  }

  static BSTNode create(int N) {
    return create(1, N);
  }

  static BSTNode create(int low, int high) {
    if (low > high) return null;
    int mid = low + (high - low) / 2;
    BSTNode node = new BSTNode(mid);
    node.size = high - low + 1;
    node.left = create(low, mid - 1);
    node.right = create(mid + 1, high);
    return node;
  }

  static BSTNode select(BSTNode node, int K) {
    if (node == null) return null;
    int leftSize = node.left == null ? 0 : node.left.size;
    if (K == leftSize + 1)
      return node;
    else if (K <= leftSize)
      return select(node.left, K);
    else
      return select(node.right, K - leftSize - 1);
  }

  private static BSTNode delete(BSTNode node, BSTNode target) {
    if (node == null) return null;
    if (target.value < node.value)
      node.left = delete(node.left, target);
    else if (target.value > node.value)
      node.right = delete(node.right, target);
    else {
      if (node.left == null) return node.right;
      if (node.right == null) return node.left;
      BSTNode successor = node.right;
      BSTNode parent = node;
      while (successor.left != null) {
        parent = successor;
        successor = successor.left;
      }
      node.value = successor.value;
      if (parent.left == successor)
        parent.left = successor.right;
      else
        parent.right = successor.right;
    }
    node.size--;
    return node;
  }

  public static int[] recover(int[] a) {
    BSTNode root = create(a.length);
    int[] b = new int[a.length];
    for (int i = 0; i < a.length; i++) {
      BSTNode node = select(root, a[i] + 1);
      b[i] = node.value;
      root = delete(root, node);
    }
    return b;
  }

  static List<String> myInuitiveAlgrithm(String input) {
    List<String> res = new ArrayList<>();
    if (isNullOrEmpty(input)) {
      return res;
    }
    char[] array = input.toCharArray();
    String[] copy = new String[1];
    copy[0] = String.valueOf(array[0]);
    for (int i = 1; i < array.length; i++) {
      for (int j = 0; j < copy.length; j++) {
        for (int idx = 0; idx <= i; idx++)
          res.add(copy[j].substring(0, idx) + String.valueOf(array[i]) + copy[j].substring(idx, i));
        res.remove(copy[j]);
      }
      copy = res.toArray(new String[1]);
    }
    return res;
  }
}
