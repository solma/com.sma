package com.shuoma;

public class SwapNodeInPairs {
    //second pass
    public ListNode swapPairs(ListNode head) {
        if(head==null) return null;
        ListNode prev=null;
        ListNode ptr1=head, ptr2=head.next;
        while(ptr1!=null&&ptr2!=null){
            if(prev!=null){
                prev.next=ptr2;
            }else{
                head=ptr2;
            }
            ptr1.next=ptr2.next;
            ptr2.next=ptr1;
            prev=ptr1;
            ptr1=ptr1.next;
            if(ptr1!=null) ptr2=ptr1.next;
            else ptr2=null;
        }
        
        return head;
    }
    
    //first pass
    public ListNode swapPairsFirstPass(ListNode head) {
        if(head==null || head.next==null) return head;
        ListNode cur=head, next=cur.next, prev=null;
        head=next;
        while(cur!=null&&next!=null){
            if(prev!=null) prev.next=next;
            cur.next=next.next;
            next.next=cur;
            prev=cur;
            cur=cur.next ; //advance cur;            
            if(cur!=null) next=cur.next; //advance next;
        }
        return head;
    }
}