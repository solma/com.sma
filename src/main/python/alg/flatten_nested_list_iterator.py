"""
Given a nested list of integers, implement an iterator to flatten it.
Each element is either an integer, or a list -- whose elements may also be integers or other lists.

Example 1:
Given the list [[1,1],2,[1,1]],
By calling next repeatedly until hasNext returns false, the order of elements returned by
next should be: [1,1,2,1,1].

Example 2:
Given the list [1,[4,[6]]],
By calling next repeatedly until hasNext returns false, the order of elements returned by
next should be: [1,4,6].
"""

# """
# This is the interface that allows for creating nested lists.
# You should not implement it, or speculate about its implementation
# """
# class NestedInteger(object):
#   def isInteger(self):
#    """
#    @return True if this NestedInteger holds a single integer, rather than a nested list.
#    :rtype bool
#    """
#
#   def getInteger(self):
#    """
#    @return the single integer that this NestedInteger holds, if it holds a single integer
#    Return None if this NestedInteger holds a nested list
#    :rtype int
#    """
#
#   def getList(self):
#    """
#    @return the nested list that this NestedInteger holds, if it holds a nested list
#    Return None if this NestedInteger holds a single integer
#    :rtype List[NestedInteger]
#    """
# Your NestedIterator object will be instantiated and called as such:
# i, v = NestedIterator(nestedList), []
# while i.hasNext(): v.append(i.next())

from alg.label import Label
Label(Label.Iterator, Label.Recursion, Label.LeetCode)

class NestedIterator(object):

  def __init__(self, nestedList):
    """
    Initialize your data structure here.
    :type nestedList: List[NestedInteger]
    """
    self._list = []
    self._idx = 0
    for nested_integer in nestedList:
      if nested_integer.isInteger():
        self._list.append(nested_integer)
      else:
        nested_iterator = NestedIterator(nested_integer.getList())
        if nested_iterator.hasNext():
          self._list.append(nested_iterator)

  def next(self):
    cur_node = self._list[self._idx]
    # cur node is a NestedIterator
    if isinstance(cur_node, NestedIterator):
      ret = cur_node.next()
      if not cur_node.hasNext():
        self._idx +=1
      return ret
    else: # cur node is a NestedInteger that is an integer
      ret = cur_node.getInteger()
      self._idx += 1
      return ret

  def hasNext(self):
    return self._idx < len(self._list)


