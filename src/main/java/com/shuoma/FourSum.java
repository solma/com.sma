package com.shuoma;
import java.util.*;
public class FourSum {
	public static void main(String[] args){
		FourSum ins=new FourSum();
		for(ArrayList<Integer> li : ins.fourSum(new int[]{-1,0,-5,-2,-2,-4,0,1,-2},  -9 ) ){
			System.out.println(li);
		}
	}
	
	public ArrayList<ArrayList<Integer>> fourSum(int[] num, int target) {
        // Start typing your Java solution below
        // DO NOT write main() function
        Arrays.sort(num);
		HashSet<ArrayList<Integer>> ret = new HashSet<ArrayList<Integer>>();
		for(int i=0;i<num.length;i++){
			//System.out.println(num[i]);
			for(ArrayList<Integer> triplet: threeSum(num, i, target-num[i]) ){
				//System.out.println(triplet);
				triplet.add(num[i]);
				Collections.sort(triplet);
				ret.add(triplet);
			}
			//System.out.println();
		}
		return new ArrayList<ArrayList<Integer>>(ret);
    }


	public ArrayList<ArrayList<Integer>> threeSum(int[] num, int forbidden, int target) {
        // Start typing your Java solution below
        // DO NOT write main() function
		HashSet<ArrayList<Integer>> lstSoln = new HashSet<ArrayList<Integer>>();

		ArrayList<Integer> tempArr = null;
		for (int i = 0; i < num.length; i++) {
			if(i==forbidden) continue;
			if (num[i] > target&&target>=0)
				break;
			int j = i + 1;
			int k = num.length - 1;
			while (j < k) {
				int sum3 = num[i] + num[j] + num[k];
				if (sum3 < target) {
					j++;
				} else if (sum3 > target) {
					k--;
				} else {
					if(j==forbidden||k==forbidden) break;
					tempArr = new ArrayList<Integer>();
					Collections.addAll(tempArr, num[i], num[j], num[k]);
					lstSoln.add(tempArr);
					j++;
					k--;
				}
			}
		}
		return new ArrayList<ArrayList<Integer>>(lstSoln);
 
    }    
   
}