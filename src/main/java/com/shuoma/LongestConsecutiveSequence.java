package com.shuoma;
import java.util.*;
public class LongestConsecutiveSequence {
    public static void main(String[] args){
        new LongestConsecutiveSequence().main();
    }
    
    public void main(){
        int[] num={0};
        longestConsecutive(num);
    }
    
    //second pass
    public int longestConsecutive(int[] num) {
        HashSet<Integer> all=new HashSet<Integer>();
        for(int i=0;i<num.length;i++){
            all.add(num[i]);
        }
        
        int cnt, maxCnt=1;
        for(int i=0;i<num.length;i++){
            if(all.contains(num[i])){
                cnt=1;
                int n=num[i]+1;
                while(true){
                    if(!all.contains(n)) break;
                    all.remove(new Integer(n));
                    n++;
                    cnt++;
                }
                //System.out.println(cnt);
                n=num[i]-1;
                while(true){
                    if(!all.contains(n)) break;
                    all.remove(new Integer(n));
                    n--;
                    cnt++;
                }
                //System.out.println(cnt);
                if(maxCnt<cnt) maxCnt=cnt;
            }
        }
        return maxCnt;
    }
    
    //first pass
    public int consecutiveSequence(HashSet<Integer> nums, int val, int increment){
        int ret=0;
        int num=val+increment;
        while(nums.contains(num)){
            nums.remove(num);
            ret+=1;
            num+=increment;            
        }
        return ret;
    }

    // public int longestConsecutive(int[] num) {
        // // Start typing your Java solution below
        // // DO NOT write main() function
        // HashSet<Integer> nums=new HashSet<Integer>();
        // int i;
        // for(i=0;i<num.length;i++) nums.add(num[i]);
        // int maxLength=0;
        // for(i=0;i<num.length;i++){
            // if(nums.contains(num[i])){
                // maxLength=Math.max(maxLength, consecutiveSequence(nums, num[i], 1)+1+consecutiveSequence(nums, num[i], -1) );
                // nums.remove(num[i]);
            // }
        // }
        // return maxLength;
    // }
}