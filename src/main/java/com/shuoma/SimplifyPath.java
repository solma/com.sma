package com.shuoma;
import java.util.*;
public class SimplifyPath{
    public static void main(String[] args){
        System.out.println(new SimplifyPath().simplifyPath(args[0]) );
    }
    
    //first pass
    public String simplifyPath(String path) {
        if(path==null||path.length()==0) return "/";
        String[] dirs=path.trim().split("/");
        Stack<String> stc=new Stack<String>();
        for(String s: dirs){
            if(s==null||s.length()==0||s.equals(".")){ //pay attention to this line
                continue;
            }else{
                if(s.equals("..")){
                    if(stc.size()>0) stc.pop();
                }else{
                    stc.push(s);
                }
            }
        }
        StringBuilder sb=new StringBuilder();
        if(stc.isEmpty()) return "/"; //pay attention to this line
        while(!stc.isEmpty()){
            sb.insert(0, stc.pop());
            sb.insert(0, "/");
        }
        return sb.toString();
    }
}