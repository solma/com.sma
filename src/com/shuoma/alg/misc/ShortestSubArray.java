//source: http://mp.weixin.qq.com/mp/appmsg/show?__biz=MjM5ODIzNDQ3Mw==&appmsgid=10000220&itemidx=1&sign=6ba8275053d9c400e9535f392b4cbaa7 
//given a array of postivie numbers, find the shortest subarray such that the sum of the element in the subarray is greater than S

public class ShortestSubArray{
    public static void main(String [] args){
        new ShortestSubArray().shortestSubArray(new int[]{5,1,3,5,10,7,4,9,2,8}, 18 );
    }
    
    public void shortestSubArray(int[] A, int S){
        int n=A.length;
        if(n<1) return;
        int [] sum=new int[n];
        sum[0]=A[0];
        for(int i=1;i<n;i++) sum[i]=A[i]+sum[i-1];
        
        int minLen=n, i=0, j=0;
        while(j<n&&i<n){
            if(sum[i]-sum[j]<S) i++;
            else{
                j++;
                if(i-j+1<minLen) minLen=i-j+1;
            }
        }
        System.out.println(minLen);
    }
} 