package com.shuoma;
public class InterleavingString {
    public boolean isInterleave(String s1, String s2, String s3) {
        int m = s1.length();
        int n = s2.length();
        int s = s3.length();
        if(m + n != s)      return false;
        //return isInterleave(s1, s2, s3, 0, 0); //Recursion TLE

        boolean[][] dp = new boolean[n + 1][m + 1];
        dp[0][0] = true;

        for(int i = 0; i < n + 1; i++)
            for(int j = 0; j < m + 1; j++) {
                if( dp[i][j] || (i-1>=0&&dp[i-1][j]&&s2.charAt(i-1)==s3.charAt(i+j-1)) || (j-1>=0&& dp[i][j-1]&& s1.charAt(j-1)==s3.charAt(i+j-1)) )
                    dp[i][j] = true;
                else
                    dp[i][j] = false;
        }

        return dp[n][m];
    }
    
    public boolean isInterleave(String s1, String s2, String s3, int j, int k) {
        for(int i=j+k;i<s3.length();i++){
            if(j<s1.length()&&s1.charAt(j)!=s3.charAt(i)&&k<s2.length()&&s2.charAt(k)!=s3.charAt(i)) return false;
            if(j<s1.length()&&s1.charAt(j)==s3.charAt(i)&&k<s2.length()&&s2.charAt(k)==s3.charAt(i)){
                return isInterleave(s1, s2, s3, j+1, k)||isInterleave(s1, s2, s3, j, k+1);                
            }else{
                if(j<s1.length()&&s1.charAt(j)==s3.charAt(i)) return isInterleave(s1, s2, s3, j+1, k);
                if(k<s2.length()&&s2.charAt(k)==s3.charAt(i)) return isInterleave(s1, s2, s3, j, k+1);   
                return false;
            }            
        }  
        return true;
    }
    
}