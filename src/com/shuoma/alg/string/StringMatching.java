package com.shuoma.alg.string;

import java.util.Scanner;

public class StringMatching {
	public static void main(String[] args) {
		new StringMatching().main();
	}

	void main() {
		Scanner sc = new Scanner(System.in);
		String s = sc.nextLine();
		System.out.println(reverseString(s));
		System.out.println(reverseString1(s));
		System.out.println(reverseWord(s));
		System.out.println(fib(10));
		int a = s.charAt(0);
		System.out.println(a);
		sc.close();
	}

	int fib(int n) {

		int prev = 1, prevv = 0, i = 0, ret = 0;
		while (i++ < n - 1) {
			ret = prev + prevv;
			prevv = prev;
			prev = ret;

		}
		return ret;

		// return n<=1?n:fib(n-1)+fib(n-2);
	}

	String reverseString1(String s) {
		char[] chr = s.toCharArray();
		int last = chr.length - 1;
		for (int i = 0; i < chr.length / 2; i++) {
			char c = chr[i];
			chr[i] = chr[last - i];
			chr[last - i] = c;
		}
		return new String(chr);
	}

	String reverseString(String s) {
		StringBuilder sb = new StringBuilder();
		for (int i = s.length() - 1; i >= 0; i--)
			sb.append(s.charAt(i));
		return new String(sb);
	}

	String reverseWord(String s) {
		StringBuilder sb = new StringBuilder();
		String[] words = s.split(" ");
		for (int i = words.length - 1; i >= 0; i--)
			sb.append(words[i] + " ");
		return new String(sb);
	}
}
