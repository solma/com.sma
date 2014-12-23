//delete duplicates in the linked list without extra space
//source: crack the interview

public class RemoveDuplicates {
    public static void main(String[] args){
        new RemoveDuplicates().main();
    }
    
    public void main(){
        int[] vals=new int[]{2,0,2,1,1,1};
        ListNode n=new ListNode(vals[0]);
        ListNode prev=n;
        for(int i=1;i<vals.length;i++){
            ListNode c=new ListNode(vals[i]);
            prev.next=c;
            prev=c;
        }
       
        removeDuplicates(n);
        printList(n);
        
    }
    
    public class ListNode {
          int val;
          ListNode next;
          ListNode(int x) {
              val = x;
              next = null;
          }
          public String toString(){
            return String.valueOf(val);
          }
    }
    
    public void printList(ListNode head){
        while(head!=null){
            System.out.println(head.val);
            head=head.next;
        }
    }

    
    public void removeDuplicates(ListNode head){
        ListNode curHead=head;
        while(curHead!=null){
            ListNode prev=curHead, cur=curHead.next;
            while(cur!=null){
                if(curHead.val==cur.val) prev.next=cur.next; //delete duplicate
                else prev=cur;
                cur=cur.next;//advance cur
            }
            curHead=curHead.next;
        }
    }


   
}