package com.shuoma;
public class ReverseWordsInAString {
    public static void main(String[] args){
        new ReverseWordsInAString().main(args[0]);
    }

    public void main(String s){
        System.out.println("\""+reverseWords(s)+"\"");
    }
    
    public String reverseWords(String s) {
       char[] arr=s.trim().replaceAll("\\s+", " ").toCharArray();
       reverse(arr, 0, arr.length-1);
       
       int l=0;
       boolean wordEnd=true;
       for(int i=0;i<arr.length;i++){
         if(arr[i]==' '){
            if(wordEnd){
                wordEnd=false;
                reverse(arr, l, i-1);
            }
        }else{
            if(!wordEnd) l=i;
            wordEnd=true;
        }        
       }
       reverse(arr, l, arr.length-1);
       return new String(arr);
    }
    
    public void reverse(char[] arr, int i, int j){
        //input checking...
        while(i<j){
            char tmp=arr[i];
            arr[i]=arr[j];
            arr[j]=tmp;
            i++;
            j--;
        }
    }
}