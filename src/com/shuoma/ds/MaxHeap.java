package com.shuoma.ds;

import java.util.Arrays;

public class MaxHeap {
	int[] data;
	
	public MaxHeap(int[] a){
		data=a;
	}
	
	public void heapify(int i){
		heapify(i, data.length);
	}
	
	public void heapify(int i, int end){
		int left=2*i, right=left+1;
		int largest=i;
		if(left<end&&data[left]>data[i]) largest=left;
		if(right<end&&data[right]>data[largest]) largest=right;
		if(largest!=i){
			int temp=data[i];
			data[i]=data[largest];
			data[largest]=temp;
			
			heapify(largest, end);
		}
	}
	
	public String toString(){
		return Arrays.toString(data);
	}

}
