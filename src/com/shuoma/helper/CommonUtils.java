package com.shuoma.helper;

import java.util.Random;

public class CommonUtils {
    public static Random r = new Random();
	
	
    /************
     * array method
     *********/
    
    
    public static void swap(int[] a, int i, int j) {
        int n=a.length;
    	if (i == j || i<0 || j<0 || i>=n || j>=n) return;
        int tmp = a[i];
        a[i] = a[j];
        a[j] = tmp;
    }
    
    public static int[] rotate(int[] a, int start){
    	int n=a.length;
    	int[] ret=new int[n];
    	for(int i=start;i<n;i++){
    		ret[i-start]=a[i];
    	}
    	for(int i=0;i<start;i++){
    		ret[i+n-start]=a[i];
    	}
    	return ret;
    }
    
    
	public static int[] genRandomArray() {
        int length = r.nextInt(10)+2; //at least two elements
        int[] ret = new int[length];
        for (int i = 0; i < length; i++) {
            ret[i] = r.nextInt(1000)*(r.nextBoolean()?1:-1);
        }
        return ret;
    }

}
