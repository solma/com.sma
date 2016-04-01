package com.sma.alg;

import static com.sma.annotation.Tag.DataStructure.StringT;

import com.sma.annotation.Tag;

/**  Given a byte array, which is an encoding of characters.
  *  Here is the rule:
  * a. If the first bit of a byte is 0, that byte stands for a one-byte character
    b. If the first bit of a byte is 1, that byte and its following byte
      together stand for a two-byte character
      Now implement a function to decide if the last character is a one-byte
      character or a two-byte character
Constraint: You must scan the byte array from the end to the start.
Otherwise it will be very trivial.
 *
 */

@Tag(dss = StringT)
public class ByteCoding{
    public static void main(String[] args){
       new ByteCoding().main();
    }

    public void main(){
        byte[] data={3 -2, -5};
        if ( data[data.length-1]>>7 > 0 ) System.out.println("false");
        else System.out.println( lastCharacterIsOneByte(data) );
    }

    public boolean lastCharacterIsOneByte(byte[] data){
        boolean[] dp=new boolean[data.length+2];
        int n=dp.length;
        dp[n-1]=false;
        dp[n-2]=true;
        for(int i=n-3;i>=0;i--){
            boolean isOne = ((data[i]>>7)&1)>0;
            if (isOne) dp[i] = dp[i+2];
            else dp[i] = dp[i+1];
        }
        return dp[0];
    }
}
