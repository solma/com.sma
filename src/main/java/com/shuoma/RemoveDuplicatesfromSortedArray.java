package com.shuoma;
public class RemoveDuplicatesfromSortedArray {
    //second pass
    public int removeDuplicates(int[] A) {
        
        if(A.length<1) return 0;
        int cnt=1;
        for(int i=1;i<A.length;i++){
            if(A[i]>A[i-1]) A[cnt++]=A[i];
        }
        return cnt;
    }
    
    //first pass
    public int removeDuplicatesFirstPass(int[] A) {
        int n=A.length;
        if(n<1) return 0;
        int storeIdx=1, noOfRep=0, noOfDel=0;
        int prev=A[0];
        for(int i=1;i<n;i++){
            if(A[i]==prev){
                noOfRep+=1;
                if(noOfRep>=2){
                    noOfDel+=1;
                }else{
                    A[storeIdx++]=A[i];
                }
            }else{
                prev=A[i];
                noOfRep=0;
                A[storeIdx++]=A[i];
            }
        }
        return n-noOfDel;
    }
    
}