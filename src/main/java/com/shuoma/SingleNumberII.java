package com.shuoma;
public class SingleNumberII {
	public int singleNumber(int[] A) {
		int ones = 0, twos = 0;
		int nextOnes, nextTwos;
		for (int a : A) {
			nextOnes = (~a & ones) | (a & ~ones & ~twos);
			nextTwos = (~a & twos) | (a & ones);
			ones = nextOnes;
			twos = nextTwos;
		}
		return ones;
	}
}