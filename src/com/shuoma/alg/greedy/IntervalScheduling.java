package com.shuoma.alg.greedy;

import java.util.ArrayList;
import java.util.Collections;

import com.shuoma.alg.dp.WeightedIntervalScheduling;
import com.shuoma.ds.Interval;

public class IntervalScheduling {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		ArrayList<Interval> list=WeightedIntervalScheduling.generateRandomListOfIntervals(false);
		schedule(list);
		
	}
	
	public static ArrayList<Interval> schedule(ArrayList<Interval> input){
		ArrayList<Interval> ret=new ArrayList<Interval>();
		int n=input.size();
		if(n<1) return ret;
		if(n<2) return input;
		Collections.sort(input);
		System.out.println("input: \n"+ input);
		Interval prev=input.get(0);
		ret.add(prev);
		for(int i=1;i<n;i++){
			Interval cur=input.get(i);
			if(cur.start>=prev.end){
				ret.add(cur);
				prev=cur;
			}
		}
		System.out.println("ret: \n"+ ret);
		return ret;
	}
}
