//source: Elements of Programming Interviews
import java.util.*;
public class StringDecomposition{
    public static void main(String[] args){
        new StringDecomposition().main();
    }
    
    public void main(){
        String[] dict=new String[]{"ab", "cd", "ef", "abc", "d", "ea", "b", "e"};
        ArrayList<ArrayList<String>> decompositions=decompose(dict, "eabcd");
        for(ArrayList<String> decomp: decompositions){
            System.out.println(decomp);
        }
    }
    
    public ArrayList<ArrayList<String>> decompose(String[] dict, String s){
        HashSet<String> words=new HashSet<String>();
        for(String w: dict) words.add(w);
        
        //the length of the last string of the decomposition at position k
        ArrayList<ArrayList<Integer>> lenOfLastWord=new ArrayList<ArrayList<Integer>>();
        int sLen=s.length();
        for(int i=0;i<sLen;i++){
            lenOfLastWord.add(new ArrayList<Integer>());
        }
        
        for(int i=0;i<sLen;i++){
            for(int wLen=i+1; wLen>=1; wLen--){
                if(words.contains(s.substring(i+1-wLen ,i+1))
                   && (wLen==i+1 || lenOfLastWord.get(i-wLen).size()>0)
                   )
                   lenOfLastWord.get(i).add(wLen);
            }
        }
        return buildDecompositions(s, lenOfLastWord, new ArrayList<String>(), sLen-1);
    }
    
    public ArrayList<ArrayList<String>> buildDecompositions(String s, ArrayList<ArrayList<Integer>> lenOfLastWord, ArrayList<String> curDecomp, int idx){
        ArrayList<ArrayList<String>> ret=new ArrayList<ArrayList<String>>();
        if(idx<0){
            ret.add(new ArrayList<String>(curDecomp) );
            return ret;
        }
        for(Integer wLen:  lenOfLastWord.get(idx)){
            String lastWord=s.substring(idx+1-wLen, idx+1);
            curDecomp.add(0, lastWord);
            ret.addAll( buildDecompositions(s, lenOfLastWord, curDecomp, idx-wLen) );
            curDecomp.remove(0);
        }
        return ret;        
    }
    
}