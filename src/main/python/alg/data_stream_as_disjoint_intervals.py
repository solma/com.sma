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

import bisect

from alg.label import Label

Label(Label.Array, Label.BinarySearch, Label.UnionFind, Label.LeetCode)

class SummaryRangesUnionFind(object):

  def __init__(self):
    self._cluster_lead = {}
    self._cluster_members = {}
    self._cluster_boundaries = {}

  def get_intervals(self):
    return sorted(
      [self._cluster_boundaries[lead][0], self._cluster_boundaries[lead][1]] for lead in self._cluster_boundaries)

  def add_number(self, val) -> None:
    # already seen
    if val in self._cluster_lead:
      return

    prev_val_cluster, next_val_cluster = None, None
    if val - 1 in self._cluster_lead:
      prev_val_cluster = self._cluster_lead[val - 1]
    if val + 1 in self._cluster_lead:
      next_val_cluster = self._cluster_lead[val + 1]

    if not prev_val_cluster and not next_val_cluster:
      self._cluster_lead[val] = val
      self._cluster_members[val] = [val]
      self._cluster_boundaries[val] = [val, val]
    else:
      if prev_val_cluster and next_val_cluster:
        tmp_li = [val] + self._cluster_members[next_val_cluster]
        for m in tmp_li:
          self._cluster_lead[m] = prev_val_cluster
          self._cluster_members[prev_val_cluster].append(m)
          self._cluster_boundaries[prev_val_cluster] = [min(m, self._cluster_boundaries[prev_val_cluster][0]),
                                                        max(m, self._cluster_boundaries[prev_val_cluster][1])]
        del self._cluster_members[next_val_cluster]
        del self._cluster_boundaries[next_val_cluster]
      elif prev_val_cluster:
        self._cluster_lead[val] = prev_val_cluster
        self._cluster_members[prev_val_cluster].append(val)
        self._cluster_boundaries[prev_val_cluster] = [min(val, self._cluster_boundaries[prev_val_cluster][0]),
                                                      max(val, self._cluster_boundaries[prev_val_cluster][1])]
      else:
        self._cluster_lead[val] = next_val_cluster
        self._cluster_members[next_val_cluster].append(val)
        self._cluster_boundaries[next_val_cluster] = [min(val, self._cluster_boundaries[next_val_cluster][0]),
                                                      max(val, self._cluster_boundaries[next_val_cluster][1])]

class SummaryRangesBinarySearch(object):

  def __init__(self):
    self._intervals = []
    self._seen = set()

  def get_intervals(self):
    return self._intervals

  def add_number(self, val) -> None:
    interval = [val, val]

    if val in self._seen:
      return None
    self._seen.add(val)

    if not self._intervals:
      self._intervals = [interval]
      return None

    # Find the intervals immediately before (i) and after (j) interval using binary search
    j = bisect.bisect_left(self._intervals, interval)
    i = j - 1

    # New interval extends the interval to it's left [3, 7] + [8, 8] = [3, 8]
    if i >= 0 and self._intervals[i][1] == val - 1:
      interval = [self._intervals[i][0], val]

      # New interval extends the interval to it's left and to it's right [3, 7] + [8, 8] + [9, 12] = [3, 12]
      if j < len(self._intervals) and self._intervals[j][0] == val + 1:
        interval = [interval[0], self._intervals[j][1]]
        self._intervals = self._intervals[:i] + [interval] + self._intervals[j + 1:]
      else:
        self._intervals = self._intervals[:i] + [interval] + self._intervals[j:]

    # New interval only extends the interval to it's right [8, 8] + [9, 12] = [8, 12]
    elif j < len(self._intervals) and self._intervals[j][0] == val + 1:
      interval = [val, self._intervals[j][1]]
      self._intervals = self._intervals[:j] + [interval] + self._intervals[j + 1:]

    # New interval is disjoint from all other intervals [3, 6] + [8, 8] + [10, 12] = [3, 6] + [8, 8] + [10, 12]
    else:
      self._intervals = self._intervals[:j] + [interval] + self._intervals[j:]

    return None

from utils import random_helper

for _ in range(100):
  rand_arr = random_helper.gen_rand_array(10, 20, False, False)
  bs, uf = SummaryRangesBinarySearch(), SummaryRangesUnionFind()
  for val in rand_arr:
    bs.add_number(val)
    uf.add_number(val)
    ans1, ans2 = bs.get_intervals(), uf.get_intervals()
    if ans1 != ans2:
      print(rand_arr)
      print(' '.join(str(ele) for ele in ans1), ' != ', ' '.join(str(ele) for ele in ans2))
      break

