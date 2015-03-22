package com.shuoma;
/**
 * Definition for an interval.
 * 
 */
import java.util.*;
public class MergeIntervals {
    public class Interval {
     int start;
      int end;
      Interval() { start = 0; end = 0; }
      Interval(int s, int e) { start = s; end = e; }
      public String toString(){return "("+start+","+end+")";}
    }
    
    
    public static void main(String[] args){
        new MergeIntervals().main();

    }
    
    public void main(){
            ArrayList<Interval> intervals=new ArrayList<Interval>();
        intervals.add(new Interval(1,4));
        intervals.add(new Interval(1,4));
        System.out.println(  merge(intervals) );

    }
    
    //second pass
    public class CustomComparator implements Comparator<Interval> {
        public int compare(Interval o1, Interval o2) {
            return o1.start-o2.start;
        }
    }   
    
    public ArrayList<Interval> merge(ArrayList<Interval> intervals) {
        int n=intervals.size();
        if(n<2) return intervals;
        Collections.sort(intervals, new CustomComparator());
        
        Interval openInt=intervals.get(0);
        ArrayList<Interval> res=new ArrayList<Interval>();
                
        for(int i=1;i<n;i++){
            Interval cur=intervals.get(i);
            if(openInt.end<cur.start){
                res.add(openInt);
                openInt=cur;
            }else{
                if(openInt.end<cur.end){
                    openInt.end=cur.end;
                }
            }
        }
        res.add(openInt);
        
        return res;
    }

    //first pass
    public ArrayList<Interval> mergeFirstPass(ArrayList<Interval> intervals) {
        int n=intervals.size();
        if(n<2) return intervals;
        Collections.sort(intervals, new CustomComparator());
        
        Interval openInt=intervals.get(0);
        ArrayList<Interval> res=new ArrayList<Interval>();
                
        for(int i=1;i<n;i++){
            Interval cur=intervals.get(i);
            if(openInt.end<cur.start){
                res.add(openInt);
                openInt=cur;
            }else{
                if(openInt.end<cur.end){
                    openInt.end=cur.end;
                }
            }
        }
        res.add(openInt);
        
        return res;
    }
}

