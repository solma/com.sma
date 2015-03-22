package com.shuoma;
import java.util.*;
public class Anagram{
	public static void main(String[] args){
		new Anagram().main();
	}
	
	public void main(){
		//System.out.println(sortString("cadae"));
		System.out.println(anagrams(new String[]{"icde", "cdei", "adae", "aeea", "ecid"}));
	}
    
    // pass2:    
    public ArrayList<String> anagrams(String[] strs) {
        HashMap<String, ArrayList<String>> anagramSignitures=new HashMap<String, ArrayList<String>>();    
        for(String str: strs){
            String sig=sign(str);
            if(!anagramSignitures.containsKey(sig)){
                anagramSignitures.put(sig, new ArrayList<String>());
            }
            anagramSignitures.get(sig).add(str);
        }
        
        ArrayList<String> ret=new ArrayList<String>();
        for(String str: anagramSignitures.keySet() ){
            if(anagramSignitures.get(str).size()>1 ){
                ret.addAll(anagramSignitures.get(str));
            }
        }
        
        return ret;
    }
    
    public String sign(String str){
        char[] arr=str.toCharArray();
        Arrays.sort(arr);
        return new String(arr);
    }
    
    
    // pass1:

	 // public ArrayList<String> anagrams(String[] strs) {
        // // Start typing your Java solution below
        // // DO NOT write main() function
		// HashMap<String, ArrayList<String>> anagramSignature=new HashMap<String, ArrayList<String>>();
		// int i;
		// String signa;
		// for(i=0;i<strs.length;i++){
			// signa=sortString(strs[i]);
			// if(!anagramSignature.containsKey(signa)){
				// anagramSignature.put(signa,new ArrayList<String>());
			// }
			// anagramSignature.get(signa).add(strs[i]);		
		// }

		
		// ArrayList<String> ret=new ArrayList<String>();
		// for(Map.Entry <String, ArrayList<String>> ele : anagramSignature.entrySet()){
			// if(ele.getValue().size()>1){
				// ret.addAll(ele.getValue());
			// }
		// }
		// return ret;
    // }
	
	// public String sortString(String str){
		// ArrayList<String> str_list=new ArrayList<String>();
		// for(int i=0;i<str.length();i++){
			// str_list.add(str.substring(i,i+1));
		// }
		// Collections.sort(str_list);
		// String ret="";
		// for(String s : str_list){
			// ret+=s;
		// }
		// return ret;			
	// }
}