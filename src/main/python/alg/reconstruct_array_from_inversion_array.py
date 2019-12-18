"""
Given a number N, and an array A[] which represent the inversion count
of some permutation [a1, a2, ... an] of [1, ..., N]. That is, A[i] means
that there are A[i] numbers from [aj, an] that is smaller than ai.
Return the permutation [a1, a2, ... an].
"""
from alg.label import Label
Label(Label.Queue, Label.QuadraticTime, Label.LinearithmicTime, Label.Interview)

class ReconstructArrayFromInversionArray(object):
  def ReconstructArrayFromInversionArray(self, inversion):
    n = len(inversion)
    ret, natural = [], range(1, n + 1)
    for i in range(n):
      idx = inversion[i]
      ret.append(natural[idx])
      del natural[idx] # O(n)
    return ret

  def ReconstructArrayFromInversionArrayBinaryTreeBased(self, inversion):
    def BuildBinaryTree(lo, hi):
      if lo > hi:
        return None
      m = (lo + hi) >> 1
      root = BinaryTreeNode(m)
      root.left = BuildBinaryTree(lo, m - 1)
      root.right = BuildBinaryTree(m + 1, hi)
      root.size = hi - lo + 1
      return root

    n = len(inversion)
    root = BuildBinaryTree(1, n)
    ret = []
    for x in inversion:
      selected = root.Select(x + 1)
      ret.append(selected.value)
      print(ret)
      root = root.Delete(selected)
    return ret

class BinaryTreeNode(object):
  def __init__(self, value):
    self.value = value
    self.left = None
    self.right = None
    self.size = 1 # num of nodes in the tree

  def Select(self, size):
    def Select(cur_node, size):
      if cur_node is None:
        return cur_node
      left_size = cur_node.left.size if cur_node.left else 0
      if left_size + 1 == size:
        return cur_node
      elif left_size + 1 < size:
        return Select(cur_node.right, size - left_size - 1)
      else:
        return Select(cur_node.left, size)
    return Select(self, size)

  def Delete(self, node):
    def Delete(cur_node, delete_node):
      if cur_node is None:
        return cur_node
      elif cur_node.value > delete_node.value:
        cur_node.left = Delete(cur_node.left, delete_node)
        return cur_node
      elif cur_node.value < delete_node.value:
        cur_node.right = Delete(cur_node.right, delete_node)
        return cur_node
      else:
        if cur_node.left and cur_node.right:
          # find the successor:
          successor = cur_node.right
          parent = cur_node
          while successor:
            parent = successor
            successor = successor.left
          if cur_node.right != successor:
            parent.left = successor.right
          else:
            parent.right = successor.right
          # delete cur_node by swap with successor
          cur_node.value = successor.value
          cur_node.size -= 1
          return cur_node
        else: # left or right is None
          return cur_node.left or cur_node.right
    return Delete(self, node)

ins = ReconstructArrayFromInversionArray()
print(ins.ReconstructArrayFromInversionArrayBinaryTreeBased([0, 1, 0, 0]))




