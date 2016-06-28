"""
Given a data stream input of non-negative integers a1, a2, ..., an, ...,
summarize the numbers seen so far as a list of disjoint intervals.

For example, suppose the integers from the data stream are 1, 3, 7, 2, 6, ...,
then the summary will be:

[1, 1]
[1, 1], [3, 3]
[1, 1], [3, 3], [7, 7]
[1, 3], [7, 7]
[1, 3], [6, 7]

Follow up:
What if there are lots of merges and the number of disjoint intervals are
small compared to the data stream's size?
"""

from data_structure import Interval
import bisect
from alg.label import Label

Label(Label.Array, Label.BinarySearch, Label.TreeSet, Label.LeetCode)

class SummaryRanges(object):

  def __init__(self):
    self._intervals = []

  def addNum(self, val):
    def getStarts(intervals):
      return [ele.start for ele in intervals]

    # print 'val: %d'%val
    interval = Interval(val, val)
    floor_idx = bisect.bisect_left(getStarts(self._intervals), val) - 1
    # print 'floor idx: %d'%floor_idx
    if floor_idx >= 0:
      floor_interval = self._intervals[floor_idx]
      # print 'floor interval: %s'%floor_interval
      if val <= floor_interval.end:
        return
      if val == floor_interval.end + 1:
        interval.start = floor_interval.start
        del self._intervals[floor_idx]
        floor_idx -= 1

    ceiling_idx = bisect.bisect_right(getStarts(self._intervals), val)
    # print 'ceiling idx: %d'%ceiling_idx
    if ceiling_idx < len(self._intervals):
      ceiling_interval = self._intervals[ceiling_idx]
      # print 'ceiling interval: %s'%ceiling_interval
      if val == ceiling_interval.start - 1:
        interval.end = ceiling_interval.end
      del self._intervals[ceiling_idx]

    self._intervals.insert(floor_idx + 1, interval)

  def getIntervals(self):
    return self._intervals

obj = SummaryRanges()
for val in range(100):
  obj.addNum(val)
  print ' '.join(str(ele) for ele in obj.getIntervals())
