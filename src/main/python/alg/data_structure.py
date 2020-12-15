def make_list(li):
  nodes = [ListNode(x) for x in li]
  for i in range(1, len(nodes)):
    nodes[i - 1].next = nodes[i]
  return nodes[0]

def list2str(head):
  ret = ''
  while head:
    ret += str(head.val) + '->'
    head = head.next
  ret += 'NULL'
  return ret

def is_equal_list(l1, l2):
  if l1 is None and l2 is None:
    return True
  if l1 is None or l2 is None:
    return False
  return l1.val == l2.val and is_equal_list(l1.next, l2.next)

class ListNode(object):
  def __init__(self, x):
    self.val = x
    self.next = None

class TreeNode(object):
  def __init__(self, x):
    self.val = x
    self.left = None
    self.right = None

class SegmentTreeNode(object):
  def __init__(self, value, idx_range, left = None, right = None):
    self.sum = value
    self.left = left
    self.right = right
    self.range = idx_range
