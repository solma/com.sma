"""
Given a singly linked list, return a random node's value from the linked list.
Each node must have the same probability of being chosen.

Follow up:
What if the linked list is extremely large and its length is unknown to you?
Could you solve this efficiently without using extra space?

Example:

// Init a singly linked list [1,2,3].
ListNode head = new ListNode(1);
head.next = new ListNode(2);
head.next.next = new ListNode(3);
Solution solution = new Solution(head);

// getRandom() should return either 1, 2, or 3 randomly.
Each element should have equal probability of returning.
solution.getRandom();
"""

from alg.label import Label
Label(Label.Math, Label.ReservoirSampling, Label.LinearithmicTime, Label.LeetCode)

# Definition for singly-linked list.
# class ListNode(object):
#     def __init__(self, x):
#         self.val = x
#         self.next = None

class LinkedListRandomNode(object):

  def __init__(self, head):
    self.head = head

  def getRandom(self):
    import random
    ans, cnt = 0, 0
    head = self.head
    while head:
      if random.randint(0, cnt) == 0:
        ans = head.val
      head = head.next
      cnt += 1
    return ans
