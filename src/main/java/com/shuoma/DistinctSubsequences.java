package com.shuoma;
public class DistinctSubsequences {
   public static void main(String[] args){
       System.out.println(new DistinctSubsequences().numDistinct("rababitit","rbit"));
   }
   public int numDistinct(String S, String T) {
        int[] occurence = new int[T.length() + 1];
        occurence[0] = 1;
        //o[i, j]=o[i-1, j]+(S[i]==T[j])*o[i-1, j-1];
        for(int i = 0; i < S.length(); i++){
            for(int j = T.length(); j >=1 ; j--)
                if(S.charAt(i) == T.charAt(j-1)){
                    occurence[j] += occurence[j-1];
                }
        }
        return occurence[T.length()];
    }
}