package com.shuoma;
import java.util.Stack;

public class ValidParenthesis{
    public boolean isValid(String s) {
        if(s==null) return true;
        Stack<Character> stack=new Stack<Character>();
        char c;
        for(int i=0;i<s.length();i++){
            c=s.charAt(i);
            if(c=='('||c=='{'||c=='[') stack.push(c);
            else{
                if(!stack.isEmpty() && match((char)stack.peek(), c)) stack.pop(); //catch: stack is empty
                else return false;
            }
        }
        if(stack.isEmpty()) return true;  //catch stack is empty
        else return false;
    }
    
    public boolean match(char stack, char input){
        if( (stack=='{'&&input=='}')||(stack=='('&&input==')')||(stack=='['&&input==']') ) return true;
        else return false;
    }
}