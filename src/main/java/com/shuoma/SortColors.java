package com.shuoma;
public class SortColors {
    public static void main(String[] args){
        new SortColors().sortColors(new int[]{2});
    }
    
    //second pass
    public void sortColors(int[] A) {
        int l=0, r=A.length;
        for(int i=0;i<r;){
          if(A[i]==0) swap(A, i++, l++);
          else{
              if(A[i]==2) swap(A, i, --r);
              else i++;
          }
        }
    }
    
 
    //first pass
    public void sortColorsFirstPass(int[] A) {
        int n=A.length;
        int b=n, r=0;
        for(int i=0;i<b;++i){
            if(A[i]==0)   swap(A, i, r++);
            else if(A[i]==2) swap(A, i--, --b);
        }
    }
    
    private void swap(int[] A, int i, int j){
        if(i==j) return;
        int tmp=A[i];
        A[i]=A[j];
        A[j]=tmp;
    }
}