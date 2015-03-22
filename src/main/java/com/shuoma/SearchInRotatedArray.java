package com.shuoma;
public class SearchInRotatedArray{ //works for with an w/o duplicates
    public static void main(String[] args){ 
        new SearchInRotatedArray().main();
    }
    
    public void main(){
        int[] A=new int[]{2, 2, 2, 2, 3, 2,2,2,2,2};
        System.out.println(search(A, 3));
    }
    
    //without find the pivot
    public int search(int[] A, int key) {
        int N=A.length;
        int L = 0;
        int R = N - 1;
         
      while (L <= R) {
        int M = L + ((R - L) / 2);
        if (A[M] == key) return M;
     
        // the left half is sorted
        if (A[L] < A[M]) {
          if (A[L] <= key && key < A[M])
            R = M - 1;
          else
            L = M + 1;
        }
        // the right half is sorted
        else {
          if(A[L]>A[M]){
              if (A[M] < key && key <= A[R])
                L = M + 1;
              else 
                R = M - 1;
          }else{//A[L]==A[M]
              L++;
          }
          
        }
      }
      return -1;
    }
    
    //find the pivot
    public int findPivot(int[] A) {
        int l=0, r=A.length-1, m;
        while(A[l]>A[r]){
            m=l+((r-l)>>1);
            if(A[m]>A[r]){
                l=m+1;
            }else{
                r=m;
            }
        }
        return l;
    }
}
