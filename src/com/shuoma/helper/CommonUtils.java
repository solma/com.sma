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
	
	
	/***
	 * Arithmetic
	 */
	public static boolean isNumber(char c){
		int diff=c-'0';
		if(diff>=0&&diff<=9) return true;
		else return false;
	}
	
	
	/**
	 * 
	 * @param newChar
	 * @param stackTop
	 * @return newChar >= stackTop
	 */
	public static boolean higherOrEqualPriority(char newChar, char stackTop){
		switch(newChar){
		case '+':case '-':
			if(stackTop=='+'||stackTop=='-'||stackTop=='=')
				return true;
			else 
				return false;
		case '*':case '/':
			if(stackTop=='('||stackTop=='s'||stackTop=='c'||stackTop=='l'||stackTop=='^')
				return false;
			else
				return true;
		case '=':
			if(stackTop=='=')
				return true;
			else 
				return false;
		case 's': case 'c': case 'l': case '^':
	         if(stackTop=='(')
	             return false;
	         else 
	             return  true;
	    default:
	    	return false;
	    }
	}
	
	
	public static double operator(double a,double b,char theta)
	{
		switch(theta)
		{
		case '+':
			return a+b;
	   	case '-':
			return a-b;
	    case '*':
			return a*b;
	    case '/':
			return a/b;
		case '^':
	        return Math.pow(a,b);
	    }
		return 0;	  
	}
	
	
	

}
