package com.shuoma;
public class ValidNumber{
    public static void main(String[] args){
        new ValidNumber().main();
    }
    
    public void main(){
        String s=" 0.1 ";
        System.out.println(isNumber(s));
    }

    public boolean isNumber(String s) {
//my first try:        return s.matches("[+-]*\\d*.?\\d*(e[+-])?\\d+");
        return s.matches("^\\s*[+-]?(\\d+|\\d*\\.\\d+|\\d+\\.\\d*)([eE][+-]?\\d+)?\\s*$");
    }
}