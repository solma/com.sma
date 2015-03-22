package com.shuoma;
/**
 * Definition for singly-linked list.
*/ 
 
public class AddTwoNumbers {
    public static void main(String [] args){
        new AddTwoNumbers().main();
    }
    
    public void main(){
        int[] A= new int[]{5};
        int[] B = new int[]{5};
        ListNode l1=createList(A);
        ListNode l2=createList(B);
        printList(l1);
        printList(l2);
        
        ListNode sum=addTwoNumbers(l1, l2);
        printList(sum);
    }
    
    public void printList(ListNode sum){
        while(sum!=null){
            System.out.print(sum.val+"\t");
            sum=sum.next;
        }
        System.out.println();
    }
    
    public ListNode createList(int [] ar){
        ListNode head=new ListNode(ar[0]);
        ListNode cur=head;
        for(int i=1;i<ar.length;i++){
           cur.next=new ListNode(ar[i]);
           cur=cur.next;
        }
        return head;
    }
    
    //pass 2:
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        if(l2==null) return l1;
        if(l1==null) return l2;
        
        int n=length(l1);
        int m=length(l2);
        int diffInLen=n-m;
        if(diffInLen>0) extendToAlign(l2, diffInLen);
        else extendToAlign(l1, -diffInLen);
        
        int carry=0;
        ListNode prev=null,cur=l1, b=l2;
        for(int i=0;i<Math.max(n,m);i++){
            cur.val=cur.val+b.val+carry;            
            if(cur.val>9){
                cur.val-=10;
                carry=1;
            }else{
                carry=0;
            }
            prev=cur;
            cur=cur.next;
            b=b.next;            
        }
        if(carry==1) prev.next=new ListNode(1);
        return l1;
    }
    
    public int length(ListNode head){
        int cnt=0;
        ListNode cur=head; //use the local variable to iterater through the listnode
        while(cur!=null){
            cnt+=1;
            cur=cur.next;
        }
        return cnt;
    }
    
    public void extendToAlign(ListNode head, int offset){
        ListNode cur=head;
        //move to the last one
        while(cur.next!=null){
            cur=cur.next;
        }
        
        for(int i=0;i<offset; i++){
            cur.next=new ListNode(0);
            cur=cur.next;
        }
    }
    
    // pass 1
    // public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        // // Start typing your Java solution below
        // // DO NOT write main() function
        // // assume add l1 to l2
        // int carry=0;
        // ListNode head=l2, l2Prev=null;
        // while(l1!=null&&l2!=null){
            // l2.val+=l1.val+carry;
            // if(l2.val>=10){
                // l2.val-=10;
                // carry=1;
            // }else carry=0;
            // l1=l1.next;
            // l2Prev=l2;
            // l2=l2.next;
        // }
        // if(l1==null&&l2!=null){
            // l2.val+=carry;
            // while(l2.val>=10){
                // l2.val-=10;
                // if(l2.next!=null) l2.next.val+=1;
                // else l2.next=new ListNode(1);
                // l2=l2.next;
            // }
        // }
        // if(l2==null&&l1!=null){
            // //copy rest of l1 to l2
            // if(l2Prev!=null) l2Prev.next=l1;
            // else head=l1;
            
            // l1.val+=carry;
            // while(l1.val>=10){
                // l1.val-=10;
                // if(l1.next!=null) l1.next.val+=1;
                // else l1.next=new ListNode(1);
                // l1=l1.next;
            // }

        // }
        // if(l2==null&&l1==null&&carry==1) l2Prev.next=new ListNode(1);
        // return head;   
    // }
    
    
}