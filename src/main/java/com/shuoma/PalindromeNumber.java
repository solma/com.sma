package com.shuoma;
public class PalindromeNumber {
//second pass
    public boolean isPalindrome(int x) {
        if (x < 0)
            return false;
        int div = 1;
        while (x / div >= 10) {
            div *= 10;
        }
        while (x != 0) {
            int l = x / div;
            int r = x % 10;
            if (l != r)
                return false;
            x = (x % div) / 10;
            div /= 100;
        }
        return true;
    }


//first pass
    public boolean isPalindromeFirstPass(int x) {
        String s=String.valueOf(x);
        int i=0, j=s.length()-1;
        while (i<j) {
            if (s.charAt(i)!=s.charAt(j)) return false;
            i++;            j--;
        }
        return true;
    }

}