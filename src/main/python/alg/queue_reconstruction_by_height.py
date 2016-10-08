"""
Suppose you have a random list of people standing in a queue. Each person is described
by a pair of integers (h, k), where h is the height of the person and k is
the number of people in front of this person who have a height greater than or equal to h.
Write an algorithm to reconstruct the queue.

Note:
The number of people is less than 1,100.

Example

Input:
[[7,0], [4,4], [7,1], [5,0], [6,1], [5,2]]

Output:
[[5,0], [7,0], [5,2], [6,1], [4,4], [7,1]]
"""
from alg.label import Label
Label(Label.Queue, Label.Greedy, Label.QuadraticTime, Label.LeetCode)

class QueueReconstructionByHeight(object):
  def reconstructQueue(self, people):
    import heapq as hq
    heap = []
    rest = [[t[0], t[1], t[1]] for t in people]
    for t in rest:
      if t[1] == 0:
        hq.heappush(heap, t)

    ret = []
    while heap:
      chosen = hq.heappop(heap)
      ret.append(chosen[:-1])

      tmp = []
      for t in rest:
        if t[0] <= chosen[0]:
          t = [t[0], t[1], t[2] - 1]
          if t[2] == 0:
            hq.heappush(heap, [t[0], t[1], 0])
          else:
            tmp.append(t)
        else:
          tmp.append(t)
      rest = tmp
    return ret


ins = QueueReconstructionByHeight()
print ins.reconstructQueue([[7,0], [4,4], [7,1], [5,0], [6,1], [5,2]])
