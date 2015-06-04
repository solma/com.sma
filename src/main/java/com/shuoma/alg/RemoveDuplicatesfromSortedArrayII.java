package com.shuoma.alg;

import static com.shuoma.annotation.Tag.DataStructure.Array;
import static com.shuoma.annotation.Tag.Reference.LeetCode;
import static com.shuoma.annotation.Tag.Trick.InplaceSwap;

import com.shuoma.annotation.Tag;

@Tag(dss = Array, reference = LeetCode, tricks = InplaceSwap)
public class RemoveDuplicatesfromSortedArrayII {
    //second pass
    public int removeDuplicates(int[] A) {

        if(A.length<1) return 0;
        int cnt=1;
        int dup=0;
        for(int i=1;i<A.length;i++){
            if(A[i]==A[i-1]){
                if(dup==0){
                    dup++;
                    A[cnt++]=A[i];
                }
            }else{
                dup=0;
                A[cnt++]=A[i];
            }
        }
        return cnt;
    }

    //first pass
    //[1,1,1,2,2,3],
    public int removeDuplicatesFirstPass(int[] A) {
        int n=A.length;
        if(n<1) return 0;

        int noOfDup=0, prev=A[0], noOfRep=0;
        for(int i=1;i<n;i++){
            if(A[i]==prev){
                noOfRep+=1;
                if(noOfRep>=2){
                    noOfDup+=1;
                }
            }else{
                prev=A[i];
                noOfRep=0;
            }
        }
        return n-noOfDup;
    }
}
