package com.shuoma;
import java.util.*;
public class PermutationsII{
    public static void main(String[] args){
        new PermutationsII().main();
    }    
    
    public void main(){
//        int A[]=new int[]{-1,2,-1,2,1,-1,2,1};
		int A[]=new int[]{1,2,1};
        for(ArrayList<Integer> p: permuteUnique(A)){
            for(Integer i: p)
                System.out.print(i+" ");
            System.out.println();
        }        
    }
    
    
    public ArrayList<ArrayList<Integer>> permuteUnique(int[] num) {
        if(num == null) return null;
        Arrays.sort(num);
        return perm(num, 0, new boolean[num.length]);
    }
    
    
    public ArrayList<ArrayList<Integer>> perm(int[] num, int l, boolean[] used){
        ArrayList<ArrayList<Integer>> rl = new ArrayList<ArrayList<Integer>>();
        if(l == num.length) rl.add(new ArrayList<Integer>());
        else for(int i = 0; i < num.length; i++){
                if(used[i] || (i != 0 && num[i] == num[i - 1] && used[i - 1]) ) continue;
                used[i] = true;
                for(ArrayList<Integer> x : perm(num, l + 1, used)){
                    x.add(0, num[i]);
                    rl.add(x);
                }
                used[i] = false;
            }
        return rl;
    }

}
