package com.shuoma.alg.string;

import java.util.Arrays;
import java.util.Scanner;

public class StringMatching {
	public static void main(String[] args) {
		new StringMatching().main();
	}

	void main() {
		//TODO Aho¨CCorasick algorithm
		
		/*Scanner sc = new Scanner(System.in);
		String s = sc.nextLine();
		System.out.println(reverseString(s));
		System.out.println(reverseString1(s));
		System.out.println(reverseWord(s));
		System.out.println(fib(10));
		int a = s.charAt(0);
		System.out.println(a);
		sc.close();*/
		
		
		//print next('ababc')
		//match('abbba', 'bba', 'bf')
		String mode="sunday";
		int idx=match("ababcababababcabab", "baba", "kmp");
		System.out.println(idx);
		//calNext("participate in parachute");
		//calNext("ababcababababcabab",mode);

	}
	
	// calculate the next array in KMP/sunday algorithm
	public int[] calNext(String t, String method){
		int[] next=null;
		switch (method) {
		case "kmp":
			next=new int[t.length()];
			next[0]=-1;
			int i=0, j=-1;
			while(i<t.length()-1){
				/*System.out.println(i+" "+t.charAt(i)+" "+j+" "+t.charAt(j)
						+" "+t.substring(0,i+1)+" "+Arrays.toString(Arrays.copyOfRange(next, 0, i+1)) );
				*/
				if(j==-1 || t.charAt(i)==t.charAt(j)){
					i+=1;
					j+=1;
					next[i]=j;
				}
				else
					j=next[j];
			}
			break;
		case "sunday":
			next=new int[26];
			for(i=0;i<next.length;i++) next[i]=t.length();
			for(i=0;i<t.length();i++){
				next[t.charAt(i)-'a']=t.length()-i;
			}
		default:
			break;
		}

			
		return next;
	}

	public int match(String s, String t, String method){
		//preprocessing before match
		int[] next=null;
		if( method.equals("kmp") || method.equals("sunday")) next=calNext(t,method);

		int i,j;
		i=j=0;
		while(i+j < s.length()){ 
			if( s.charAt(i+j)==t.charAt(j)){
				if (j == t.length()-1){
					System.out.println(
							s.substring(0,i)+" "+s.substring(i,i+t.length())+" "+s.substring(i+t.length()) );
					return i;
				}
				j+=1;
			}
			else{
				if( method.equals( "kmp")){  //kmp
					i+=j-next[j];
					j=Math.max(next[j],0);
				}
				else{  
				  if(method.equals("bf")){//brutal force
					i+=1; //when t does not have a "pattern", i can increase more aggressively, i.e. max(1,j)
					j=0;
				  }
				  else{ //sunday
					i+=next[s.charAt(Math.min(i+t.length(),s.length()-1))-'a'];
					j=0;
				  }
				}
			}
		}
		System.out.println(s+" failed to match "+t);
		return -1;
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
