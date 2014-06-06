package com.shuoma.ds.tree;

import java.util.Arrays;

public class TournamentTree {

	public int[] nodes;
	
	public TournamentTree(int[] array){
		int treeSize=(array.length<<1)-1;
		nodes=new int[treeSize];
		
		System.arraycopy(array, 0, nodes, treeSize-array.length, array.length);
		System.out.println(Arrays.toString(nodes));

		//build the tournament tree; bottom up
		for(int i=treeSize-1;i>=treeSize-array.length;i-=2){
			heapify(i);
			System.out.println(Arrays.toString(nodes));
		}
	}
	
	public void heapify(int idx){
		if(idx==0) return;
		int siblingIdx=idx+(int)Math.pow(-1, (idx&1)+1), fatherIdx=(idx-1)>>1;
		int larger=Math.max(nodes[idx], nodes[siblingIdx]);
		if(larger>nodes[fatherIdx]){
			nodes[fatherIdx]=larger;
			if(fatherIdx>0) heapify(fatherIdx);
		}
	}
	
	
	public int[] losersOfRoot(){
		int[] path=new int[(int)Math.log(nodes.length)+1];
		int noOfLosersOfRoot=0;
		int idx=0, left=1, right=2;
		while(right<nodes.length){
			if(nodes[idx]==nodes[left]){
				path[noOfLosersOfRoot++]=nodes[right];
				idx=left;
			}else{
				path[noOfLosersOfRoot++]=nodes[left];
				idx=right;
			}
			left=(idx<<1)+1;
			right=left+1;
		}
		return Arrays.copyOf(path, noOfLosersOfRoot);
	}
	
	
	
	

}
