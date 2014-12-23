import java.util.*;
//find all numbers that have n digits that are monotonically increasing
//source: wechat
public class NumbersWithNIncreasingDigits{
    public static void main(String[] args){
        System.out.println(new NumbersWithNIncreasingDigits().numbersWithNIncreasingDigits(5));
    }
    
    public ArrayList<String> numbersWithNIncreasingDigits(int n){
        return numbersWithNIncreasingDigits(n, new StringBuilder(), 1);
    }
    
    public ArrayList<String> numbersWithNIncreasingDigits(int n, StringBuilder sb, int sIdx){
        ArrayList<String> ret=new ArrayList<String>();
        //System.out.println(sIdx+" "+sb.toString());
        if(sb.length()==n){
            ret.add(sb.toString());
            return ret;
        }
        for(int i=sIdx;i<10;i++){            
            sb.append(i);
            ret.addAll(numbersWithNIncreasingDigits(n, sb, i+1));
            sb.deleteCharAt(sb.length()-1);
        }
        return ret;
    }
}