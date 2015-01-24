package com.shuoma.alg.string;

import java.util.ArrayList;
import java.util.List;

/**
 * Given a string, reconstruct as follows, 
 * e.g. given "Hello_World" and 3, output "e_lhlowrdlo"
 *    e       -          l
 * h    l   o   w     r    d
 *        l        o
 *           
 * e.g. given "Hello_World" and 2, output "hlowrdel_ol"
 *     h   l   o   w    r   d
 *       e   l   _    o   l
 */
public class ZigzagString {
    public static void main(String[] args) {
        System.out.println(zigzag("hello world", 2));
    }
    
    static String zigzag(String s, int numberOfRows) {
        List<StringBuilder> rows = new ArrayList<>(numberOfRows);
        for(int i = 0; i < numberOfRows; i++) rows.add(new StringBuilder());
        
        int curRow = (numberOfRows - 1) >> 1, increment = -1;
        for (char c : s.toCharArray()) {
            rows.get(curRow).append(c);
            if (curRow == 0 || curRow == numberOfRows - 1) increment *= -1;
            curRow += increment;
        }
        
        StringBuilder res = new StringBuilder();
        for(StringBuilder sb : rows) {
            res.append(sb);
        }
        return res.toString();
    }
}
