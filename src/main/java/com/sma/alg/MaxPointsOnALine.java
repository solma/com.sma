package com.sma.alg;


import static com.sma.annotation.Tag.DataStructure.Hash;
import static com.sma.annotation.Tag.Difficulty.D2;
import static com.sma.annotation.Tag.Reference.LeetCode;

import com.sma.annotation.Tag;

import java.util.*;

class Point {
  int x;
  int y;

  Point(int a, int b) {
    x = a;
    y = b;
  }

  public String toString() {
    return x + "," + y;
  }
}

@Tag(dl = D2, dss = Hash, references = LeetCode)
public class MaxPointsOnALine {
  public static void main(String[] args) {
    new MaxPointsOnALine().main();

  }

  public void main() {
    //int[][] points={{0,-12},{5,2},{2,5},{0,-5},{1,5},{2,-2},{5,-4},{3,4},{-2,4},{-1,4},{0,-5},{0,-8},{-2,-1},{0,-11},{0,-9}};
    int[][] points = {{2, 3}, {3, 3}, {-5, 3}};
    Point[] pts = new Point[points.length];
    for (int i = 0; i < points.length; i++) {
      pts[i] = new Point(points[i][0], points[i][1]);
    }
    System.out.println(maxPoints(pts));
  }

  public int maxPoints(Point[] points) {
    HashMap<Double, Integer> slopeCnt;
    int n = points.length;
    if (n < 2)
      return n;
    int maxCnt = 2;
    for (int i = 0; i < n; i++) {
      slopeCnt = new HashMap<>();
      int dup = 1;
      for (int j = i + 1; j < n; j++) {
        double slope;
        if (points[j].x == points[i].x) {
          if (points[j].y == points[i].y) {
            dup++;
            continue;
          }
          slope = Double.MAX_VALUE;
        } else {
          slope = (points[j].y - points[i].y + 0.0) / (points[j].x - points[i].x)
              + 0.0;//problem of -0.0
        }
        if (!slopeCnt.containsKey(slope))
          slopeCnt.put(slope, 0);
        slopeCnt.put(slope, slopeCnt.get(slope) + 1);
        System.out.println(slope + " " + slopeCnt.get(slope));
      }
      for (int cnt : slopeCnt.values()) {
        maxCnt = Math.max(maxCnt, cnt + dup);
      }
      maxCnt = Math.max(maxCnt, dup);
    }
    return maxCnt;
  }


  //TLE
  // public int maxPoints(Point[] points) {
  // HashMap<String, HashSet<Integer>> slopeCnt=new HashMap<String, HashSet<Integer>>();
  // int n=points.length;
  // if(n<2) return n;
  // double slope,intercept;
  // int max=2;

  // for(int i=0;i<n;i++){
  // for(int j=i+1;j<n;j++){
  // if(points[i].x==points[j].x){
  // slope=Double.MAX_VALUE;
  // intercept=points[j].y;
  // }
  // else{
  // slope=(points[j].y-points[i].y+0.0)/(points[j].x-points[i].x);
  // intercept=(points[i].y*points[j].x-points[j].y*points[i].x+0.0)/(points[j].x-points[i].x);
  // }
  // String key=String.format("%.6f", slope)+"-"+String.format("%.6f", intercept);
  // if(!slopeCnt.containsKey(key)) slopeCnt.put(key,new HashSet<Integer>());
  // slopeCnt.get(key).add(i);
  // slopeCnt.get(key).add(j);
  // int cnt=slopeCnt.get(key).size();

  // if(cnt>max){
  // max=cnt;
  // System.out.println(slopeCnt.get(key)+" "+points[i]+" "+points[j]);
  // }
  // }
  // }
  // return max;
  // }
}
