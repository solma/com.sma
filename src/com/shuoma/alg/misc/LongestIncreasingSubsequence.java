// source: 编程之美
// http://blog.csdn.net/liangbopirates/article/details/9421399
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Random;

public class LongestIncreasingSubsequence {
  public static void main(String[] args) {
    new LongestIncreasingSubsequence().main();
  }

  private Random rand = new Random();

  int[] genRanArray() {
    int n = rand.nextInt(10) + 8;
    int[] ret = new int[n];
    for (int i = 0; i < n; i++) {
      ret[i] = rand.nextInt(100) + 1;
    }
    return ret;
  }

  public void main() {
    for (int i = 0; i < 5; i++) {
      int[] num;
      num = genRanArray();
      // {77, 97, 30, 63, 89, 75, 99, 34, 43, 80, 46, 37, 64, 31, 23, 12}
      // num=new int[]{77, 97, 30, 63, 89, 75, 99, 34, 43, 80, 46, 37, 64, 31, 23, 12};
      System.out.println("array=" + Arrays.toString(num));
      int[] a = queueBased(num);
      int[] b = dp(num);
      int[] c = dpWithBinarySearch(num);
      if (a == null || b == null || a.length != b.length || a.length != c.length) {
        System.out.println(Arrays.toString(num));
        System.out.println(a.length + "  " + Arrays.toString(a));
        System.out.println(b.length + "  " + Arrays.toString(b));
        System.out.println(c.length + " " + Arrays.toString(c));
      }
    }
  }

  // this can only be used to get the length but not the actuall sequence
  int[] queueBased(int[] num) {
    int n = num.length;
    if (n == 0) return null;
    ArrayList<Integer> queue = new ArrayList<Integer>();
    for (int i = 0; i < n; i++) {
      if (queue.isEmpty() || queue.get(queue.size() - 1) < num[i])
        queue.add(num[i]);
      else {
        int L = -1, R = queue.size();
        // replace the first one larger or equal than num[i]
        while (L + 1 != R) {
          int M = L + (R - L) / 2;
          if (queue.get(M) < num[i])
            L = M;
          else
            R = M;
        }
        // replace Rth element;
        queue.remove(R);
        queue.add(R, num[i]);
      }
    }
    int[] ret = new int[queue.size()];
    for (int i = 0; i < ret.length; i++)
      ret[i] = queue.get(i);
    return ret;
  }


  class LIS {
    int len;
    int min;
    ArrayList<ArrayList<Integer>> paths;

    public LIS(int length, int minEndValue) {
      this.len = length;
      this.min = minEndValue;
      paths = new ArrayList<ArrayList<Integer>>();
    }

    public void add(ArrayList<Integer> path) {
      paths.add(path);
    }


  }


  int[] dpWithBinarySearch(int[] nums) {

    HashMap<Integer, LIS> lisOfAllLength = new HashMap<Integer, LIS>();
    LIS zeroLIS = new LIS(0, 0);
    zeroLIS.add(new ArrayList<Integer>());
    lisOfAllLength.put(0, zeroLIS);

    int maxLis = 0;
    for (int i = 0; i < nums.length; i++) {
      int l = -1, r = maxLis + 1;
      while (l + 1 != r) {
        int m = l + (r - l) / 2;
        if (lisOfAllLength.get(m).min >= nums[i])
          r = m;
        else
          l = m;
      }

      if (l >= 0 && lisOfAllLength.get(l).min < nums[i]) {
        int curLen = l + 1;
        if (maxLis < curLen) {
          maxLis = curLen;
          lisOfAllLength.put(curLen, new LIS(curLen, nums[i]));
        }
        LIS curLIS = lisOfAllLength.get(curLen);
        for (ArrayList<Integer> path : lisOfAllLength.get(l).paths) {
          if (path.size() == 0 || path.get(path.size() - 1) < nums[i]) {
            ArrayList<Integer> seq = new ArrayList<Integer>(path);
            seq.add(nums[i]);
            curLIS.add(seq);
          }
        }
        curLIS.min = nums[i];
      }
      System.out.println("i=" + i + " l=" + l + ", maxLis=" + maxLis);
      for (Integer len : lisOfAllLength.keySet()) {
        System.out.println("len=" + len + " :" + "  min=" + lisOfAllLength.get(len).min + "   lis="
            + lisOfAllLength.get(len).paths);
      }
    }

    int[] ret = new int[maxLis];
    for (int i = 1; i <= maxLis; i++) {
      ret[i - 1] = lisOfAllLength.get(i).min;
    }

    return ret;
  }

  int[] dp(int[] nums) {
    // as we discussed in slides, in order to use DP in this example, we need
    // 1. a size array to keep track of longest LIS ending with current position
    // 2. an accordingly string array to keep track of the path for printing out
    String[] paths = new String[nums.length];
    int[] sizes = new int[nums.length];

    // firstly we assign the initial values to each path/size, by setting size
    // to 1 and path equals the value (that means initially each path starting/ending
    // with its current position
    for (int i = 0; i < nums.length; i++) {
      sizes[i] = 1;
      paths[i] = nums[i] + " ";// we add a space for separation
    }

    // before we start the loop,
    // we define a support variable called maxLength to keep track
    int maxLength = 1;// notice it is 1, but it really does not matter if sets to 0

    for (int i = 1; i < nums.length; i++) {
      // notice we start from 2nd postion, 1st position is no point
      for (int j = 0; j < i; j++) { // the inner loop is to check all previous items!
        // now it's the key step,
        // when do we append our current index to the previous subsequence?
        // it has to meet 2 requirements,
        // current>previous ending and size is increasing!
        if (nums[i] > nums[j] && sizes[i] < sizes[j] + 1) { // notice plus one!!
                                                            // if so, we need update sizes and path
          sizes[i] = sizes[j] + 1;
          paths[i] = paths[j] + nums[i] + " ";// append current values to end!
          // also update the maxLength if necessary
        }
      }
      if (maxLength < sizes[i]) maxLength = sizes[i];
    }
    for (int i = 1; i < nums.length; i++) {
      if (sizes[i] == maxLength) {
        String[] fields = paths[i].split(" ");
        int[] ret = new int[fields.length];
        for (int j = 0; j < ret.length; j++)
          ret[j] = Integer.parseInt(fields[j]);
        return ret;
      }
    }
    return null;
    // return maxLength;
  }
}
