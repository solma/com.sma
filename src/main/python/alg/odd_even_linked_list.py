"""
Given a singly linked list, group all odd nodes together followed by the even nodes.
Please note here we are talking about the node number and not the value in the nodes.

You should try to do it in place. The program should run in
O(1) space complexity and O(nodes) time complexity.

Example:
Given 1->2->3->4->5->NULL,
return 1->3->5->2->4->NULL.

Note:
The relative order inside both the even and odd groups should remain as it was in the input.
The first node is considered odd, the second node even and so on ...
"""

from alg.label import Label

Label(Label.LinkedList)

class OddEvenLinkedList(object):
  def oddEvenList(self, head):
    if head is None:
      return head

    odd = head
    even = odd.next
    even_head = even

    while even is not None:
      odd.next = even.next
      if odd.next is None:
        break
      odd = odd.next
      even.next = odd.next
      even = even.next

    # concat odd list with even list
    odd.next = even_head
    return head
