def make_list(li):
  nodes = map(ListNode, li)
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
  if l1 == l2 == None:
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

class Interval(object):
  def __init__(self, s=0, e=0):
    self.start = s
    self.end = e

  def __str__(self):
    return '(%d, %d)'%(self.start, self.end)
