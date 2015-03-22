package com.shuoma;
import java.util.HashSet;

public class SetMatrixZeros {
    public void setZeroes(int[][] matrix) {
        int nRow=matrix.length;
        if(nRow>0){
            int nCol=matrix[0].length;
            if(nCol>0){
                HashSet<Integer> zeroRows=new HashSet<Integer>();
                HashSet<Integer> zeroCols=new HashSet<Integer>();
                int i,j;
                for(i=0;i<nRow;i++)
                    for( j=0; j<nCol; j++){
                        if(matrix[i][j]==0){
                            zeroRows.add(i);
                            zeroCols.add(j);
                        }                        
                    }
                for(i=0;i<nRow;i++){
                    if(zeroRows.contains(i)){
                        matrix[i]=new int[nCol];
                        continue;
                    }
                    for( j=0; j<nCol; j++)
                        if(zeroCols.contains(j)) matrix[i][j]=0;
                }
                        
                
            }
        }
    }
}