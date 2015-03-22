package com.shuoma;
public class AddBinary {
    public static void main(String[] args){
        new AddBinary().main();
    }

    public void main(){
        System.out.println(addBinary("11", "11011100"));
    }

    //pass2
    /*
    lessons learned:
    1. how to convert between string and char array[]
    2. char[] is not automatically initialized to zeros
    3. how to convert between int/byte and char(16 bit same as short)
    4. carry=(A[i]&B[i])|(A[i]&carry)|(B[i]&carry)
    */
    public String addBinary(String a, String b) {

       int n=a.length(), m=b.length();
       char[] A=a.toCharArray();
       char[] B=b.toCharArray();
       byte[] C=new byte[Math.max(n,m)];

       int diffInLen=n-m;
       if(diffInLen>0) B=align(B, diffInLen);
       else A=align(A, -diffInLen);

       //System.out.println(Arrays.toString(A)+"\n"+Arrays.toString(B));

       byte carry=0;
       for(int i=Math.max(n,m)-1;i>=0;i--){
            C[i]=(byte)( (A[i]-'0')^(B[i]-'0')^carry );
            carry=(byte)( ((A[i]-'0')&(B[i]-'0'))|((A[i]-'0')&carry)|((B[i]-'0')&carry) );
       }

       for(int i=0;i<C.length;i++) C[i]+='0';

       if(carry==0) return new String(C);
       else return "1"+new String(C);

    }

    public char[] align(char[] arr, int offset){
        char[] ret=new char[arr.length+offset];
        for(int i=0;i<ret.length;i++) ret[i]='0';
        for(int i=arr.length-1;i>=0;i--){
            ret[i+offset]=arr[i];
        }
        return ret;
    }

    //pass1

    // public String addBinary(String a, String b) {
        // Start typing your Java solution below
        // DO NOT write main() function
        // if(a==null||b==null){
            // if(a==null) return b;
            // else return a;
        // }
        // int i=a.length()-1, j=b.length()-1;
        // StringBuilder sb=new StringBuilder();
        // int carry=0;
        // while(i>=0&&j>=0){
            // if(a.charAt(i)=='1'&&b.charAt(j)=='1'){
                // if(carry==0){ sb.insert(0, '0'); carry=1;}
                // else sb.insert(0, '1');
            // }else{
                // if(a.charAt(i)=='1'||b.charAt(j)=='1'){
                    // if(carry==0) sb.insert(0, '1');
                    // else sb.insert(0, '0');
                // }else{
                    // if(carry==0) sb.insert(0, '0');
                    // else{ sb.insert(0, '1'); carry=0;}
                // }
            // }
            // i--;
            // j--;
        // }
        // if(i<0){

            // for(;j>=0;j--){
               // System.out.println(carry+" "+b.charAt(j));
               // if(b.charAt(j)=='0'){
                // if(carry==0) sb.insert(0, '0');
                // else{ sb.insert(0, '1');carry=0;}
               // }else{
                 // if(carry==0) sb.insert(0, '1');
                 // else sb.insert(0, '0');
               // }
           // }
           // if(carry==1) sb.insert(0, '1');
        // }else{
            // for(;i>=0;i--){
               // if(a.charAt(i)=='0'){
                // if(carry==0) sb.insert(0, '0');
                // else{ sb.insert(0, '1'); carry=0;}
                // carry=0;
               // }else{
                 // if(carry==0) sb.insert(0, '1');
                 // else sb.insert(0, '0');
               // }
           // }
           // if(carry==1) sb.insert(0, '1');
        // }
        // return sb.toString();
    // }
}
