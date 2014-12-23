import java.util.*;
//given a number n remove k digits such that the resulting number is minimized
//source: wechat
public class RemoveToProduceSmallestNumber{
    public static void main(String[] args){
        int n=Integer.parseInt(args[0]),  k=Integer.parseInt(args[1]);
        new RemoveToProduceSmallestNumber().main(n, k);
    }
    
    public void main(int n, int k){
        remove(n,k);
    }
    
    public void remove(int n, int k){
        //put n into an array
        ArrayList<Integer> nList=new ArrayList<Integer>();
        while(n>0){
            nList.add(n%10);
            n/=10;
        }
        int[] nArray=new int[nList.size()];
        for(int i=0;i<nArray.length;i++){
            nArray[i]=nList.get(nArray.length-1-i);
        }
        //System.out.println(Arrays.toString(nArray));
        //System.out.println(nList);
        
        //remove k digits
        int cntRemovedDigits=0;
        int idx=0;
        int preIdx=0;
        while(cntRemovedDigits<k&&idx<nArray.length-1){
            while(nArray[preIdx++]==-1);
            if(nArray[idx]>nArray[idx+1]){
                cntRemovedDigits+=1;
                nArray[idx]=-1;
                if(preIdx>0){
                    preIdx--;
                    //idx--;
                }
            }else{
                idx++;
            }
            //System.out.println(nArray[preIdx]+", "+nArray[idx]);
        }
        
        //shift array to remove digits
        int deletedCnt=0;
        for(int i=0;i<nArray.length;i++){
            if(nArray[i]==-1) deletedCnt++;
            else{
                nArray[i-deletedCnt]=nArray[i];
            }
        }
        for(int i=0;i<nArray.length-k;i++){
            System.out.print(nArray[i]);
        }
        System.out.println();
        
        
    }
}