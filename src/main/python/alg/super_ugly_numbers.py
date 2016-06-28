"""
Write a program to find the nth super ugly number.
Super ugly numbers are positive numbers whose all prime factors are in the given prime list primes
of size k. For example, [1, 2, 4, 7, 8, 13, 14, 16, 19, 26, 28, 32] is the sequence of the
first 12 super ugly numbers given primes = [2, 7, 13, 19] of size 4.
Note:
1. 1 is a super ugly number for any given primes.
2. The given numbers in primes are in ascending order.
3. 0 < k <= 100, 0 < n <= 106, 0 < primes[i] < 1000.
"""
from alg.label import Label
from collections import deque
import heapq as hq

Label(Label.PriorityQueueT, Label.Stack, Label.LeetCode)

class SuperUglyNumbers(object):
  def nthSuperUglyNumber(self, n, primes):
    qs = [deque([i]) for i in primes]
    ret = [1]
    heap = [(primes[i], i) for i in range(len(primes))]
    hq.heapify(heap)
    for i in range(1, n):
      ret.append(self.getNext(primes, qs, heap))
    return ret[-1]

  def getNext(self, primes, qs, heap):
    v, idx = hq.heappop(heap)
    qs[idx].popleft()
    for i in range(idx, len(primes)):
      qs[i].append(v * primes[i])
    hq.heappush(heap, (qs[idx][0], idx))
    return v
