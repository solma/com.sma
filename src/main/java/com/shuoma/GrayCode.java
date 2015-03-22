package com.shuoma;
import java.util.*;
import java.lang.Math;
public class GrayCode {
	public static void main(String[] args){
		GrayCode ins=new GrayCode();
		for(int i: ins.grayCode(3))
			System.out.println(i);
	}	

    public ArrayList<Integer> grayCode(int n) {
		ArrayList<Integer> codes;
		if(n<=2){
			codes=new ArrayList<Integer>();
			if(n>=0) codes.add(0);
			if(n>=1) codes.add(1);
			if(n==2){
				codes.add(3);
				codes.add(2);
			}
			return codes;
		}

		codes=grayCode(n-1);
		int i;
		for(i=codes.size()-1;i>=0;i--){
			codes.add(codes.get(i)+(int)Math.pow(2,n-1));
		}
		return codes;
    }
	
}