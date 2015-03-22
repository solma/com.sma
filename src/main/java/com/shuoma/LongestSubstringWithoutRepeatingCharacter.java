package com.shuoma;
import java.util.*;

public class LongestSubstringWithoutRepeatingCharacter {
    public static void main(String[] args){
        new LongestSubstringWithoutRepeatingCharacter().main();
    }
    
    public void main(){
        System.out.println(lengthOfLongestSubstring("wlrbbmqbhcdarzowkkyhiddqscdxrjmowfrxsjybldbefsarcbynecdyggxxpklorellnmpapqfwkhopkmco"));
    }
    
    //second pass
    public int lengthOfLongestSubstring(String s) {
        int n=s.length();
        int[] lastPos=new int[256];
        for(int i=0;i<lastPos.length;i++) lastPos[i]=-1;
        int maxLen=0,curLen=0;
        for(int i=0;i<n;i++){
            char letter=s.charAt(i);
            if(lastPos[letter]!=-1&&i-lastPos[letter]<=curLen){
              curLen=i-lastPos[letter];
            }else{
              curLen+=1; 
            }
            lastPos[letter]=i;
            maxLen=maxLen>=curLen?maxLen:curLen;
            //System.out.println(letter+" "+curLen+" "+maxLen);
        }
        return maxLen;
    }
    
    
    //first pass
    // public int lengthOfLongestSubstring(String s) {
        // if (s==null || s.length()==0) return 0;
        // int sLen=s.length();
        // int max_len=Math.min(sLen, 26);
        // for(int len=max_len;len>=1;len--)
            // for(int sIdx=0;sIdx<=sLen-len;sIdx++){
                // if (noRepeatingCharacter(s, sIdx, len)) return len;
           // }
        // return -1;
    // }
    
    // public Boolean noRepeatingCharacter(String s, int sIdx, int sLen){
        // HashSet<Character> letters=new HashSet<Character>();
        // for(int i=sIdx;i<=sIdx+sLen-1;i++){
            // if(letters.contains(s.charAt(i))) return false;
            // letters.add(s.charAt(i));
        // }
        // return true;
    // }
    
    
    
}