package com.shuoma.alg.streaming;

import com.shuoma.util.RandomUtil;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

/**
 * Given a stream, compute the stats, e.g. average, min of a sliding window.
 */
public class SlidingWindowStats {
    public static void main(String[] args) {
        int length = 100, K = 3;
        int[] ary;

        ary = RandomUtil.genRandomArray(length, 10, true, false);
        ary = new int[] {1, 4, 8, 9, 10, 5, 5, 10, 8, 5};

        SlidingWindowStats ins = new SlidingWindowStats(K, ary);

        System.out.println(Arrays.toString(ary));
        //    System.out.println("\t" + Arrays.toString(ins.average()));
        System.out.println("\t" + Arrays.toString(ins.max()));
    }

    /**
     * Window size.
     */
    int K;
    /**
     * Array that simulates a stream.
     */
    int[] stream;

    /**
     * @param k      Window size.
     * @param stream Array that simulates a stream.
     */
    public SlidingWindowStats(int k, int[] stream) {
        this.K = k;
        this.stream = stream;
    }

    /**
     * Get the average of the sliding window.
     */
    public double[] average() {
        double sum = 0;
        double[] avg = new double[stream.length - K + 1];
        for (int i = 0; i < stream.length; i++) {
            sum += stream[i];
            if (i >= K)
                sum -= stream[i - K];

            // compute avg
            if (i >= K - 1) {
                avg[i - K + 1] = (int) (sum / K * 100) / 100.0;
            }
        }
        return avg;
    }

    /**
     * Get the max of the sliding window.
     */
    public int[] max() {
        int[] max = new int[stream.length - K + 1];
        Deque<Integer> queue = new ArrayDeque<>();

    /* first window */
        for (int i = 0; i < K; i++) {
            while (!queue.isEmpty() && stream[i] >= stream[queue.peekLast()])
                queue.pollLast();
            queue.add(i);
        }
        System.out.println(queue);

    /* following windows */
        for (int i = K; i < stream.length; i++) {
            max[i - K] = stream[queue.peekFirst()];
            while (!queue.isEmpty() && stream[i] >= stream[queue.peekLast()])
                queue.pollLast();
            while (!queue.isEmpty() && queue.peekFirst() <= i - K)
                queue.pollFirst();
            queue.add(i);
        }
        max[stream.length - K] = stream[queue.peekFirst()];
        return max;
    }
}
