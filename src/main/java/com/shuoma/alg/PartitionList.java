package com.shuoma.alg;
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
public class PartitionList {
    public static void main(String[] args){
        new PartitionList().main();
    }

    public void main(){
        int[] vals=new int[]{1,4,3,2,5,2};
        ListNode n=new ListNode(vals[0]);
        ListNode prev=n;
        for(int i=1;i<vals.length;i++){
            ListNode c=new ListNode(vals[i]);
            prev.next=c;
            prev=c;
        }

        n=partition(n, 3);
        while(n!=null){
            System.out.println(n.val);
            n=n.next;
        }
    }

    public class ListNode {
      int val;
      ListNode next;
      ListNode(int x) {
          val = x;
          next = null;
      }
      public String toString(){ return String.valueOf(val);}
    }

    //second pass
    public ListNode partition(ListNode head, int x) {
        ListNode beforeListHead=null, beforeListTail=null, afterListHead=null, afterListTail=null;
        ListNode cur=head;
        while(cur!=null){
            if(cur.val>=x){
                if(afterListTail==null){
                    afterListTail=cur;
                    afterListHead=afterListTail;
                }else{
                    afterListTail.next=cur;
                    afterListTail=afterListTail.next;
                }
            }else{
                if(beforeListTail==null){
                    beforeListTail=cur;
                    beforeListHead=beforeListTail;
                }else{
                    beforeListTail.next=cur;
                    beforeListTail=beforeListTail.next;
                }
            }
            cur=cur.next;
        }
        if(afterListTail!=null) afterListTail.next=null; //pay attention to this line

        if(beforeListHead!=null){
            beforeListTail.next=afterListHead;
            return beforeListHead;
        }else{
            return afterListHead;
        }
    }

    //first pass
    public ListNode partition1(ListNode head, int x) {
        ListNode cur=head, root=new ListNode(1);
        root.next=head;
        ListNode storePtr=root, prev=root;
        boolean beforeFirstSwap=true;
        while(cur!=null){
            if(cur.val<x){
                if(!beforeFirstSwap){
                    prev.next=cur.next;
                    ListNode tmp=cur;
                    cur=prev.next;
                    tmp.next=storePtr.next;
                    storePtr.next=tmp;
                }else{
                    prev=cur;
                    cur=cur.next;
                }
                storePtr=storePtr.next;
            }else{
                beforeFirstSwap=false;
                prev=cur;
                cur=cur.next;
            }
        }
                            //System.out.println(root.next+" "+root.next.next);

        return root.next;
    }
}
