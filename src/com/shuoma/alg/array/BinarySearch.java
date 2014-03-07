package com.shuoma.alg.array;

public class BinarySearch {
	int linearSearchFirstAppear(int[] a, int key){
        for(int i=0;i<a.length;i++){
            if(a[i]==key) return i;
        }
        return -1;
    }
    
    int binarySearchFirstAppear(int[] a, int key) {
        //return the first appearance
        int low = -1, high = a.length, med;
        while (low+1 != high) {
            med = low + (high - low) / 2;
            //System.out.println("high=" + high + ", low=" + low + ", med=" + med);
            if (a[med] < key) low = med;
            else high = med;
        }
        if(high>=a.length||a[high]!=key) return high=-1; //this is the tricky line
        return high;
    }
    
    int linearSearchLastAppear(int[] a, int key){
        for(int i=a.length-1;i>=0;i--){
            if(a[i]==key) return i;
        }
        return -1;
    }
    
    int binarySearchLastAppear(int[] a, int key) {
        //return the first appearance
        int low = -1, high = a.length, med;
        while (low+1 != high) {
            med = low + (high - low) / 2;
            //System.out.println("high=" + high + ", low=" + low + ", med=" + med);
            if (a[med] > key) high = med;
            else low = med;
        }
        if(low<0||a[low]!=key) low=-1;//this is the tricky line
        return low;
    }
}
