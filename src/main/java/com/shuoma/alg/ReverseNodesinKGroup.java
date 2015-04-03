package com.shuoma.alg;

import static com.shuoma.annotation.Tag.DataStructure.LinkedList;
import static com.shuoma.annotation.Tag.Difficulty.D2;
import static com.shuoma.annotation.Tag.Source.LeetCode;

import com.shuoma.annotation.Tag;

@Tag(dl = D2, dss = LinkedList, source = LeetCode)
public class ReverseNodesinKGroup {
  public class ListNode {
    int val;
    ListNode next;

    ListNode(int x) {
      val = x;
      next = null;
    }

    public String toString() {
      return String.valueOf(val);
    }
  }

  public static void main(String[] args) {
    new ReverseNodesinKGroup().main();
  }

  public void main() {
    int[] vals = new int[] {1, 4, 3, 2, 5, 6};
    ListNode n = new ListNode(vals[0]);
    ListNode prev = n;
    for (int i = 1; i < vals.length; i++) {
      ListNode c = new ListNode(vals[i]);
      prev.next = c;
      prev = c;
    }

    n = reverseKGroup(n, 3);
    print(n);
  }

  public void print(ListNode n) {
    System.out.print("\t");
    while (n != null) {
      System.out.print(n.val);
      n = n.next;
    }
    System.out.println();
  }

  public ListNode reverseKGroup(ListNode head, int k) {
    if (head == null || k <= 0)
      return null;
    ListNode dummy = new ListNode(0);
    dummy.next = head;
    head = dummy;
    for (int i = 0; head.next != null; head = head.next)
      if (i++ % k == 0) {
        System.out.print("Before Reverse: i=" + i + " head=" + head.val + " next=");
        if (head.next == null)
          System.out.print("null");
        else
          System.out.print(head.next.val);
        print(head);

        head.next = reverse(head.next, k);

        System.out.print("After Reverse: i=" + i + " head=" + head.val + " next=");
        if (head.next == null)
          System.out.print("null");
        else
          System.out.print(head.next.val);
        print(head);
      }
    return dummy.next;
  }

  //reverse k nodeMap starting from node
  public ListNode reverse(ListNode node, int k) {
    ListNode ptr = node;
    int i = 0;
    for (; ptr != null && i < k; i++)
      ptr = ptr.next;
    if (i < k)
      return node;
    //System.out.println(ptr+" "+node);
    ListNode prev = null;
    ListNode cur = node;

    for (; i > 0; i--) {
      ListNode tmp = cur;
      cur = cur.next;
      tmp.next = prev;
      prev = tmp;
    }
    node.next = ptr;
    //System.out.println(" "+node);
    return prev;
  }


  // public ListNode reverseKGroup(ListNode head, int k) {
  // if(head==null||head.next==null||k==1) return head;
  // int idx=1;
  // ListNode start=head, root=new ListNode(1), prevStart=root;
  // root.next=start;
  // ListNode cur=start.next, prev=start;

  // while(cur!=null){

  // while(cur!=null&&idx<k){
  // ListNode tmp=cur;
  // cur=cur.next;
  // tmp.next=prev;
  // prev=tmp;
  // idx++;
  // }
  // //connecting the reversed list with the before
  // prevStart.next=prev;
  // //connecting the reversed list with the after
  // start.next=cur;
  // if(idx<k){//reverse back
  // cur=prev.next;
  // start=prev;
  // while(cur!=null){
  // ListNode tmp=cur;
  // cur=cur.next;
  // tmp.next=prev;
  // prev=tmp;
  // }
  // //connecting the reversed list with the before
  // prevStart.next=prev;
  // //connecting the reversed list with the after
  // start.next=cur;
  // break;
  // }
  // //initialize for next round
  // prevStart=start;
  // start=cur;
  // prev=start;
  // if(cur!=null) cur=start.next;
  // else break; //only one node left
  // idx=1;
  // }

  // return root.next;


  // }
}
