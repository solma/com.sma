"""
Serialization is the process of converting a data structure or object into a sequence of bits so that it can be stored
in a file or memory buffer, or transmitted across a network connection link to be reconstructed later in the same or
another computer environment.

Design an algorithm to serialize and deserialize a binary tree. There is no restriction on how your
serialization/deserialization algorithm should work. You just need to ensure that a binary tree can be serialized to
a string and this string can be deserialized to the original tree structure.

For example, you may serialize the following tree
    1
   / \
  2   3
     / \
    4   5
as "[1,2,3,null,null,4,5]", just the same as how LeetCode OJ serializes a binary tree. You do not necessarily need to
follow this format, so please be creative and come up with different approaches yourself.
Note: Do not use class member/global/static variables to store states. Your serialize and deserialize algorithms
should be stateless.
"""

from alg.data_structure import TreeNode
from alg.label import Label

Label(Label.BinaryTree, Label.LeetCode)

class SerializeAndDeserializeBinaryTree:
  # pre-order with left subtree prefixed with its size
  def serialize(self, root):
    if root is None:
      return None
    ret = [str(root.val)]
    left = self.serialize(root.left)
    if not left is None:
      left_list = left.split(',')
      ret += [str(len(left_list))] + left_list
    else:
      ret.append('0')
    right = self.serialize(root.right)
    if not right is None:
      ret += right.split(',')
    return ','.join(ret)

  def deserialize(self, data):
    def build_tree_recur(data):
      if not data:
        return None
      root = TreeNode(data[0])
      left_end = 1 + int(data[1])
      root.left = build_tree_recur(data[2:left_end + 1])
      root.right = build_tree_recur(data[left_end + 1:])
      return root

    return build_tree_recur(data.split(',')) if data else None
