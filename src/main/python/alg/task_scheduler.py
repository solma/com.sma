"""
Given a char array representing tasks CPU need to do. It contains capital letters A to Z where
different letters represent different tasks.
Tasks could be done without original order. Each task could be done in one interval.
For each interval, CPU could finish one task or just be idle.

However, there is a non-negative cooling interval n that means between two same tasks,
there must be at least n intervals that CPU are doing different tasks or just be idle.

You need to return the least number of intervals the CPU will take to finish all the given tasks.

Example 1:
Input: tasks = ['A','A','A','B','B','B'], n = 2
Output: 8
Explanation: A -> B -> idle -> A -> B -> idle -> A -> B.
Note:
The number of tasks is in the range [1, 10000].
The integer n is in the range [0, 100].
"""

from alg.label import *
Label(Label.Greedy, Label.PriorityQueueT, Label.LeetCode)

class Solution(object):
  def leastInterval(self, tasks, n):
    """
    :type tasks: List[str]
    :type n: int
    :rtype: int
    """
    from collections import Counter
    num_finished_task, task_len = 0, len(tasks)
    counter = Counter(tasks)
    ret = []
    while num_finished_task < task_len:
      candidates = counter.most_common(n)
      is_any = False
      for num, _ in candidates:
        if num in ret[-n:]:
          continue
        num_finished_task += 1
        ret.append(num)
        is_any = True
        counter.subtract([num])
        break
      if not is_any:
        ret.append('idle')
    print(ret)
    return len(ret)

ins = Solution()
for case in [
  [],
  ['A', 'A'],
  ['A','A','A','B','B','B'],
]:
  print(ins.leastInterval(case, 2))
