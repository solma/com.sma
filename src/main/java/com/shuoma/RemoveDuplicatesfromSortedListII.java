package com.shuoma;
/**
 * Definition for singly-linked list.
 * 
 */
public class RemoveDuplicatesfromSortedListII {
    public static void main(String[] args){
        new RemoveDuplicatesfromSortedListII().main();
    }
    
    public void main(){
        int[] array={2,2,3};
        System.out.println(deleteDuplicates(ListNode.buildList(array)) );
    }

  
  //second pass
  public ListNode deleteDuplicates(ListNode head) {
	  ListNode prev = new ListNode(0);
      prev.next = head;
      head = prev;
      
      
      while(prev.next!=null){
          ListNode cur=prev.next;
          while(cur.next!=null && cur.next.val==cur.val){
              cur=cur.next;
          }
          if(cur!=prev.next){
              prev.next=cur.next;
          }else{
              prev=cur;   
          }
      }
      return head.next;  
	  
	  
	   //in-place
//        if(head==null) return null;
//        ListNode prevprev=new ListNode(-1), prev=head, cur=head.next;
//        prevprev.next=prev;
//        
//        ListNode superHead=prevprev;
//        boolean dup=false;
//        while(cur!=null){
//            if(cur.val==prev.val){
//               dup=true;
//               prev.next=cur.next; //delete cur
//               cur=cur.next;
//            }else{
//              if(dup){
//                  prevprev.next=cur;//delete prev
//              }else{
//                  prevprev.next=prev;
//                  prevprev=prev;
//              }
//               prev=cur;
//                cur=cur.next;
//              dup=false;
//            }
//        }
//        if(dup) prevprev.next=null;
//        
//        return superHead.next;
    }
    
    //first pass
    public ListNode deleteDuplicatesFirstPass(ListNode head) {
        ListNode cur=head, pseudoHead=new ListNode(0), prev=null;
        pseudoHead.next=head;
        boolean del;
        while(cur!=null){
            del=false;
            while(cur.next!=null&&cur.val==cur.next.val){
                cur=cur.next;
                del=true;
            }
            if(del){ 
                if(prev!=null) prev.next=cur.next;
                else pseudoHead.next=cur.next;
            }
            else prev=cur;
            cur=cur.next;
        }
        return pseudoHead.next;
    }
}