package com.shuoma;
import java.util.*;
public class SpiralMatrixII {
    public static void main(String[] args){
        new SpiralMatrixII().main();
    }
    
    public void main(){
        int[][] matrix=generateMatrix(5);
        for(int i=0;i<matrix.length;i++){
            System.out.println(Arrays.toString(matrix[i]));
        }
    }

//second pass    
    public int[][] generateMatrix(int n) {  
        if(n<=0) return new int[][]{};
        int[][] ret=new int[n][n];
        
        int[][] dir={{0,1},{1,0},{0,-1},{-1,0}};
        
        int dirChange;
        int i=0,j=0, dirIdx=0;
        int val=1;
        while(true){
            ret[i][j]=val++;
            for(dirChange=0;dirChange<dir.length;dirChange++){
                int newDirIdx=(dirIdx+dirChange)%dir.length;
                if(validMove(i+dir[newDirIdx][0], j+dir[newDirIdx][1], n, ret)){
                    i+=dir[newDirIdx][0];
                    j+=dir[newDirIdx][1];
                    dirIdx=newDirIdx;//change the direction
                    break;
                }
            }
            if(dirChange==dir.length) break;
        }
        return ret;
    }
    
     boolean validMove(int i, int j, int n, int[][] matrix){
        if(i<0||i>=n) return false;
        if(j<0||j>=n) return false;
        if(matrix[i][j]>0) return false;
        return true;
    }

    
    //first pass
    // public int[][] generateMatrix(int n) {
        // if(n<=0) return new int[][]{};
        // int[][] ret=new int[n][n];
        // int i, j;
        // for(i=0;i<n;i++)
            // for(j=0;j<n;j++)
                // ret[i][j]=-1;
        // int dirChange=0, dir=0;
        // int val=1;
        // int[][] dirOffset=new int[][]{{0,1}, {1,0}, {0, -1}, {-1, 0}};
        // i=0;j=0;
        // while(ret[i][j]==-1||dirChange==1){ //catch: use of dirChange
            // ret[i][j]=val;
            // if( ((j<n-1&&dir==0)||(j>0&&dir==2)||(i<n-1&&dir==1)||(i>0&&dir==3)) && ret[i+dirOffset[dir][0]][j+dirOffset[dir][1]]==-1){
                // i+=dirOffset[dir][0];
                // j+=dirOffset[dir][1];
                // val+=1;
                // dirChange=0;
            // }else{
                // dir=(dir+1)%4;
                // dirChange+=1;
            // }
        // }
        // return ret;
        
    // }
}