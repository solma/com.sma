package com.shuoma.ds.linkedlist;

public class ListOperation {

	
	public static void main(String[] args) {
		ListNode l1=ListNode.buildList(new int[]{1,2,3,6});
		ListNode l2=ListNode.buildList(new int[]{4,5});
		
		//test split
//		System.out.println(splitAlternatively(l1));
//		System.out.println(l1);
		
		
		//test join
//		joinAlternatively(l1, l2);
//		System.out.println(l1);
		
		//test swap
		System.out.println(swapAlternatively(l1));
	}
	
	/**
	 * 
	 * @param li, e.g. 1->2->3->4->5
	 * @return 2->4 and li becomes 1->3->5
	 */
	public static ListNode splitAlternatively(ListNode li){
		ListNode cur=li, p2Head=null;
		while(cur!=null){
			ListNode p2=cur.next;
			if(p2Head==null) p2Head=p2;
			if(p2!=null) cur.next=p2.next;
			cur=cur.next;
			if(cur!=null) p2.next=cur.next;
		}
		return p2Head;
	}
	
	/**
	 * @param li, e.g. 1->2->3->4->5
	 * @return 2->1->4->3->5 
	 */
	public static ListNode swapAlternatively(ListNode li){
		if(li==null) return null;
		
		ListNode prev=li,pseudo=new ListNode(0);
		ListNode  cur, after, before;
		before=pseudo;
		
		cur=prev.next;
		while(prev!=null&&cur!=null){
			after=cur.next;
			cur.next=prev;
			before.next=cur;
			before=prev;
			
			prev=after;
			if(prev!=null) cur=prev.next;
		}
		if(before!=null) before.next=prev;
		return pseudo.next;
	}
	
	/**
	 * 
	 * @param l1
	 * @param l2
	 * @return l1 (adding l2 list to l1)
	 * e.g. l1: 5->3, l2: 2->4->1, return l1: 5->2->3->4->1 
	 */
	public static ListNode joinAlternatively(ListNode l1, ListNode l2){
		if(l1==null) return l2;
		
		ListNode p1=l1, p2=l2;
		while(p1!=null&&p2!=null){
			ListNode p1Next=p1.next;
			p1.next=p2;
			p1=p1Next;
			ListNode p2Next=p2.next;
			if(p1!=null) p2.next=p1;
			p2=p2Next;
		}
		return l1;
	}

}
