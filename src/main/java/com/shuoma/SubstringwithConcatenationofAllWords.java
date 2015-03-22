package com.shuoma;
import java.util.ArrayList;
import java.util.HashMap;

public class SubstringwithConcatenationofAllWords{
    public ArrayList<Integer> findSubstring(String S, String[] L) {
        int len =  L[0].length();
        int count = L.length;

        HashMap<String, Integer> words = new HashMap<String, Integer> ();
        for ( String s:L ) {
            if ( words.containsKey( s ) ){
                words.put( s, 1+words.get(s) );
            } else {
                words.put( s, 1 );
            }
        }
        ArrayList<Integer> rv = new ArrayList<Integer>();

        int slen = S.length();

        for ( int i=0; i<=slen-count*len;  ) {//the first character of matching

            HashMap<String, Integer> targets = new HashMap<String, Integer>  (words);
            int forward = i;
            while ( true ) {
                String sub = S.substring( forward, forward+len);
                if ( targets.containsKey ( sub ) ) {
                    if (targets.get(sub) == 1 ) {
                        targets.remove ( sub ) ;
                    } else {
                        targets.put ( sub, targets.get (sub)-1 ) ;
                    }
                    if ( targets.isEmpty() ) {
                        rv.add ( i );
                        break;
                    }
                    forward += len;
                } else {
                    break;
                }
            }
            i++;
        }
        return rv;
    }
}