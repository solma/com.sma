from random import *
import sys

_K = 0
_V = 1
_L = 2
_R = 3
_SUM = 4


class BinarySearchTree:
  def __init__(self):
    self._root = []
    self._depth = 0
    self._values = []

  def insert(self, key, value):
    if not self._root:
      self._root = [key, value, [], []]
    else:
      prev = []
      cur = self._root
      while cur:
        prev = cur
        if cur[_V] > value:
          cur = cur[_R]
        else:
          if cur[_V] < value:
            cur = cur[_L]
          else:
            return
      # print prev[_V], value
      if not prev[_R] and not prev[_L]: self._depth += 1
      if prev[_V] > value:
        prev[_R] = [key, value, [], []]
      else:
        prev[_L] = [key, value, [], []]
    self._values.append(value)
    self._values.sort(reverse=True)

  def find(self, key):
    if not self._root:
      print
      "tree is empty"
    else:
      prev = []
      cur = self.root
      while cur:
        prev = cur
        if cur[_V] > key:
          cur = cur[_R]
        else:
          if cur[_V] < key:
            cur = cur[_L]
          else:
            return cur

  def _print(self, order=0):
    if order == 0:
      self._pre_order(self._root)
      return
    """
    if order == 1:
        in_order(self._root)
    """

  def _pre_order(self, cur):
    if cur:
      print
      cur[_V],
      self._pre_order(cur[_L])
      self._pre_order(cur[_R])

  def print_(self):
    min_pos = sys.maxsize
    tree = []

    level = depth = 0

    queue = [(self._root, depth, self._values.index(self._root[_V]))]
    # queue=[(self._root, depth, 2**(self._depth-1)*value_width)]
    cur, level, pos = queue[0]

    tree.append([(pos, cur[_V])])
    min_pos = min(pos, min_pos)

    while depth < self._depth:
      father_line = []
      bridge_line = ""
      child_line = []

      last_child_pos = 0
      last_father_pos = 1
      while level == depth:
        if cur[_L] or cur[_R]:
          father_line.append(pos)
          children = [cur[_L], cur[_R]]

          for i in range(len(children)):
            if children[i]:
              child_pos = self._values.index(children[i][_V])
              # child_pos=pos+(-1)**(i+1)*(max(2**(self._depth-depth-2),1)*value_width)
              min_pos = min(child_pos, min_pos)

              queue.append((children[i], level + 1, child_pos))
              child_line.append((
                queue[-1][2], str(queue[-1][0][_V]) + "," + str(queue[-1][0][_SUM])))
              if i == 0:
                bridge_line += ''.join([' '] * (
                  child_pos - max(last_child_pos, last_father_pos))) + ''.join(
                  ['-'] * (pos + 1 - child_pos))
              else:
                if not children[0]:  # left child is empty
                  bridge_line += ''.join(
                    [' '] * (pos - max(last_child_pos, last_father_pos) - 1))
                  bridge_line += ' '
                bridge_line += ''.join(['-'] * (child_pos + 1 - pos))
              last_child_pos = child_pos
              # print pos, child_pos, depth
          last_father_pos = pos

        del queue[0]
        if queue:
          cur, level, pos = queue[0]
        else:
          break

      tree.append([])
      for i in range(len(father_line)):
        tree[-1].append((father_line[i], '|'))

      tree.append(bridge_line)

      tree.append([])
      for i in range(len(child_line)):
        tree[-1].append((child_line[i][0], '|'))

      tree.append([])
      for i in range(len(child_line)):
        tree[-1].append((child_line[i][0], child_line[i][1]))

      if not queue: break
      depth += 1

    if min_pos > 5: min_pos -= 5
    value_width = 2
    for line in tree:
      if isinstance(line, str):
        print
        line[min_pos:]
      else:
        for i in range(len(line)):
          if i == 0:
            print
            "%*s" % (line[i][0] - min_pos, line[i][1]),
          else:
            print
            "%*s" % (line[i][0] - line[i - 1][0], line[i][1]),
        print

  def max_leaf_depth_diff(self):
    mm = [0, sys.maxsize]
    self._dfs_diff(self._root, 0, mm)
    print
    mm[0], mm[1], mm[0] - mm[1]

  def _dfs_diff(self, node, depth, mm):
    if node:
      if not node[_L] and not node[_R]:
        mm[0] = max(depth, mm[0])
        mm[1] = min(depth, mm[1])
      self._dfs_diff(node[_L], depth + 1, mm)
      self._dfs_diff(node[_R], depth + 1, mm)

  def _countTree(self, N):
    """
    For the key values 1...numKeys, how many structurally unique
    binary search trees are possible that store those keys?
    Strategy: consider that each value could be the root.
    Recursively find the size of the left and right subtrees.
    """
    if N <= 1:
      return 1
    sum = 0
    for i in range(1, N + 1):
      sum += self._countTree(i - 1) * self._countTree(N - i)
    return sum

  def _printAllBinaryTree(self, N, min, max):
    """
    For the key values 1...numKeys, how many structurally unique
    binary search trees are possible that store those keys?
    print all these trees in the inorder
    Strategy: consider that each value could be the root.
    Recursively find the size of the left and right subtrees.
    """
    if min > max:
      return [""]
    bst = []
    for i in range(min, max + 1):
      left = self._printAllBinaryTree(N, min, i - 1)
      right = self._printAllBinaryTree(N, i + 1, max)
      print
      i, left, right
      for l in left:
        for r in right:
          bst.append(l + str(i) + r)
    return bst

  def _max_sum_subtree_recur(self, node, max_sum):
    if not node:
      return -sys.maxsize
    else:
      left_sum = self._max_sum_subtree_recur(node[_L], max_sum)
      right_sum = self._max_sum_subtree_recur(node[_R], max_sum)

      if left_sum > -sys.maxsize and right_sum > -sys.maxsize:
        sum = left_sum + right_sum + node[_V]
      else:
        if left_sum > -sys.maxsize or right_sum > -sys.maxsize:
          if left_sum > -sys.maxsize:
            sum = left_sum + node[_V]
          else:
            sum = right_sum + node[_V]
        else:
          sum = node[_V]

      max_sum = max([left_sum, right_sum, sum, max_sum])
      node.append(max_sum)
      return sum

  def _max_sum_subtree(self):
    max_sum = -sys.maxsize
    self._max_sum_subtree_recur(self._root, max_sum)
    print
    "sum=" + str(self._root[_SUM])


bst = BinarySearchTree()

# li=[2, 7, 8, 1, 4]
# """
li = []
for i in range(10):
  li.append(randint(-10, 10))
# """
print
li

for i in range(len(li)):
  bst.insert(i, li[i])
print
print
bst._values
print
bst._depth

print
bst._countTree(3)

bst._max_sum_subtree()

bst.print_()

N = 3
print
bst._printAllBinaryTree(N, 1, N)
# bst.max_leaf_depth_diff()
