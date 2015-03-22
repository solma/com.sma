package com.shuoma;
public class SingleNumber {
    public int singleNumber(int[] A) {
        if(A.length==0) return -1;
        int ret=A[0];
        for(int i=1;i<A.length;i++){
            ret^=A[i];
        }        
        return ret;
    }
}