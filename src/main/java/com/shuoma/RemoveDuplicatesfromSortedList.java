package com.shuoma;
/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) {
 *         val = x;
 *         next = null;
 *     }
 * }
 */
public class RemoveDuplicatesfromSortedList {
    //second pass
    public ListNode deleteDuplicates(ListNode head) {
        if(head==null) return null;
        ListNode prev=head, cur=head.next, store=head;
        
        while(cur!=null){
            if(cur.val>prev.val){
              store.next=cur;
              store=store.next;
            } 
            prev=cur;
            cur=cur.next;
        }
        store.next=null; //tricky!!!
        
        return head;
    }
 
//first pass 
    public ListNode deleteDuplicatesFirstPass(ListNode head) {
        int noOfRep=0;
        ListNode cur=head, prev=null;
        while(cur!=null){
            if(prev!=null&&prev.val==cur.val){
                noOfRep+=1;
                if(noOfRep>0){
                    prev.next=cur.next;
                }
            }else{
                noOfRep=0;
                prev=cur;
            }
            cur=cur.next;
        }
        return head;

    }
}