package com.shuoma;

public class LinkListCycle {
    public boolean hasCycle(ListNode head) {
        ListNode ptr1=head, ptr2=head;
        while(true){
            for(int i=0;i<2;i++){
                if(ptr2!=null)  ptr2=ptr2.next;
                else return false;
            }
            ptr1=ptr1.next;
            if(ptr2==ptr1) return true;
        }
    }
}