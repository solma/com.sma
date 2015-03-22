package com.shuoma;
import java.util.*;
public class PermutationSequence{
    public static void main(String[] args){
        new PermutationSequence().main();
    }    
    
    public void main(){
        System.out.println(getPermutation(5, 10));        
    }

    public String getPermutation(int n,int k) {
        int[] num=new int[n];
		int kk=k;
        for(int i=0;i<n;i++) num[i]=i+1;
        while(k-->1){
            nextPermutation(num);
        }
		String ret="";
        for(int i=0;i<n;i++) ret+=num[i];
		System.out.println(kk+ " th permutation is "+Arrays.toString(num) );

		previousPermutation(num);
        System.out.println((kk-1)+" th permutation is "+Arrays.toString(num ) );
        return ret;
    }
    
        private void swap(int[] num, int a, int b) {
            int temp = num[a];
            num[a] = num[b];
            num[b] = temp;
        }

        /* O(n) -- reverse the array, given a range */
        private void reverse(int[] num, int l, int r) {
            while (l < r) {
                swap(num, l++, r--);
            }
        }

        /* O(n) -- find the next permutation */
        public void nextPermutation(int[] num) {
            // find descending part from right to left
            int cur = num.length - 1;
            while (cur>0 && num[cur-1] >= num[cur]) cur--;
            if (cur > 0) {
                cur--;
                int right = num.length-1;
                while (num[right] <= num[cur])  right--;
                //System.out.println(cur+" "+right);
                swap(num,cur, right);
                reverse(num, cur+1, num.length-1); 
            }else{// the last permutation, so the next is the first in the alphabetical order
                Arrays.sort(num);
            }
            
        }
    

		public void previousPermutation(int[] num){
		  int cur=num.length-1;
		  while(cur>0&&num[cur-1]<=num[cur]) cur--;
		  if(cur>0){
			cur--;
			int right=num.length-1;
			while(num[right]>=num[cur]) right--;
			swap(num, cur, right);
			reverse(num, cur+1, num.length-1);
		  }
		}
    
    //recursion algorithm TLE
    // public String getPermutation(int n, int k) {
        // ArrayList<String> ret=firstKPermutationsByRecursion(n, new StringBuilder(), k);
        // return ret.get(ret.size()-1);        
    // }
    
        ArrayList<String> firstKPermutationsByRecursion(int n, StringBuilder perm, int K){
            //firt K permutatins in alphabetical order 
            ArrayList<String> ret=new ArrayList<String>();
            for(int i=0; i<n; i++){
                if(perm.toString().contains(String.valueOf(i+1) )) continue;
                perm.append(i+1);
                if(perm.length()==n)  ret.add(perm.toString());            
                for(String s: firstKPermutationsByRecursion(n, perm, K)){
                    if(ret.size()>=K) break;
                    ret.add(s);
                }
                perm.deleteCharAt(perm.length()-1);
            }
            return ret;
        }

}
