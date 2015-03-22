package com.shuoma;
import java.util.*;

public class GasStation {
	public int canCompleteCircuit(int[] gas, int[] cost) {
		int min = 0, total = 0, len = gas.length, index = -1;
		for (int i = 0; i < len; i++) {
			total += gas[i] - cost[i];
			if (total < min) {
				index = i;
				min = total;
			}
		}
		return total >= 0 ? index + 1 : -1;
	}

	// TLE
	public int canCompleteCircuitTLE(int[] gas, int[] cost) {
		int n = gas.length;

		// find the maximum difference between cost[i] and gas[i]
		int max = 0;
		ArrayList<Integer> candidates = new ArrayList<Integer>();

		int cnt = 0;
		for (int i = 0; i < n; i++) {
			if (gas[i] - cost[i] >= max) {
				if (gas[i] - cost[i] > max) {
					max = gas[i] - cost[i];
					candidates.clear();
				}
				candidates.add(i);
			}
		}
		// System.out.println(candidates);

		int gasCnt = 0;
		for (Integer start : candidates) {
			int j = start;
			while (true) {
				gasCnt += gas[j];
				if (gasCnt >= cost[j]) {
					gasCnt -= cost[j];
					j = (j + 1) % n;
				} else {
					break;
				}
				if (j == start)
					return j;
			}
		}
		return -1;
	}

	public static void main(String[] args) {
		System.out.println(new GasStation().canCompleteCircuit(new int[] { 67,
				68, 69, 70, 71, 72, 73, 74, 75, 76, 77, 78, 79, 80, 81, 82, 83,
				84, 85, 86, 87, 88, 89, 90, 91, 92, 93, 94, 95, 96, 97, 98, 99,
				100, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17,
				18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31, 32, 33,
				34, 35, 36, 37, 38, 39, 40, 41, 42, 43, 44, 45, 46, 47, 48, 49,
				50, 51, 52, 53, 54, 55, 56, 57, 58, 59, 60, 61, 62, 63, 64, 65,
				66 }, 
				new int[] { 27, 28, 29, 30, 31, 32, 33, 34, 35, 36, 37,
				38, 39, 40, 41, 42, 43, 44, 45, 46, 47, 48, 49, 50, 51, 52, 53,
				54, 55, 56, 57, 58, 59, 60, 61, 62, 63, 64, 65, 66, 67, 68, 69,
				70, 71, 72, 73, 74, 75, 76, 77, 78, 79, 80, 81, 82, 83, 84, 85,
				86, 87, 88, 89, 90, 91, 92, 93, 94, 95, 96, 97, 98, 99, 100, 1,
				2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19,
				20, 21, 22, 23, 24, 25, 26 }));
	}
}