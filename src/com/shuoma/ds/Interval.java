package com.shuoma.ds;

import java.util.ArrayList;

public class Interval implements Comparable<Interval>{
	public double start;
	public double end;
	public double weight;
	
	public Interval(double start, double end){
		this.start=start;
		this.end=end;
	}
	
	public Interval(double start, double end, double weight){
		this(start, end);
		this.weight=weight;
	}
	
	@Override
	public int compareTo(Interval o) {
		double endDiff=end-o.end;
		double startDiff=start-o.start;
		if(endDiff>0) return 1;
		else{
			if(endDiff<0) return -1;
			else{
				if(startDiff>0) return 1;
				else {
					if(startDiff<0) return -1;
					return 0;
				}
			}
		}
	}
	
	public int bisect(ArrayList<Interval> list){
		int l=-1, r=list.size();
		while(l+1!=r){
			int m=l+(r-l)/2;
			if(list.get(m).end>start) r=m;
			else l=m;
		}
		return l;
	}
	
	public String toString(){
		return "("+start+"~"+end+" : "+weight+")";
	}
}