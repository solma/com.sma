package com.sma.alg;

import static com.sma.annotation.Tag.Algorithm.Sorting;
import static com.sma.annotation.Tag.DataStructure.StringT;
import static com.sma.annotation.Tag.Reference.LeetCode;

import com.sma.annotation.Tag;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Tag(algs = Sorting, dss = StringT, references = LeetCode)
public class Anagram {

  public List<String> anagrams(String[] strs) {
    Map<String, ArrayList<String>> anagramSignatures = new HashMap<>();
    for (String str : strs) {
      String sig = sign(str);
      if (!anagramSignatures.containsKey(sig)) {
        anagramSignatures.put(sig, new ArrayList<String>());
      }
      anagramSignatures.get(sig).add(str);
    }

    ArrayList<String> ret = new ArrayList<>();
    for (String str : anagramSignatures.keySet()) {
      if (anagramSignatures.get(str).size() > 1) {
        ret.addAll(anagramSignatures.get(str));
      }
    }

    return ret;
  }

  public String sign(String str) {
    char[] arr = str.toCharArray();
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
