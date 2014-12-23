/*
given an array A,size=n,elements range from 0~n-1,some number missing some repeated. Find which numbers are missing and which are repeated. Time: O(n), Space: O(1)?
Source: daizhiguizhong
*/

import java.util.*;
public class ArrayStat{
    public static void main(String[] args){
        new ArrayStat().stat(new int[]{1,3,3,2});
    }
    
    public void stat(int[] A){
        int n=A.length;
        if(n==0) return;
        System.out.println(Arrays.toString(A));
        for(int i=0;i<n;i++) A[A[i]%n]+=n;
        System.out.println(Arrays.toString(A));
        for(int i=0;i<n;i++) A[i]/=n;
        System.out.println(Arrays.toString(A));        
    }
} 

