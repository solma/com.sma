package com.shuoma;
public class ValidPalindrome{
    public boolean isPalindrome(String s) {
        if(s==null) return false;
        if(s.length()==0) return true;
        s=s.replaceAll("[[^\\d]&&[^a-zA-Z]]", ""); //catch how to use regex and string is immutatble
        int i=0, j=s.length()-1;
        s=s.toLowerCase();
        while(i<j){
            if(s.charAt(i)!=s.charAt(j)) return false;
            i++; j--;
        }
        return true;
    }
}
